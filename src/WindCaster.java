import java.util.LinkedList;
import java.util.stream.Collectors;

public class WindCaster extends Tours{
    public WindCaster(){
        this.name = "WindCaster";
        this.pdv = 30;
        this.atk = 5;
        this.atkspeed = 1.5;
        this.range = 6;
        this.elem = new Element(Type.Wind);
        this.cost = 50;
    }

    private Monstres cible(){
        LinkedList<Monstres> monstresEnRange = this.cibles.stream()
        .filter(p->hypothénus(distance(p.position.getX(),this.position.getX()), distance(p.position.getY(),this.position.getY())) <= this.range)
        .collect(Collectors.toCollection(LinkedList::new));
        Monstres temp = monstresEnRange.getFirst();
        for(Monstres m : monstresEnRange){
            if(hypothénus(distance(m.position.getX(), this.position.getX()),distance(m.position.getY(), this.position.getY()))<hypothénus(distance(temp.position.getX(), this.position.getX()),distance(temp.position.getY(), this.position.getY()))){
            temp = m;
            }
        }
        return temp;
    }
}

