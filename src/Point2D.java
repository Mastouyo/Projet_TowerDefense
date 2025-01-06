/**
 * Classe `Point2D` qui représente un point en 2D avec des coordonnées `x` et `y`.
 * Cette classe permet de stocker et manipuler des positions dans un espace bidimensionnel.
 */
public class Point2D {

    /** Coordonnée `x` du point. */
    private double x;

    /** Coordonnée `y` du point. */
    private double y;

    /**
     * Constructeur de la classe `Point2D`.
     * Initialise le point avec des coordonnées données.
     *
     * @param x La coordonnée `x` du point.
     * @param y La coordonnée `y` du point.
     */
    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // --- GETTERS ET SETTERS ---

    /**
     * Retourne la coordonnée `x` du point.
     *
     * @return La coordonnée `x`.
     */
    public double getX() {
        return x;
    }

    /**
     * Retourne la coordonnée `y` du point.
     *
     * @return La coordonnée `y`.
     */
    public double getY() {
        return y;
    }

    /**
     * Définit une nouvelle position pour le point.
     *
     * @param x La nouvelle coordonnée `x`.
     * @param y La nouvelle coordonnée `y`.
     */
    public void setLocation(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Retourne une représentation textuelle du point.
     *
     * @return Une chaîne de caractères représentant le point sous la forme `(x, y)`.
     */
    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }

    public double distance(Point2D p){
        return Math.sqrt((this.x-p.x)*(this.x-p.x) + (this.y-p.y)*(this.y-p.y));
    }
}

