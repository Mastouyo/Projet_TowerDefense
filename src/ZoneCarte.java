import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader; 
import java.io.IOException;

/**
 * Classe `ZoneCarte` qui représente la zone d'affichage de la carte du jeu.
 * La classe fournit des méthodes pour calculer la taille des cases, lire les dimensions de la carte et dessiner le terrain.
 */
public class ZoneCarte {

    /** Point central de la zone de la carte. */
    public Point2D center = new Point2D(350, 350);

    /** Demi-distance pour le cadre d'affichage de la carte. */
    public Point2D halfDist = new Point2D(350, 350);

    /**
     * Calcule la hauteur (nombre de lignes) de la carte à partir du fichier.
     *
     * @param nomFichier Le chemin du fichier de la carte.
     * @return La hauteur de la carte (nombre de lignes).
     * @throws IOException En cas d'erreur lors de la lecture du fichier.
     */
    public int hauteurMap(String nomFichier) throws IOException {
        int hauteur = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(nomFichier))) {
            while (br.readLine() != null) {
                hauteur++;
            }
        }
        return hauteur;
    }

    /**
     * Calcule la largeur (nombre de colonnes) de la carte à partir du fichier.
     *
     * @param nomFichier Le chemin du fichier de la carte.
     * @return La largeur de la carte (nombre de colonnes).
     * @throws IOException En cas d'erreur lors de la lecture du fichier.
     */
    public int largeurMap(String nomFichier) throws IOException {
        int largeur = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(nomFichier))) {
            String ligne = br.readLine();
            if (ligne != null) {
                largeur = ligne.length();
            }
        }
        return largeur;
    }

    /**
     * Calcule la taille des cases de la carte en fonction de ses dimensions.
     *
     * @param nomFichier Le chemin du fichier de la carte.
     * @return La taille des cases en pixels.
     * @throws IOException En cas d'erreur lors de la lecture du fichier.
     */
    public int calculerTailleCases(String nomFichier) throws IOException {
        int hauteurMap = hauteurMap(nomFichier);
        int largeurMap = largeurMap(nomFichier);

        if (hauteurMap >= largeurMap) {
            return 700 / hauteurMap;  // La taille de la zone de la carte est fixée à 700 pixels de côté
        } else {
            return 700 / largeurMap;
        }
    }

    /**
     * Dessine une case sur la carte en fonction de son type.
     * Les couleurs varient selon le type de case (Spawn, Base, Route, Constructible, Non-Constructible).
     *
     * @param c La case à dessiner (de type {@link Case}).
     */
    public void dessineCase(Case c) {
        double x = c.getCentre().getX();
        double y = c.getCentre().getY();
        int t = c.getTaille() / 2;  // La taille correspond à la moitié du côté pour un carré
        TypesCases type = c.getType();

        // Définition des couleurs selon le type de la case
        if (type == TypesCases.Spawn) {
            StdDraw.setPenColor(Color.RED);
        } else if (type == TypesCases.Base) {
            StdDraw.setPenColor(Color.ORANGE);
        } else if (type == TypesCases.Route) {
            StdDraw.setPenColor(194, 178, 127);  // Beige clair
        } else if (type == TypesCases.Constructible) {
            StdDraw.setPenColor(Color.LIGHT_GRAY);
        } else {
            StdDraw.setPenColor(11, 102, 35);  // Vert pour les zones non-constructibles (décor)
        }

        // Dessin de la case remplie
        StdDraw.filledSquare(x, y, t);

        // Dessin du contour de la case
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.line(x - t, y - t, x + t, y - t);
        StdDraw.line(x - t, y - t, x - t, y + t);
        StdDraw.line(x + t, y + t, x + t, y - t);
        StdDraw.line(x + t, y + t, x - t, y + t);
    }

    /**
     * Dessine l'ensemble du terrain en parcourant les cases de la carte.
     *
     * @param carte La carte contenant les informations des cases.
     * @throws IOException En cas d'erreur lors de la lecture du fichier de la carte.
     */
    public void dessineTerrain(Carte carte) throws IOException {
        for (int i = 0; i < hauteurMap(carte.getFichier()); i++) {
            for (int j = 0; j < largeurMap(carte.getFichier()); j++) {
                // Dessin de la case (par ligne et colonne)
                dessineCase(carte.getElement(i, j));
            }
        }
    }

    /**
     * Dessine le cadre de la carte (rectangle délimitant la zone de la carte).
     */
    public void dessineCarte() {
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.rectangle(center.getX(), center.getY(), halfDist.getX(), halfDist.getY());
    }
}
