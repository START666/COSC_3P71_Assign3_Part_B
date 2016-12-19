/**
 * Created by Xuhao Chen on 2016/12/19.
 */
public class Velocity {
    private int x;  //direction
    private double value;
    public Velocity(int x, double value){
        setX(x);
        this.value = value;
    }
    public double getSpeed(){
        return x * value;
    }
    public void setX(int x){
        if(x>0) this.x = 1;
        else if(x==0) this.x=0;
        else if(x<0) this.x=-1;
    }
    public int getX(){
        return this.x;
    }
    public void setValue(double value){
        this.value = value;
    }

    public double getValue(){
        return this.value;
    }
}
