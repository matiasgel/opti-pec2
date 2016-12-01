package edu.uoc.pec2; /**********************************************************************************
* Implementación de la heurística CWS para resolver el CVRP. 
*
* Máster en Ingeniería Computacional y Matemática
* Optimización Combinatoria
**********************************************************************************/

import java.io.Serializable;
import java.util.LinkedList;

public class Inputs implements Serializable{

     /*******************************************************************************
     * Variables de instancia 
     ******************************************************************************/

    private CNode[] CNodes; // Nodos
    private float vCap = 0.0F; // Capacidad máxima de los vehículos   
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