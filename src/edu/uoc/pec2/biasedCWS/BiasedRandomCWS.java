package edu.uoc.pec2.biasedCWS;

import cern.jet.random.engine.RandomEngine;
import edu.uoc.pec2.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import cern.jet.random.Distributions;
import cern.jet.random.Uniform;
import cern.jet.random.engine.MersenneTwister64;



/**
 * Created by Matias on 22/11/2016.
 */
public class BiasedRandomCWS {
    private static final long MAXITER=10000;
    private  Integer iter=0;
    Test test;
    Inputs inputs;
    Integer age=0;
    static Solution currentSol;

    public BiasedRandomCWS(Inputs inputs,Test aTest){
        this.inputs=inputs;
        this.test=aTest;
    }

    static Runnable getTask(Inputs inputs,Test test){

       return   () -> {
              for(int i=0;i<3000;i++){
                BiasedRandomCWS bs = new BiasedRandomCWS(inputs,test);
                Solution newSol=bs.randomSolve();
                newSol = RouteCache.getInstance().improve(newSol);
                checkSol(newSol);
              }
       };
    }

    public static synchronized void   checkSol(Solution newSol) {
        if(!(currentSol.getCosts()<=newSol.getCosts())){
            currentSol=newSol;

        }
    }

    ;


    public static Solution solve(Test aTest, Inputs inputs) {
        CWS cws=new CWS();
        currentSol = cws.solve(aTest, inputs);
        RouteCache.getInstance().addAll(currentSol.getRoutes());
        ObjectCloner<Inputs>  inputsClonner =new ObjectCloner();
        ObjectCloner<Test>  testClonner =new ObjectCloner();
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for(int ii=0;ii<5;ii++)
                    try {
                        executor.execute(BiasedRandomCWS.getTask(inputsClonner.deepCopy(inputs), testClonner.deepCopy(aTest)));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

        try {
            executor.shutdown();
            while(!executor.isTerminated()) {
             Boolean s=   executor.awaitTermination(10, TimeUnit.SECONDS);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return currentSol;
    }



    private Solution randomSolve() {
        CWS cws= new CWS();
        Solution solution = cws.generateDummySol(inputs);
        CNode depot = inputs.getCNodes()[0];
        List<CEdge> savings = new LinkedList<CEdge>();
        Uniform randomGenerator=new Uniform(new MersenneTwister64( new Random().nextInt()));
        for(CEdge e : inputs.getSavings())
            savings.add(0, e);
        while(savings.isEmpty() == false){
            double beta=randomGenerator.nextDoubleFromTo(0.05,0.25);
            CEdge ijCEdge = this.getRandomEdge(savings,randomGenerator,beta);
            cws.verifyEdge(test, inputs, solution, depot, savings, ijCEdge);
        }
        return solution;
    }

    private CEdge getRandomEdge(List<CEdge> savings, Uniform rn, double beta) {
        int index=0;
        double cumu=0.0;
        double rand=rn.nextDouble();
        while (index<savings.size()){
            Double d=Distributions.geometricPdf(index,beta);
            cumu+=d;
            if(rand<cumu)
                return savings.get(index);
            index++;
        }
        return savings.get(rn.nextIntFromTo(0,savings.size()-1));
    }


    public static void main(String argv[]){

    }
}
