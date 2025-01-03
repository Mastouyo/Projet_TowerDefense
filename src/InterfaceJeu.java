import java.io.IOException;

public class InterfaceJeu {
    private ZoneInfoJeu zoneInfoJeu; 
    private ZoneInfoJoueur zoneInfoJoueur; 
    private ZoneBoutique zoneBoutique; 
    private ZoneCarte zoneCarte; 
    public Point2D center =new Point2D(500,350);
    public Point2D halfDist= new Point2D(500,350);

    public InterfaceJeu(Player player){
        //Création et affichage de la fenêtre de base 
        StdDraw.setCanvasSize(1024, 720);
        StdDraw.setXscale(-12, 1012);
        StdDraw.setYscale(-10, 710);
        StdDraw.enableDoubleBuffering();

        zoneBoutique = new ZoneBoutique();
        zoneCarte = new ZoneCarte();
        zoneInfoJeu = new ZoneInfoJeu();
        zoneInfoJoueur = new ZoneInfoJoueur(player);
    }

    public void afficheJeu() throws IOException{
        zoneBoutique.dessineBoutique();
        zoneInfoJeu.dessineInfoJoueur();
        zoneInfoJoueur.dessineInfoJoueur();
        zoneCarte.dessineCarte();

        String chemin = "resources/maps/5-8.mtp" ; 
        Carte carte = new Carte(chemin) ; 

        Point2D spawn = carte.getSpawn();
        int spawnX = spawn.getX() ; 
        int spawnY = spawn.getY() ; 
        System.out.println("(" + spawnX + "," + spawnY + ")");

        zoneCarte.dessineTerrain(carte);
        
        StdDraw.show();
    }

}
