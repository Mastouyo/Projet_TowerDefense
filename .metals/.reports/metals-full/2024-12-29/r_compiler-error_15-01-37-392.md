file:///C:/Users/eliot/Documents/Université/L2/PO/PROJET/clone%20ntm/Projet_TowerDefense/src/WindCaster.java
### java.util.NoSuchElementException: next on empty iterator

occurred in the presentation compiler.

presentation compiler configuration:
Scala version: 3.3.3
Classpath:
<HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\scala-lang\scala3-library_3\3.3.3\scala3-library_3-3.3.3.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\scala-lang\scala-library\2.13.12\scala-library-2.13.12.jar [exists ]
Options:



action parameters:
uri: file:///C:/Users/eliot/Documents/Université/L2/PO/PROJET/clone%20ntm/Projet_TowerDefense/src/WindCaster.java
text:
```scala
import java.util.LinkedList;
import java.util.stream.Collectors;

public class WindCaster extends Tours{
    public WindCaster(){
        this.name = "WindCaster";
        this.pdv = 30;
        this.atk = 5;
        this.atkspeed = 1.5;
        this.range = 6;
        this.elem = new Element(Type.Wind);
        this.cost = 50;
    }

    private Monstres cible(){
        LinkedList<Monstres> monstresEnRange = this.cibles.stream()
        .filter(p->hypothénus(distance(p.position.getX(),this.position.getX()), distance(p.position.getY(),this.position.getY())) <= this.range)
        .collect(Collectors.toCollection(LinkedList::new));
        Monstres temp = monstresEnRange.getFirst();
        for(Monstres m : monstresEnRange){
            if(hypothénus(distance(m.position.getX(), this.position.getX()),distance(m.position.getY(), this.position.getY()))<hypothénus(distance(temp.position.getX(), this.position.getX()),distance(temp.position.getY(), this.position.getY()))){
            temp = m;
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
	scala.meta.internal.pc.PcCollector.<init>(PcCollector.scala:44)
	scala.meta.internal.pc.PcSemanticTokensProvider$Collector$.<init>(PcSemanticTokensProvider.scala:61)
	scala.meta.internal.pc.PcSemanticTokensProvider.Collector$lzyINIT1(PcSemanticTokensProvider.scala:61)
	scala.meta.internal.pc.PcSemanticTokensProvider.Collector(PcSemanticTokensProvider.scala:61)
	scala.meta.internal.pc.PcSemanticTokensProvider.provide(PcSemanticTokensProvider.scala:90)
	scala.meta.internal.pc.ScalaPresentationCompiler.semanticTokens$$anonfun$1(ScalaPresentationCompiler.scala:110)
```
#### Short summary: 

java.util.NoSuchElementException: next on empty iterator