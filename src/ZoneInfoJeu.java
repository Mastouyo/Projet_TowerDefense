import java.awt.Color;

public class ZoneInfoJeu {
    private Point2D center = new Point2D (856, 641);
    private Point2D halfDist = new Point2D (144,25);
  
    public void dessineInfoJoueur(){
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.rectangle(center.getX(), center.getY(), halfDist.getX(), halfDist.getY());
    }
}
