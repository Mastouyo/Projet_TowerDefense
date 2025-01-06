import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * Classe Boss qui hérite de la classe Monstres.
 * Représente un ennemi de type "Boss" avec des caractéristiques spécifiques.
 */
public class Boss extends Monstres {

    /**
     * Constructeur de la classe Boss.
     * Initialise les attributs du Boss avec des valeurs spécifiques.
     *
     * @param map La carte (de type {@link Carte}) sur laquelle le Boss évolue.
     */
    public Boss(Carte map) {
        this.name = "Boss"; // Nom du monstre
        this.pdv = 150;     // Points de vie du Boss
        this.atk = 100;     // Points d'attaque du Boss
        this.atkspeed = 10.0; // Vitesse d'attaque du Boss
        this.range = 2.0;   // Portée d'attaque du Boss
        this.elem = new Element(Type.Fire); // Élément associé (Feu)
        this.speed = 0.5;   // Vitesse de déplacement du Boss
        this.reward = 100;  // Récompense en cas de victoire contre le Boss
        this.map = map;     // Carte sur laquelle le Boss est placé
    }

    /**
     * Détermine la tour la plus proche parmi les cibles disponibles dans la portée du Boss.
     *
     * @return La tour la plus proche (de type {@link Tours}) si elle existe, sinon `null`.
     */
    private Tours cible() {
        // Filtre les tours dans la portée du Boss
        LinkedList<Tours> toursEnRange = this.cibles.stream()
                .filter(p -> hypothénus(distance(p.position.getX(), this.position.getX()), 
                                        distance(p.position.getY(), this.position.getY())) <= this.range)
                .collect(Collectors.toCollection(LinkedList::new));

        // Vérifie qu'il existe au moins une tour dans la portée
        if (toursEnRange.isEmpty()) {
            return null;  // Si aucune tour n'est en portée, retourne null
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

        return temp; // Retourne la tour la plus proche
    }

}

