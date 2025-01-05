import java.util.Comparator;

public class WindGrognard extends Monstres{
    public WindGrognard(Carte map){
        this.name = "WindGrognard";
        this.pdv = 1;
        this.atk = 7;
        this.atkspeed = 2;
        this.range  = 5;
        this.elem = new Element(Type.Wind);
        this.speed = 2;
        this.reward = 1;
        this.map=map;
    }

    private Tours cible(){
        return this.cibles.stream().filter(p->hypothénus(distance(p.position.getX(),this.position.getX()), distance(p.position.getY(),this.position.getY())) <= this.range).min(Comparator.comparingDouble(Tours::getPdv)).orElse(null);
    }
}
