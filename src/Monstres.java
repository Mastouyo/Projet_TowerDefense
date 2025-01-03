import java.util.LinkedList;


public abstract class Monstres extends Entites {
    protected double speed; // Vitesse de déplacement
    protected int reward; // Récompense donnée au joueur lorsqu'il est tué
    protected LinkedList<Tours> cibles; // Tours potentielles à attaquer

    public Monstres() {
        this.cibles = new LinkedList<>();
    }

    // Mise à jour de la position et du comportement
    public void update(double deltaTime, Player player) {
        // Déplacer le monstre sur son chemin
        Point2D nextPosition = calculateNextPosition(deltaTime);
        this.position = nextPosition;

        // Vérifier si le monstre a atteint la base
        if (hasReachedBase()) {
            player.takeDamage(this); // Inflige des dégâts au joueur
            this.pdv = 0; // Le monstre est considéré comme détruit
        }
    }

    // Vérifie si le monstre est toujours vivant
    public boolean isAlive() {
        return this.pdv > 0;
    }

    // Calcule la prochaine position en fonction de la vitesse
    private Point2D calculateNextPosition(double deltaTime) {
        // Implémentez ici la logique pour avancer sur le chemin (par exemple, vers la prochaine case)
        double nextX = this.position.getX() + this.speed * deltaTime;
        double nextY = this.position.getY(); // Exemple : mouvement uniquement sur l'axe X
        return new Point2D((int)(nextX), (int)(nextY));
    }

    // Vérifie si le monstre a atteint la base du joueur
    private boolean hasReachedBase() {
        // Implémentez ici la logique pour vérifier si la position actuelle correspond à la base
        // Exemple fictif : considérer que la base est en (0, 0)
        return this.position.getX() <= 0 && this.position.getY() <= 0;
    }
}

