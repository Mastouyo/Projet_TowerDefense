import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Wave {
    private final int waveNumber;  // Numéro de la vague
    private final List<EnemySpawn> enemySpawns;  // Liste des ennemis à générer
    public double currentTime;  // Temps écoulé dans la vague
    private boolean completed;  // Indique si la vague est terminée
    private final LinkedList<Monstres> activeMonsters;  // Monstres actuellement actifs
    private final Carte carte;  // Référence à la carte pour gérer le chemin des ennemis

    // Classe interne pour représenter le moment où un ennemi est généré
    private static class EnemySpawn {
        double spawnTime;  // Temps d'apparition
        String enemyType;  // Type d'ennemi

        public EnemySpawn(double spawnTime, String enemyType) {
            this.spawnTime = spawnTime;
            this.enemyType = enemyType;
        }
    }

    // Constructeur avec numéro de vague et carte
    public Wave(int waveNumber, Carte carte) {
        this.waveNumber = waveNumber;
        this.carte = carte;
        this.enemySpawns = new ArrayList<>();
        this.currentTime = 0;
        this.completed = false;
        this.activeMonsters = new LinkedList<>();
    }

    // Ajoute un spawn d'ennemi à la vague
    public void addEnemySpawn(double spawnTime, String enemyType) {
        if (spawnTime < 0) {
            throw new IllegalArgumentException("Le temps de spawn doit être positif.");
        }
        enemySpawns.add(new EnemySpawn(spawnTime, enemyType));
        System.out.println("Ajout de l'ennemi : " + enemyType + " au temps : " + spawnTime);
    }

    // Charge une vague à partir d'une liste de paires temps/ennemi
    public void loadFromData(List<String> waveData) {
        try {
            for (String line : waveData) {
                String[] parts = line.split("\\|");
                if (parts.length != 2) {
                    throw new IllegalArgumentException("Format invalide pour la ligne : " + line);
                }

                double spawnTime = Double.parseDouble(parts[0].trim());
                String enemyType = parts[1].trim();
                addEnemySpawn(spawnTime, enemyType);  // Utilisation de la méthode pour ajouter l'ennemi
            }
        } catch (Exception e) {
            System.err.println("Erreur lors du chargement des données de la vague : " + e.getMessage());
        }
    }

    // Mise à jour de la vague
    public List<Monstres> update(double deltaTime) {
        currentTime += deltaTime;
    
        System.out.println("Temps actuel dans la vague " + waveNumber + " : " + currentTime + " secondes");
    
        List<Monstres> spawnedMonsters = new ArrayList<>();
        enemySpawns.removeIf(spawn -> {
            if (spawn.spawnTime <= currentTime) {
                Monstres enemy = createEnemy(spawn.enemyType);
                if (enemy != null) {
                    Point2D spawnPosition = carte.getSpawn();  // Position initiale (spawn)
                    if (spawnPosition == null) {
                        System.err.println("Erreur : Position de spawn non définie !");
                        return false;
                    }
                    enemy.setPosition(spawnPosition);
                    enemy.setChemin(new LinkedList<>(carte.getChemin()));  // Associe le chemin
                    activeMonsters.add(enemy);
                    spawnedMonsters.add(enemy);
                    System.out.println("Ennemi généré : " + spawn.enemyType + " à " + spawnPosition);
                } else {
                    System.err.println("Erreur : Impossible de créer un ennemi de type " + spawn.enemyType);
                }
                return true;  // Supprimer l'ennemi de la liste une fois généré
            }
            return false;
        });
    
        if (enemySpawns.isEmpty() && activeMonsters.stream().allMatch(monstre -> !monstre.isAlive())) {
            completed = true;
            System.out.println("La vague " + waveNumber + " est terminée !");
        }
    
        System.out.println("Nombre de monstres actifs : " + activeMonsters.size());
        return spawnedMonsters;
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

    // Réinitialise la vague
    public void resetWave() {
        this.currentTime = 0;
        this.completed = false;
        this.activeMonsters.clear();
        System.out.println("La vague " + waveNumber + " a été réinitialisée.");
    }

    // Affiche des informations sur la vague
    public void debugPrint() {
        System.out.println("Vague numéro : " + waveNumber);
        System.out.println("Ennemis à générer :");
        for (EnemySpawn spawn : enemySpawns) {
            System.out.println("  Temps : " + spawn.spawnTime + ", Type : " + spawn.enemyType);
        }
        System.out.println("Monstres actifs : " + activeMonsters.size());
    }

    // Getter pour le numéro de vague
    public int getWaveNumber() {
        return waveNumber;
    }
}
