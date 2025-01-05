public class CombatManagerMonstres extends CombatManager {
    private Monstres attaquant;
    private Tours cible;

    public CombatManagerMonstres(Monstres attaquant, Tours cible) {
        super(attaquant.getElem(), attaquant.getAtk(), cible.getElem(), cible.getPdv());
        this.attaquant = attaquant;
        this.cible = cible;
    }

    @Override
    public void dealDamage() {
        super.dealDamage();
        cible.setPdv(getCiblePdv());
        System.out.println("Le monstre " + attaquant.getClass().getSimpleName() + " a infligé des dégâts à la tour " + cible.getName());
    }
}

