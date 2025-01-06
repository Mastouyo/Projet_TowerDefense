import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.awt.Color;

public class Carte extends ZoneCarte {
    private String Fichier;
    private int tailleCase; 
    private ArrayList<ArrayList<Case>> carte;
    private LinkedList<Case> casesConstructbiles;
    private LinkedList<Case> chemin;
    private LinkedList<Tours> tours ;

    private double largeur = 350.0; 
    private double hauteur = 350.0;
    private Point2D mapCenter = new Point2D(largeur, hauteur);

    public Carte(String Fichier) throws IOException {
        this.Fichier = Fichier;  
        this.tailleCase = calculerTailleCases(Fichier);  
        this.carte = chargerCarte();
        this.casesConstructbiles = initListeCasesConstructibles(); 
        this.chemin = initChemin(); 
        this.tours=new LinkedList<>();
    }

    public String getFichier() {
        return this.Fichier; 
    }

    public Case getTile(double row, double col) {
        int rowIndex = (int) row;
        int colIndex = (int) col;
        if (rowIndex < 0 || colIndex < 0 || rowIndex >= carte.size() || colIndex >= carte.get(rowIndex).size()) {
            return null; // Hors limites
        }
        return carte.get(rowIndex).get(colIndex);
    }

    public int getTailleCase() {
        return tailleCase;
    }

    public LinkedList<Case> getChemin() {
        return this.chemin; 
    }

    public Case getElement(int i, int j) {
        return this.carte.get(i).get(j); 
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
        ArrayList<ArrayList<Case>> carte = new ArrayList<>();
        int y = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(this.Fichier))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                ArrayList<Case> ligneCases = new ArrayList<>();
                int x = 0;

