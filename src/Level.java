import java.io.*;
import java.util.*;

/**
 * Classe `Level` qui représente un niveau du jeu.
 * Un niveau est défini par un fichier contenant le chemin de la carte et la liste des vagues associées.
 * La classe gère le chargement de la carte et des vagues, ainsi que leur état d'avancement.
 */
public class Level {

    /** Chemin du fichier de niveau. */
    private final String levelFilePath;

    /** Fichier de la carte associée au niveau. */
    private String mapFile;

    /** Carte associée au niveau. */
    private Carte carte;

    /** Liste des fichiers des vagues. */
    private final List<String> waveFiles;

    /** Liste des vagues associées au niveau. */
    private final List<Wave> waves;

    /** Index de la vague actuelle. */
    private int currentWaveIndex;

    /**
     * Constructeur de la classe `Level`.
     * Initialise un niveau en chargeant la carte et les vagues à partir du fichier de niveau.
     *
     * @param levelFilePath Chemin du fichier de niveau.
     * @throws IOException En cas d'erreur lors du chargement du fichier de niveau.
     */
    public Level(String levelFilePath) throws IOException {
        this.levelFilePath = levelFilePath;
        this.waveFiles = new ArrayList<>();
        this.waves = new ArrayList<>();
        this.currentWaveIndex = 1;
        loadLevel();  // Charge le niveau
    }

    /**
     * Charge le niveau depuis le fichier spécifié.
     * Lit le fichier pour obtenir le fichier de la carte et les fichiers de vagues.
     *
     * @throws IOException En cas d'erreur de lecture du fichier.
     */
    private void loadLevel() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(levelFilePath))) {
            String line = br.readLine();
            this.mapFile = "resources/maps/" + line.trim() + ".mtp";
            System.out.println("Chargement de la carte : " + this.mapFile);
            // Réinitialisation explicite de l'ancienne carte
            this.carte = null;  // Libère la mémoire de l'ancienne carte
            this.carte = new Carte(mapFile);  // Crée une nouvelle instance de la carte
    
            waveFiles.clear();  // Vide la liste précédente pour éviter des doublons entre niveaux
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String waveFile = "resources/waves/" + line.trim() + ".wve";
                    waveFiles.add(waveFile);
                }
            }
    
            if (waveFiles.isEmpty()) {
                throw new IOException("Aucune vague spécifiée pour ce niveau !");
            }
            
            waves.clear();  // Réinitialiser les vagues pour éviter les anciennes vagues
            loadWaves();  // Charge les vagues associées au niveau
        }
    }
    
    

    /**
     * Charge les vagues à partir des fichiers associés.
     * Chaque fichier de vague est lu et transformé en une instance de `Wave`.
     */
    private void loadWaves() {
        for (String waveFile : waveFiles) {
            if (!new File(waveFile).exists()) {
                System.err.println("Erreur : Le fichier de vague " + waveFile + " est introuvable.");
                continue;
            }
            try {
                List<String> waveData = new ArrayList<>();
                try (BufferedReader br = new BufferedReader(new FileReader(waveFile))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        if (!line.trim().isEmpty()) {
                            waveData.add(line.trim());
                        }
                    }
                }

                if (waveData.isEmpty()) {
                    System.err.println("Avertissement : La vague " + waveFile + " est vide.");
                    continue;
                }

                Wave wave = new Wave(waves.size() + 1, carte);
                wave.loadFromData(waveData);
                waves.add(wave);
                System.out.println("Vague chargée depuis : " + waveFile);
            } catch (IOException e) {
                System.err.println("Erreur lors du chargement de la vague : " + waveFile + " - " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.err.println("Format invalide dans le fichier de vague : " + waveFile + " - " + e.getMessage());
            }
        }
    }

    /**
     * Met à jour l'état de la vague actuelle.
     * Si la vague actuelle est terminée, passe à la suivante.
     *
     * @param deltaTime Le temps écoulé depuis la dernière mise à jour.
     */
    public void updateCurrentWave(double deltaTime) {
        if (currentWaveIndex < waves.size()) {
            Wave currentWave = waves.get(currentWaveIndex);
            List<Monstres> spawnedMonsters = currentWave.update(deltaTime);
    
            if (spawnedMonsters == null) {
                spawnedMonsters = new LinkedList<>(); // Pour éviter les NullPointerException
            }
    
            if (currentWave.isCompleted() && spawnedMonsters.isEmpty()) {
                System.out.println("Vague " + (currentWaveIndex + 1) + " terminée !");
                currentWaveIndex++;
            }
        }
    }
    

    /**
     * Réinitialise toutes les vagues du niveau.
     * Réinitialise l'index de la vague actuelle et réinitialise chaque vague.
     */
    public void resetWaves() {
        currentWaveIndex = 0;
        for (Wave wave : waves) {
            wave.resetWave();
        }
        System.out.println("Toutes les vagues ont été réinitialisées.");
    }

    /**
     * Affiche des informations sur le niveau (carte, nombre de vagues, détails des vagues).
     */
    public void displayLevelInfo() {
        System.out.println("Carte du niveau : " + mapFile);
        System.out.println("Nombre de vagues : " + waves.size());
        for (int i = 0; i < waves.size(); i++) {
            System.out.println("Vague " + (i + 1) + " : " + waves.get(i).getActiveMonsters().size() + " ennemis actifs.");
        }
    }

    public boolean allWavesComplete() {
        System.out.println("currentWaveIndex : " + currentWaveIndex + ", waves.size() : " + waves.size());
        return currentWaveIndex >= waves.size();
    }
    
    public void incrementWaveIndex() {
        if (currentWaveIndex < waves.size() - 1) {
            currentWaveIndex++;
        } else {
            System.out.println("Toutes les vagues du niveau ont été terminées.");
        }
    }
    

    // --- GETTERS ---

    /**
     * Retourne la carte associée au niveau.
     *
     * @return La carte associée (de type {@link Carte}).
     */
    public Carte getCarte() {
        return carte;
    }

    /**
     * Retourne la liste des vagues du niveau.
     *
     * @return Une liste de vagues (de type {@link List}<{@link Wave}>).
     */
    public List<Wave> getWaves() {
        return waves;
    }

    /**
     * Retourne l'index de la vague actuelle.
     *
     * @return L'index de la vague actuelle.
     */
    public int getCurrentWaveIndex() {
        return currentWaveIndex;
    }
}
