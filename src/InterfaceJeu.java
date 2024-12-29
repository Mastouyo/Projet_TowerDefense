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

        /* 
        String cheminMap1 = "resources/maps/10-10.mtp" ;
        int tailleCases = zoneCarte.calculerTailleCases(cheminMap1);
        System.out.println(tailleCases) ; 
        */ 

        Point2D test = new Point2D(50, 50) ; 
        Case testCase = new Case(Types.Spawn, 50, test) ;
        


        StdDraw.show();
    }

}
