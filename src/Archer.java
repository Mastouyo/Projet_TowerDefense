import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * Classe `Archer` qui représente une tour de type "Archer".
 * Hérite de la classe `Tours` et dispose de caractéristiques spécifiques.
 * La tour "Archer" attaque le dernier monstre qui entre dans sa portée.
 */
public class Archer extends Tours {

    /**
     * Constructeur de la classe `Archer`.
     * Initialise une tour "Archer" avec des valeurs spécifiques pour ses attributs.
     */
    public Archer() {
        this.name = "Archer";        // Nom de la tour
        this.pdv = 30;               // Points de vie de la tour
        this.atk = 5;                // Points d'attaque de la tour
        this.atkspeed = 1;           // Vitesse d'attaque de la tour
        this.range = 2;              // Portée d'attaque de la tour
        this.elem = new Element(Type.None);  // Aucun élément associé (type neutre)
        this.cost = 20;              // Coût de construction de la tour
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
            return null;  // Retourne null si aucun monstre n'est à portée
        } else {
            return monstresEnRange.getLast();  // Retourne le dernier monstre entré en portée
        }
    }
}

