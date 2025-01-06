/**
 * Classe abstraite `Entites` qui représente une entité générale du jeu.
 * Cette classe regroupe les attributs et méthodes communs aux entités telles que les tours et les monstres.
 */
abstract class Entites {

    /** Nom de l'entité. */
    protected String name;

    /** Points de vie de l'entité. */
    protected double pdv;

    /** Points d'attaque de l'entité. */
    protected double atk;

    /** Vitesse d'attaque de l'entité. */
    protected double atkspeed;

    /** Portée d'attaque de l'entité. */
    protected double range;

    /** Élément de l'entité (par exemple, Feu, Eau, Terre, etc.). */
    protected Element elem;

    /** Position de l'entité sur la carte (coordonnées 2D). */
    protected Point2D position;

    /**
     * Constructeur par défaut de la classe `Entites`.
     * Initialise une entité sans paramètres.
     */
    public Entites() {
    }

    /**
     * Retourne la position de l'entité.
     *
     * @return La position de l'entité sous forme d'un objet {@link Point2D}.
     */
    public Point2D getPosition() {
        return position;
    }

    /**
     * Définit la position de l'entité.
     *
     * @param position La nouvelle position de l'entité sous forme d'un objet {@link Point2D}.
     */
    public void setPosition(Point2D position) {
        this.position = position;
    }

    /**
     * Retourne les points de vie de l'entité.
     *
     * @return Les points de vie de l'entité.
     */
    public double getPdv() {
        return pdv;
    }

    /**
     * Définit les points de vie de l'entité.
     *
     * @param pdv Les nouveaux points de vie de l'entité.
     */
    public void setPdv(double pdv) {
        this.pdv = pdv;
    }

    /**
     * Retourne les points d'attaque de l'entité.
     *
     * @return Les points d'attaque de l'entité.
     */
    public double getAtk() {
        return atk;
    }

    /**
     * Retourne la vitesse d'attaque de l'entité.
     *
     * @return La vitesse d'attaque de l'entité.
     */
    public double getAtkspeed() {
        return atkspeed;
    }

    /**
     * Retourne la portée d'attaque de l'entité.
     *
     * @return La portée d'attaque de l'entité.
     */
    public double getRange() {
        return range;
    }

    /**
     * Retourne l'élément de l'entité.
     *
     * @return L'élément de l'entité (de type {@link Element}).
     */
    public Element getElem() {
        return elem;
    }

    /**
     * Retourne le nom de l'entité.
     *
     * @return Le nom de l'entité.
     */
    public String getName() {
        return name;
    }

    /**
     * Vérifie si l'entité est tuée.
     * Une entité est considérée comme tuée si ses points de vie sont égaux à 0.
     *
     * @return `true` si l'entité est tuée, `false` sinon.
     */
    public boolean estTué() {
        return this.getPdv() == 0;
    }

    /**
     * Calcule l'hypoténuse entre deux valeurs, correspondant à la distance euclidienne entre deux points.
     *
     * @param x La différence sur l'axe X.
     * @param y La différence sur l'axe Y.
     * @return La distance euclidienne entre les deux points.
     */
    public double hypothénus(double x, double y) {
        return Math.sqrt(x * x + y * y);
    }

    /**
     * Calcule la distance absolue entre deux coordonnées sur un même axe.
     *
     * @param x1 La première coordonnée.
     * @param x2 La deuxième coordonnée.
     * @return La distance absolue entre les deux coordonnées.
     */
    public double distance(double x1, double x2) {
        return Math.abs(x1 - x2);
    }
}
