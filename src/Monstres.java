import java.util.LinkedList;

public abstract class Monstres extends Entites {
    protected double speed; // Vitesse de déplacement (cases/seconde)
    protected int reward; // Récompense donnée au joueur lorsqu'il est tué
    protected LinkedList<Tours> cibles; // Tours potentielles à attaquer
    protected LinkedList<Case> chemin; // Chemin à suivre
    protected int currentCaseIndex; // Index de la case actuelle sur le chemin

    public Monstres() {
        this.cibles = new LinkedList<>();
        this.chemin = new LinkedList<>();
        this.currentCaseIndex = 0;
    }

    // Mise à jour de la position du monstre
    public void update(double deltaTime, Player player) {
        if (pdv <= 0) return; // Ne pas mettre à jour si le monstre est mort

        if (currentCaseIndex >= chemin.size()) {
            // Le monstre atteint la base
            System.out.println(name + " a atteint la base !");
            player.reduirePdv((int) atk); // Inflige des dégâts au joueur
            pdv = 0; // Le monstre est détruit
        } else {
            avancer(deltaTime); // Déplacement
        }
    }

    // Fonction pour avancer sur le chemin
    private void avancer(double deltaTime) {
        if (!chemin.isEmpty() && currentCaseIndex < chemin.size()) {
            Point2D prochaineCase = chemin.get(currentCaseIndex).getCentre();
            double dx = prochaineCase.getX() - position.getX();
            double dy = prochaineCase.getY() - position.getY();
            double distance = Math.sqrt(dx * dx + dy * dy);
    
            if (distance <= speed * deltaTime) {
                // Si la distance est inférieure à la vitesse, avancer à la prochaine case
                position = prochaineCase;
                currentCaseIndex++;
            } else {
                // Déplacer progressivement vers la prochaine case
                double ratio = (speed * deltaTime) / distance;
                position.setLocation(
                   (int) (position.getX() + dx * ratio),
                   (int) (position.getY() + dy * ratio)
                );
            }
        }
    }
    

    // Vérifie si le monstre est encore vivant
    public boolean isAlive() {
        return this.pdv > 0;
    }

    // Définit le chemin que le monstre doit suivre
    public void setChemin(LinkedList<Case> chemin) {
        this.chemin = chemin;
        this.currentCaseIndex = 0; // Réinitialise l'index du chemin
    }

    // Gère la récompense lorsque le monstre est tué
    public void onDeath(Player player) {
        if (isAlive()) return; // Pas de récompense si le monstre est encore en vie
        player.recompense(this);
        System.out.println(name + " a été tué. Récompense ajoutée !");
    }

    // Dessine le monstre sur l'écran
    public void render() {
        if (isAlive()) {
            StdDraw.setPenColor(this.elem.getCouleur());
            StdDraw.filledCircle(this.position.getX(), this.position.getY(), 10); // Taille du monstre
        }
    }
}

