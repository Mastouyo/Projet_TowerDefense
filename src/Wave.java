import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe `Wave` qui représente une vague d'ennemis dans le jeu.
 * Chaque vague est définie par un numéro, une liste de monstres à générer, et une carte associée.
 * La classe gère le chargement, l'apparition et la gestion des monstres.
 */
public class Wave {

    /** Numéro de la vague. */
    private final int waveNumber;

    /** Liste des spawns d'ennemis à générer. */
    private final List<EnemySpawn> enemySpawns;

    /** Temps écoulé dans la vague. */
    public double currentTime;

    /** Indique si la vague est terminée. */
    private boolean completed;

    /** Liste des monstres actuellement actifs. */
    public final LinkedList<Monstres> activeMonsters;

    /** Carte associée pour gérer les positions et le chemin des ennemis. */
    private final Carte carte;

    /**
     * Classe interne `EnemySpawn` qui représente l'apparition d'un ennemi.
     * Chaque spawn est défini par un type d'ennemi et un temps d'apparition.
     */
    private static class EnemySpawn {
        /** Temps d'apparition de l'ennemi. */
        double spawnTime;

        /** Type d'ennemi à générer. */
        String enemyType;

        /**
         * Constructeur de la classe `EnemySpawn`.
         *
         * @param spawnTime Le temps auquel l'ennemi doit apparaître.
         * @param enemyType Le type d'ennemi.
         */
        public EnemySpawn(double spawnTime, String enemyType) {
            this.spawnTime = spawnTime;
            this.enemyType = enemyType;
        }
    }

    /**
     * Constructeur de la classe `Wave`.
     * Initialise une vague avec un numéro et une carte associée.
     *
     * @param waveNumber Le numéro de la vague.
     * @param carte La carte associée à la vague.
     */
    public Wave(int waveNumber, Carte carte) {
        this.waveNumber = waveNumber;
        this.carte = carte;
        this.enemySpawns = new ArrayList<>();
        this.currentTime = 0;
        this.completed = false;
        this.activeMonsters = new LinkedList<>();
    }

    /**
     * Ajoute un spawn d'ennemi à la vague.
     *
     * @param spawnTime Le temps d'apparition de l'ennemi.
     * @param enemyType Le type d'ennemi.
     * @throws IllegalArgumentException Si le temps de spawn est négatif.
     */
    public void addEnemySpawn(double spawnTime, String enemyType) {
        if (spawnTime < 0) {
            throw new IllegalArgumentException("Le temps de spawn doit être positif.");
        }
        enemySpawns.add(new EnemySpawn(spawnTime, enemyType));
        System.out.println("Ajout de l'ennemi : " + enemyType + " au temps : " + spawnTime);
    }

    /**
     * Charge les données de la vague à partir d'une liste de chaînes de caractères.
     * Chaque ligne doit contenir le temps d'apparition et le type d'ennemi, séparés par "|".
     *
     * @param waveData Liste des données de la vague.
     */
    public void loadFromData(List<String> waveData) {
        try {
            for (String line : waveData) {
                String[] parts = line.split("\\|");
                if (parts.length != 2) {
                    throw new IllegalArgumentException("Format invalide pour la ligne : " + line);
                }

                double spawnTime = Double.parseDouble(parts[0].trim());
                String enemyType = parts[1].trim();
                addEnemySpawn(spawnTime, enemyType);
            }
        } catch (Exception e) {
            System.err.println("Erreur lors du chargement des données de la vague : " + e.getMessage());
        }
    }

    /**
     * Met à jour la vague en fonction du temps écoulé.
     * Génère des ennemis selon leur temps d'apparition et met à jour l'état des monstres.
     *
     * @param deltaTime Le temps écoulé depuis la dernière mise à jour.
     * @return Une liste de nouveaux monstres générés lors de la mise à jour.
     */
    public List<Monstres> update(double deltaTime) {
        currentTime += deltaTime;
        System.out.println("Temps actuel dans la vague " + waveNumber + " : " + currentTime + " secondes");

        List<Monstres> spawnedMonsters = new ArrayList<>();
        enemySpawns.removeIf(spawn -> {
            if (spawn.spawnTime <= currentTime) {
                Monstres enemy = createEnemy(spawn.enemyType);
                if (enemy != null) {
                    Point2D spawnPosition = carte.getSpawn();
                    if (spawnPosition == null) {
                        System.err.println("Erreur : Position de spawn non définie !");
                        return false;
                    }
                    enemy.setPosition(spawnPosition);
                    enemy.setChemin(new LinkedList<>(carte.getChemin()));
                    activeMonsters.add(enemy);
                    spawnedMonsters.add(enemy);
                    System.out.println("Ennemi généré : " + spawn.enemyType + " à " + spawnPosition);
                }
                return true;
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

    /**
     * Vérifie si la vague est terminée.
     *
     * @return `true` si la vague est terminée, `false` sinon.
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * Crée un monstre à partir de son type.
     *
     * @param enemyType Le type de l'ennemi à créer.
     * @return Un objet `Monstres` correspondant au type, ou `null` si le type est inconnu.
     */
    private Monstres createEnemy(String enemyType) {
        switch (enemyType) {
            case "Minion":
                return new Minion(carte);
            case "Earth Brute":
                return new EarthBrute(carte);
            case "Water Brute":
                return new WaterBrute(carte);
            case "Fire Grognard":
                return new FireGrognard(carte);
            case "Wind Grognard":
                return new WindGrognard(carte);
            case "Boss":
                return new Boss(carte);
            default:
                System.err.println("Type d'ennemi inconnu : " + enemyType);
                return null;
        }
    }

    /**
     * Réinitialise la vague en remettant le temps à 0 et en supprimant les monstres actifs.
     */
    public void resetWave() {
        this.currentTime = 0;
        this.completed = false;
        this.activeMonsters.clear();
        System.out.println("La vague " + waveNumber + " a été réinitialisée.");
    }

    /**
     * Affiche des informations de débogage sur la vague.
     */
    public void debugPrint() {
        System.out.println("Vague numéro : " + waveNumber);
        System.out.println("Ennemis à générer :");
        for (EnemySpawn spawn : enemySpawns) {
            System.out.println("  Temps : " + spawn.spawnTime + ", Type : " + spawn.enemyType);
        }
        System.out.println("Monstres actifs : " + activeMonsters.size());
    }

    // --- GETTERS ---

    /**
     * Retourne la liste des monstres actifs dans la vague.
     *
     * @return Une liste des monstres actifs.
     */
    public LinkedList<Monstres> getActiveMonsters() {
        return activeMonsters;
    }

    /**
     * Retourne le numéro de la vague.
     *
     * @return Le numéro de la vague.
     */
    public int getWaveNumber() {
        return waveNumber;
    }

    public boolean waveComplete() {
        return activeMonsters.stream()
                             .filter(p -> p != null && p.position != null)
                             .allMatch(p -> {
                                 Case caseCourante = carte.caseSelonCoordonees(p.position);
                                 return caseCourante != null && caseCourante.getType() == TypesCases.Base;
                             });
    }

    public int getMonstersCount() {
        return (int) activeMonsters.stream()
                                   .filter(monster -> monster != null) // Exclure les nulls
                                   .count(); // Compter les monstres non nulls
    }
    
    public void setActiveMonsters(List<Monstres> monstres) {
        if (monstres == null) {
            throw new IllegalArgumentException("La liste des monstres ne peut pas être null !");
        }
        activeMonsters.clear(); // Vider la liste actuelle
        activeMonsters.addAll(monstres); // Ajouter les nouveaux monstres
    }
    
}
