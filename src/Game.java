import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Game {
    private Player player; // Le joueur
    private LinkedList<Monstres> monstres; // Liste des monstres actifs
    private List<Wave> waves; // Liste des vagues à affronter
    private int currentWaveIndex; // Index de la vague en cours
    private boolean gameRunning;

    public void launch() {
        init();
        long previousTime = System.currentTimeMillis();
        while (isGameRunning()) {
            long currentTime = System.currentTimeMillis();
            double deltaTimeSec = (double) (currentTime - previousTime) / 1000;
            previousTime = currentTime;
            update(deltaTimeSec);
        }
    }
    
    private boolean isGameRunning() {
        if (player.aPerdu()) {
            System.out.println("Défaite !");
            return false;
        }
        if (allEnemiesDead() && isLastWaveComplete()) {
            System.out.println("Victoire !");
            return false;
        }
        return gameRunning;
    }
    
    // Fonction simulant la vérification de la fin des vagues et des ennemis
    private boolean allEnemiesDead() {
        return monstres.stream().noneMatch(monstre -> monstre.isAlive());
    }
    
    private boolean isLastWaveComplete() {
        return currentWaveIndex >= waves.size();
    }
    
    private void init() {
        System.out.println("Initialisation du jeu...");
        player = new Player(); 
        monstres = new LinkedList<>();
        waves = loadWaves(); // Charger les vagues du jeu
        currentWaveIndex = 0;
        gameRunning = true;

        startNextWave(); // Démarrer la première vague
    }
    
    private void update(double deltaTimeSec) {
        // Mise à jour des monstres
        monstres.forEach(monstre -> monstre.update(deltaTimeSec, player));

        // Retirer les monstres morts
        monstres = monstres.stream().filter(Monstres::isAlive).collect(Collectors.toCollection(LinkedList::new));

        // Vérifier si la vague est terminée
        if (monstres.isEmpty() && currentWaveIndex < waves.size()) {
            startNextWave();
        }

        // Mettre à jour les tours et les autres éléments du jeu
        player.update(deltaTimeSec,monstres);
    }

    private void endGame() {
        if (player.aPerdu()) {
            System.out.println("Le joueur a perdu !");
        } else {
            System.out.println("Félicitations, vous avez gagné !");
        }
    }

   private List<Wave> loadWaves() {
    List<Wave> loadedWaves = new ArrayList<>();
    String cheminFichier = "resources/waves/waves.txt"; // Chemin vers votre fichier de vagues

    try (BufferedReader br = new BufferedReader(new FileReader(cheminFichier))) {
        String ligne;
        Wave currentWave = null;

        while ((ligne = br.readLine()) != null) {
            ligne = ligne.trim();
            if (ligne.isEmpty()) {
                continue; // Ignorer les lignes vides
            }

            if (ligne.matches("\\d+")) { // Si la ligne contient seulement un numéro, c'est une nouvelle vague
                int numeroVague = Integer.parseInt(ligne);
                currentWave = new Wave(numeroVague);
                loadedWaves.add(currentWave);
            } else { // Sinon, c'est un ennemi avec un temps de spawn
                if (currentWave != null) {
                    String[] parts = ligne.split(",");
                    String typeMonstre = parts[0].trim();
                    double tempsSpawn = Double.parseDouble(parts[1].trim());
                    currentWave.addEnemySpawn(tempsSpawn, typeMonstre);
                }
            }
        }
    } catch (IOException e) {
        System.err.println("Erreur lors du chargement des vagues : " + e.getMessage());
    }

    return loadedWaves;
}

    private void startNextWave() {
        if (currentWaveIndex < waves.size()) {
            // Charger la prochaine vague
            Wave currentWave = waves.get(currentWaveIndex);
            System.out.println("Démarrage de la vague " + (currentWaveIndex + 1));
            
            // Ajouter les monstres de la vague à la liste des monstres actifs
            List<Monstres> nouveauxMonstres = currentWave.update(0); // Initialisation
            monstres.addAll(nouveauxMonstres);
    
            // Passer à la vague suivante
            currentWaveIndex++;
        } else {
            System.out.println("Toutes les vagues ont été complétées !");
            gameRunning = false; // Le jeu s'arrête si toutes les vagues sont terminées
        }
    }
    
    
}
