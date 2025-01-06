import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * Classe `EarthBrute` qui représente un monstre de type "Brute de Terre".
 * Hérite de la classe `Monstres` et dispose de caractéristiques spécifiques.
 */
public class EarthBrute extends Monstres {

    /**
     * Constructeur de la classe `EarthBrute`.
     * Initialise un monstre "EarthBrute" avec des valeurs spécifiques pour ses attributs.
     *
     * @param map La carte (de type {@link Carte}) sur laquelle le monstre évolue.
     */
    public EarthBrute(Carte map) {
        this.name = "EarthBrute";  // Nom du monstre
        this.pdv = 30;             // Points de vie du monstre
        this.atk = 5;              // Points d'attaque du monstre
        this.atkspeed = 1;         // Vitesse d'attaque du monstre
        this.range = 3;            // Portée d'attaque du monstre
        this.elem = new Element(Type.Earth);  // Élément "Terre"
        this.speed = 1;            // Vitesse de déplacement du monstre
        this.reward = 3;           // Récompense en cas de défaite du monstre
        this.map = map;            // Carte sur laquelle le monstre est placé
    }

    /**
     * Détermine la tour la plus proche dans la portée d'attaque du monstre.
     * Filtre les tours présentes dans la portée du monstre et sélectionne la plus proche.
     *
     * @return La tour la plus proche (de type {@link Tours}) si elle existe, sinon `null`.
     */
    private Tours cible() {
        // Filtre les tours en portée
        LinkedList<Tours> toursEnRange = this.cibles.stream()
                .filter(p -> hypothénus(distance(p.position.getX(), this.position.getX()),
                                        distance(p.position.getY(), this.position.getY())) <= this.range)
                .collect(Collectors.toCollection(LinkedList::new));

        // Si aucune tour n'est en portée, retourne `null`
        if (toursEnRange.isEmpty()) {
            return null;
        }

        // Initialise la première tour comme référence
        Tours temp = toursEnRange.getFirst();

        // Recherche de la tour la plus proche en fonction de la distance
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
