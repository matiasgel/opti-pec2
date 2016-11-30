package edu.uoc.pec2.biasedCWS;

import edu.uoc.pec2.*;

import java.util.LinkedList;
import java.util.List;
import cern.jet.random.Distributions;
import cern.jet.random.Uniform;
import cern.jet.random.engine.MersenneTwister64;



/**
 * Created by Matias on 22/11/2016.
 */
public class BiasedRandomCWS {
    private Uniform randomGenerator=new Uniform(new MersenneTwister64());
    private final Integer MAXTIME=300;
    private final long MAXITER=1000;
    private  Integer iter=0;
    private long start;
    Test test;
    Inputs inputs;
    Integer age=0;

    public Solution solve(Test aTest, Inputs inputs) {
        this.inputs=inputs;
        this.test=aTest;
        Solution currentSol = CWS.solve(aTest, inputs);
        RouteCache cache = new RouteCache();
        cache.addAll(currentSol.getRoutes());

         start = ElapsedTime.systemTime();
        this.iter=0;
        while(this.endCondition()){
            Solution newSol=this.randomSolve();
            newSol = cache.improve(newSol);
            if(!(currentSol.getCosts()<=newSol.getCosts())) {
                currentSol=newSol;
                age=0;
            }else
                age++;
        }
        return currentSol;
    }



    private Solution randomSolve() {
        Solution solution = CWS.generateDummySol(inputs);
        CNode depot = inputs.getCNodes()[0];
        List<CEdge> savings = new LinkedList<CEdge>();
        for(CEdge e : inputs.getSavings())
            savings.add(0, e);
        while(savings.isEmpty() == false){
            CEdge ijCEdge = this.getRandomEdge(savings);
            CWS.verifyEdge(test, inputs, solution, depot, savings, ijCEdge);
        }
        return solution;
    }

    private CEdge getRandomEdge(List<CEdge> savings) {
        int index=0;
        double cumu=0.0;
        Double rn=randomGenerator.nextDouble();
        double beta=randomGenerator.nextDoubleFromTo(0.05,0.25);
        while (true){
            Double d=Distributions.geometricPdf(index,beta);
            cumu+=d;
            if(rn<cumu)
                return savings.get(index);
            if(!(++index<savings.size()))index=0;
        }
    }

    private boolean endCondition() {
        Double current = ElapsedTime.calcElapsed(start,ElapsedTime.systemTime());
        return ((current-start)<this.MAXTIME&&this.iter++<this.MAXITER);
    }
    public static void main(String argv[]){

    }
}
