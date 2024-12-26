import java.math

public class Archer extends Tours{
    
    public Archer(){
    this.name = "Archer";
        this.pdv = 30;
        this.atk = 5;
        this.atkspeed = 1;
        this.range = 2;
        this.elem = new Element(Type.None);
        this.cost = 20;
    }

    private Monstres cible(){
        for(Monstres m : this.cibles){
            int x = m.position.getX();
            int y = m.position.getY();
            if(maths.sqr(x*x + y*y) <= this.range){

            }
        }
    }
    
}
