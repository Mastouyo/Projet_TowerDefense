import java.awt.Color;

public class ZoneBoutique {
    private Point2D center = new Point2D(856, 303);
    private Point2D halfDist = new Point2D(144,303);

    public void dessineBoutique(){
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.rectangle(center.getX(), center.getY(), halfDist.getX(), halfDist.getY());
    }
}
