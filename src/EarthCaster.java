import java.util.Comparator;

/**
 * Classe `EarthCaster` qui représente une tour de type "Caster de Terre".
 * Hérite de la classe `Tours` et dispose de caractéristiques spécifiques.
 */
public class EarthCaster extends Tours {

    /**
     * Constructeur de la classe `EarthCaster`.
     * Initialise une tour "EarthCaster" avec des valeurs spécifiques pour ses attributs.
     */
    public EarthCaster() {
        this.name = "EarthCaster";  // Nom de la tour
        this.pdv = 50;              // Points de vie de la tour
        this.atk = 7;               // Points d'attaque de la tour
        this.atkspeed = 0.5;        // Vitesse d'attaque de la tour
        this.range = 2.5;           // Portée d'attaque de la tour
        this.elem = new Element(Type.Earth);  // Élément "Terre"
        this.cost = 100;            // Coût de construction de la tour
    }

    /**
     * Détermine le monstre cible dans la portée d'attaque de la tour.
     * Cette méthode sélectionne le monstre avec les points de vie les plus élevés parmi ceux
     * qui se trouvent dans la portée d'attaque de la tour.
     *
     * @return Le monstre avec le plus de points de vie (de type {@link Monstres}) dans la portée de la tour,
     *         ou `null` s'il n'y a aucun monstre à portée.
     */
    private Monstres cible() {
        return this.cibles.stream()
                .filter(p -> hypothénus(distance(p.position.getX(), this.position.getX()),
                                        distance(p.position.getY(), this.position.getY())) <= this.range)
                .max(Comparator.comparingDouble(Monstres::getPdv))  // Sélectionne le monstre avec le plus de points de vie
                .orElse(null);  // Retourne null si aucun monstre n'est à portée
    }
}

