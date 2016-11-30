package edu.uoc.pec2; /**********************************************************************************
* Implementación de la heurística CWS para resolver el CVRP. 
*
* Máster en Ingeniería Computacional y Matemática
* Optimización Combinatoria
**********************************************************************************/

import java.util.LinkedList;

public class Solution{
	
    /*******************************************************************************
    * Variables de instancia 
    ******************************************************************************/
	
    private static long nInstances = 0;
    private long id; 
    private double costs = 0.0, time = 0.0;
    private float demand = 0.0F;
    private LinkedList<Route> routes;

    /*******************************************************************************
    * Constructor 
    ******************************************************************************/    
    
    public Solution(){   
	   nInstances++;
       id = nInstances;
       routes = new LinkedList<Route>();}
   
    /*******************************************************************************
     * Getters y Setters 
     ******************************************************************************/
    
    public LinkedList<Route> getRoutes(){return routes;}

    public void setRoutes(LinkedList<Route> routes){
        this.routes=routes;
    }
    
    public long getId(){return id;}
    
    public double getCosts(){return costs;}
    
    public float getDemand(){return demand;}
    
    public double getTime(){return time;}
    
    public void setCosts(double c){costs = c;}
    
    public void setDemand(float d){demand = d;}
    
    public void setTime(double t){time = t;}
    
    /*******************************************************************************
     * MÉTODO PÚBLICO toString()
     ******************************************************************************/
    
    @Override
    public String toString(){
        Route aRoute;
        String s = "\r\nSol ID : " + this.id + "\r\n";
        s += "Sol costs: " + this.costs + "\r\n";
        s += "Sol time: " + this.time + "\r\n";
        s += "# of routes in sol: " + this.routes.size();
        s += "\r\n";
        s += "List of routes (cost and nodes): \r\n";
        for (int i = 1; i <= routes.size(); i++){
            aRoute = this.routes.get(i - 1);
            s += "Route " + i;
            s += " Costs = " + aRoute.getCosts();
            s += " Demand  = " + aRoute.getDemand();
            s += "\r\n";}
        return s;}

    public Solution copy(){
        Solution newSolution=new Solution();
        newSolution.setCosts(this.getCosts());
        newSolution.setTime(this.getTime());
        newSolution.setDemand(this.getDemand());
        LinkedList<Route> routes=new LinkedList<>();
        for (Route r:this.getRoutes())
            routes.add(r.copy());
        newSolution.setRoutes(routes);
        return newSolution;
    }

    public void calc() {
        this.costs=0.0;
        this.demand=0;
        for (Route r:this.getRoutes()
             ) {
            this.costs+=r.getCosts();
            this.demand+=r.getDemand();
        }
    }
}