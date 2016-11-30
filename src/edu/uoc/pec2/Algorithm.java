package edu.uoc.pec2;

import edu.uoc.pec2.biasedCWS.BiasedRandomCWS;

/**********************************************************************************
* Implementaci�n de la heur�stica CWS para resolver el CVRP. 
*
* M�ster en Ingenier�a Computacional y Matem�tica
* Optimizaci�n Combinatoria
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
     * M�TODO P�BLICO solve()
     * M�todo general de resoluci�n 
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