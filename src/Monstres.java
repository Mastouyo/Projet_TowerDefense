import java.util.LinkedList;

abstract class Monstres extends Entites{
    protected double speed;
    protected int reward;
    protected LinkedList<Tours> cibles;
    public Monstres(){
        
    }

    private void recompense(){
        if(this.estTué()){
            //player.money +=reward;
        }
    }

}
