public class Player {
    private int pdv;
    private int money;

    public Player (){
        this.pdv = 100;
        this.money = 50;
    }

    public boolean aPerdu(){
        return this.pdv==0;
    }

    public void takeDamage(Monstres m){
        this.pdv -=m.getAtk();
     }

    
    public void recompense(Monstres m){
        if(m.estTué()){
            this.money +=m.reward;
        }
    }

    // Achète une tour si le joueur a assez d'argent
    public boolean acheterTour(Tours t) {
        if (this.money >= t.getCost()) {
            this.money -= t.getCost();
            return true; // Achat réussi
        } else {
            System.out.println("Pas assez d'argent pour acheter cette tour !");
            return false; // Achat échoué
        }
    }

    public boolean construireTour(Tours t, Point2D position, Carte carte) {
        if (acheterTour(t)) { // Vérifie si l'achat est possible
            if (carte.placerTour(t, position)) { // Vérifie si la position est constructible
                System.out.println("Tour placée en " + position);
                return true;
            } else {
                System.out.println("Impossible de placer une tour ici !");
                this.money += t.getCost(); // Rembourse l'argent si placement échoué
                return false;
            }
        }
        return false;
    }

    public int getPdv() {
        return pdv;
    }

    public int getMoney() {
        return money;
    }
    
     // Rendu graphique des informations du joueur
     public void render() {
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.text(100, 650, "PdV : " + this.pdv);
        StdDraw.setPenColor(StdDraw.YELLOW);
        StdDraw.text(100, 600, "Money : " + this.money);
    }
}
