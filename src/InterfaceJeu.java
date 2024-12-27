import java.awt.Color;
import java.io.IOException;


public class InterfaceJeu {
    
    private ZoneInfoJeu zoneInfoJeu; 
    private ZoneInfoJoueur zoneInfoJoueur; 
    private ZoneBoutique zoneBoutique; 
    private ZoneCarte zoneCarte; 
    public Point2D center =new Point2D(500,350);
    public Point2D halfDist= new Point2D(500,350);

    public InterfaceJeu(){
        //Création et affichage de la fenêtre de base 
        StdDraw.setCanvasSize(1024, 720);
        StdDraw.setXscale(-12, 1012);
        StdDraw.setYscale(-10, 710);
        StdDraw.enableDoubleBuffering();

        zoneBoutique = new ZoneBoutique();
        zoneCarte = new ZoneCarte();
        zoneInfoJeu = new ZoneInfoJeu();
        zoneInfoJoueur = new ZoneInfoJoueur();
    }

    public void afficheJeu() throws IOException{
        zoneBoutique.dessineBoutique();
        zoneInfoJeu.dessineInfoJoueur();
        zoneInfoJoueur.dessineInfoJoueur();
        zoneCarte.dessineCarte();

        String cheminMap1 = "resources/maps/10-10.mtp" ;
        int tailleCases = zoneCarte.calculerTailleCases(cheminMap1);
        System.out.println(tailleCases) ; 

        Point2D test = new Point2D(35, 200);
        Point2D test2 = new Point2D(35+tailleCases, 200);
        Point2D test3 = new Point2D(35+tailleCases*2, 200);
        Point2D test4 = new Point2D(35+tailleCases*3, 200);
        Point2D test5 = new Point2D(35+tailleCases*4, 200);
        Point2D test6 = new Point2D(35+tailleCases*5, 200);
        Point2D test7 = new Point2D(35+tailleCases*6, 200);
        Point2D test8 = new Point2D(35+tailleCases*7, 200);
        Point2D test9 = new Point2D(35+tailleCases*8, 200);
        Point2D test10 = new Point2D(35+tailleCases*9, 200);

        
        zoneCarte.dessineCase(test, tailleCases, Color.GREEN);
        zoneCarte.dessineCase(test2, tailleCases, Color.GREEN);
        zoneCarte.dessineCase(test3, tailleCases, Color.GREEN);
        zoneCarte.dessineCase(test4, tailleCases, Color.GREEN);
        zoneCarte.dessineCase(test5, tailleCases, Color.GREEN);
        zoneCarte.dessineCase(test6, tailleCases, Color.GREEN);
        zoneCarte.dessineCase(test7, tailleCases, Color.GREEN);
        zoneCarte.dessineCase(test8, tailleCases, Color.GREEN);
        zoneCarte.dessineCase(test9, tailleCases, Color.GREEN);
        zoneCarte.dessineCase(test10, tailleCases, Color.GREEN);


        StdDraw.show();
    }

}
