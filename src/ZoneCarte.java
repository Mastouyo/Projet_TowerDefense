import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader; 
import java.io.IOException;
import java.util.ArrayList;


public class ZoneCarte {
    public Point2D center = new Point2D(350,350);
    public Point2D halfDist = new Point2D(350,350); 
    

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

    /* 
    public Point2D coordoneesDepart(String nomFichier) throws IOException{ 
        // La fonction renvoie les coordonées du centre de la première case (en haut à gauche),
        // en fonction du nombre de cases et de leurs disposition

        // Coordonées point haut gauche = 

        int tailleMap = 700; 
        int tailleCases = calculerTailleCases(nomFichier) ; 
        int largeurCarte = largeurMap(nomFichier) ; 


        // |---|---------------|----|
        // temp1     cases      temp1 
        int temp1 = (tailleMap - largeurCarte * tailleCases) / 2;  


        return new Point2D(0, 0) ; 
    }
        */

    public void dessineCase(Case c){
        int x = c.getCentre().getX() ; 
        int y = c.getCentre().getY() ; 
        int t = c.getTaille() / 2 ; 
        TypesCases type = c.getType() ; 

        if (type == TypesCases.Spawn){ StdDraw.setPenColor(Color.RED);}
        else if (type == TypesCases.Base){ StdDraw.setPenColor(Color.ORANGE);}
        else if (type == TypesCases.Route){ StdDraw.setPenColor(194, 178, 127);}
        else if (type == TypesCases.Constructible){ StdDraw.setPenColor(Color.LIGHT_GRAY);}
        else { StdDraw.setPenColor(11, 102, 35);}
        
        StdDraw.filledSquare(x, y, t);

        StdDraw.setPenColor(Color.BLACK);
        StdDraw.line(x-t, y-t, x+t, y-t) ; 
        StdDraw.line(x-t, y-t, x-t, y+t) ;
        StdDraw.line(x+t, y+t, x+t, y-t) ; 
        StdDraw.line(x+t, y+t, x-t, y+t) ;
    }


    public void dessineTerrain(String chemin) throws IOException {
        Carte carte = new Carte(chemin) ; 

        for (int i = 0; i < hauteurMap(carte.getChemin()); i ++){
            for (int j = 0; j < largeurMap(carte.getChemin()); j ++){
                
                // Print pour vérifier la lecture des cases (visiblement correct)
                System.out.println(carte.getElement(i, j).toString());
                
                dessineCase(carte.getElement(i, j)); 
            }
        } 
    }    

    public void dessineCarte(){
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.rectangle(center.getX(), center.getY(), halfDist.getX(), halfDist.getY());
    }
}
