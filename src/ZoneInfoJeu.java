import java.awt.Color;
import java.awt.Font;
import java.util.LinkedList;

/**
 * Classe `ZoneInfoJeu` qui représente la zone d'affichage des informations générales sur la partie.
 * Cette zone affiche des informations telles que le niveau actuel et la progression dans les vagues.
 */
public class ZoneInfoJeu {

    /** Niveau actuel de la partie. */
    private Level level;

    /** Liste des niveaux disponibles. */
    private LinkedList<Level> levels;

    /** Index du niveau actuel (base 0). */
    private int levelIndex;

    /** Point central de la zone d'affichage. */
    private Point2D center = new Point2D(856, 688);

    /** Demi-dimensions de la zone d'affichage. */
    private Point2D halfDist = new Point2D(144, 12);

    /**
     * Constructeur de la classe `ZoneInfoJeu`.
     * Initialise la zone d'affichage des informations du jeu.
     *
     * @param level Le niveau actuel.
     * @param levels La liste des niveaux disponibles.
     * @param levelIndex L'index du niveau actuel.
     */
    public ZoneInfoJeu(Level level, LinkedList<Level> levels, int levelIndex) {
        this.level = level;
        this.levels = levels;
        this.levelIndex = levelIndex;
    }

    /**
     * Met à jour les informations affichées sur l'état actuel du jeu.
     *
     * @param level Le niveau actuel mis à jour.
     * @param levelIndex L'index du niveau actuel.
     */
    public void update(Level level) {
        this.level = level;  // Met à jour l'objet `Level`
    }

    /**
     * Dessine la zone contenant les informations sur le jeu (niveau et progression des vagues).
     */
    public void dessineInfoJeu() {
        // Effacer la zone précédente
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.filledRectangle(center.getX(), center.getY(), halfDist.getX(), halfDist.getY());

        // Dessin du cadre de la zone
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.rectangle(center.getX(), center.getY(), halfDist.getX(), halfDist.getY());

        // Définition de la police d'écriture
        Font font = new Font("sans serif", Font.PLAIN, 20);
        StdDraw.setFont(font);

        // Affichage des informations du niveau et des vagues
        String levelText = "Lvl : " + (levelIndex + 1) + "/" + levels.size();
        String waveText = "Wave : " + (level.getCurrentWaveIndex() + 1) + "/" + level.getWaves().size();

        StdDraw.text(center.getX() - 100, center.getY() - 5, levelText);
        StdDraw.text(center.getX() + 80, center.getY() - 5, waveText);
    }

    /**
     * Retourne l'index du niveau actuel.
     *
     * @return L'index du niveau actuel.
     */
    public int getLevelIndex() {
        return levelIndex;
    }

    /**
     * Retourne le niveau actuel.
     *
     * @return Le niveau actuel.
     */
    public Level getLevel() {
        return level;
    }
}
