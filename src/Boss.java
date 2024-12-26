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
}
