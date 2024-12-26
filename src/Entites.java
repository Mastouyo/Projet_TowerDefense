abstract class Entites {
    protected String name;
    protected double pdv;
    protected double atk;
    protected double atkspeed;
    protected double range;
    protected Element elem;
    protected Point2D position;

    public Entites (){
        
    }

    public double getPdv() {
        return pdv;
    }


    public double getAtk() {
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
