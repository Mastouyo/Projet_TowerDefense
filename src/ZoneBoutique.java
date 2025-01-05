import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;


public class ZoneBoutique {
    private Point2D center = new Point2D(856, 303);
    private Point2D halfDist = new Point2D(144,303);
    private Archer archer = new Archer();
    private EarthCaster eC = new EarthCaster();
    private FireCaster fC = new FireCaster();
    private WaterCaster waC = new WaterCaster();
    private WindCaster wiC = new WindCaster();
    private List<TourBoutique> toursBoutique;

    private class TourBoutique {
        Tours tour;
        double xMin, xMax, yMin, yMax;

        public TourBoutique(Tours tour, double xMin, double xMax, double yMin, double yMax) {
            this.tour = tour;
            this.xMin = xMin;
            this.xMax = xMax;
            this.yMin = yMin;
            this.yMax = yMax;
        }

        public boolean isClicked(double sourisX, double sourisY) {
            return sourisX >= xMin && sourisX <= xMax && sourisY >= yMin && sourisY <= yMax;
        }
    }

    public ZoneBoutique() {
        toursBoutique = new ArrayList<>();
        toursBoutique.add(new TourBoutique(new Archer(), 785, 927, 555, 595));
        toursBoutique.add(new TourBoutique(new EarthCaster(), 927, 1070, 555, 595));
        toursBoutique.add(new TourBoutique(new FireCaster(), 785, 927, 495, 535));
        toursBoutique.add(new TourBoutique(new WaterCaster(), 927, 1070, 495, 535));
        toursBoutique.add(new TourBoutique(new WindCaster(), 785, 927, 435, 475));
    }

    public void dessineBoutique() {
        // Dessin du cadre de la boutique
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.rectangle(center.getX(), center.getY(), halfDist.getX(), halfDist.getY());
    
        // Dimensions pour les cadres
        double largeurCadre = halfDist.getX() / 2 -1;
        double hauteur = halfDist.getY() / 10;
        int startX = 785; // Coordonnée X initiale
        int startY = 575; // Coordonnée Y initiale
        double spacingY = 2 * hauteur; // Espacement vertical entre les cadres
    
        // Liste des tours avec leurs positions
        Tours[] tours = {archer, eC, fC, waC, wiC};
        Point2D[] positions = {
            new Point2D(856, 575),
            new Point2D(1000, 575),
            new Point2D(856, (575 - spacingY)),
            new Point2D(1000, 575 - spacingY),
            new Point2D(856, 575 - 2 * spacingY)
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

    private void dessineCaseTour(Tours t, Point2D p){
        //dessin du visuel de la tour entrée en parametre
        t.drawVisuel(p,15);
        //dessin des statistiques de la tour entrée en parametre
        StdDraw.setPenColor(Color.BLACK);
        Font font = new Font("sans serif", Font.PLAIN, 10);
        StdDraw.setFont(font);
        StdDraw.text(p.getX()-55,p.getY()+10, "PV : " + String.valueOf(t.getPdv() + " | ATK : " + String.valueOf(t.getAtk())));
        StdDraw.text(p.getX()-55,p.getY(), "SPD : " + String.valueOf(t.getAtkspeed() + " | RA : " + String.valueOf(t.getRange())));
        StdDraw.text(p.getX()-55,p.getY()-10, "Cost : " + String.valueOf(t.getCost()));
    }

    public Tours detecteTourClique(double mouseX, double mouseY) {
        for (TourBoutique tb : toursBoutique) {
            if (tb.isClicked(mouseX, mouseY)) {
                System.out.println(tb.tour);
                return tb.tour;
            }
        }
        return null; // Aucun clic valide
    }
}