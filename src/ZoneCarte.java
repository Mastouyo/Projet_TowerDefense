import java.awt.Color;

public class ZoneCarte {
    public Point2D center = new Point2D(350,350);
    public Point2D halfDist = new Point2D(350,350); 
    
    public void dessineCase(Point2D xy, int taille, Color couleur){ 
        // taille : largeur du carré 
        // x et y : coordonées du centre du carré 
        int x = xy.getX();
        int y = xy.getY();

        StdDraw.setPenColor(couleur);
        StdDraw.filledSquare(x, y, taille); 

        StdDraw.setPenColor(Color.BLACK);
        StdDraw.line(x-taille, y-taille, x+taille, y-taille) ; 
        StdDraw.line(x-taille, y-taille, x-taille, y+taille) ;
        StdDraw.line(x+taille, y+taille, x+taille, y-taille) ; 
        StdDraw.line(x+taille, y+taille, x-taille, y+taille) ;
    }



    public int calculerTailleCases(int nombreCases){
       return 0 ;
    }


    public void dessineTerrain(){
        // Dessine toute les cases du terrain en fonction d'une map du dossier ressources. La fonction
    } 


    public void dessineCarte(){
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.rectangle(center.getX(), center.getY(), halfDist.getX(), halfDist.getY());
    }

}
