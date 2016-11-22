/**********************************************************************************
* Implementación de la heurística CWS para resolver el CVRP. 
*
* Máster en Ingeniería Computacional y Matemática
* Optimización Combinatoria
**********************************************************************************/

import java.util.LinkedList;

public class Inputs{

     /*******************************************************************************
     * Variables de instancia 
     ******************************************************************************/

    private Node[] nodes; // Nodos 
    private float vCap = 0.0F; // Capacidad máxima de los vehículos   
    private LinkedList<Edge> savings = null; // Lista de arcos
   
    /*******************************************************************************
     * Constructor 
     ******************************************************************************/

    public Inputs(int n, Test aTest){   
	   nodes = new Node[n];}

    /*******************************************************************************
     * Getters y Setters 
     ******************************************************************************/

    public Node[] getNodes(){return nodes;}

    public LinkedList<Edge> getSavings(){return savings;}

    public float getVehCap(){return vCap;}

    public void setVehCap(float c){vCap = c;}

    public void setList(LinkedList<Edge> sList){savings = sList;}
}