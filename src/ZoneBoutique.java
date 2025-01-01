import java.awt.Color;


import java.awt.Font;


public class ZoneBoutique {
    private Point2D center = new Point2D(856, 303);
    private Point2D halfDist = new Point2D(144,303);
    private Archer archer = new Archer();
    private EarthCaster eC = new EarthCaster();
    private FireCaster fC = new FireCaster();
    private WaterCaster waC = new WaterCaster();
    private WindCaster wiC = new WindCaster();

    public void dessineBoutique(){
        //dessin du cadre de la boutique
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.rectangle(center.getX(), center.getY(), halfDist.getX(), halfDist.getY());

        //dessin des cadres des tours
        StdDraw.setPenColor(Color.YELLOW);
        StdDraw.rectangle(785, 575, halfDist.getX()/2-2, halfDist.getY()/10);
        StdDraw.rectangle(927, 575, halfDist.getX()/2-2, halfDist.getY()/10);
        StdDraw.rectangle(785, 575 - 2 * halfDist.getY()/10, halfDist.getX()/2-2, halfDist.getY()/10);
        StdDraw.rectangle(927, 575 - 2 * halfDist.getY()/10, halfDist.getX()/2-2, halfDist.getY()/10);
        StdDraw.rectangle(785, 575 - 4 * halfDist.getY()/10, halfDist.getX()/2-2, halfDist.getY()/10);
        
        //dessin des tours et de leurs statistiques
        //archer
        dessineCaseTour(archer, new Point2D(856, 575));
        //earth caster
        dessineCaseTour(eC,new Point2D(1000, 575 ));
        //fire caster
        dessineCaseTour(fC,new Point2D(856, 575 - 2 * halfDist.getY()/10));
        //water caster
        dessineCaseTour(waC,new Point2D(1000, 575 - 2 * halfDist.getY()/10));
        //wind caster
        dessineCaseTour(wiC,new Point2D(856, 575 - 4 * halfDist.getY()/10));
    }

    private void dessineCaseTour(Tours t, Point2D p){
        //dessin du visuel de la tour entrée en parametre
        t.drawVisuel(p,15);
        //dessin des statistiques de la tour entrée en parametre
        StdDraw.setPenColor(Color.BLACK);
        Font font = new Font("sans serif", Font.PLAIN, 10);
        StdDraw.setFont(font);
        StdDraw.text(p.getX()-55,p.getY()+5, "PV : " + String.valueOf(t.getPdv() + " | ATK : " + String.valueOf(t.getAtk())));
        StdDraw.text(p.getX()-55,p.getY()-5, "SPD : " + String.valueOf(t.getAtkspeed() + " | RA : " + String.valueOf(t.getRange())));
        StdDraw.text(p.getX()-55,p.getY()-15, "Cost : " + String.valueOf(t.getCost()));
    }
}