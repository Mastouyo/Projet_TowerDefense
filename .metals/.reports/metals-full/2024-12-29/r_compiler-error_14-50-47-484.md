file:///C:/Users/eliot/Documents/Université/L2/PO/PROJET/clone%20ntm/Projet_TowerDefense/src/Element.java
### java.util.NoSuchElementException: next on empty iterator

occurred in the presentation compiler.

presentation compiler configuration:
Scala version: 3.3.3
Classpath:
<HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\scala-lang\scala3-library_3\3.3.3\scala3-library_3-3.3.3.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\scala-lang\scala-library\2.13.12\scala-library-2.13.12.jar [exists ]
Options:



action parameters:
uri: file:///C:/Users/eliot/Documents/Université/L2/PO/PROJET/clone%20ntm/Projet_TowerDefense/src/Element.java
text:
```scala
import java.awt.Color;
import java.util.ArrayList;

enum Type {Fire, Water, Wind, Earth, None}

public class Element {
    private Type element;
    private Color couleur; 
    private Type resistance;
    private ArrayList<Type> faiblesse;

    public Element(Type element){
        this.faiblesse = new ArrayList<>();
        this.element = element;
        if (element == Type.Fire){
            this.couleur = new Color(184, 22, 1);
            this.resistance = Type.Earth;
            this.faiblesse.add(Type.Water);
        }
        else if(element == Type.Earth){
            this.couleur = new Color(0, 167, 15);
            this.resistance = Type.Wind;
            this.faiblesse.add(Type.Fire);
        }
        else if(element == Type.Wind){
            this.couleur = new  Color(242, 211, 0);
            this.resistance = Type.Water;
            this.faiblesse.add(Type.Earth);
        }
        else if(element == Type.Wind){
            this.couleur = new  Color(6, 0, 160);
            this.resistance = Type.Fire;
            this.faiblesse.add(Type.Wind);
        }
        else if(element == Type.None){
            this.couleur = Color.BLACK;
            this.resistance = null;
            this.faiblesse.add(Type.Wind);
            this.faiblesse.add(Type.Fire);
            this.faiblesse.add(Type.Earth);
            this.faiblesse.add(Type.Water);
        }
    }

    public Type getElement() {
        return element;
    }

    public Color getCouleur() {
        return couleur;
    }

    public Type getResistance() {
        return resistance;
    }

    public ArrayList<Type> getFaiblesse() {
        return faiblesse;
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