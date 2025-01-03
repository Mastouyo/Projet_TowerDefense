import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Queue; 
import java.util.LinkedList;


public class Carte extends ZoneCarte  {
    
    private String Fichier ;
    private int tailleCase; 
    private ArrayList<ArrayList<Case>> carte ; 
    private ArrayList<Case> casesConstructbiles ; 
    private ArrayList<Case> chemin ; 

    private int largeur = 350 ; 
    private int hauteur = 350 ;
    private Point2D mapCenter = new Point2D(largeur, hauteur) ; 


    public Carte (String Fichier) throws IOException{
        this.Fichier = Fichier ;  
        this.tailleCase = calculerTailleCases(Fichier) ;  
        this.carte = chargerCarte();
        this.casesConstructbiles = initListeCasesConstructibles() ; 
    }

    public String getChemin(){
        return this.Fichier ; 
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
        String cheminFichier = this.Fichier ; 
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

    private ArrayList<Case> initListeCasesConstructibles(){
        ArrayList<Case> casesConstructibles = new ArrayList<>() ; 
        for (ArrayList<Case> ligne : carte){
            for (Case c : ligne){
                if (c.getType() == TypesCases.Constructible){
                    casesConstructibles.add(c) ; 
                }
            }
        }
        return casesConstructibles ; 
    }

    public void ajouterCaseConstructible(Case c){
        c.setLibreTrue();
        this.casesConstructbiles.add(c) ; 
    }

    public void retirerCaseConstructible(Case c){
        c.setLibreFalse();
        this.casesConstructbiles.remove(c) ; 
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

    // FONCTIONNE 
    public Case getCaseSpawn(){
        for (ArrayList<Case> ligne : carte){
            for (Case c : ligne){
                if (c.getType() == TypesCases.Spawn){
                    return c; 
                }
            }
        }
        return null ; 
    }

    // FONCTIONNE
    public Case getCaseBase(){
        for (ArrayList<Case> ligne : carte){
            for (Case c : ligne){
                if (c.getType() == TypesCases.Base){
                    return c; 
                }
            }
        }
        return null ; 
    }

    // Renvoie la case aux indices i et j dans la liste de liste Carte
    // FONCTIONNE
    public Case caseSelonIndices(int i, int j){
        if ((i < 0) || (j < 0)){ return null; }
        return this.carte.get(i).get(j) ; 
    }

    // Renvoie les indices dans la list de liste carte de la case C
    // FONCTIONNE
    public Point2D positionDansLaCarte(Case c){
        for (int i = 0 ; i < this.carte.size(); i ++){
            if (this.carte.get(i).contains(c)){
                return new Point2D (i , this.carte.get(i).indexOf(c)) ; 
            }
        }
        return null ;    
    }

    
    public Case prochaineCase(Case c, ArrayList<Case> queue){
        int positionI = positionDansLaCarte(c).getX() ; 
        int positionJ = positionDansLaCarte(c).getY() ; 

        // Case au dessus 
        Case cUP = caseSelonIndices(positionI - 1, positionJ);
        Case cRIGHT = caseSelonIndices(positionI, positionJ + 1);
        Case cDOWN = caseSelonIndices(positionI + 1, positionJ);
        Case cLEFT = caseSelonIndices(positionI, positionJ - 1);




        if (((cUP.getType() == TypesCases.Route) || (cUP.getType() == TypesCases.Base)) &&  
        (queue.contains(cUP) == false)){
            return cUP; }
        

        // Case à droite 
        else if (((cRIGHT.getType() == TypesCases.Route) || (cRIGHT.getType() == TypesCases.Base)) &&  
        (queue.contains(cRIGHT) == false)){
            return cRIGHT; 
        }

         // Case en bas
         else if (((cDOWN.getType() == TypesCases.Route) || (cDOWN.getType() == TypesCases.Base)) &&  
         (queue.contains(cDOWN) == false)){
             return cDOWN;
         }

         // Case à gauche
         else if (((cLEFT.getType() == TypesCases.Route) || (cLEFT.getType() == TypesCases.Base)) &&  
         (queue.contains(cLEFT) == false)){
             return cLEFT;
         }

         else { return null ; }
    }

    public ArrayList<Case> initChemin(){
        ArrayList<Case> cheminMonstres = new ArrayList<Case>() ;
        
        Case courant = getCaseSpawn() ; 
        cheminMonstres.add(courant) ; 

        while (cheminMonstres.contains(getCaseBase()) == false ) {
            courant = prochaineCase(courant, cheminMonstres) ;
            cheminMonstres.add(courant) ;
        }
        return cheminMonstres ;
    }

    public void afficheChemin (ArrayList<Case> chemin){
        for (int i = 0 ; i < chemin.size() ; i ++){
            System.out.println(chemin.get(i).toString()) ; 
        }
    }
    
}
