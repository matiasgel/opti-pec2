package edu.uoc.pec2;

import java.io.Serializable;

/**********************************************************************************
* Implementación de la heurística CWS para resolver el CVRP. 
*
* Máster en Ingeniería Computacional y Matemática
* Optimización Combinatoria
**********************************************************************************/

public class CNode implements Serializable{
	
    /*******************************************************************************
    * Variables de instancia 
    ******************************************************************************/
	
    private int id; // Identificador
    private float x, y, demand; // Coordenadas x e y, y demanda
    private Route inRoute = null; // Ruta que incluye al nodo
    private boolean isInterior = false; // Nodo interior (no conectado directamente con el depot)
    private CEdge diCEdge = null; // Arco depot-nodo
    private CEdge idCEdge = null; // Arco nodo-depot

    /*******************************************************************************
    * Constructor 
    ******************************************************************************/
    
    public CNode(int nodeId, float nodeX, float nodeY, float nodeDemand){
	   id = nodeId;
       x = nodeX;
       y = nodeY;
       demand = nodeDemand;}

    /*******************************************************************************
    * Getters y Setters 
    ******************************************************************************/
    
    public int getId(){return id;}
    
    public float getX(){return x;}
    
    public float getY(){return y;}
    
    public float getDemand(){return demand;}
    
    public Route getInRoute(){return inRoute;}
    
    public boolean getIsInterior(){return isInterior;}
    
    public CEdge getDiCEdge(){return diCEdge;}
    
    public CEdge getIdCEdge(){return idCEdge;}
    
    public void setInRoute(Route r){inRoute = r;}
    
    public void setIsInterior(boolean value){isInterior = value;}
    
    public void setDiCEdge(CEdge e){
        diCEdge = e;}
    
    public void setIdCEdge(CEdge e){
        idCEdge = e;}
    
	/******************************************************************************* 
     * MÉTODO PÚBLICO toString()
     ******************************************************************************/

    @Override
    public String toString(){   
	   String s = "" + this.id + " ";
        s += this.x + " ";
        s += this.y + " ";
        s = this.demand + "";
        return s;}

    public void setId(int id) {
        this.id = id;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setDemand(float demand) {
        this.demand = demand;
    }

    public boolean isInterior() {
        return isInterior;
    }

    public void setInterior(boolean interior) {
        isInterior = interior;
    }
}