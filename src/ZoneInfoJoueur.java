import java.awt.Color;

public class ZoneInfoJoueur{
    private Point2D center = new Point2D(856, 641);
    private Point2D halfDist = new Point2D(144,25);
    private Player joueur;

    public void dessineInfoJoueur(){
        this.joueur = new Player();
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.rectangle(center.getX(), center.getY(), halfDist.getX(), halfDist.getY());

        //argent du joueur
        drawCoin(15);
        StdDraw . setPenColor ( new Color (212 , 175 ,55) ); //met le pinceau en doré
        StdDraw.text(center.getX()-80, center.getY(),String.valueOf(joueur.getMoney()));

        //vie du joueur
        drawHeart(15 );
        StdDraw.text(center.getX()+80, center.getY(),String.valueOf(joueur.getPdv()));
    }

    public void drawCoin( double radius) {
        StdDraw . setPenColor ( new Color (212 , 175 ,55) );
        StdDraw . filledCircle ( center . getX ()-120 , center . getY () , radius );
        StdDraw . setPenColor ( new Color (192 , 192 ,192) );
        StdDraw . filledCircle ( center . getX ()-120 , center . getY () , 0.7 * radius );
    }

    public void drawHeart( double halfHeight) {
        StdDraw . setPenColor ( new Color (223 , 75 , 95) );
        double x = center.getX()+120;
        double [] listX = new double []
        {
        x ,
        x - halfHeight ,
        x - halfHeight ,
        x - 0.66 * halfHeight ,
        x - 0.33 * halfHeight ,
        x ,
        x + 0.33 * halfHeight ,
        x + 0.66 * halfHeight ,
        x + halfHeight ,
        x + halfHeight ,
        };
        double [] listY = new double []
        {
        center . getY () - halfHeight ,
        center . getY () ,
        center . getY () + 0.5 * halfHeight ,
        center . getY () + halfHeight ,
        center . getY () + halfHeight ,
        center . getY () + 0.5 * halfHeight ,
        center . getY () + halfHeight ,
        center . getY () + halfHeight ,
        center . getY () + 0.5 * halfHeight ,
        center . getY () ,
        };
        StdDraw . filledPolygon ( listX , listY );
    }
    
    
}