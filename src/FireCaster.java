import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * Classe `FireCaster` qui représente une tour de type "Caster de Feu".
 * Hérite de la classe `Tours` et dispose de caractéristiques spécifiques.
 * La tour "FireCaster" attaque le monstre le plus proche dans sa portée.
 */
public class FireCaster extends Tours {

    /**
     * Constructeur de la classe `FireCaster`.
     * Initialise une tour "FireCaster" avec des valeurs spécifiques pour ses attributs.
     */
    public FireCaster() {
        this.name = "FireCaster";        // Nom de la tour
        this.pdv = 30;                   // Points de vie de la tour
        this.atk = 10;                   // Points d'attaque de la tour
        this.atkspeed = 0.5;             // Vitesse d'attaque de la tour
        this.range = 2.5;                // Portée d'attaque de la tour
        this.elem = new Element(Type.Fire);  // Élément "Feu"
        this.cost = 100;                 // Coût de construction de la tour
    }

    /**
     * Détermine le monstre cible dans la portée d'attaque de la tour.
     * Cette méthode sélectionne le monstre le plus proche parmi ceux qui sont
     * dans la portée de la tour.
     *
     * @return Le monstre le plus proche (de type {@link Monstres}) dans la portée de la tour,
     *         ou `null` s'il n'y a aucun monstre à portée.
     */
    private Monstres cible() {
        // Filtre les monstres dans la portée d'attaque
        LinkedList<Monstres> monstresEnRange = this.cibles.stream()
                .filter(p -> hypothénus(distance(p.position.getX(), this.position.getX()),
                                        distance(p.position.getY(), this.position.getY())) <= this.range)
                .collect(Collectors.toCollection(LinkedList::new));

        // Si aucun monstre n'est dans la portée, retourne `null`
        if (monstresEnRange.isEmpty()) {
            return null;
        }

        // Initialisation du premier monstre comme référence
        Monstres temp = monstresEnRange.getFirst();

        // Recherche du monstre le plus proche
        for (Monstres m : monstresEnRange) {
            if (hypothénus(distance(m.position.getX(), this.position.getX()),
                           distance(m.position.getY(), this.position.getY())) <
                hypothénus(distance(temp.position.getX(), this.position.getX()),
                           distance(temp.position.getY(), this.position.getY()))) {
                temp = m;  // Mise à jour du monstre le plus proche
            }
        }

        return temp;  // Retourne le monstre le plus proche
    }
}

