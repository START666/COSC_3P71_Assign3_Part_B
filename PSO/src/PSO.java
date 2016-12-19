import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Xuhao Chen on 2016/12/19.
 */
public class PSO {

    private int particleNum;
    private int MAX_GEN;
    private ArrayList<Particle> particleList;
    private double gBestX,gBestFitness;

    public PSO(int particleNum,int MAX_GEN){
        this.particleNum = particleNum;
        this.MAX_GEN = MAX_GEN;
        gBestFitness = Double.MAX_VALUE;
    }

    public void solve(){
        particleList = new ArrayList<Particle>();
        //init particles randomly
        Random random = new Random();
        for(int i=0;i<particleNum;i++){
            Particle p = new Particle(random.nextDouble());
            particleList.add(p);
        }

        for(int i=0;i<MAX_GEN;i++){
            for(Particle p : particleList){
                p.evaluate();
                p.updatePBest();
            }

            //update gBest
            for(Particle p : particleList){
                if(p.fitness<gBestFitness){
                    gBestFitness = p.fitness;
                    gBestX = p.x;
                }
            }

            for(Particle p : particleList){
                updateVelocity(p);
                p.x += p.v.getSpeed();  //update position
            }
        }
        System.out.println("Best: " + String.format("%.5f", gBestFitness));
        System.out.println("Best location: x = "+ String.format("%.5f", gBestX));
    }

    private void updateVelocity(Particle p){
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
