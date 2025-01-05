import java.io.IOException;

public class InterfaceJeu {
    private ZoneInfoJeu zoneInfoJeu;      // Affiche les informations sur la partie
    private ZoneInfoJoueur zoneInfoJoueur;  // Affiche les informations du joueur
    private ZoneBoutique zoneBoutique;    // Affiche la boutique pour acheter des tours
    private ZoneCarte zoneCarte;          // Affiche la carte du jeu
    private Carte carte;  // Carte actuelle du niveau
    public Point2D center = new Point2D(500, 350);  // Centre de la carte
    public Point2D halfDist = new Point2D(500, 350);  // Distances d'affichage

    public InterfaceJeu(Player player, Carte carte) throws IOException {
        // Configuration de la fenêtre
        StdDraw.setCanvasSize(1024, 720);
        StdDraw.setXscale(-12, 1012);
        StdDraw.setYscale(-10, 710);
        StdDraw.enableDoubleBuffering();  // Évite le scintillement

        // Initialisation des zones d'interface
        zoneBoutique = new ZoneBoutique();  // Affiche la boutique de tours
        zoneCarte = new ZoneCarte();        // Zone graphique pour la carte du jeu
        zoneInfoJeu = new ZoneInfoJeu();    // Informations générales de la partie
        zoneInfoJoueur = new ZoneInfoJoueur(player);  // Informations du joueur (argent, tours, PV)

        this.carte = carte;  // La carte utilisée pour dessiner
    }

    public void afficheJeu() {
        try {
            // Affichage de la boutique, des informations et de la carte
            System.out.println("Affichage de la boutique...");
            zoneBoutique.dessineBoutique();  // Affiche la boutique des tours

            System.out.println("Affichage des informations du joueur...");
            zoneInfoJoueur.dessineInfoJoueur();  // Affiche les informations du joueur

            System.out.println("Affichage des informations de la partie...");
            zoneInfoJeu.dessineInfoJoueur();  // Affiche les informations générales de la partie

            System.out.println("Affichage de la carte...");
            zoneCarte.dessineCarte();  // Affiche la carte

            // Dessin des détails de la carte
            System.out.println("Affichage du terrain...");
            zoneCarte.dessineTerrain(carte);  // Dessine le terrain avec la carte actuelle
            StdDraw.show();  // Affiche tous les éléments sur l'écran

        } catch (IOException e) {
            System.err.println("Erreur lors de l'affichage du jeu : " + e.getMessage());
        }
    }
}


