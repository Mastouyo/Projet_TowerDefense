import java.awt.Color;
import java.util.ArrayList;

enum Type {Fire, Water, Wind, Earth, None}

/**
 * Classe `Element` qui représente l'affinité élémentaire d'une entité (tour ou monstre).
 * Chaque élément possède une couleur associée, une résistance et une ou plusieurs faiblesses.
 */
public class Element {

    /** Type de l'élément (Feu, Eau, Vent, Terre, Aucun). */
    private Type element;

    /** Couleur associée à l'élément. */
    private Color couleur;

    /** Élément contre lequel cet élément est résistant. */
    private Type resistance;

    /** Liste des éléments contre lesquels cet élément est faible. */
    private ArrayList<Type> faiblesse;

    /**
     * Constructeur de la classe `Element`.
     * Initialise l'élément avec son type, sa couleur, sa résistance et sa liste de faiblesses.
     *
     * @param element Le type de l'élément (par exemple {@link Type#Fire}, {@link Type#Water}, etc.).
     */
    public Element(Type element) {
        this.faiblesse = new ArrayList<>();
        this.element = element;

        switch (element) {
            case Fire:
                this.couleur = new Color(184, 22, 1);  // Couleur rougeâtre pour le Feu
                this.resistance = Type.Earth;          // Résistant contre la Terre
                this.faiblesse.add(Type.Water);        // Faible contre l'Eau
                break;
            case Earth:
                this.couleur = new Color(0, 167, 15);  // Couleur verte pour la Terre
                this.resistance = Type.Wind;           // Résistant contre le Vent
                this.faiblesse.add(Type.Fire);         // Faible contre le Feu
                break;
            case Wind:
                this.couleur = new Color(242, 211, 0); // Couleur jaune pour le Vent
                this.resistance = Type.Water;          // Résistant contre l'Eau
                this.faiblesse.add(Type.Earth);        // Faible contre la Terre
                break;
            case Water:
                this.couleur = new Color(6, 0, 160);   // Couleur bleue pour l'Eau
                this.resistance = Type.Fire;           // Résistant contre le Feu
                this.faiblesse.add(Type.Wind);         // Faible contre le Vent
                break;
            case None:
                this.couleur = Color.BLACK;            // Couleur noire pour un élément neutre
                this.resistance = null;                // Aucun élément contre lequel il est résistant
                this.faiblesse.add(Type.Wind);
                this.faiblesse.add(Type.Fire);
                this.faiblesse.add(Type.Earth);
                this.faiblesse.add(Type.Water);         // Faible contre tous les éléments
                break;
        }
    }

    /**
     * Retourne le type de l'élément.
     *
     * @return Le type de l'élément (par exemple {@link Type#Fire}, {@link Type#Earth}, etc.).
     */
    public Type getElement() {
        return element;
    }

    /**
     * Retourne la couleur associée à l'élément.
     *
     * @return La couleur (de type {@link Color}) associée à l'élément.
     */
    public Color getCouleur() {
        return couleur;
    }

    /**
     * Retourne l'élément contre lequel cet élément est résistant.
     *
     * @return Le type de l'élément contre lequel cet élément est résistant,
     *         ou `null` s'il n'a aucune résistance.
     */
    public Type getResistance() {
        return resistance;
    }

    /**
     * Retourne la liste des éléments contre lesquels cet élément est faible.
     *
     * @return Une liste d'éléments (de type {@link ArrayList}) représentant les faiblesses de cet élément.
     */
    public ArrayList<Type> getFaiblesse() {
        return faiblesse;
    }
}