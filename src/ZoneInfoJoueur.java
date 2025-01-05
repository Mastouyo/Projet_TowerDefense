import java.awt.Color;
import java.awt.Font;

public class ZoneInfoJoueur {
    private Point2D center = new Point2D(856, 641);  // Position centrale
    private Point2D halfDist = new Point2D(144, 25);  // Dimensions de la zone
    private Player player;

    public ZoneInfoJoueur(Player player) {
        this.player = player;
    }

    // Méthode pour dessiner les informations du joueur
    public void dessineInfoJoueur() {
        StdDraw.setPenColor(Color.BLACK);  // Bordure noire
        StdDraw.rectangle(center.getX(), center.getY(), halfDist.getX(), halfDist.getY());  // Dessine la zone

        // Argent du joueur
        drawCoin(15);  // Dessine l'icône de la pièce
        StdDraw.setPenColor(new Color(212, 175, 55));  // Couleur dorée
        Font font = new Font("sans serif", Font.PLAIN, 20);  // Police de texte
        StdDraw.setFont(font);
        StdDraw.text(center.getX() - 80, center.getY(), String.valueOf(player.getMoney()));  // Affiche l'argent

        // Vie du joueur
        drawHeart(15);  // Dessine l'icône du cœur
        StdDraw.text(center.getX() + 80, center.getY(), String.valueOf(player.getPdv()));  // Affiche les points de vie
    }

    // Méthode pour dessiner la pièce
    public void drawCoin(double radius) {
        StdDraw.setPenColor(new Color(212, 175, 55));  // Couleur dorée
        StdDraw.filledCircle(center.getX() - 120, center.getY(), radius);  // Dessine la pièce
        StdDraw.setPenColor(new Color(192, 192, 192));  // Couleur argentée
        StdDraw.filledCircle(center.getX() - 120, center.getY(), 0.7 * radius);  // Dessine l'intérieur de la pièce
    }

    // Méthode pour dessiner le cœur
    public void drawHeart(double halfHeight) {
        StdDraw.setPenColor(new Color(223, 75, 95));  // Couleur du cœur
        double x = center.getX() + 120;  // Position du cœur
        double[] listX = new double[]{
            x, x - halfHeight, x - halfHeight, x - 0.66 * halfHeight, x - 0.33 * halfHeight,
            x, x + 0.33 * halfHeight, x + 0.66 * halfHeight, x + halfHeight, x + halfHeight
        };
        double[] listY = new double[]{
            center.getY() - halfHeight, center.getY(), center.getY() + 0.5 * halfHeight, 
            center.getY() + halfHeight, center.getY() + halfHeight, 
            center.getY() + 0.5 * halfHeight, center.getY() + halfHeight, 
            center.getY() + halfHeight, center.getY() + 0.5 * halfHeight, center.getY()
        };
        StdDraw.filledPolygon(listX, listY);  // Dessine le cœur
    }
}

