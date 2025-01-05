public class Point2D {
    private double x;
    private double y;

    public Point2D(double x , double y){
        this.x=x;
        this.y=y;
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }

    public void setLocation(double x, double y){
        this.x=x;
        this.y=y;
    }

    @Override
    public String toString(){
        return "(" + this.x + ", " + this.y + ")" ; 
    } 

}
