/**
 * Created by Xuhao Chen on 2016/12/19.
 */
public class Particle1D {

    public double x;
    public Velocity1D v;
    public double fitness;
    public double pBestX, pBestFitness;

    public Particle1D(double x){
        this.fitness = Double.MAX_VALUE;
        this.x = x;
        this.pBestX = x;
        this.pBestFitness = Double.MAX_VALUE;
        this.v = new Velocity1D(0,0);  //initial velocity is 0
    }

    public void evaluate(){
        fitness =  Math.pow(x, 4) - 2 * Math.pow(x, 3);    //y = x^4 - 2x^3
        fitness = -25 * Math.exp(-0.2 * Math.sqrt( 0.5 * (x * x) )) - Math.exp( 0.5*(Math.cos(2 * Math.PI * x)) )  + Math.E + 20;   //Ackley function
//        fitness = Math.pow(x, 2) + 2 * x + 1;    //y = x^2 + 2x + 1
//        fitness = Math.sin(x);    //y = sin(x)
    }

    public void updatePBest(){
        if(fitness<pBestFitness){
            pBestFitness = fitness;
            pBestX = x;
        }
    }

    public void setLocation(double x){
        this.x = x;
    }

    public void setVelocity(int x, double value){
        this.v = new Velocity1D(x,value);
    }
    public void setVelocity(Velocity1D v){
        setVelocity(v.getX(),v.getValue());
    }
}
