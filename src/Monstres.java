import java.util.LinkedList;

public class Monstres extends Entites {
    protected Carte map;
    protected double speed; // Vitesse de déplacement (cases/seconde)
    protected int reward; // Récompense donnée au joueur lorsqu'il est tué
    protected LinkedList<Tours> cibles; // Tours potentielles à attaquer
    public LinkedList<Case> getChemin() {
        return chemin;
    }

    protected LinkedList<Case> chemin; // Chemin à suivre
    protected int currentCaseIndex; // Index de la case actuelle sur le chemin

    public Monstres() {
        this.cibles = new LinkedList<>();
        this.chemin = new LinkedList<>();
        this.currentCaseIndex = 0;
    }

    /**
     * Met à jour la position du monstre en fonction de son déplacement.
     * Gère le déplacement sur le chemin en tenant compte de la vitesse et du temps écoulé.
     * @param deltaTimeSec Le temps écoulé en secondes depuis la dernière mise à jour.
     * @param player Référence au joueur pour gérer l'arrivée du monstre à la base.
     */
    public void update(double deltaTimeSec, Player player) {
        if (pdv <= 0) return; // Ne pas mettre à jour si le monstre est mort

        if (chemin == null || chemin.isEmpty()) {
            System.err.println("Erreur : Chemin non défini pour le monstre " + name);
            return;
        }

        if (currentCaseIndex >= chemin.size()) {
            System.out.println(name + " a atteint la base !");
            player.reduirePdv((int) atk); // Inflige des dégâts au joueur
            pdv = 0; // Le monstre est détruit
            return;
        }

        double cellSize = 700.0 / map.getTailleCase(); // Taille d'un carreau
        double distanceToTravel = speed * cellSize * deltaTimeSec; // Distance à parcourir en pixels

        while (distanceToTravel > 0 && currentCaseIndex < chemin.size()) {
            Case prochaineCase = chemin.get(currentCaseIndex);
            if (prochaineCase == null) {
                System.err.println("Erreur : Case suivante invalide dans le chemin !");
                break;
            }
            Point2D prochainePosition = prochaineCase.getCentre();
            double dx = prochainePosition.getX() - position.getX();
            double dy = prochainePosition.getY() - position.getY();
            double distanceToTarget = Math.sqrt(dx * dx + dy * dy);

            if (distanceToTarget < 1e-3) {
                currentCaseIndex++;
                continue;
            }

            if (distanceToTravel >= distanceToTarget) {
                // Atteint la prochaine case
                position.setLocation(prochainePosition.getX(), prochainePosition.getY());
                currentCaseIndex++;
                distanceToTravel -= distanceToTarget; // Réduire la distance restante
            } else {
                // Déplacement partiel vers la cible
                position.setLocation(
                    (position.getX() + distanceToTravel * dx / distanceToTarget),
                    (position.getY() + distanceToTravel * dy / distanceToTarget)
                );
                distanceToTravel = 0; // Fin du déplacement pour cette frame
            }
        }

        // Vérification pour éviter NullPointerException
        if (currentCaseIndex < chemin.size()) {
            double currentRow = ((350 + 350 - position.getY()) / cellSize);
            double currentCol = ((position.getX() - (350 - 350)) / cellSize);
            Case currentTile = map.getTile(currentRow, currentCol);
            if (currentTile != null && !currentTile.getCentre().equals(this.getPosition())) {
                this.setPosition(currentTile.getCentre());
            }
        }
    }

    // Vérifie si le monstre est encore vivant
    public boolean isAlive() {
        return this.pdv > 0;
    }

    // Définit le chemin que le monstre doit suivre
    public void setChemin(LinkedList<Case> chemin) {
        if (chemin == null || chemin.isEmpty()) {
            System.err.println("Erreur : Chemin vide ou nul passé au monstre " + name);
        } else {
            this.chemin = chemin;
            this.currentCaseIndex = 0; // Réinitialise l'index du chemin
            this.position = chemin.get(0).getCentre(); // Initialise la position au début du chemin
        }
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
            StdDraw.filledCircle((float) position.getX(), (float) position.getY(), 10); // Taille du monstre
        }
    }

    // Débogage pour afficher des informations sur la position
    public void debugPosition() {
        System.out.println("Position actuelle du monstre " + name + " : " + position);
        System.out.println("Prochaine case cible : " + (currentCaseIndex < chemin.size() ? chemin.get(currentCaseIndex) : "Aucune"));
    }

    public Carte getMap() {
        return map;
    }
}
