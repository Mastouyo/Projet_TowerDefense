/**
 * Classe `CombatManagerTours` qui gère le combat entre une tour attaquante et un monstre cible.
 * Cette classe hérite de `CombatManager` et redéfinit la méthode `dealDamage` pour appliquer les dégâts spécifiques des tours aux monstres.
 */
public class CombatManagerTours extends CombatManager {

    /** La tour attaquante. */
    private Tours attaquant;

    /** Le monstre cible. */
    private Monstres cible;

    /**
     * Constructeur de la classe `CombatManagerTours`.
     * Initialise un gestionnaire de combat entre une tour et un monstre.
     *
     * @param attaquant La tour qui attaque.
     * @param cible Le monstre qui est ciblé.
     */
    public CombatManagerTours(Tours attaquant, Monstres cible) {
        super(attaquant.getElem(), attaquant.getAtk(), cible.getElem(), cible.getPdv());
        this.attaquant = attaquant;
        this.cible = cible;
    }

    /**
     * Applique les dégâts de la tour au monstre cible.
     * Appelle la méthode `dealDamage` de la classe parente pour calculer et appliquer les dégâts,
     * puis met à jour les points de vie du monstre cible et affiche un message indiquant l'action effectuée.
     */
    @Override
    public void dealDamage() {
        super.dealDamage();  // Applique les dégâts en utilisant la méthode parent.
        cible.setPdv(getCiblePdv());  // Met à jour les points de vie du monstre.
        System.out.println("La tour " + attaquant.getName() + " a infligé des dégâts à " + cible.getClass().getSimpleName());
    }
}


