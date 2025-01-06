import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe `ZoneBoutique` qui représente la boutique du jeu.
 * La boutique permet d'afficher et d'acheter différentes tours.
 * Chaque tour est affichée avec ses statistiques (points de vie, attaque, vitesse d'attaque, portée et coût).
 */
public class ZoneBoutique {

    /** Position du centre de la boutique. */
    private Point2D center = new Point2D(856, 303);

    /** Demi-distance de la boutique pour le cadre d'affichage. */
    private Point2D halfDist = new Point2D(144, 303);

    /** Liste des tours disponibles dans la boutique. */
    private List<TourBoutique> toursBoutique;

    /** Différents types de tours disponibles dans la boutique. */
    private Archer archer = new Archer();
    private EarthCaster eC = new EarthCaster();
    private FireCaster fC = new FireCaster();
    private WaterCaster waC = new WaterCaster();
    private WindCaster wiC = new WindCaster();

    /**
     * Classe interne `TourBoutique` représentant une tour affichée dans la boutique.
     * Chaque tour a une zone définie par des coordonnées minimales et maximales.
     */
    private class TourBoutique {
        /** Tour associée à cette case de la boutique. */
        Tours tour;

        /** Coordonnées définissant la zone cliquable de la tour. */
        double xMin, xMax, yMin, yMax;

        /**
         * Constructeur de la classe `TourBoutique`.
         *
         * @param tour La tour associée.
         * @param xMin La coordonnée minimale en `x`.
         * @param xMax La coordonnée maximale en `x`.
         * @param yMin La coordonnée minimale en `y`.
         * @param yMax La coordonnée maximale en `y`.
         */
        public TourBoutique(Tours tour, double xMin, double xMax, double yMin, double yMax) {
            this.tour = tour;
            this.xMin = xMin;
            this.xMax = xMax;
            this.yMin = yMin;
            this.yMax = yMax;
        }

        /**
         * Vérifie si un clic aux coordonnées données correspond à cette tour.
         *
         * @param sourisX Coordonnée `x` du clic.
         * @param sourisY Coordonnée `y` du clic.
         * @return `true` si le clic est à l'intérieur de la zone, `false` sinon.
         */
        public boolean isClicked(double sourisX, double sourisY) {
            return sourisX >= xMin && sourisX <= xMax && sourisY >= yMin && sourisY <= yMax;
        }
    }

    /**
     * Constructeur de la classe `ZoneBoutique`.
     * Initialise la boutique avec les tours disponibles.
     */
    public ZoneBoutique() {
        toursBoutique = new ArrayList<>();
        toursBoutique.add(new TourBoutique(new Archer(), 785, 927, 555, 595));
        toursBoutique.add(new TourBoutique(new EarthCaster(), 927, 1070, 555, 595));
        toursBoutique.add(new TourBoutique(new FireCaster(), 785, 927, 495, 535));
        toursBoutique.add(new TourBoutique(new WaterCaster(), 927, 1070, 495, 535));
        toursBoutique.add(new TourBoutique(new WindCaster(), 785, 927, 435, 475));
    }

    /**
     * Dessine la boutique sur l'écran en affichant chaque tour dans un cadre.
     * Les tours sont affichées avec leurs statistiques (points de vie, attaque, vitesse, portée et coût).
     */
    public void dessineBoutique() {
        // Dessin du cadre de la boutique
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.rectangle(center.getX(), center.getY(), halfDist.getX(), halfDist.getY());

        // Dimensions pour les cadres
        double largeurCadre = halfDist.getX() / 2 - 1;
        double hauteur = halfDist.getY() / 10;
        int startX = 785; // Coordonnée X initiale
        int startY = 575; // Coordonnée Y initiale
        double spacingY = 2 * hauteur; // Espacement vertical entre les cadres

        // Liste des tours avec leurs positions
        Tours[] tours = {archer, eC, fC, waC, wiC};
        Point2D[] positions = {
            new Point2D(856-120, 575),
            new Point2D(1000-120, 575),
            new Point2D(856-120, 575 - spacingY),
            new Point2D(1000-120, 575 - spacingY),
            new Point2D(856-120, 575 - 2 * spacingY)
        };

        // Dessin des cadres et des tours
        for (int i = 0; i < tours.length; i++) {
            double cadreX = (i % 2 == 0) ? startX : startX + 2 * largeurCadre;
            double cadreY = startY - (i / 2) * spacingY;

            // Dessiner le cadre
            StdDraw.setPenColor(Color.YELLOW);
            StdDraw.rectangle(cadreX, cadreY, largeurCadre, hauteur);

            // Dessiner la tour et ses statistiques
            StdDraw.setPenColor(Color.BLACK);
            dessineCaseTour(tours[i], positions[i]);
        }
    }

    /**
     * Dessine la représentation visuelle d'une tour avec ses statistiques.
     *
     * @param t La tour à afficher.
     * @param p La position où afficher la tour.
     */
    private void dessineCaseTour(Tours t, Point2D p) {
        // Dessin du visuel de la tour
        t.drawVisuel(p, 15);

        // Dessin des statistiques de la tour
        StdDraw.setPenColor(Color.BLACK);
        Font font = new Font("sans serif", Font.PLAIN, 10);
        StdDraw.setFont(font);
        StdDraw.text(p.getX() + 65, p.getY() + 10, "PV : " + t.getPdv() + " | ATK : " + t.getAtk());
        StdDraw.text(p.getX() + 65, p.getY(), "SPD : " + t.getAtkspeed() + " | RA : " + t.getRange());
        StdDraw.text(p.getX() + 65, p.getY() - 10, "Cost : " + t.getCost());
    }

    /**
     * Détecte si une tour a été cliquée dans la boutique en fonction des coordonnées du clic.
     *
     * @param mouseX La coordonnée `x` du clic.
     * @param mouseY La coordonnée `y` du clic.
     * @return La tour cliquée (de type {@link Tours}), ou `null` si aucune tour n'a été cliquée.
     */
    public Tours detecteTourClique(double mouseX, double mouseY) {
        for (TourBoutique tb : toursBoutique) {
            if (tb.isClicked(mouseX, mouseY)) {
                System.out.println("Tour sélectionnée : " + tb.tour.getName());
                return tb.tour;
            }
        }
        return null; // Aucun clic valide
    }
}
