public class Minion extends Monstres{
    public Minion(){
        this.name = "Minion";
        this.pdv = 10;
        this.atk = 3;
        this.atkspeed = 0;
        this.range  = 0;
        this.elem = new Element(Type.None);
        this.speed = 1;
        this.reward = 1;
    }

    private Tours cible(){
        return null;
    }
}
