package edu.uoc.pec2;

import java.io.Serializable;

/**********************************************************************************
* Implementaci�n de la heur�stica CWS para resolver el CVRP. 
*
* M�ster en Ingenier�a Computacional y Matem�tica
* Optimizaci�n Combinatoria
**********************************************************************************/

public class Test implements Serializable{
	
    /*******************************************************************************
    * Variables de instancia 
    ******************************************************************************/
	
    private String instanceName; 
    private float maxRouteCosts; // Coste m�ximo por ruta
    
    /*******************************************************************************
     * Constructor 
     ******************************************************************************/    
    
    public Test(String name, float rCosts){
        instanceName = name; 
        maxRouteCosts = rCosts;}

    /*******************************************************************************
     * Getters y Setters 
     ******************************************************************************/
    
    public String getInstanceName(){return instanceName;}    
    public float getMaxRouteCosts(){return maxRouteCosts;}

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    public void setMaxRouteCosts(float maxRouteCosts) {
        this.maxRouteCosts = maxRouteCosts;
    }
}