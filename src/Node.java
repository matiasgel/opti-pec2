/**********************************************************************************
* Implementación de la heurística CWS para resolver el CVRP. 
*
* Máster en Ingeniería Computacional y Matemática
* Optimización Combinatoria
**********************************************************************************/

public class Node{
	
    /*******************************************************************************
    * Variables de instancia 
    ******************************************************************************/
	
    private int id; // Identificador
    private float x, y, demand; // Coordenadas x e y, y demanda
    private Route inRoute = null; // Ruta que incluye al nodo
    private boolean isInterior = false; // Nodo interior (no conectado directamente con el depot)
    private Edge diEdge = null; // Arco depot-nodo
    private Edge idEdge = null; // Arco nodo-depot

    /*******************************************************************************
    * Constructor 
    ******************************************************************************/
    
    public Node(int nodeId, float nodeX, float nodeY, float nodeDemand){   
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
    
    public Edge getDiEdge(){return diEdge;}
    
    public Edge getIdEdge(){return idEdge;}
    
    public void setInRoute(Route r){inRoute = r;}
    
    public void setIsInterior(boolean value){isInterior = value;}
    
    public void setDiEdge(Edge e){diEdge = e;}
    
    public void setIdEdge(Edge e){idEdge = e;}
    
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
}