/**********************************************************************************
* Implementaci�n de la heur�stica CWS para resolver el CVRP. 
*
* M�ster en Ingenier�a Computacional y Matem�tica
* Optimizaci�n Combinatoria
**********************************************************************************/

import java.io.IOException;
import java.io.PrintWriter;

public class Outputs{
	
    /*******************************************************************************
    * Variables de instancia 
    ******************************************************************************/
	
    private Solution cwsSolution;

    /*******************************************************************************
     * Constructor 
     ******************************************************************************/

    public Outputs(){   
	   cwsSolution = null;}

    /*******************************************************************************
     * Getters y Setters 
     ******************************************************************************/
    
    public Solution getCWSSol(){return cwsSolution;}    
    public void setCWSSol(Solution cwsSol){cwsSolution = cwsSol;}
    
    /******************************************************************************* 
     * M�TODO P�BLICO sendToFile()
     * Guarda la soluci�n encontrada 
     ******************************************************************************/
    
    public void sendToFile(String outFile){
        try{   
		 PrintWriter out = new PrintWriter(outFile);
            out.println("CWS Sol");
            out.print("------------------------");
            out.print(this.cwsSolution.toString());
            out.print("-------------------------");
            out.close();} 
        catch (IOException exception){   
        	System.out.println("Error processing output file: " + exception);}}
}