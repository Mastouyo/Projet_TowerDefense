public class CombatManagerMonstres {
    private Monstres attaquant;
    private Tours cible;

    public CombatManagerMonstres(Tours tour, Monstres monstre){
        this.attaquant = monstre;
        this.cible = tour;
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
