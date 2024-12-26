abstract class Monstres extends Entites{
    protected double speed;
    protected int reward;
    public Monstres(){
        
    }

    private void recompense(){
        if(this.estTué()){
            //player.money +=reward;
        }
    }

}
