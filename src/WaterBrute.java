import java.util.Comparator;

public class WaterBrute extends Monstres{
    public WaterBrute(){
        this.name = "WaterBrute";
        this.pdv = 30;
        this.atk = 5;
        this.atkspeed = 1;
        this.range  = 3;
        this.elem = new Element(Type.Water);
        this.speed = 1;
        this.reward = 3;
    }

    private Tours cible(){
        return this.cibles.stream().filter(p->hypothénus(distance(p.position.getX(),this.position.getX()), distance(p.position.getY(),this.position.getY())) <= this.range).min(Comparator.comparingDouble(Tours::getPdv)).orElse(null);
    }
}
