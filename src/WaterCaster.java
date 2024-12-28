import java.util.LinkedList;
import java.util.stream.Collectors;

public class WaterCaster extends Tours{
    public WaterCaster(){
        this.name = "WaterCaster";
        this.pdv = 30;
        this.atk = 3;
        this.atkspeed = 1;
        this.range = 4;
        this.elem = new Element(Type.Water);
        this.cost = 50;
    }

    private Monstres cible(){
        LinkedList<Monstres> monstresEnRange = this.cibles.stream().filter(p->hypothénus(distance(p.position.getX(),this.position.getX()), distance(p.position.getY(),this.position.getY())) <= this.range).collect(Collectors.toCollection(LinkedList::new));
        if(monstresEnRange.isEmpty()){
            return null;
        }
        else{
            return monstresEnRange.getLast();
        }
    }
}
