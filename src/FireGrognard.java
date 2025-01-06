import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * Classe `FireGrognard` qui représente un monstre de type "Grognard de Feu".
 * Hérite de la classe `Monstres` et dispose de caractéristiques spécifiques.
 * Ce monstre est rapide, avec une faible résistance mais une attaque relativement puissante.
 */
public class FireGrognard extends Monstres {

    /**
     * Constructeur de la classe `FireGrognard`.
     * Initialise un monstre "FireGrognard" avec des valeurs spécifiques pour ses attributs.
     *
     * @param map La carte (de type {@link Carte}) sur laquelle le monstre évolue.
     */
    public FireGrognard(Carte map) {
        this.name = "FireGrognard";  // Nom du monstre
        this.pdv = 1;                // Points de vie du monstre (très faible)
        this.atk = 7;                // Points d'attaque du monstre
        this.atkspeed = 2;           // Vitesse d'attaque du monstre (rapide)
        this.range = 3;              // Portée d'attaque du monstre
        this.elem = new Element(Type.Fire);  // Élément "Feu"
        this.speed = 2;              // Vitesse de déplacement du monstre
        this.reward = 1;             // Récompense en cas de défaite du monstre
        this.map = map;              // Carte sur laquelle le monstre est placé
    }

    /**
     * Détermine la tour cible la plus proche dans la portée d'attaque du monstre.
     * Cette méthode sélectionne la tour la plus proche parmi celles qui sont dans la portée du monstre.
     *
     * @return La tour la plus proche (de type {@link Tours}) dans la portée du monstre,
     *         ou `null` s'il n'y a aucune tour à portée.
     */
    private Tours cible() {
        // Filtre les tours dans la portée d'attaque
        LinkedList<Tours> toursEnRange = this.cibles.stream()
                .filter(p -> hypothénus(distance(p.position.getX(), this.position.getX()),
                                        distance(p.position.getY(), this.position.getY())) <= this.range)
                .collect(Collectors.toCollection(LinkedList::new));

        // Si aucune tour n'est dans la portée, retourne `null`
        if (toursEnRange.isEmpty()) {
            return null;
        }

        // Initialisation de la première tour comme référence
        Tours temp = toursEnRange.getFirst();

        // Recherche de la tour la plus proche
        for (Tours t : toursEnRange) {
            if (hypothénus(distance(t.position.getX(), this.position.getX()),
                           distance(t.position.getY(), this.position.getY())) <
                hypothénus(distance(temp.position.getX(), this.position.getX()),
                           distance(temp.position.getY(), this.position.getY()))) {
                temp = t;  // Mise à jour de la tour la plus proche
            }
        }

        return temp;  // Retourne la tour la plus proche
    }
}
