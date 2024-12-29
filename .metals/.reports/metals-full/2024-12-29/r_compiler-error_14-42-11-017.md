file:///C:/Users/eliot/Documents/Université/L2/PO/PROJET/clone%20ntm/Projet_TowerDefense/src/ZoneCarte.java
### java.util.NoSuchElementException: next on empty iterator

occurred in the presentation compiler.

presentation compiler configuration:
Scala version: 3.3.3
Classpath:
<HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\scala-lang\scala3-library_3\3.3.3\scala3-library_3-3.3.3.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\scala-lang\scala-library\2.13.12\scala-library-2.13.12.jar [exists ]
Options:



action parameters:
uri: file:///C:/Users/eliot/Documents/Université/L2/PO/PROJET/clone%20ntm/Projet_TowerDefense/src/ZoneCarte.java
text:
```scala
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader; 
import java.io.IOException;


public class ZoneCarte {
    public Point2D center = new Point2D(350,350);
    public Point2D halfDist = new Point2D(350,350); 
    
    public void dessineCase(Point2D xy, int taille, Color couleur){ 
        // taille : largeur du carré 
        // x et y : coordonées du centre du carré 
        int x = xy.getX();
        int y = xy.getY();
        int t = taille/2 ; 

        StdDraw.setPenColor(couleur);
        StdDraw.filledSquare(x, y, t); 

        StdDraw.setPenColor(Color.BLACK);
        StdDraw.line(x-t, y-t, x+t, y-t) ; 
        StdDraw.line(x-t, y-t, x-t, y+t) ;
        StdDraw.line(x+t, y+t, x+t, y-t) ; 
        StdDraw.line(x+t, y+t, x-t, y+t) ;
    }

    public int hauteurMap(String nomFichier) throws IOException{
        int hauteur = 0;

        try(BufferedReader br = new BufferedReader(new FileReader(nomFichier))) {
            while (br.readLine() != null){
                hauteur++; 
            }
        }
        return hauteur; 
    }

    public int largeurMap(String nomFichier) throws IOException{
        int largeur = 0 ; 

        try(BufferedReader br = new BufferedReader(new FileReader(nomFichier))){
            String ligne = br.readLine();
            if (ligne != null){
                largeur = ligne.length() ; 
            }
        }

        return largeur ; 
    }

    public int calculerTailleCases(String nomFichier) throws IOException{
        int hauteurMap = hauteurMap(nomFichier) ; 
        int largeurMap = largeurMap(nomFichier) ;

       if (hauteurMap >= largeurMap){
        return 700 / hauteurMap ; // 700 étant la taille du côté du carré de notre zone "Carte"
       }
       else {
        return 700 / largeurMap ;
       }
    }

    public Point2D coordoneesDepart(String nomFichier) throws IOException{ 
        // La fonction renvoie les coordonées du centre de la première case (en haut à gauche),
        // en fonction du nombre de cases et de leurs disposition

        // Coordonées point haut gauche = 

        int tailleMap = 700; 
        int tailleCases = calculerTailleCases(nomFichier) ; 
        int largeurCarte = largeurMap(nomFichier) ; 


        // |---|---------------|----|
        // temp1     cases      temp1 
        int temp1 = (700 - largeurCarte * tailleCases) / 2;  


        return new Point2D(0, 0) ; 
    }

    


    public void dessineTerrain(){
        // Dessine toute les cases du terrain en fonction d'une map du dossier ressources.
    } 


    public void dessineCarte(){
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.rectangle(center.getX(), center.getY(), halfDist.getX(), halfDist.getY());
    }

}

```



#### Error stacktrace:

```
scala.collection.Iterator$$anon$19.next(Iterator.scala:973)
	scala.collection.Iterator$$anon$19.next(Iterator.scala:971)
	scala.collection.mutable.MutationTracker$CheckedIterator.next(MutationTracker.scala:76)
	scala.collection.IterableOps.head(Iterable.scala:222)
	scala.collection.IterableOps.head$(Iterable.scala:222)
	scala.collection.AbstractIterable.head(Iterable.scala:933)
	dotty.tools.dotc.interactive.InteractiveDriver.run(InteractiveDriver.scala:168)
	scala.meta.internal.pc.MetalsDriver.run(MetalsDriver.scala:45)
	scala.meta.internal.pc.PcCollector.<init>(PcCollector.scala:44)
	scala.meta.internal.pc.PcSemanticTokensProvider$Collector$.<init>(PcSemanticTokensProvider.scala:61)
	scala.meta.internal.pc.PcSemanticTokensProvider.Collector$lzyINIT1(PcSemanticTokensProvider.scala:61)
	scala.meta.internal.pc.PcSemanticTokensProvider.Collector(PcSemanticTokensProvider.scala:61)
	scala.meta.internal.pc.PcSemanticTokensProvider.provide(PcSemanticTokensProvider.scala:90)
	scala.meta.internal.pc.ScalaPresentationCompiler.semanticTokens$$anonfun$1(ScalaPresentationCompiler.scala:110)
```
#### Short summary: 

java.util.NoSuchElementException: next on empty iterator