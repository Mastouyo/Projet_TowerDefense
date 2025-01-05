public abstract class CombatManager {
    protected Element attaquantElem;
    protected Element cibleElem;
    protected double attaquantAtk;
    protected double ciblePdv;

    public CombatManager(Element attaquantElem, double attaquantAtk, Element cibleElem, double ciblePdv) {
        this.attaquantElem = attaquantElem;
        this.attaquantAtk = attaquantAtk;
        this.cibleElem = cibleElem;
        this.ciblePdv = ciblePdv;
    }

    // Calcule les dégâts
    protected double calculateDamage() {
        double damage = attaquantAtk * damageMult();
        return damage;
    }

    // Applique les dégâts à la cible
    public void dealDamage() {
        double damage = calculateDamage();
        ciblePdv -= damage;
        if (ciblePdv < 0) {
            ciblePdv = 0;
        }
    }

    // Calcule le multiplicateur des dégâts en fonction des éléments
    protected double damageMult() {
        if (cibleElem.getFaiblesse().contains(attaquantElem.getElement())) {
            return 1.5;
        } else if (cibleElem.getResistance() == attaquantElem.getElement()) {
            return 0.5;
        } else {
            return 1.0;
        }
    }

    // Retourne les PV de la cible après l'attaque
    public double getCiblePdv() {
        return ciblePdv;
    }
}
