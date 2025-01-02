import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList; 


public class Carte extends ZoneCarte  {
    
    private String chemin ;
    private int tailleCase; 
    private ArrayList<ArrayList<Case>> carte ; 

    private int largeur = 350 ; 
    private int hauteur = 350 ;
    private Point2D mapCenter = new Point2D(largeur, hauteur) ; 


    public Carte (String chemin) throws IOException{
        this.chemin = chemin ;  
        this.tailleCase = calculerTailleCases(chemin) ;  
        this.carte = chargerCarte();
    }

    public String getChemin(){
        return this.chemin ; 
    }

    public Case getElement(int i, int j){
        return (this.carte.get(i)).get(j) ; 
    }

    private TypesCases obtenirTypeCase(char c) {
        switch (c) {
            case 'S':
                return TypesCases.Spawn;
            case 'B':
                return TypesCases.Base;
            case 'R':
                return TypesCases.Route;
            case 'C':
                return TypesCases.Constructible;
            case 'X':
                return TypesCases.Non_Constructible;
            default :
                return TypesCases.Spawn ; 
        }
    }

    public ArrayList<ArrayList<Case>> chargerCarte() throws IOException {
        String cheminFichier = this.chemin ; 
        ArrayList<ArrayList<Case>> carte = new ArrayList<>();
        int y = 0;  // Coordonnée Y de la case (on incrémente pour chaque ligne)

        // Lecture du fichier .mtp
        try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            // Lire chaque ligne du fichier
            while ((ligne = reader.readLine()) != null) {
                ArrayList<Case> ligneCases = new ArrayList<>();
                int x = 0;  // Coordonnée X de la case (on incrémente pour chaque caractère de la ligne)

                // Pour chaque caractère de la ligne, ajouter une case
                for (char c : ligne.toCharArray()) {
                    TypesCases type = obtenirTypeCase(c); // Déterminer le type de la case en fonction du caractère


                    int offsetX = ((x * tailleCase) - (ligne.length() * tailleCase) / 2) + 35 ;
                    int offsetY = ((y * tailleCase) - (ligne.length() * tailleCase) / 2) + 35;

                    Point2D centre = new Point2D(mapCenter.getX() + offsetX, mapCenter.getY() - offsetY);
                    // Point2D centre = new Point2D(x * tailleCase, y * tailleCase); // Calcul des coordonnées du centre
                    
                    Case caseActuelle = new Case(type, tailleCase, centre); // Créer la case
                    ligneCases.add(caseActuelle); // Ajouter la case à la ligne
                    x++; // Incrémenter la coordonnée X pour la prochaine case
                }

                carte.add(ligneCases); // Ajouter la ligne de cases à la carte
                y++; // Incrémenter la coordonnée Y pour la prochaine ligne
            }
        }

        return carte; // Retourner la carte complète
    }

    public void afficherCarteString() {
        StringBuilder carteString = new StringBuilder();
        
        for (ArrayList<Case> ligne : carte) {
            for (Case c : ligne) {
                // Construire la chaîne pour chaque case sous la forme (type, x, y)
                carteString.append("(")
                            .append(c.getType().toString())   // Type de la case (SPAWN, BASE, etc.)
                            .append(", ")
                            .append((int) c.getCentre().getX()) // Coordonnée x de la case
                            .append(", ")
                            .append((int) c.getCentre().getY()) // Coordonnée y de la case
                            .append(") "); // Fermeture de la parenthèse et espace

            }
            // Ajouter un saut de ligne après chaque ligne de cases
            carteString.append("\n");
        }


        // Afficher la carte sous forme de chaîne
        System.out.println(carteString.toString());
    }    

    public Point2D getSpawn(){
        for (ArrayList<Case> ligne : carte){
            for (Case c : ligne){
                if (c.getType() == TypesCases.Spawn){
                    return c.getCentre() ; 
                }
            }
        }
        return new Point2D(-1, -1) ; 
    }

    public Point2D getBase(){
        for (ArrayList<Case> ligne : carte){
            for (Case c : ligne) {
                if (c.getType() == TypesCases.Base){
                    return c.getCentre() ; 
                }
            }
        }
        return new Point2D(-1, -1) ; 
    }

}
