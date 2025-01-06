import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * Classe `WindCaster` qui représente une tour de type "Caster de Vent".
 * Hérite de la classe `Tours` et dispose de caractéristiques spécifiques.
 * Cette tour utilise l'élément "Vent" pour attaquer les ennemis et cible le monstre le plus proche dans sa portée.
 */
public class WindCaster extends Tours {

    /**
     * Constructeur de la classe `WindCaster`.
     * Initialise une tour "WindCaster" avec des valeurs spécifiques pour ses attributs.
     */
    public WindCaster() {
        this.name = "WindCaster";        // Nom de la tour
        this.pdv = 30;                   // Points de vie de la tour
        this.atk = 5;                    // Points d'attaque de la tour
        this.atkspeed = 1.5;             // Vitesse d'attaque de la tour
        this.range = 6;                  // Portée d'attaque de la tour
        this.elem = new Element(Type.Wind);  // Élément "Vent"
        this.cost = 50;                  // Coût de construction de la tour
    }

    /**
     * Détermine le monstre cible dans la portée d'attaque de la tour.
     * Cette méthode sélectionne le monstre le plus proche de la tour.
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

        if (monstresEnRange.isEmpty()) {
            return null;  // Retourne null s'il n'y a aucun monstre à portée
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


