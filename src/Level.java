import java.io.*;
import java.util.*;

public class Level {
    private final String levelFilePath;  // Chemin du fichier de niveau
    private String mapFile;  // Fichier de la carte
    private Carte carte;  // Carte associée au niveau
    private final List<String> waveFiles;  // Liste des fichiers de vagues
    private final List<Wave> waves;  // Liste des vagues associées
    private int currentWaveIndex;  // Index de la vague actuelle

    // Constructeur
    public Level(String levelFilePath) throws IOException {
        this.levelFilePath = levelFilePath;
        this.waveFiles = new ArrayList<>();
        this.waves = new ArrayList<>();
        this.currentWaveIndex = 0;
        loadLevel();
    }

    // Charge le niveau depuis le fichier
    private void loadLevel() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(levelFilePath))) {
            String line = br.readLine();
            if (line != null) {
                this.mapFile = "resources/maps/" + line.trim() + ".mtp";
                this.carte = new Carte(mapFile);
            } else {
                throw new IOException("Fichier de niveau vide !");
            }

            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String waveFile = "resources/waves/" + line.trim() + ".wve";
                    if (waveFile.equals("resources/waves/.wve")) {
                        System.err.println("Avertissement : Ligne vide détectée pour un fichier de vague.");
                        continue;
                    }
                    waveFiles.add(waveFile);
                }
            }

            if (waveFiles.isEmpty()) {
                throw new IOException("Aucune vague spécifiée pour ce niveau !");
            }

            loadWaves();
        }
    }

    // Charge les vagues à partir des fichiers associés
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

    // Mise à jour de l'état des vagues
    public void updateCurrentWave(double deltaTime) {
        if (currentWaveIndex < waves.size()) {
            Wave currentWave = waves.get(currentWaveIndex);
            List<Monstres> spawnedMonsters = currentWave.update(deltaTime);
            if (currentWave.isCompleted() && spawnedMonsters.isEmpty()) {
                System.out.println("Vague " + (currentWaveIndex + 1) + " terminée !");
                currentWaveIndex++;
            }
        }
    }

    // Méthode pour obtenir la carte
    public Carte getCarte() {
        return carte;
    }

    // Méthode pour obtenir la liste des vagues
    public List<Wave> getWaves() {
        return waves;
    }

    // Méthode pour réinitialiser les vagues
    public void resetWaves() {
        currentWaveIndex = 0;
        for (Wave wave : waves) {
            wave.resetWave();
        }
        System.out.println("Toutes les vagues ont été réinitialisées.");
    }

    // Affiche des informations sur le niveau
    public void displayLevelInfo() {
        System.out.println("Carte du niveau : " + mapFile);
        System.out.println("Nombre de vagues : " + waves.size());
        for (int i = 0; i < waves.size(); i++) {
            System.out.println("Vague " + (i + 1) + " : " + waves.get(i).getActiveMonsters().size() + " ennemis actifs.");
        }
    }

    // Méthode pour vérifier si toutes les vagues sont complètes
    public boolean allWavesComplete() {
        return currentWaveIndex >= waves.size();
    }
}
