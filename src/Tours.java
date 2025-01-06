import java.util.LinkedList;

/**
 * Classe abstraite `Tours` qui représente une tour dans le jeu.
 * Hérite de la classe `Entites` et ajoute des attributs spécifiques aux tours,
 * comme le coût d'achat et la liste des cibles potentielles.
 */
abstract class Tours extends Entites {

    /** Coût d'achat de la tour. */
    protected int cost;

    /** Liste des monstres à portée de la tour. */
    protected LinkedList<Monstres> cibles;

    /**
     * Constructeur par défaut de la classe `Tours`.
     * Initialise une tour sans paramètres.
     */
    public Tours() {
        this.cibles = new LinkedList<>();
    }

    // --- GETTERS ---

    /**
     * Retourne le coût de la tour.
     *
     * @return Le coût d'achat de la tour.
     */
    public int getCost() {
        return cost;
    }

    /**
     * Dessine l'aspect visuel de la tour sur l'interface.
     *
     * @param p La position de la tour (de type {@link Point2D}).
     * @param taille La taille du carré qui représente la tour.
     */
    protected void drawVisuel(Point2D p, double taille) {
        StdDraw.setPenColor(this.elem.getCouleur());
        StdDraw.filledSquare(p.getX(), p.getY(), taille);  // Dessine un carré coloré représentant la tour
    }

    public void render(){
        drawVisuel(this.position, 0.5);
    }
}

