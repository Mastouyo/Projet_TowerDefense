file:///C:/Users/eliot/Documents/Université/L2/PO/PROJET/clone%20ntm/Projet_TowerDefense/src/FireGrognard.java
### java.util.NoSuchElementException: next on empty iterator

occurred in the presentation compiler.

presentation compiler configuration:
Scala version: 3.3.3
Classpath:
<HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\scala-lang\scala3-library_3\3.3.3\scala3-library_3-3.3.3.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\scala-lang\scala-library\2.13.12\scala-library-2.13.12.jar [exists ]
Options:



action parameters:
offset: 412
uri: file:///C:/Users/eliot/Documents/Université/L2/PO/PROJET/clone%20ntm/Projet_TowerDefense/src/FireGrognard.java
text:
```scala
import java.util.LinkedList;
import java.util.stream.Collectors;

public class FireGrognard extends Monstres{
    public FireGrognard(){
        this.name = "FireGrognard";
        this.pdv = 1;
        this.atk = 7;
        this.atkspeed = 2;
        this.range  = 3;
        this.elem = new Element(Type.Fire);
        this.speed = 2;
        this.reward = 1;
    }

    
    private Tours cibl@@e(){
        LinkedList<Tours> toursEnRange = this.cibles.stream().filter(p->hypothénus(distance(p.position.getX(),this.position.getX()), distance(p.position.getY(),this.position.getY())) <= this.range).collect(Collectors.toCollection(LinkedList::new));
        Tours temp = toursEnRange.getFirst();
        for(Tours t : toursEnRange){
            if(hypothénus(distance(t.position.getX(), this.position.getX()),distance(t.position.getY(), this.position.getY()))<hypothénus(distance(temp.position.getX(), this.position.getX()),distance(temp.position.getY(), this.position.getY()))){
            temp = t;
            }
        }
        return temp;
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