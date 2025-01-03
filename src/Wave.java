import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Wave {
    private int waveNumber;
    private List<EnemySpawn> enemySpawns; // Liste des ennemis à générer
    private double currentTime; // Temps écoulé dans la vague
    private boolean completed; // Indique si la vague est terminée
    private LinkedList<Monstres> activeMonsters; // Monstres actuellement actifs

    // Classe interne pour représenter le moment où un ennemi est généré
    private static class EnemySpawn {
        double spawnTime;
        String enemyType;

        public EnemySpawn(double spawnTime, String enemyType) {
            this.spawnTime = spawnTime;
            this.enemyType = enemyType;
        }
    }

    public Wave(int waveNumber) {
        this.waveNumber = waveNumber;
        this.enemySpawns = new ArrayList<>();
        this.currentTime = 0;
        this.completed = false;
        this.activeMonsters = new LinkedList<>();
    }

    // Charge une vague à partir d'une liste de paires temps/ennemi
    public void loadFromData(List<String> waveData) {
        for (String line : waveData) {
            String[] parts = line.split("\\|");
            double spawnTime = Double.parseDouble(parts[0]);
            String enemyType = parts[1];
            enemySpawns.add(new EnemySpawn(spawnTime, enemyType));
        }
    }

    // Mise à jour de la vague
    public List<Monstres> update(double deltaTime) {
        currentTime += deltaTime;

        // Liste des monstres générés dans cette mise à jour
        List<Monstres> spawnedMonsters = new ArrayList<>();

        // Générer les monstres en fonction du temps
        enemySpawns.removeIf(spawn -> {
            if (spawn.spawnTime <= currentTime) {
                Monstres enemy = createEnemy(spawn.enemyType);
                if (enemy != null) {
                    activeMonsters.add(enemy);
                    spawnedMonsters.add(enemy);
                }
                return true; // Retirer l'ennemi de la liste après sa génération
            }
            return false;
        });

        // Vérifier si la vague est terminée
        if (enemySpawns.isEmpty() && activeMonsters.stream().allMatch(Monstres::estTué)) {
            completed = true;
        }

        return spawnedMonsters;
    }

    // Vérifie si la vague est terminée
    public boolean isCompleted() {
        return completed;
    }

    // Méthode pour créer un ennemi à partir de son type
    private Monstres createEnemy(String enemyType) {
        switch (enemyType) {
            case "Minion":
                return new Minion();
            case "Earth Brute":
                return new EarthBrute();
            case "Water Brute":
                return new WaterBrute();
            case "Fire Grognard":
                return new FireGrognard();
            case "Wind Grognard":
                return new WindGrognard();
            case "Boss":
                return new Boss();
            default:
                System.err.println("Type d'ennemi inconnu : " + enemyType);
                return null;
        }
    }

    // Retourne les monstres actifs
    public LinkedList<Monstres> getActiveMonsters() {
        return activeMonsters;
    }

     // Ajoute manuellement un spawn d'ennemi à la vague (utile pour les vagues dynamiques)
    public void addEnemySpawn(double spawnTime, String enemyType) {
        enemySpawns.add(new EnemySpawn(spawnTime, enemyType));
    }

    public int getWaveNumber() {
        return waveNumber;
    }
}

