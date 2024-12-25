enum Element {Fire, Water, Wind, Earth, None}

abstract class Entites {
    protected String name;
    protected int pdv;
    protected int atk;
    protected double atkspeed;
    protected double range;
    protected Element elem;

    public Entites (){
        
    }

    public int getPdv() {
        return pdv;
    }


    public int getAtk() {
        return atk;
    }

    public double getAtkspeed() {
        return atkspeed;
    }

    public double getRange() {
        return range;
    }

    public Element getElem() {
        return elem;
    }

    public boolean estTué(){
        return this.getPdv()==0;
    }


}
