import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Game {
    
        private Player player;
        private LinkedList<Monstres> Monstres;

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
        // Exemple de conditions pour arrêter le jeu
        if (this.player.aPerdu()) { // Si les points de vie du joueur sont à 0
            System.out.println("Défaite !");
            return false;
        } 
        if (allEnemiesDead() && isLastWaveComplete()) { // Si tous les ennemis sont morts et toutes les vagues terminées
            System.out.println("Victoire !");
            return false;
        }
        return true; // Continuer à jouer
    }
    
    // Fonction simulant la vérification de la fin des vagues et des ennemis
    private boolean allEnemiesDead() {
        LinkedList<Monstres> potMonstres = Monstres.stream().filter(p->p!=null).collect(Collectors.toCollection(LinkedList::new));
        return potMonstres.isEmpty();
    }
    
    private boolean isLastWaveComplete() {
        // Implémentez une logique pour vérifier si la dernière vague est terminée.
        return true; // Exemple fictif
    }
    
    private void init() {
        // Initialisation du jeu
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
