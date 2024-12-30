import java.awt.Color;
enum Types {
    Spawn,
    Base,
    Route,
    Constructible, 
    Decor, 
}
public class Case {
   
    

    private Types type; // S = spawn, B = base, R = route, C = constructible, X = decor
    private int taille ;
    private Point2D centre; // centre de la case 
    private boolean libre; // Si une tour occupe ou non la case (par défaut libre)

    public Case(Types type, int taille, Point2D centre){
        this.type = type ;
        this.taille = taille ; 
        this.centre = centre ;
        this.libre = true ; 
    }


    public void dessineCase(){
        int x = this.centre.getX() ; 
        int y = this.centre.getY() ; 
        int t = this.taille / 2 ; 

        switch (this.type) {
            case Spawn : StdDraw.setPenColor(Color.RED);
            case Base : StdDraw.setPenColor(Color.ORANGE) ; 
            case Route : StdDraw.setPenColor(194, 178, 128) ; 
            case Constructible : StdDraw.setPenColor(Color.LIGHT_GRAY);
            case Decor : StdDraw.setPenColor(11, 102, 35);
        }
        StdDraw.filledSquare(x, y, t);

        StdDraw.setPenColor(Color.BLACK);
        StdDraw.line(x-t, y-t, x+t, y-t) ; 
        StdDraw.line(x-t, y-t, x-t, y+t) ;
        StdDraw.line(x+t, y+t, x+t, y-t) ; 
        StdDraw.line(x+t, y+t, x-t, y+t) ;
    }

    public Types getBase(){
        return this.type.Base;
    }
}
