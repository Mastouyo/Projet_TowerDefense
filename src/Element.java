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
        else if(element == Type.Water){
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
