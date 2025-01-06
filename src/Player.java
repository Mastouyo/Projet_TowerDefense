import java.util.LinkedList;

/**
 * Classe `Player` qui représente le joueur.
 * Le joueur possède des points de vie (PdV) et de l'argent pour acheter et construire des tours.
 * La classe gère les interactions du joueur, comme les achats, la gestion des récompenses et la mise à jour de son état.
 */
public class Player {

    /** Points de vie du joueur. */
    private int pdv;

    /** Argent disponible du joueur. */
    private int money;

    /**
     * Constructeur par défaut de la classe `Player`.
     * Initialise le joueur avec des points de vie et de l'argent de départ.
     */
    public Player() {
        this.pdv = 100;  // Points de vie initiaux
        this.money = 50; // Argent initial
    }

    /**
     * Vérifie si le joueur a perdu.
     *
     * @return `true` si les points de vie sont inférieurs ou égaux à 0, `false` sinon.
     */
    public boolean aPerdu() {
        return this.pdv <= 0;
    }

    /**
     * Ajoute des points de vie au joueur.
     *
     * @param montant Le nombre de points de vie à ajouter.
     */
    public void ajouterPdv(int montant) {
        this.pdv += montant;
        System.out.println("Soin reçu : +" + montant + " PdV. PdV actuels : " + this.pdv);
    }

    /**
     * Réduit les points de vie du joueur.
     *
     * @param montant Le nombre de points de vie à retirer.
     */
    public void reduirePdv(int montant) {
        this.pdv = Math.max(0, this.pdv - montant); // Empêche les PdV d'être négatifs
        System.out.println("Le joueur a perdu " + montant + " PdV. PdV restants : " + this.pdv);
    }

    /**
     * Récompense le joueur lorsqu'un monstre est tué.
     *
     * @param m Le monstre tué (de type {@link Monstres}).
     */
    public void recompense(Monstres m) {
        if (m.estTué()) {
            this.money += m.reward;
            System.out.println("Récompense de " + m.reward + " ajoutée. Argent actuel : " + this.money);
        }
    }

    /**
     * Achète une tour si le joueur a assez d'argent.
     *
     * @param t La tour à acheter (de type {@link Tours}).
     * @return `true` si l'achat est réussi, `false` sinon.
     */
    public boolean acheterTour(Tours t) {
        if (this.money >= t.getCost()) {
            this.money -= t.getCost();
            System.out.println("Tour " + t.getName() + " achetée pour " + t.getCost() + ". Argent restant : " + this.money);
            return true;
        } else {
            System.out.println("Pas assez d'argent pour acheter la tour " + t.getName() + " !");
            return false;
        }
    }

    /**
     * Construit une tour sur une case constructible.
     *
     * @param t La tour à construire.
     * @param position La position où construire la tour.
     * @param carte La carte du jeu.
     * @return `true` si la construction est réussie, `false` sinon.
     */
    public boolean construireTour(Tours t, Point2D position, Carte carte) {
        if (acheterTour(t)) { // Vérifie si l'achat est possible
            Case caseSouhaitee = carte.caseSelonCoordonees(position);
            if (caseSouhaitee != null && caseSouhaitee.getType() == TypesCases.Constructible) {
                carte.placerTour(t, position); // Ajoute la tour sur la carte
                System.out.println("Tour " + t.getName() + " placée en " + position);
                return true;
            } else {
                System.out.println("Impossible de placer la tour ici !");
                this.money += t.getCost(); // Rembourse l'argent si le placement échoue
                return false;
            }
        }
        return false;
    }

    /**
     * Met à jour l'état du joueur en gérant les monstres atteignant la base.
     *
     * @param deltaTime Le temps écoulé depuis la dernière mise à jour.
     * @param monstres La liste des monstres sur la carte.
     * @param carte La carte du jeu.
     */
    public void update(double deltaTime, LinkedList<Monstres> monstres, Carte carte) {
        LinkedList<Monstres> monstresAyantAtteintBase = new LinkedList<>();

        for (Monstres monstre : monstres) {
            if (monstre.getPosition().equals(carte.getBase())) { // Vérifie si le monstre a atteint la base
                reduirePdv((int) monstre.getAtk()); // Réduit les points de vie du joueur en fonction de l'attaque du monstre
                monstresAyantAtteintBase.add(monstre); // Ajoute le monstre à la liste des monstres à retirer
                monstre.setPdv(0); // Considère le monstre comme détruit après avoir infligé des dégâts
                System.out.println("Le monstre " + monstre.getClass().getSimpleName() + " a atteint la base.");
            }
        }

        // Supprime les monstres qui ont atteint la base
        monstres.removeAll(monstresAyantAtteintBase);
        System.out.println("État actuel : PdV = " + this.pdv + ", Argent = " + this.money);
    }

    /**
     * Ajoute de l'argent au joueur (par exemple via un bonus ou une récompense).
     *
     * @param montant Le montant d'argent à ajouter.
     */
    public void ajouterArgent(int montant) {
        this.money += montant;
        System.out.println("Bonus de " + montant + " ajouté. Argent total : " + this.money);
    }

    /**
     * Réduit l'argent disponible du joueur.
     *
     * @param montant Le montant à retirer.
     */
    public void reduireArgent(int montant) {
        if (montant <= this.money) {
            this.money -= montant;
            System.out.println("Montant de " + montant + " retiré. Argent restant : " + this.money);
        } else {
            System.out.println("Fonds insuffisants pour retirer " + montant);
        }
    }

    // --- GETTERS ET SETTERS ---

    /**
     * Retourne les points de vie du joueur.
     *
     * @return Les points de vie du joueur.
     */
    public int getPdv() {
        return pdv;
    }

    /**
     * Définit les points de vie du joueur.
     *
     * @param pdv Les nouveaux points de vie du joueur.
     */
    public void setPdv(int pdv) {
        this.pdv = pdv;
    }

    /**
     * Retourne l'argent disponible du joueur.
     *
     * @return L'argent disponible.
     */
    public int getMoney() {
        return money;
    }
}
