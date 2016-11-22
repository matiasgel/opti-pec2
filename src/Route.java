/**********************************************************************************
* Implementación de la heurística CWS para resolver el CVRP. 
*
* Máster en Ingeniería Computacional y Matemática
* Optimización Combinatoria
**********************************************************************************/

import java.util.LinkedList;
import java.util.List;

public class Route{
	
	/******************************************************************************* 
     * Variables de instancia
     ******************************************************************************/
    
    private double costs = 0.0;
    private float demand = 0.0F;
    private LinkedList<Edge> edges;
    
    /*******************************************************************************
     * Constructor 
     ******************************************************************************/

    public Route(){   
	   edges = new LinkedList<Edge>();}
    
    /*******************************************************************************
     * Getters y Setters
     ******************************************************************************/
    
    public double getCosts(){return costs;}
    
    public float getDemand(){return demand;}
    
    public List<Edge> getEdges(){return edges;}
    
    public void setCosts(double c){costs = c;}
    
    public void setDemand(float d){demand = d;}
    
    public void setEdges(LinkedList<Edge> e){edges = e;}
    
	/******************************************************************************* 
     * MÉTODO PÚBLICO reverse()
     * Cambia el sentido de la ruta 
     ******************************************************************************/
    
    public void reverse(){   
        for(int i = 0; i < edges.size(); i++){   
        	Edge e = edges.get(i);
            Edge invE = e.getInverseEdge();
            edges.remove(e);
            edges.add(0, invE);}}

	/******************************************************************************* 
     * MÉTODO PÚBLICO toString()
     ******************************************************************************/
    
    @Override
    public String toString(){   
	   String s = "\nRute costs: " + this.costs;
       s += "\nRuta demand:" + this.demand;
       s += "\nRuta edges: " + this.edges;
       return s;}
}