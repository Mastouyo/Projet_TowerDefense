import java.util.LinkedList;
import java.util.stream.Collectors;

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

    // private Monstres cible(){
    //     LinkedList<Monstres> monstreEnRange = new LinkedList();
    //     for(Monstres m : this.cibles){
    //         int x = m.position.getX();
    //         int y = m.position.getY();
    //         if(Math.sqrt(x*x + y*y) <= this.range){
    //             monstreEnRange.add(m);
    //         }
    //     }
    //     return monstreEnRange.getLast();
    // }
    

    private Monstres cible(){
        LinkedList<Monstres> monstresEnRange = this.cibles.stream().filter(p->hypothénus(distance(p.position.getX(),this.position.getX()), distance(p.position.getY(),this.position.getY())) <= this.range).collect(Collectors.toCollection(LinkedList::new));
        if(monstresEnRange.isEmpty()){
            return null;
        }
        else{
            return monstresEnRange.getLast();
        }
    }

    
}
