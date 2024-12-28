import java.util.Comparator;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class EarthCaster extends Tours{
    public EarthCaster(){
        this.name = "EarthCaster";
        this.pdv = 50;
        this.atk = 7;
        this.atkspeed = 0.5;
        this.range = 2.5;
        this.elem = new Element(Type.Earth);
        this.cost = 100;
    }

    private Monstres cible(){
        return this.cibles.stream().filter(p->hypothénus(distance(p.position.getX(),this.position.getX()), distance(p.position.getY(),this.position.getY())) <= this.range).max(Comparator.comparingDouble(Monstres::getPdv)).orElse(null);
    }
}
