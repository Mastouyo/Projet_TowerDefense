file:///C:/Users/eliot/Documents/Université/L2/PO/PROJET/clone%20ntm/Projet_TowerDefense/src/Case.java
### java.util.NoSuchElementException: next on empty iterator

occurred in the presentation compiler.

presentation compiler configuration:
Scala version: 3.3.3
Classpath:
<HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\scala-lang\scala3-library_3\3.3.3\scala3-library_3-3.3.3.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\scala-lang\scala-library\2.13.12\scala-library-2.13.12.jar [exists ]
Options:



action parameters:
offset: 512
uri: file:///C:/Users/eliot/Documents/Université/L2/PO/PROJET/clone%20ntm/Projet_TowerDefense/src/Case.java
text:
```scala
import java.awt.Color;
enum Types {
    Spawn,
    Base,
    Route,
    Constructible, 
    Decor, 
}
public class Case {
   
    

    private Types type; // S = spawn, B = base, R = route, C = constructible, X = decor
    private int taille ;
    private Point2D centre; // centre de la case 
    private boolean libre; // Si une tour occupe ou non la case (par défaut libre)

    public Case(Types type, int taille, Point2D centre){
        this.type = type ;
        this.taille = taille ;@@ 
        this.centre = centre ;
        this.libre = true ; 
    }


    public void dessineCase(){
        int x = this.centre.getX() ; 
        int y = this.centre.getY() ; 
        int t = this.taille / 2 ; 

        switch (this.type) {
            case Spawn : StdDraw.setPenColor(Color.RED);
            case Base : StdDraw.setPenColor(Color.ORANGE) ; 
            case Route : StdDraw.setPenColor(194, 178, 128) ; 
            case Constructible : StdDraw.setPenColor(Color.LIGHT_GRAY);
            case Decor : StdDraw.setPenColor(11, 102, 35);
        }
        StdDraw.filledSquare(x, y, t);

        StdDraw.setPenColor(Color.BLACK);
        StdDraw.line(x-t, y-t, x+t, y-t) ; 
        StdDraw.line(x-t, y-t, x-t, y+t) ;
        StdDraw.line(x+t, y+t, x+t, y-t) ; 
        StdDraw.line(x+t, y+t, x-t, y+t) ;
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