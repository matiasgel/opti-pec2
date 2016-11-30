package edu.uoc.pec2;

import edu.uoc.pec2.biasedCWS.BiasedRandomCWS;

/**********************************************************************************
* Implementación de la heurística CWS para resolver el CVRP. 
*
* Máster en Ingeniería Computacional y Matemática
* Optimización Combinatoria
**********************************************************************************/

public class Algorithm{
	
    /*******************************************************************************
    * Variables de instancia 
    ******************************************************************************/	
	
    private Test aTest;
    private Inputs inputs;
    private Solution cwsSol = null;
    private Outputs outputs = new Outputs();

    /*******************************************************************************
    * Constructor 
    ******************************************************************************/
            
    Algorithm(Test myTest, Inputs myInputs){
        aTest = myTest;
        inputs = myInputs;}
    
    /*******************************************************************************
     * MÉTODO PÚBLICO solve()
     * Método general de resolución 
     ******************************************************************************/
    
    public Outputs solve(){
    	long start = ElapsedTime.systemTime();
        BiasedRandomCWS rCws=new BiasedRandomCWS();
        cwsSol = rCws.solve(aTest, inputs);
        double elapsed = ElapsedTime.calcElapsed(start, ElapsedTime.systemTime());
        cwsSol.setTime(elapsed);
        System.out.println("CWS sol cost: " + cwsSol.getCosts());
        System.out.println("CWS sol time: " + cwsSol.getTime());
        outputs.setCWSSol(cwsSol);        
        return outputs;}
}