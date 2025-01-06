/**
 * Classe abstraite `CombatManager` qui gère la logique de combat entre un attaquant et une cible.
 * Cette classe définit les mécanismes de calcul des dégâts, d'application des dégâts,
 * et prend en compte les interactions élémentaires (faiblesses et résistances).
 */
public abstract class CombatManager {
    
    /** Élément de l'attaquant (par exemple, feu, eau, etc.). */
    protected Element attaquantElem;
    
    /** Élément de la cible (par exemple, feu, eau, etc.). */
    protected Element cibleElem;
    
    /** Points d'attaque de l'attaquant. */
    protected double attaquantAtk;
    
    /** Points de vie de la cible. */
    protected double ciblePdv;

    /**
     * Constructeur de la classe `CombatManager`.
     * Initialise les éléments de l'attaquant et de la cible, ainsi que les points d'attaque et de vie.
     *
     * @param attaquantElem L'élément de l'attaquant.
     * @param attaquantAtk Les points d'attaque de l'attaquant.
     * @param cibleElem L'élément de la cible.
     * @param ciblePdv Les points de vie de la cible.
     */
    public CombatManager(Element attaquantElem, double attaquantAtk, Element cibleElem, double ciblePdv) {
        this.attaquantElem = attaquantElem;
        this.attaquantAtk = attaquantAtk;
        this.cibleElem = cibleElem;
        this.ciblePdv = ciblePdv;
    }

    /**
     * Calcule les dégâts infligés par l'attaquant à la cible en fonction du multiplicateur d'éléments.
     *
     * @return Le montant des dégâts calculés.
     */
    protected double calculateDamage() {
        double damage = attaquantAtk * damageMult();
        return damage;
    }

    /**
     * Applique les dégâts calculés à la cible.
     * Si les points de vie de la cible deviennent inférieurs à 0, ils sont ramenés à 0.
     */
    public void dealDamage() {
        double damage = calculateDamage();
        ciblePdv -= damage;
        if (ciblePdv < 0) {
            ciblePdv = 0;
        }
    }

    /**
     * Calcule le multiplicateur de dégâts en fonction des interactions élémentaires :
     * - Si l'élément de l'attaquant est une faiblesse de l'élément de la cible, le multiplicateur est de 1.5.
     * - Si l'élément de l'attaquant est une résistance de la cible, le multiplicateur est de 0.5.
     * - Sinon, le multiplicateur est de 1.0.
     *
     * @return Le multiplicateur des dégâts.
     */
    protected double damageMult() {
        if (cibleElem.getFaiblesse().contains(attaquantElem.getElement())) {
            return 1.5;
        } else if (cibleElem.getResistance() == attaquantElem.getElement()) {
            return 0.5;
        } else {
            return 1.0;
        }
    }

    /**
     * Retourne les points de vie restants de la cible après l'application des dégâts.
     *
     * @return Les points de vie restants de la cible.
     */
    public double getCiblePdv() {
        return ciblePdv;
    }
}

