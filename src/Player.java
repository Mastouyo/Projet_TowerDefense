import java.util.LinkedList;

public class Player {
    private int pdv; // Points de vie du joueur
    private int money; // Argent disponible

    public Player() {
        this.pdv = 100; // Points de vie initiaux
        this.money = 50; // Argent initial
    }

    // Vérifie si le joueur a perdu
    public boolean aPerdu() {
        return this.pdv <= 0;
    }

    // Ajoute des points de vie au joueur (exemple via bonus ou soin)
    public void ajouterPdv(int montant) {
        this.pdv += montant;
        System.out.println("Soin reçu : +" + montant + " PdV. PdV actuels : " + this.pdv);
    }

    // Réduit les points de vie du joueur
    public void reduirePdv(int montant) {
        this.pdv = Math.max(0, this.pdv - montant); // Empêche les PdV d'être négatifs
        System.out.println("Le joueur a perdu " + montant + " PdV. PdV restants : " + this.pdv);
    }

    // Récompense donnée lorsque le joueur tue un monstre
    public void recompense(Monstres m) {
        if (m.estTué()) {
            this.money += m.reward;
            System.out.println("Récompense de " + m.reward + " ajoutée. Argent actuel : " + this.money);
        }
    }

    // Achète une tour si le joueur a assez d'argent
    public boolean acheterTour(Tours t) {
        if (this.money >= t.getCost()) {
            this.money -= t.getCost();
            System.out.println("Tour " + t.getName() + " achetée pour " + t.getCost() + ". Argent restant : " + this.money);
            return true; // Achat réussi
        } else {
            System.out.println("Pas assez d'argent pour acheter la tour " + t.getName() + " !");
            return false; // Achat échoué
        }
    }

    // Construit une tour sur une case constructible
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

    // Mise à jour de l'état du joueur (gestion des monstres atteignant la base)
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

        // Supprimer les monstres qui ont atteint la base
        monstres.removeAll(monstresAyantAtteintBase);
        System.out.println("État actuel : PdV = " + this.pdv + ", Argent = " + this.money);
    }

    // Getter pour les points de vie
    public int getPdv() {
        return pdv;
    }

    // Setter pour les points de vie
    public void setPdv(int pdv) {
        this.pdv = pdv;
    }

    // Getter pour l'argent
    public int getMoney() {
        return money;
    }

    // Ajout d'argent (par exemple via bonus ou récompense)
    public void ajouterArgent(int montant) {
        this.money += montant;
        System.out.println("Bonus de " + montant + " ajouté. Argent total : " + this.money);
    }

    // Réduit l'argent disponible (par exemple lors d'une réparation)
    public void reduireArgent(int montant) {
        if (montant <= this.money) {
            this.money -= montant;
            System.out.println("Montant de " + montant + " retiré. Argent restant : " + this.money);
        } else {
            System.out.println("Fonds insuffisants pour retirer " + montant);
        }
    }
}

