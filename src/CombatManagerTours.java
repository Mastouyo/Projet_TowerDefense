public class CombatManagerTours {
    private Monstres cible;
    private Tours attaquant;

    public CombatManagerTours(Tours tour, Monstres monstre){
        this.attaquant = tour;
        this.cible = monstre;
    }

    private void dealDamage(){
        cible.pdv -= attaquant.atk*damageMult();
    }

    private double damageMult (){
        Element elementA = attaquant.getElem();
        Element elementC = cible.getElem();
        if(elementC.getFaiblesse().contains(elementA.getElement())){
            return 1.5;
        }
        else if (elementC.getResistance() == elementA.getElement()){
            return 0.5;
        }
        else{
            return 1.0;
        }
    }
}
