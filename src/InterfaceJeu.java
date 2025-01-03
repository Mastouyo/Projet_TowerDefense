import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;





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

        String chemin = "resources/maps/10-10.mtp" ; 
        Carte carte = new Carte(chemin) ; 
 
        zoneCarte.dessineTerrain(carte);
        
        StdDraw.show();

        ArrayList<Case> cheminMonstres = carte.initChemin() ; 
        carte.afficheChemin(cheminMonstres);
    }

}
