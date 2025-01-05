public class CombatManagerTours extends CombatManager {
    private Tours attaquant;
    private Monstres cible;

    public CombatManagerTours(Tours attaquant, Monstres cible) {
        super(attaquant.getElem(), attaquant.getAtk(), cible.getElem(), cible.getPdv());
        this.attaquant = attaquant;
        this.cible = cible;
    }

    @Override
    public void dealDamage() {
        super.dealDamage();
        cible.setPdv(getCiblePdv());
        System.out.println("La tour " + attaquant.getName() + " a infligé des dégâts à " + cible.getClass().getSimpleName());
    }
}

