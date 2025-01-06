import java.util.Comparator;

/**
 * Classe `WaterBrute` qui représente un monstre de type "Brute d'Eau".
 * Hérite de la classe `Monstres` et dispose de caractéristiques spécifiques.
 * Le "WaterBrute" est un monstre avec une attaque moyenne, une bonne résistance, et un comportement spécifique pour cibler les tours avec les points de vie les plus faibles.
 */
public class WaterBrute extends Monstres {

    /**
     * Constructeur de la classe `WaterBrute`.
     * Initialise un monstre "WaterBrute" avec des valeurs spécifiques pour ses attributs.
     *
     * @param map La carte (de type {@link Carte}) sur laquelle le "WaterBrute" évolue.
     */
    public WaterBrute(Carte map) {
        this.name = "WaterBrute";         // Nom du monstre
        this.pdv = 30;                    // Points de vie du monstre
        this.atk = 5;                     // Points d'attaque du monstre
        this.atkspeed = 1;                // Vitesse d'attaque du monstre
        this.range = 3;                   // Portée d'attaque du monstre
        this.elem = new Element(Type.Water);  // Élément "Eau"
        this.speed = 1;                   // Vitesse de déplacement du monstre
        this.reward = 3;                  // Récompense en cas de défaite du monstre
        this.map = map;                   // Carte sur laquelle le monstre est placé
    }

    /**
     * Détermine la cible de l'attaque du "WaterBrute".
     * Ce monstre cible la tour avec le moins de points de vie dans sa portée d'attaque.
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

