abstract class Entites {
    protected String name;
    protected double pdv;
    protected double atk;
    protected double atkspeed;
    protected double range;
    protected Element elem;
    protected Point2D position;
   
    public Point2D getPosition() {
        return position;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }

    
    public Entites (){
        
    }

    public double getPdv() {
        return pdv;
    }

    public void setPdv(double pdv) {
        this.pdv = pdv;
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

    public String getName() {
        return name;
    }

    public boolean estTué(){
        return this.getPdv()==0;
    }

    public double hypothénus(int x, int y){
      return Math.sqrt(x*x + y*y);
    }

    public int distance(int x1, int x2){
        return Math.abs(x1-x2);
        
    }
}
