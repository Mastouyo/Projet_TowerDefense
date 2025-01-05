import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Level {
    private final String mapFile;  // Fichier de la carte
    private final List<String> waveFiles;  // Liste des fichiers de vagues
    private Carte carte;  // Carte du niveau
    private final List<Wave> waves;  // Liste des vagues associées

    // Constructeur
    public Level(String levelFilePath) throws IOException {
        this.waveFiles = new ArrayList<>();
        this.waves = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(levelFilePath))) {
            System.out.println("Chargement du niveau depuis : " + levelFilePath);
            String line = br.readLine();

            if (line != null) {
                // Ajout automatique de l'extension .mtp si elle est manquante
                if (!line.trim().endsWith(".mtp")) {
                    this.mapFile = "resources/maps/" + line.trim() + ".mtp";
                } else {
                    this.mapFile = "resources/maps/" + line.trim();
                }
                System.out.println("Carte à charger : " + this.mapFile);
            } else {
                throw new IOException("Fichier de niveau vide !");
            }

            // Chargement des fichiers de vagues
            while ((line = br.readLine()) != null) {
                String waveFile = "resources/waves/" + line.trim();
                waveFiles.add(waveFile);
                System.out.println("Ajout de la vague : " + waveFile);
            }

            if (waveFiles.isEmpty()) {
                throw new IOException("Aucune vague spécifiée pour ce niveau !");
            }
        }

        loadCarte();
        loadWaves();
    }

    // Charge la carte
    private void loadCarte() throws IOException {
        try {
            this.carte = new Carte(mapFile);
            System.out.println("Carte chargée avec succès depuis : " + mapFile);
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement de la carte : " + e.getMessage());
            throw e;
        }
    }

    // Charge les vagues
    private void loadWaves() {
        int waveNumber = 1;  // Compteur pour les numéros de vague
    
        for (String waveFile : waveFiles) {
            try (BufferedReader br = new BufferedReader(new FileReader(waveFile))) {
                Wave wave = new Wave(waveNumber, carte);  // Création de la vague avec numéro et carte
                String line;
    
                System.out.println("Chargement de la vague depuis : " + waveFile);
    
                List<String> waveData = new ArrayList<>();
                while ((line = br.readLine()) != null) {
                    line = line.trim();  // Supprimer les espaces autour de la ligne
                    if (!line.isEmpty()) {
                        waveData.add(line);  // Ajout des données à la liste
                    }
                }
    
                wave.loadFromData(waveData);  // Chargement des ennemis dans la vague
                waves.add(wave);  // Ajout de la vague à la liste des vagues
                waveNumber++;  // Incrémenter le numéro de la vague
    
            } catch (IOException e) {
                System.err.println("Erreur lors du chargement de la vague : " + waveFile + " - " + e.getMessage());
            }
        }
    }
    

    // Retourne la carte
    public Carte getCarte() {
        return carte;
    }

    // Retourne la liste des vagues
    public List<Wave> getWaves() {
        return waves;
    }
}

