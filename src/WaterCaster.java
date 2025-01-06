import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * Classe `WaterCaster` qui représente une tour de type "Caster d'Eau".
 * Hérite de la classe `Tours` et dispose de caractéristiques spécifiques.
 * Cette tour utilise l'élément "Eau" pour attaquer les ennemis et cible le dernier monstre entré dans sa portée d'attaque.
 */
public class WaterCaster extends Tours {

    /**
     * Constructeur de la classe `WaterCaster`.
     * Initialise une tour "WaterCaster" avec des valeurs spécifiques pour ses attributs.
     */
    public WaterCaster() {
        this.name = "WaterCaster";        // Nom de la tour
        this.pdv = 30;                    // Points de vie de la tour
        this.atk = 3;                     // Points d'attaque de la tour
        this.atkspeed = 1;                // Vitesse d'attaque de la tour
        this.range = 4;                   // Portée d'attaque de la tour
        this.elem = new Element(Type.Water);  // Élément "Eau"
        this.cost = 50;                   // Coût de construction de la tour
    }

    /**
     * Détermine le monstre cible dans la portée d'attaque de la tour.
     * Cette méthode sélectionne le dernier monstre entré dans la portée de la tour.
     *
     * @return Le dernier monstre (de type {@link Monstres}) dans la portée de la tour,
     *         ou `null` s'il n'y a aucun monstre à portée.
     */
    private Monstres cible() {
        LinkedList<Monstres> monstresEnRange = this.cibles.stream()
                .filter(p -> hypothénus(distance(p.position.getX(), this.position.getX()),
                                        distance(p.position.getY(), this.position.getY())) <= this.range)
                .collect(Collectors.toCollection(LinkedList::new));

        if (monstresEnRange.isEmpty()) {
            return null;  // Retourne null s'il n'y a aucun monstre à portée
        } else {
            return monstresEnRange.getLast();  // Retourne le dernier monstre entré dans la portée
        }
    }
}

