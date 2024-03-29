package edu.uoc.pec2; /**********************************************************************************
* Implementaci�n de la heur�stica CWS para resolver el CVRP. 
*
* M�ster en Ingenier�a Computacional y Matem�tica
* Optimizaci�n Combinatoria
**********************************************************************************/

import java.io.Serializable;
import java.util.LinkedList;

public class Inputs implements Serializable{

     /*******************************************************************************
     * Variables de instancia 
     ******************************************************************************/

    private CNode[] CNodes; // Nodos
    private float vCap = 0.0F; // Capacidad m�xima de los veh�culos   
    private LinkedList<CEdge> savings = null; // Lista de arcos
   
    /*******************************************************************************
     * Constructor 
     ******************************************************************************/

    public Inputs(int n, Test aTest){   
	   CNodes = new CNode[n];}

    /*******************************************************************************
     * Getters y Setters 
     ******************************************************************************/

    public CNode[] getCNodes(){return CNodes;}

    public LinkedList<CEdge> getSavings(){return savings;}

    public float getVehCap(){return vCap;}

    public void setVehCap(float c){vCap = c;}

    public void setList(LinkedList<CEdge> sList){savings = sList;}


    public void setCNodes(CNode[] CNodes) {
        this.CNodes = CNodes;
    }

    public float getvCap() {
        return vCap;
    }

    public void setvCap(float vCap) {
        this.vCap = vCap;
    }

    public void setSavings(LinkedList<CEdge> savings) {
        this.savings = savings;
    }
}