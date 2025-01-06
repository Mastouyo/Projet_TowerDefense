import java.util.LinkedList;

public class Monstres extends Entites {
    protected Carte map;
    protected double speed; // Vitesse de déplacement (cases/seconde)
    protected int reward; // Récompense donnée au joueur lorsqu'il est tué
    protected LinkedList<Tours> cibles; // Tours potentielles à attaquer
    protected boolean enChemin = true;
    
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

    
    public void update(double deltaTimeSec, Player player) {
        // Initialiser la position actuelle du monstre
        Point2D positionActuelle = this.position;
        double distanceParcourue = this.speed;
    
        // Vérifier si le monstre atteint la prochaine case sur le chemin
        while (distanceParcourue > 0 && !chemin.isEmpty()) {
            Case prochaineCase = chemin.peek();
            Point2D positionProchaineCase = prochaineCase.getCentre();
            double distanceVersProchaineCase = positionActuelle.distance(positionProchaineCase);
    
            if (distanceParcourue >= distanceVersProchaineCase) {
                // Le monstre atteint la prochaine case
                distanceParcourue -= distanceVersProchaineCase;
                positionActuelle = positionProchaineCase;
                chemin.poll(); // Retirer la case atteinte du chemin
    
                // Vérifier si le monstre atteint la base
                if (prochaineCase.getType() == TypesCases.Base) {
                    player.setPdv((int)player.getPdv() - (int)this.atk);; // Gérer l'arrivée du monstre à la base
                    this.enChemin = false;
                    return; // Arrêter la mise à jour
                }
            } else {
                // Le monstre se déplace vers la prochaine case
                double ratio = distanceParcourue / distanceVersProchaineCase;
                double newX = positionActuelle.getX() + ratio * (positionProchaineCase.getX() - positionActuelle.getX());
                double newY = positionActuelle.getY() + ratio * (positionProchaineCase.getY() - positionActuelle.getY());
                positionActuelle = new Point2D(newX, newY);
                distanceParcourue = 0; // Le déplacement est terminé pour cette mise à jour
            }
        }

        
        // Mettre à jour la position du monstre
        this.position = positionActuelle;
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

    public boolean isEnChemin(){
        return enChemin;
    }
}
