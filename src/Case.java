public class Case {
    private TypesCases type; // S = spawn, B = base, R = route, C = constructible, X = decor
    private int taille ;
    private Point2D centre; // centre de la case 
    private boolean libre; // Si une tour occupe ou non la case (par défaut libre)

    

    public Case(TypesCases type, int taille, Point2D centre){
        this.type = type ;
        this.taille = taille ; 
        this.centre = centre ;
        this.libre = true ; 
    }

    

    public TypesCases getType(){
        return this.type ; 
    }

    public Point2D getCentre(){
        return this.centre ; 
    }

    public int getTaille(){
        return this.taille ; 
    }

    public boolean getLibre(){
        return this.libre ; 
    }

    public void setLibreFalse(){
        this.libre = false ; 
    }

    public void setLibreTrue(){
        this.libre = true ; 
    }

    @Override
    public String toString(){
        return "(" + this.type + ", " + "(" + this.centre.getX() + "," + this.centre.getY() + "))" ; 
    }

    public boolean contains(double x, double y){
        return (x >= this.centre.getX() - this.taille/2 && x <= this.centre.getX() + this.taille/2 && y >= this.centre.getY() - this.taille/2 && y <= this.centre.getY() + this.taille/2) ;
    }
}
