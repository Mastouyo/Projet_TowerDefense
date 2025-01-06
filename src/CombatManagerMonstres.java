/**
 * Classe `CombatManagerMonstres` qui gère le combat entre un monstre attaquant et une tour cible.
 * Cette classe hérite de `CombatManager` et redéfinit la méthode `dealDamage` pour appliquer les dégâts spécifiques aux monstres.
 */
public class CombatManagerMonstres extends CombatManager {

    /** Monstre attaquant. */
    private Monstres attaquant;

    /** Tour cible. */
    private Tours cible;

    /**
     * Constructeur de la classe `CombatManagerMonstres`.
     * Initialise un gestionnaire de combat entre un monstre et une tour.
     *
     * @param attaquant Le monstre attaquant.
     * @param cible La tour cible.
     */
    public CombatManagerMonstres(Monstres attaquant, Tours cible) {
        super(attaquant.getElem(), attaquant.getAtk(), cible.getElem(), cible.getPdv());
        this.attaquant = attaquant;
        this.cible = cible;
    }

    /**
     * Applique les dégâts du monstre à la tour cible.
     * Appelle la méthode `dealDamage` de la classe parente pour calculer et appliquer les dégâts,
     * puis met à jour les points de vie de la tour cible et affiche un message indiquant l'action effectuée.
     */
    @Override
    public void dealDamage() {
        super.dealDamage();  // Applique les dégâts en utilisant la méthode parent.
        cible.setPdv(getCiblePdv());  // Met à jour les points de vie de la tour cible.
        System.out.println("Le monstre " + attaquant.getClass().getSimpleName() + " a infligé des dégâts à la tour " + cible.getName());
    }
}


