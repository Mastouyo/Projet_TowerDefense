import java.util.LinkedList;
import java.util.stream.Collectors;

public class FireGrognard extends Monstres{
    public FireGrognard(Carte map){
        this.name = "FireGrognard";
        this.pdv = 1;
        this.atk = 7;
        this.atkspeed = 2;
        this.range  = 3;
        this.elem = new Element(Type.Fire);
        this.speed = 2;
        this.reward = 1;
        this.map=map;
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
