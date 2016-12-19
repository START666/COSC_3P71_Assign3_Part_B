import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Xuhao Chen on 2016/12/19.
 */
public class PSO {

    private int particleNum;
    private int MAX_GEN;
    private ArrayList<Particle1D> particle1DList;
    private double gBestX,gBestFitness;

    public PSO(int particleNum,int MAX_GEN){
        this.particleNum = particleNum;
        this.MAX_GEN = MAX_GEN;
        gBestFitness = Double.MAX_VALUE;
    }

    public void solve(){
        particle1DList = new ArrayList<Particle1D>();
        //init particles randomly
        Random random = new Random();
        for(int i=0;i<particleNum;i++){
            Particle1D p = new Particle1D(random.nextDouble());
            particle1DList.add(p);
        }

        for(int i=0;i<MAX_GEN;i++){
            for(Particle1D p : particle1DList){
                p.evaluate();
                p.updatePBest();
            }

            //update gBest
            for(Particle1D p : particle1DList){
                if(p.fitness<gBestFitness){
                    gBestFitness = p.fitness;
                    gBestX = p.x;
                }
            }

            for(Particle1D p : particle1DList){
                updateVelocity1D(p);
                p.x += p.v.getSpeed();  //update position
            }
        }
        System.out.println("Best: " + String.format("%.5f", gBestFitness));
        System.out.println("Best location: x = "+ String.format("%.5f", gBestX));
    }

    private void updateVelocity1D(Particle1D p){
        //inertia term
        double w = 0.8;
        double iv = p.v.getSpeed() * w;

        //cognitive term
        double c1 = 1.5;
        double r1 = Math.random();
        double cv = c1 * r1 * (p.pBestX - p.x);

        //social term
        double c2 = 1.5;
        double r2 = Math.random();
        double sv = c2 * r2 * (gBestX - p.x);

        double v = iv + cv + sv;
        p.setVelocity((int) v,v);
    }

    public static void main(String[] args){
        PSO pso = new PSO(1000,1000);
        pso.solve();
    }
}
