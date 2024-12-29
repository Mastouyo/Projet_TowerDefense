file:///C:/Users/eliot/Documents/Université/L2/PO/PROJET/clone%20ntm/Projet_TowerDefense/src/InterfaceJeu.java
### java.util.NoSuchElementException: next on empty iterator

occurred in the presentation compiler.

presentation compiler configuration:
Scala version: 3.3.3
Classpath:
<HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\scala-lang\scala3-library_3\3.3.3\scala3-library_3-3.3.3.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\scala-lang\scala-library\2.13.12\scala-library-2.13.12.jar [exists ]
Options:



action parameters:
offset: 959
uri: file:///C:/Users/eliot/Documents/Université/L2/PO/PROJET/clone%20ntm/Projet_TowerDefense/src/InterfaceJeu.java
text:
```scala
import java.awt.Color;
import java.io.IOException;




public class InterfaceJeu {
    
    private ZoneInfoJeu zoneInfoJeu; 
    private ZoneInfoJoueur zoneInfoJoueur; 
    private ZoneBoutique zoneBoutique; 
    private ZoneCarte zoneCarte; 
    public Point2D center =new Point2D(500,350);
    public Point2D halfDist= new Point2D(500,350);

    public InterfaceJeu(){
        //Création et affichage de la fenêtre de base 
        StdDraw.setCanvasSize(1024, 720);
        StdDraw.setXscale(-12, 1012);
        StdDraw.setYscale(-10, 710);
        StdDraw.enableDoubleBuffering();

        zoneBoutique = new ZoneBoutique();
        zoneCarte = new ZoneCarte();
        zoneInfoJeu = new ZoneInfoJeu();
        zoneInfoJoueur = new ZoneInfoJoueur();
    }

    public void afficheJeu() throws IOException{
        zoneBoutique.dessineBoutique();
        zoneInfoJeu.dessineInfoJoueur();
        zoneInfoJoueur.dessineInfoJou@@eur();
        zoneCarte.dessineCarte();

        /* 
        String cheminMap1 = "resources/maps/10-10.mtp" ;
        int tailleCases = zoneCarte.calculerTailleCases(cheminMap1);
        System.out.println(tailleCases) ; 
        */ 

        Point2D test = new Point2D(50, 50) ; 
        // Case testCase = new Case(, 50, test) ;
        


        StdDraw.show();
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
	scala.meta.internal.pc.HoverProvider$.hover(HoverProvider.scala:34)
	scala.meta.internal.pc.ScalaPresentationCompiler.hover$$anonfun$1(ScalaPresentationCompiler.scala:368)
```
#### Short summary: 

java.util.NoSuchElementException: next on empty iterator