/**
 * Enumération `TypesCases` qui représente les différents types de cases sur la carte du jeu.
 * Chaque type de case a un rôle spécifique dans le jeu :
 * - `Spawn` : Case où les monstres apparaissent.
 * - `Base` : Case représentant la base du joueur à défendre.
 * - `Route` : Case empruntée par les monstres pour se déplacer vers la base.
 * - `Constructible` : Case sur laquelle le joueur peut construire une tour.
 * - `Non_Constructible` : Case sur laquelle aucune construction n'est possible.
 */
public enum TypesCases {
    /** Case où les monstres apparaissent. */
    Spawn,

    /** Case représentant la base du joueur à défendre. */
    Base,

    /** Case empruntée par les monstres pour se déplacer vers la base. */
    Route,

    /** Case sur laquelle le joueur peut construire une tour. */
    Constructible,

    /** Case sur laquelle aucune construction n'est possible. */
    Non_Constructible,
}
