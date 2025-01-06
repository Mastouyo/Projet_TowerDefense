import java.util.LinkedList;
import java.util.stream.Collectors;
import java.io.IOException;

public class Game {
    private Player player; // Le joueur
    private InterfaceJeu uI; // Interface graphique du jeu
    private LinkedList<Level> levels; // Liste des niveaux
    private int currentLevelIndex; // Index du niveau actuel
    private LinkedList<Monstres> monstres; // Liste des monstres actifs
    private boolean gameRunning; // État du jeu

    public void launch() {
        init(); // Initialisation du jeu
        long previousTime = System.currentTimeMillis();
        Tours selec = new Archer(); 

        while (isGameRunning()) {
            long currentTime = System.currentTimeMillis();
            double deltaTimeSec = (double) (currentTime - previousTime) / 1000;
            previousTime = currentTime;

            if (StdDraw.isMousePressed()) {
                double mouseX = StdDraw.mouseX();
                double mouseY = StdDraw.mouseY();

                if (uI.getZoneBoutique().detecteTourClique(mouseX, mouseY) != null) {
                    selec = uI.getZoneBoutique().detecteTourClique(mouseX, mouseY);
                }

                // Ajout des impressions de débogage
                System.out.println("Tour sélectionnée: " + selec.getName());
                System.out.println("Coût de la tour: " + selec.getCost());
                System.out.println("Argent du joueur: " + player.getMoney());
                System.out.println("Case constructible: " + levels.get(currentLevelIndex).getCarte().getCaseConstructible(mouseX, mouseY));
                System.out.println("Instance de Tours: " + (selec instanceof Tours));

                if ((selec.getCost() <= player.getMoney()) && 
                    (levels.get(currentLevelIndex).getCarte().getCaseConstructible(mouseX, mouseY) != null) && 
                    (selec instanceof Tours)) {

                    Case c = levels.get(currentLevelIndex).getCarte().getCaseConstructible(mouseX, mouseY);
                    Point2D centreCase = levels.get(currentLevelIndex).getCarte().getCaseConstructible(mouseX, mouseY).getCentre();
                    Tours nouvelleTour = instanceTour(selec);
                    nouvelleTour.setPosition(centreCase);
                    //  levels.get(currentLevelIndex).getCarte().placerTour(nouvelleTour, centreCase);
                    levels.get(currentLevelIndex).getCarte().addTower(nouvelleTour); // Add the tower to the map's list of towers
                    levels.get(currentLevelIndex).getCarte().retirerCaseConstructible(c);

                    player.acheterTour(selec);


                    System.out.println("Tour placée avec succès");
                }
            }
            update(deltaTimeSec,levels.get(currentLevelIndex).getCarte()); // Mise à jour du jeu
            render(); // Affichage du jeu
        }
        endGame(); // Fin du jeu
    }
 
    private Tours instanceTour(Tours sele){
        if (sele instanceof Archer){
            return new Archer() ; 
        }
        else if (sele instanceof EarthCaster){
            return new EarthCaster() ; 
        }
        else if (sele instanceof FireCaster){
            return new FireCaster() ; 
        }
        else if (sele instanceof WaterCaster){
            return new WaterCaster() ; 
        }
        else if (sele instanceof WindCaster){
            return new WindCaster() ; 
        }
        else {
            return null ; 
        }
    }

    private void init() {
        System.out.println("Initialisation du jeu...");
        player = new Player(); // Création du joueur
        monstres = new LinkedList<>();
        levels = new LinkedList<>();
        currentLevelIndex = 0;
        gameRunning = true;

        // Charger les niveaux (chaque fichier correspond à un niveau)
        try {
            levels.add(new Level("resources/levels/level1.lvl"));
            levels.add(new Level("resources/levels/level2.lvl"));
            levels.add(new Level("resources/levels/level3.lvl"));

            if (!levels.isEmpty()) {
                // Création de l'interface du jeu avec le joueur et la carte du premier niveau
                uI = new InterfaceJeu(player, levels.get(0).getCarte(), levels.get(0), levels, currentLevelIndex);
                loadCurrentLevel(); // Charger le premier niveau
            }

        } catch (IOException e) {
            System.err.println("Erreur lors du chargement des niveaux ou de l'interface : " + e.getMessage());
            gameRunning = false;
        }
    }