                for (char c : ligne.toCharArray()) {
                    TypesCases type = obtenirTypeCase(c);
                    double offsetX = ((x * tailleCase) - (ligne.length() * tailleCase) / 2.0) + 35.0;
                    double offsetY = ((y * tailleCase) - (ligne.length() * tailleCase) / 2.0) + 35.0;
                    Point2D centre = new Point2D(mapCenter.getX() + offsetX, mapCenter.getY() - offsetY);
                    ligneCases.add(new Case(type, tailleCase, centre));
                    x++;
                }
                carte.add(ligneCases);
                y++;
            }
        }
        return carte;
    }

    public void afficherCarteString() {
        StringBuilder carteString = new StringBuilder();
        for (ArrayList<Case> ligne : carte) {
            for (Case c : ligne) {
                carteString.append("(")
                           .append(c.getType().toString())
                           .append(", ")
                           .append(c.getCentre().getX())
                           .append(", ")
                           .append(c.getCentre().getY())
                           .append(") ");
            }
            carteString.append("\n");
        }
        System.out.println(carteString);
    }

    private LinkedList<Case> initListeCasesConstructibles() {
        LinkedList<Case> casesConstructibles = new LinkedList<>(); 
        for (ArrayList<Case> ligne : carte) {
            for (Case c : ligne) {
                if (c.getType() == TypesCases.Constructible) {
                    casesConstructibles.add(c); 
                }
            }
        }
        return casesConstructibles; 
    }

    public Case getCaseConstructible(double x, double y) {
        for (Case c : this.casesConstructbiles) {
            if (c.contains(x, y)) {
                return c; 
            }
        }
        return null; 
    }

    public LinkedList<Case> getCasesConstructibles() {
        return this.casesConstructbiles; 
    }

    public void ajouterCaseConstructible(Case c) {
        c.setLibreTrue();
        this.casesConstructbiles.add(c); 
    }

    public void retirerCaseConstructible(Case c) {
        c.setLibreFalse();
        this.casesConstructbiles.remove(c); 
    }

    public Point2D getSpawn() {
        for (ArrayList<Case> ligne : carte) {
            for (Case c : ligne) {
                if (c.getType() == TypesCases.Spawn) {
                    return c.getCentre(); 
                }
            }
        }
        return new Point2D(-1.0, -1.0); 
    }

    public Point2D getBase() {
        for (ArrayList<Case> ligne : carte) {
            for (Case c : ligne) {
                if (c.getType() == TypesCases.Base) {
                    return c.getCentre(); 
                }
            }
        }
        return new Point2D(-1.0, -1.0); 
    }

    public Case getCaseSpawn() {
        for (ArrayList<Case> ligne : carte) {
            for (Case c : ligne) {
                if (c.getType() == TypesCases.Spawn) {
                    return c; 
                }
            }
        }
        return null; 
    }

    public Case getCaseBase() {
        for (ArrayList<Case> ligne : carte) {
            for (Case c : ligne) {
                if (c.getType() == TypesCases.Base) {
                    return c; 
                }
            }
        }
        return null; 
    }

    public Case caseSelonCoordonees(Point2D co) {
        for (ArrayList<Case> ligne : carte) {
            for (Case c : ligne) {
                if (c.getCentre().equals(co)) {
                    return c; 
                }
            }
        }
        return null; 
    }

    public Case caseSelonIndices(double i, double j) {
        int rowIndex = (int) i;
        int colIndex = (int) j;
        if (rowIndex < 0 || colIndex < 0 || rowIndex >= carte.size() || colIndex >= carte.get(rowIndex).size()) {
            return null; 
        }
        return carte.get(rowIndex).get(colIndex); 
    }

    public Point2D positionDansLaCarte(Case c) {
        for (int i = 0; i < carte.size(); i++) {
            if (carte.get(i).contains(c)) {
                return new Point2D(i, carte.get(i).indexOf(c)); 
            }
        }
        return null;    
    }

    public Case prochaineCase(Case c, LinkedList<Case> queue) {
        Point2D position = positionDansLaCarte(c);
        if (position == null) return null;

        double positionI = position.getX(); 
        double positionJ =  position.getY(); 

        Case cUP = caseSelonIndices(positionI - 1, positionJ);
        Case cRIGHT = caseSelonIndices(positionI, positionJ + 1);
        Case cDOWN = caseSelonIndices(positionI + 1, positionJ);
        Case cLEFT = caseSelonIndices(positionI, positionJ - 1);

        if (cUP != null && (cUP.getType() == TypesCases.Route || cUP.getType() == TypesCases.Base) && !queue.contains(cUP)) {
            return cUP;
        } else if (cRIGHT != null && (cRIGHT.getType() == TypesCases.Route || cRIGHT.getType() == TypesCases.Base) && !queue.contains(cRIGHT)) {
            return cRIGHT; 
        } else if (cDOWN != null && (cDOWN.getType() == TypesCases.Route || cDOWN.getType() == TypesCases.Base) && !queue.contains(cDOWN)) {
            return cDOWN;
        } else if (cLEFT != null && (cLEFT.getType() == TypesCases.Route || cLEFT.getType() == TypesCases.Base) && !queue.contains(cLEFT)) {
            return cLEFT;
        } else {
            return null;
        }
    }

    public LinkedList<Case> initChemin() {
        LinkedList<Case> cheminMonstres = new LinkedList<>();
        Case courant = getCaseSpawn(); 
        cheminMonstres.add(courant); 

        while (!cheminMonstres.contains(getCaseBase())) {
            courant = prochaineCase(courant, cheminMonstres); 
            if (courant == null) break;
            cheminMonstres.add(courant); 
        }
        return cheminMonstres;
    }

    public void afficheChemin() {
        for (Case caseChemin : chemin) {
            System.out.println(caseChemin.toString()); 
        }
    }

    public void placerTour(Tours t, Point2D xy) {
        Case caseSouhaitee = caseSelonCoordonees(xy);
        System.out.println(caseSouhaitee); 

        if (caseSouhaitee != null && casesConstructbiles.contains(caseSouhaitee)) {
            Point2D centreCase = caseSouhaitee.getCentre(); 
            t.drawVisuel(centreCase, caseSouhaitee.getTaille() / 3);
            retirerCaseConstructible(caseSouhaitee);
        }

    }

    // Fonction de test qui dessine un petit carré noir sur toute les cases constructbiles 
    public void montreCasesConstructibles() {
        LinkedList<Case> casesConstructibles = this.casesConstructbiles ; 

        for (int i = 0 ; i < casesConstructibles.size() ; i ++){
            double x = casesConstructibles.get(i).getCentre().getX() ; 
            double y = casesConstructibles.get(i).getCentre().getY() ; 

            StdDraw.filledSquare(x, y, 10);
        }
    }

    // Fonction de test qui dessine un petit carré rose sur toute les cases du chemin 
    public void montreCasesChemin(){
        LinkedList<Case> chemin = this.chemin ; 

        for (int i = 0 ; i < chemin.size() ; i ++){
            double x = chemin.get(i).getCentre().getX() ; 
            double y = chemin.get(i).getCentre().getY() ; 

            StdDraw.setPenColor(Color.PINK);
            StdDraw.filledSquare(x, y, 10);
        }
    }

    public LinkedList<Point2D> convertitCheminPixel() {
        LinkedList<Point2D> pixelList = new LinkedList<Point2D>();
        double centerX = 350;
        double centerY = 350;

        for (Case c : chemin) {
            double x = centerX + (c.getCentre().getX() - mapCenter.getX()) * tailleCase;
            double y = centerY + (c.getCentre().getY() - mapCenter.getY()) * tailleCase;
            pixelList.add(new Point2D(x, y));
        }

        return pixelList;
    }

    public void addTower(Tours t){
        this.tours.add(t) ;
    }

    public LinkedList<Tours> getTowers(){
        return this.tours ; 
    }
}
