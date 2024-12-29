import java.io.IOException;
import java.util.ArrayList; 


public class Carte extends ZoneCarte {
    
    private String chemin ;

    public Carte (String chemin) throws IOException{
        this.chemin = chemin ; 

        int hauteur = hauteurMap(this.chemin) ;
        int largeur = largeurMap(this.chemin) ; 

        ArrayList<ArrayList<Case>> lignes = new ArrayList<>() ;
        for (int i = 0 ; i < hauteur; i ++) {
            ArrayList<Case> colonnes = new ArrayList<>() ; 
            lignes.add(colonnes) ; 
        } 
    }
}