    private void loadCurrentLevel() {
        Level currentLevel = levels.get(currentLevelIndex);
        System.out.println("Chargement du niveau : " + (currentLevelIndex + 1));
        monstres.clear();
        currentLevel.resetWaves(); // Réinitialiser les vagues du niveau
        uI.setCarte(currentLevel.getCarte());
        uI.getZoneInfoJeu().update(currentLevel);
    }

    private boolean isGameRunning() {
        if (player.aPerdu()) {
            System.out.println("Le joueur a perdu !");
            return false;
        }
        if (currentLevelIndex >= levels.size()) {
            System.out.println("Victoire totale ! Tous les niveaux sont complétés !");
            return false;
        }
        return gameRunning;
    }

    private void update(double deltaTimeSec,Carte map) {
        Level currentLevel = levels.get(currentLevelIndex);

        // Mise à jour de la vague actuelle
        currentLevel.updateCurrentWave(deltaTimeSec);
        
        // Ajouter les monstres nouvellement apparus
        LinkedList<Monstres> nouveauxMonstres = new LinkedList<>(currentLevel.getWaves().stream()
            .flatMap(wave -> wave.getActiveMonsters().stream())
            .collect(Collectors.toList()));

        // Ajouter uniquement les monstres non déjà dans la liste
        for (Monstres monstre : nouveauxMonstres) {
            if (!monstres.contains(monstre)) {
                System.out.println("Ajout du monstre : " + monstre.getName() + " à la position initiale " + monstre.getPosition());
                monstres.add(monstre);
            }
        }

        // Mise à jour des monstres
        monstres.forEach(monstre -> {
            //System.out.println("Position avant mise à jour : " + monstre.getPosition());
            monstre.update(deltaTimeSec, player);
            // Vérifier si le monstre avance ou reste bloqué
            if (monstre.getChemin().isEmpty()) {
                System.err.println("Erreur : Le chemin est vide pour le monstre " + monstre.getName());
                return;
            }
            Point2D currentPos = monstre.getPosition();
            Point2D targetPos = monstre.getChemin().get(0).getCentre();

            if (currentPos.equals(targetPos)) {
                System.err.println("Avertissement : Le monstre " + monstre.getName() + " n'a pas avancé de la position " + currentPos);
                System.out.println("Détails du chemin :");
                for (Case c : monstre.getChemin()) {
                    System.out.println("- " + c.getCentre());
                }
            }
            //System.out.println("Position après mise à jour : " + monstre.getPosition());
        });
        
        // Retirer les monstres morts
        
        monstres = monstres.stream().filter(Monstres::isAlive).filter(Monstres :: isEnChemin).collect(Collectors.toCollection(LinkedList::new));
        

        if (monstres.isEmpty()) {
            if (currentLevel.getCurrentWaveIndex() < currentLevel.getWaves().size()) {
                currentLevel.getWaves().get(currentLevel.getCurrentWaveIndex()).setActiveMonsters(monstres);
            } else {
                System.out.println("Toutes les vagues du niveau ont été complétées !");
            }
        }
        
        
        // Si toutes les vagues du niveau sont terminées, passer au niveau suivant
        if (currentLevel.allWavesComplete()) {
            System.out.println("wave finie");
            currentLevelIndex++;
            if (currentLevelIndex < levels.size()) {
                loadCurrentLevel(); // Charger le niveau suivant
                System.out.println("Passage au niveau suivant !");
            } else {
                System.out.println("Toutes les vagues de tous les niveaux ont été complétées !");
                gameRunning = false; // Fin du jeu si tous les niveaux sont terminés
            }
        }

        
        // Mise à jour du joueur
        player.update(deltaTimeSec, monstres, currentLevel.getCarte());
    }

    private void render() {
        StdDraw.clear();
        uI.afficheJeu();
    
        for (Monstres monstre : monstres) {
            monstre.render();
        }
    
        // Render towers on the map
        for (Tours tour : levels.get(currentLevelIndex).getCarte().getTowers()) {
            tour.drawVisuel(tour.getPosition(), 20);
            
        }
    
        StdDraw.show();
    }

    private void endGame() {
        StdDraw.clear();
        if (player.aPerdu()) {
            StdDraw.text(512, 360, "Défaite ! Le joueur a perdu !");
        } else {
            StdDraw.text(512, 360, "Victoire totale ! Merci d'avoir joué !");
        }
        StdDraw.show();
    }


}
