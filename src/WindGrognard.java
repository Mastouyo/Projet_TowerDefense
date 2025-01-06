import java.util.Comparator;

/**
 * Classe `WindGrognard` qui représente un monstre de type "Grognard de Vent".
 * Hérite de la classe `Monstres` et dispose de caractéristiques spécifiques.
 * Le "WindGrognard" est un monstre rapide avec une faible résistance, une attaque puissante,
 * et un comportement qui cible la tour ayant le moins de points de vie.
 */
public class WindGrognard extends Monstres {

    /**
     * Constructeur de la classe `WindGrognard`.
     * Initialise un monstre "WindGrognard" avec des valeurs spécifiques pour ses attributs.
     *
     * @param map La carte (de type {@link Carte}) sur laquelle le "WindGrognard" évolue.
     */
    public WindGrognard(Carte map) {
        this.name = "WindGrognard";        // Nom du monstre
        this.pdv = 1;                      // Points de vie du monstre (très faible)
        this.atk = 7;                      // Points d'attaque du monstre
        this.atkspeed = 2;                 // Vitesse d'attaque du monstre (rapide)
        this.range = 5;                    // Portée d'attaque du monstre
        this.elem = new Element(Type.Wind);  // Élément "Vent"
        this.speed = 2;                    // Vitesse de déplacement du monstre (rapide)
        this.reward = 1;                   // Récompense donnée en cas de défaite du monstre
        this.map = map;                    // Carte sur laquelle le monstre évolue
    }

    /**
     * Détermine la tour cible dans la portée d'attaque du "WindGrognard".
     * Cette méthode sélectionne la tour ayant les points de vie les plus faibles.
     *
     * @return La tour avec les points de vie les plus faibles (de type {@link Tours}) dans la portée du monstre,
     *         ou `null` s'il n'y a aucune tour à portée.
     */
    private Tours cible() {
        return this.cibles.stream()
                .filter(p -> hypothénus(distance(p.position.getX(), this.position.getX()),
                                        distance(p.position.getY(), this.position.getY())) <= this.range)
                .min(Comparator.comparingDouble(Tours::getPdv))  // Sélectionne la tour avec le moins de points de vie
                .orElse(null);  // Retourne `null` si aucune tour n'est à portée
    }
}

