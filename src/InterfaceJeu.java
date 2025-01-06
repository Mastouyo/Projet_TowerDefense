import java.io.IOException;
import java.util.LinkedList;

/**
 * Classe `InterfaceJeu` qui représente l'interface graphique du jeu.
 * Cette classe gère l'affichage des différentes zones du jeu, telles que la carte, la boutique,
 * les informations du joueur et les informations générales sur la partie.
 */
public class InterfaceJeu {

    /** Zone affichant les informations générales sur la partie. */
    private ZoneInfoJeu zoneInfoJeu;

    /** Zone affichant les informations du joueur. */
    private ZoneInfoJoueur zoneInfoJoueur;

    /** Zone affichant la boutique où le joueur peut acheter des tours. */
    private ZoneBoutique zoneBoutique;

    /** Zone affichant la carte du jeu. */
    private ZoneCarte zoneCarte;

    /** Carte actuelle du niveau. */
    private Carte carte;

    /** Centre de la carte (coordonnées 2D). */
    public Point2D center = new Point2D(500, 350);

    /** Demi-distances utilisées pour le dimensionnement de l'affichage. */
    public Point2D halfDist = new Point2D(500, 350);

    /**
     * Constructeur de la classe `InterfaceJeu`.
     * Initialise l'interface de jeu avec les zones graphiques pour la boutique, la carte, les informations du joueur et de la partie.
     *
     * @param player Le joueur courant, dont les informations doivent être affichées.
     * @param carte La carte actuelle du niveau.
     * @param level Le niveau actuel.
     * @param levels La liste des niveaux disponibles.
     * @param levelIndex L'indice du niveau courant.
     * @throws IOException En cas d'erreur lors du chargement des ressources pour l'affichage.
     */
    public InterfaceJeu(Player player, Carte carte, Level level, LinkedList<Level> levels, int levelIndex) throws IOException {
        // Configuration de la fenêtre de jeu
        StdDraw.setCanvasSize(1024, 720);  // Taille de la fenêtre
        StdDraw.setXscale(-12, 1012);      // Échelle sur l'axe X
        StdDraw.setYscale(-10, 710);       // Échelle sur l'axe Y
        StdDraw.enableDoubleBuffering();   // Active le double buffering pour éviter le scintillement

        // Initialisation des zones de l'interface
        zoneBoutique = new ZoneBoutique();                 // Zone de la boutique
        zoneCarte = new ZoneCarte();                       // Zone de la carte
        zoneInfoJeu = new ZoneInfoJeu(level, levels, levelIndex);  // Zone des informations générales sur la partie
        zoneInfoJoueur = new ZoneInfoJoueur(player);        // Zone des informations du joueur

        this.carte = carte;  // Carte utilisée pour dessiner la carte actuelle
    }

    /**
     * Affiche l'interface du jeu avec les différentes zones : boutique, informations, carte, terrain.
     * Cette méthode effectue les dessins successifs et affiche le rendu sur la fenêtre.
     */
    public void afficheJeu() {
        try {
            // Affichage des différentes zones de l'interface
            System.out.println("Affichage de la boutique...");
            zoneBoutique.dessineBoutique();  // Affiche la boutique des tours

            System.out.println("Affichage des informations du joueur...");
            zoneInfoJoueur.dessineInfoJoueur();  // Affiche les informations du joueur (argent, tours, PV)

            System.out.println("Affichage des informations de la partie...");
            zoneInfoJeu.dessineInfoJeu();  // Affiche les informations générales sur la partie (niveau, vague)

            System.out.println("Affichage de la carte...");
            zoneCarte.dessineCarte();  // Affiche la carte du jeu

            System.out.println("Affichage du terrain...");
            zoneCarte.dessineTerrain(carte);  // Dessine le terrain correspondant à la carte actuelle

            StdDraw.show();  // Affiche le rendu complet à l'écran

        } catch (IOException e) {
            System.err.println("Erreur lors de l'affichage du jeu : " + e.getMessage());
        }
    }

    public ZoneInfoJeu getZoneInfoJeu() {
        return zoneInfoJeu;
    }

    public ZoneInfoJoueur getZoneInfoJoueur() {
        return zoneInfoJoueur;
    }

    public ZoneBoutique getZoneBoutique() {
        return zoneBoutique;
    }

    public ZoneCarte getZoneCarte() {
        return zoneCarte;
    }

    public Carte getCarte() {
        return carte;
    }

    public Point2D getCenter() {
        return center;
    }

    public Point2D getHalfDist() {
        return halfDist;
    }
    
    public void setCarte(Carte carte) {
        this.carte = carte;
    }
}



