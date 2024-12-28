import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader; 
import java.io.IOException;


public class ZoneCarte {
    public Point2D center = new Point2D(350,350);
    public Point2D halfDist = new Point2D(350,350); 
    
    public void dessineCase(Point2D xy, int taille, Color couleur){ 
        // taille : largeur du carré 
        // x et y : coordonées du centre du carré 
        int x = xy.getX();
        int y = xy.getY();
        int t = taille/2 ; 

        StdDraw.setPenColor(couleur);
        StdDraw.filledSquare(x, y, t); 

        StdDraw.setPenColor(Color.BLACK);
        StdDraw.line(x-t, y-t, x+t, y-t) ; 
        StdDraw.line(x-t, y-t, x-t, y+t) ;
        StdDraw.line(x+t, y+t, x+t, y-t) ; 
        StdDraw.line(x+t, y+t, x-t, y+t) ;
    }

    public int hauteurMap(String nomFichier) throws IOException{
        int hauteur = 0;

        try(BufferedReader br = new BufferedReader(new FileReader(nomFichier))) {
            while (br.readLine() != null){
                hauteur++; 
            }
        }
        return hauteur; 
    }

    public int largeurMap(String nomFichier) throws IOException{
        int largeur = 0 ; 

        try(BufferedReader br = new BufferedReader(new FileReader(nomFichier))){
            String ligne = br.readLine();
            if (ligne != null){
                largeur = ligne.length() ; 
            }
        }

        return largeur ; 
    }

    public int calculerTailleCases(String nomFichier) throws IOException{
        int hauteurMap = hauteurMap(nomFichier) ; 
        int largeurMap = largeurMap(nomFichier) ;

       if (hauteurMap >= largeurMap){
        return 700 / hauteurMap ; // 700 étant la taille du côté du carré de notre zone "Carte"
       }
       else {
        return 700 / largeurMap ;
       }
    }

    public Point2D coordoneesDepart(String nomFichier) throws IOException{ 
        // La fonction renvoie les coordonées du centre de la première case (en haut à gauche),
        // en fonction du nombre de cases et de leurs disposition

        // Coordonées point haut gauche = 

        int tailleMap = 700; 
        int tailleCases = calculerTailleCases(nomFichier) ; 
        int largeurCarte = largeurMap(nomFichier) ; 


        // |---|---------------|----|
        // temp1     cases      temp1 
        int temp1 = (700 - largeurCarte * tailleCases) / 2;  


        return new Point2D(0, 0) ; 
    }

    


    public void dessineTerrain(){
        // Dessine toute les cases du terrain en fonction d'une map du dossier ressources.
    } 


    public void dessineCarte(){
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.rectangle(center.getX(), center.getY(), halfDist.getX(), halfDist.getY());
    }

}
