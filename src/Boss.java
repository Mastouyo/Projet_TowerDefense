import java.util.LinkedList;
import java.util.stream.Collectors;

public class Boss extends Monstres{
    public Boss(){
        this.name = "Boss";
        this.pdv = 150;
        this.atk = 100;
        this.atkspeed = 10.0;
        this.range  = 2.0;
        this.elem = new Element(Type.Fire);
        this.speed = 0.5;
        this.reward = 100;
    }
    
    private Tours cible(){
        LinkedList<Tours> toursEnRange = this.cibles.stream().filter(p->hypothénus(distance(p.position.getX(),this.position.getX()), distance(p.position.getY(),this.position.getY())) <= this.range).collect(Collectors.toCollection(LinkedList::new));
        Tours temp = toursEnRange.getFirst();
        for(Tours t : toursEnRange){
            if(hypothénus(distance(t.position.getX(), this.position.getX()),distance(t.position.getY(), this.position.getY()))<hypothénus(distance(temp.position.getX(), this.position.getX()),distance(temp.position.getY(), this.position.getY()))){
            temp = t;
            }
        }
        return temp;
    }

}
