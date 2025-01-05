import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Game {
    private Player player;
    private InterfaceJeu uI;
    private Level niveau;
    private Carte carte;
    private LinkedList<Monstres> monstres;
    private List<Wave> waves;
    private List<String> levels;  // Liste des niveaux
    private int currentWaveIndex;
    private int currentLevelIndex;
    private boolean gameRunning;

    public void launch() {
        init();
        long previousTime = System.currentTimeMillis();

        while (isGameRunning()) {
            long currentTime = System.currentTimeMillis();
            double deltaTimeSec = (double) (currentTime - previousTime) / 1000.0;
            previousTime = currentTime;

            update(deltaTimeSec);
            render();
        }

        endGame();
    }

    private void init() {
        System.out.println("Initialisation du jeu...");
        player = new Player();
        monstres = new LinkedList<>();
        gameRunning = true;

        try {
            loadGame("resources/games/game.g");  // Charger le fichier de jeu
            currentLevelIndex = 0;
            loadNextLevel();  // Charger le premier niveau
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement du fichier de jeu : " + e.getMessage());
            gameRunning = false;
        }
    }

    private void loadGame(String gameFilePath) throws IOException {
        levels = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(gameFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                levels.add("resources/levels/" + line.trim() + ".lvl");
                System.out.println("Niveau ajouté : " + line.trim());
            }
        }

        if (levels.isEmpty()) {
            throw new IOException("Aucun niveau spécifié dans le fichier de jeu !");
        }
    }

    private void loadNextLevel() {
        try {
            String nextLevelFile = levels.get(currentLevelIndex);
            System.out.println("Chargement du niveau : " + nextLevelFile);
    
            niveau = new Level(nextLevelFile); // Charger le niveau (carte + vagues)
            carte = niveau.getCarte(); // Récupérer la carte
    
            // Ne rechargez pas la carte ici, utilisez celle déjà chargée.
            waves = niveau.getWaves(); // Charger les vagues
            currentWaveIndex = 0;
    
            uI = new InterfaceJeu(player, carte); // Créer l'interface avec la carte actuelle
            startNextWave(); // Démarrer la première vague
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement du niveau suivant : " + e.getMessage());
            gameRunning = false;
        }
    }
    

    private boolean isGameRunning() {
        System.out.println("Vérification de l'état du jeu...");
        System.out.println("Nombre de monstres actifs : " + monstres.size());
        if (player.aPerdu()) {
            System.out.println("Le joueur a perdu !");
            return false;
        }
        if (allEnemiesDead() && isLastWaveComplete()) {
            System.out.println("Niveau terminé !");
            if (currentLevelIndex < levels.size() - 1) {
                nextLevel();  // Passer au niveau suivant
            } else {
                System.out.println("Victoire totale !");
                return false;  // Fin du jeu si tous les niveaux sont terminés
            }
        }
        return gameRunning;
    }

    private void nextLevel() {
        currentLevelIndex++;
        System.out.println("Passage au niveau suivant : " + (currentLevelIndex + 1));
        loadNextLevel();  // Charger le prochain niveau
    }

    private boolean allEnemiesDead() {
        return monstres.stream().noneMatch(Monstres::isAlive);
    }

    private boolean isLastWaveComplete() {
        // Vérifiez que tous les ennemis sont morts
        boolean allMonstersDead = monstres.stream().allMatch(Monstres::isAlive);
        
        // La vague est terminée si tous les ennemis sont morts et si la liste des vagues est vide
        return allMonstersDead && currentWaveIndex >= waves.size();
    }
    

    private void update(double deltaTime) {
        System.out.println("=== Mise à jour du jeu ===");
        System.out.println("DeltaTimeSec : " + deltaTime + " secondes");
    
        // Mettre à jour chaque monstre
        for (Monstres monstre : monstres) {
            if (monstre.isAlive()) {
                monstre.update(deltaTime, player);  // Met à jour la position des monstres
            }
        }
    
        // Mettre à jour la vague en cours
        if (waves.size() > currentWaveIndex) {
            Wave currentWave = waves.get(currentWaveIndex);
            currentWave.update(deltaTime);  // Mise à jour de la vague
            System.out.println("Temps actuel dans la vague " + (currentWaveIndex + 1) + " : " + currentWave.currentTime + " secondes");
        }
    
        if (allEnemiesDead() && isLastWaveComplete()) {
            gameRunning = false;
        }
    }
    

    private void render() {
        StdDraw.clear();
        System.out.println("Rendu en cours...");

        uI.afficheJeu();  // Affiche la carte et les infos
        System.out.println("Interface de jeu affichée.");

        for (Monstres monstre : monstres) {
            if (monstre.isAlive()) {
                monstre.render();  // Dessin du monstre
                System.out.println("Monstre affiché : " + monstre.getClass().getSimpleName());
            }
        }

        StdDraw.show();
    }

    private void endGame() {
        if (player.aPerdu()) {
            System.out.println("Défaite ! Le joueur a perdu.");
        } else {
            System.out.println("Félicitations ! Vous avez terminé toutes les vagues.");
        }
        StdDraw.clear();
        StdDraw.text(512, 360, "Fin du jeu. Merci d'avoir joué !");
        StdDraw.show();
    }

    private void startNextWave() {
        if (currentWaveIndex < waves.size()) {
            Wave currentWave = waves.get(currentWaveIndex);
            System.out.println("Démarrage de la vague " + (currentWaveIndex + 1));
            List<Monstres> nouveauxMonstres = currentWave.update(0);  // Initialisation des monstres
            monstres.addAll(nouveauxMonstres);
            currentWaveIndex++;
        } else {
            System.out.println("Toutes les vagues ont été complétées !");
            gameRunning = false;
        }
    }
}
