import java.lang.Math;
import java.util.LinkedList;

public class Archer extends Tours{
    
    public Archer(){
    this.name = "Archer";
        this.pdv = 30;
        this.atk = 5;
        this.atkspeed = 1;
        this.range = 2;
        this.elem = new Element(Type.None);
        this.cost = 20;
    }

    private Monstres cible(){
        LinkedList<Monstres> monstreEnRange = new LinkedList();
        for(Monstres m : this.cibles){
            int x = m.position.getX();
            int y = m.position.getY();
            if(Math.sqrt(x*x + y*y) <= this.range){
                monstreEnRange.add(m);
            }
        }
        return monstreEnRange.getLast();
    }
    
}
