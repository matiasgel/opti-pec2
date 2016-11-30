package edu.uoc.pec2;

/**********************************************************************************
* Implementación de la heurística CWS para resolver el CVRP. 
*
* Máster en Ingeniería Computacional y Matemática
* Optimización Combinatoria
**********************************************************************************/

public class CEdge implements Comparable<CEdge>{
	
    /*******************************************************************************
    * Variables de instancia 
    ******************************************************************************/
	
    private CNode origin, end;
    private double costs = 0.0, savings = 0.0; 
    private Route inRoute = null; 
    private CEdge inverseCEdge = null;
    private double geometricP=0.0;
    
    /*******************************************************************************
     * Constructor 
     ******************************************************************************/

    public CEdge(CNode originCNode, CNode endCNode){
	   origin = originCNode;
       end = endCNode;}

    public double getGeometricP() {
        return geometricP;
    }

    public void setGeometricP(double geometricP) {
        this.geometricP = geometricP;
    }

    /*******************************************************************************
     * Getters y Setters 
     ******************************************************************************/


    public CNode getOrigin(){return origin;}
    
    public CNode getEnd(){return end;}
    
    public double getCosts(){return costs;}
    
    public double getSavings(){return savings;}
    
    public Route getInRoute(){return inRoute;}
    
    public CEdge getInverseCEdge(){return inverseCEdge;}
    
    public void setCosts(double c){costs = c;}
    
    public void setSavings(double s){savings = s;}
    
    public void setInRoute(Route r){inRoute = r;}
    
    public void setInverse(CEdge e){
        inverseCEdge = e;}

    /*******************************************************************************
     * MÉTODO PÚBLICO calcCosts()
     * Calcula la distancia entre dos nodos
     ******************************************************************************/
    
    public double calcCosts(CNode origin, CNode end){
	   double X1 = origin.getX(); 
	   double Y1 = origin.getY();
       double X2 = end.getX(); 
       double Y2 = end.getY();
       double d = Math.sqrt((X2 - X1) * (X2 - X1) + (Y2 - Y1) * (Y2 - Y1));
       return d;}
    
    /*******************************************************************************
     * MÉTODO PÚBLICO calcSavings()
     * Calcula los savings dados dos clientes y el depot
     ******************************************************************************/

    public double calcSavings(CNode origin, CNode end, CNode depot){
    	double X1 = origin.getX(); 
    	double Y1 = origin.getY();
        double X2 = end.getX(); 
        double Y2 = end.getY();
        double Xd = depot.getX(); 
        double Yd = depot.getY();
        double odC = Math.sqrt((Xd - X1)*(Xd - X1) + (Yd - Y1)*(Yd - Y1));
        double deC = Math.sqrt((X2 - Xd)*(X2 - Xd) + (Y2 - Yd)*(Y2 - Yd));
        double oeC = Math.sqrt((X2 - X1)*(X2 - X1) + (Y2 - Y1)*(Y2 - Y1));
        return odC + deC - oeC;}
    
    /*******************************************************************************
     * MÉTODO PÚBLICO compareTo()
     ******************************************************************************/
    
    @Override
    public int compareTo(CEdge otherCEdge){
       double s1 = this.getSavings();
       double s2 = otherCEdge.getSavings();
       if(s1 < s2) return -1;
       if(s1 > s2) return 1;
       return 0;} 
    
    /*******************************************************************************
     * MÉTODO PÚBLICO toString()
     ******************************************************************************/
    
    @Override
    public String toString(){   
	   String s = "\nEdge origin: " + this.origin;
        s += "\nEdge end: " + this.end;
        s += "\nEdge costs: " + this.costs;
        s = "\nEdge savings: " + this.savings;
        return s;}
}