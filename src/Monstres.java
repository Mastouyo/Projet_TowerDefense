import java.util.LinkedList;

abstract class Monstres extends Entites{
    protected double speed;
    protected int reward;
    protected LinkedList<Tours> cibles;
    public Monstres(){
        
    }

    private void drawVisuel(Tours t, Point2D p, double taille){
        StdDraw . setPenColor ( this.elem.getCouleur());
        StdDraw . filledCircle( p . getX ()-120 , p . getY () , taille );
    }

}
