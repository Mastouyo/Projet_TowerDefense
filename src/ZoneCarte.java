import java.awt.Color;

public class ZoneCarte {
    public Point2D center = new Point2D(350,350);
    public Point2D halfDist = new Point2D(350,350); 
    
    /*public void dessineCase(){
        
        int halfLength = ;
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.filledSquare(centerX , centerY , halfLength);
    }*/
    public void dessineCarte(){
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.rectangle(center.getX(), center.getY(), halfDist.getX(), halfDist.getY());
    }

}
