/**
 * Classe `Minion` qui représente un monstre de base du jeu.
 * Hérite de la classe `Monstres` et dispose de caractéristiques spécifiques.
 * Le "Minion" est un monstre simple avec peu de points de vie et une faible attaque.
 */
public class Minion extends Monstres {

    /**
     * Constructeur de la classe `Minion`.
     * Initialise un monstre "Minion" avec des valeurs par défaut pour ses attributs.
     *
     * @param map La carte (de type {@link Carte}) sur laquelle le "Minion" évolue.
     */
    public Minion(Carte map) {
        this.name = "Minion";         // Nom du monstre
        this.pdv = 10;                // Points de vie du monstre
        this.atk = 3;                 // Points d'attaque du monstre
        this.atkspeed = 0;            // Vitesse d'attaque du monstre (nulle)
        this.range = 0;               // Portée d'attaque du monstre (aucune)
        this.elem = new Element(Type.None);  // Élément "Aucun" (neutre)
        this.speed = 1;               // Vitesse de déplacement du monstre
        this.reward = 1;              // Récompense donnée en cas de défaite du monstre
        this.map = map;               // Carte sur laquelle le "Minion" évolue
    }

    /**
     * Détermine la cible de l'attaque du "Minion".
     * Ce type de monstre ne peut attaquer aucune tour.
     *
     * @return Toujours `null` car ce monstre n'a pas d'attaque.
     */
    private Tours cible() {
        return null;  // Le "Minion" ne cible aucune tour
    }
}

