import java.awt.Color;

enum Type {Fire, Water, Wind, Earth, None}

public class Element {
    private Type element;
    private Color couleur; 
    private Type resistance;
    private Type faiblesse;

    public Element(Type element){
        this.element = element;
        if (element == Type.Fire){
            this.couleur = new Color(184, 22, 1);
            this.resistance = Type.Earth;
            this.faiblesse = Type.Water;
        }
        else if(element == Type.Earth){
            this.couleur = new Color(0, 167, 15);
            this.resistance = Type.Wind;
            this.faiblesse = Type.Fire;
        }
        else if(element == Type.Wind){
            this.couleur = new  Color(242, 211, 0);
            this.resistance = Type.Water;
            this.faiblesse = Type.Earth;
        }
        else if(element == Type.Wind){
            this.couleur = new  Color(6, 0, 160);
            this.resistance = Type.Fire;
            this.faiblesse = Type.Wind;
        }
        else if(element == Type.None){
            this.couleur = Color.BLACK;
            this.resistance = null;
            this.faiblesse = Type.Wind;
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

    public Type getFaiblesse() {
        return faiblesse;
    }
}
