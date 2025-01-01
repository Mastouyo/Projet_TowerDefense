import java.util.LinkedList;

// import javafx.scene.paint.Color;

abstract class Tours extends Entites{
    protected int cost;
    protected LinkedList<Monstres> cibles;
    public  Tours(){
        
    }

    public int getCost() {
        return cost;
    }

    protected void drawVisuel(Point2D p, double taille){
        StdDraw . setPenColor ( this.elem.getCouleur());
        StdDraw . filledSquare( p . getX ()-120 , p . getY () , taille );
    }

}
