file:///C:/Users/eliot/Documents/Université/L2/PO/PROJET/clone%20ntm/Projet_TowerDefense/src/EarthBrute.java
### java.util.NoSuchElementException: next on empty iterator

occurred in the presentation compiler.

presentation compiler configuration:
Scala version: 3.3.3
Classpath:
<HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\scala-lang\scala3-library_3\3.3.3\scala3-library_3-3.3.3.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\scala-lang\scala-library\2.13.12\scala-library-2.13.12.jar [exists ]
Options:



action parameters:
offset: 383
uri: file:///C:/Users/eliot/Documents/Université/L2/PO/PROJET/clone%20ntm/Projet_TowerDefense/src/EarthBrute.java
text:
```scala
import java.util.LinkedList;
import java.util.stream.Collectors;

public class EarthBrute extends Monstres{
    public EarthBrute(){
        this.name = "EarthBrute";
        this.pdv = 30;
        this.atk = 5;
        this.atkspeed = 1;
        this.range  = 3;
        this.elem = new Element(Type.Earth);
        this.speed = 1;
        this.reward = 3;
    }

   @@ private Tours cible(){
        LinkedList<Tours> monstresEnRange = this.cibles.stream().filter(p->hypothénus(distance(p.position.getX(),this.position.getX()), distance(p.position.getY(),this.position.getY())) <= this.range).collect(Collectors.toCollection(LinkedList::new));
        if(monstresEnRange.isEmpty()){
            return null;
        }
        else{
            return monstresEnRange.getLast();
        }
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