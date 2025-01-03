public class Player {
    private int pdv;
    private int money;

    public Player (){
        this.pdv = 100;
        this.money = 50;
    }

    public boolean aPerdu(){
        return this.pdv==0;
    }

    public void takeDamage(Monstres m){
        this.pdv -=m.getAtk();
     }

    
    public void recompense(Monstres m){
        if(m.estTué()){
            this.money +=m.reward;
        }
    }

    //faire une fonction pour achter la tour seulement si le cout est inferieur ou egal a l'argent.
    public void depenseTour(Tours t){
        this.money -= t.cost;
    }

    public int getPdv() {
        return pdv;
    }

    public int getMoney() {
        return money;
    }
    //faire une fonction pour construire des tours (trouver comment avoir la position de la souris)
}
