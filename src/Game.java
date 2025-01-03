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
        player.update(deltaTimeSec);
    }

    private void endGame() {
        if (player.aPerdu()) {
            System.out.println("Le joueur a perdu !");
        } else {
            System.out.println("Félicitations, vous avez gagné !");
        }
    }

    private List<Wave> loadWaves() {
        // Charger les vagues depuis un fichier ou les générer dynamiquement
        List<Wave> loadedWaves = new LinkedList<>();
        loadedWaves.add(new Wave(1)); // Exemple : une vague contenant des monstres
        loadedWaves.add(new Wave(2)); // Ajoutez d'autres vagues
        return loadedWaves;
    }
    
}
