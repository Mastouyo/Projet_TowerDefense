import java.util.LinkedList;
import java.util.stream.Collectors;

public class EarthBrute extends Monstres{
    public EarthBrute(Carte map){
        this.name = "EarthBrute";
        this.pdv = 30;
        this.atk = 5;
        this.atkspeed = 1;
        this.range  = 3;
        this.elem = new Element(Type.Earth);
        this.speed = 1;
        this.reward = 3;
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
