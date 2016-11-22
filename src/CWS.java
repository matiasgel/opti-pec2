/**********************************************************************************
* Implementación de la heurística CWS para resolver el CVRP. 
*
* Máster en Ingeniería Computacional y Matemática
* Optimización Combinatoria
**********************************************************************************/

import java.util.LinkedList;
import java.util.List;

public class CWS{
	
    /*******************************************************************************
     * MÉTODO PÚBLICO solve()
     * Devuelve una solución basada en la heurística CWS 
     ******************************************************************************/
	
    public static Solution solve(Test aTest, Inputs inputs){
        Solution currentSol = generateDummySol(inputs);
        Node depot = inputs.getNodes()[0];
        int index = 0;     
        List<Edge> savings = new LinkedList<Edge>();
        for(Edge e : inputs.getSavings())
          savings.add(0, e);   
        while(savings.isEmpty() == false){  
        	Edge ijEdge = savings.get(index);
            savings.remove(ijEdge);
            Node iNode = ijEdge.getOrigin();
            Node jNode = ijEdge.getEnd();
            Route iR = iNode.getInRoute();
            Route jR = jNode.getInRoute();
            boolean isMergePossible = false;
            isMergePossible = checkMergingConditions(aTest, inputs, iR, jR, ijEdge);
            if(isMergePossible == true){   
            	Edge iE = getEdge(iR, iNode, depot); 
            	iR.getEdges().remove(iE);
            	iR.setCosts(iR.getCosts() - iE.getCosts());
            	if(iR.getEdges().size()>1) iNode.setIsInterior(true);
            	if(iR.getEdges().get(0).getOrigin() != depot) iR.reverse();
            	Edge jE = getEdge(jR, jNode, depot);
            	jR.getEdges().remove(jE);
            	jR.setCosts(jR.getCosts() - jE.getCosts());
            	if(jR.getEdges().size() > 1) jNode.setIsInterior(true);
            	if(jR.getEdges().get(0).getOrigin() == depot) jR.reverse();
            	iR.getEdges().add(ijEdge);
            	iR.setCosts(iR.getCosts() + ijEdge.getCosts());
            	iR.setDemand(iR.getDemand() + ijEdge.getEnd().getDemand());
            	jNode.setInRoute(iR);
            	for(Edge e : jR.getEdges()){   
            		iR.getEdges().add(e);
            		iR.setDemand(iR.getDemand() + e.getEnd().getDemand());
            		iR.setCosts(iR.getCosts() + e.getCosts());
            		e.getEnd().setInRoute(iR);}
            	currentSol.setCosts(currentSol.getCosts() - ijEdge.getSavings());
            	currentSol.getRoutes().remove(jR);}}
        	return currentSol;}
    

    /*******************************************************************************
     * MÉTODO PÚBLICO generateDummySol()
     * Devuelve una solución inicial dummy con una ruta por cliente 
     ******************************************************************************/
        
    private static Solution generateDummySol(Inputs inputs){ 
        Solution sol = new Solution();
        for(int i = 1; i < inputs.getNodes().length; i++){     
            Node iNode = inputs.getNodes()[i];
            Edge diEdge = iNode.getDiEdge();
            Edge idEdge = iNode.getIdEdge();
            Route didRoute = new Route();
            didRoute.getEdges().add(diEdge);
            didRoute.setDemand(diEdge.getEnd().getDemand());
            didRoute.setCosts(diEdge.getCosts());
            didRoute.getEdges().add(idEdge);
            didRoute.setCosts(didRoute.getCosts() + idEdge.getCosts());
            iNode.setInRoute(didRoute); 
            iNode.setIsInterior(false); 
            sol.getRoutes().add(didRoute);
            sol.setCosts(sol.getCosts() + didRoute.getCosts());
            sol.setDemand(sol.getDemand() + didRoute.getDemand());}
        return sol;}

    /*******************************************************************************
     * MÉTODO PÚBLICO getEdge()
     * Devuelve el arco de la ruta especificada que contiene el depot y un nodo dado 
     ******************************************************************************/
    
    private static Edge getEdge(Route aRoute, Node iNode, Node depot){   
        Edge firstEdge = aRoute.getEdges().get(0);
        Node origin = firstEdge.getOrigin();
        Node end = firstEdge.getEnd();
        if ((origin == iNode && end == depot)||(origin == depot && end == iNode))
            return firstEdge;
        else{
            int lastIndex = aRoute.getEdges().size() - 1;
            Edge lastEdge = aRoute.getEdges().get(lastIndex);
            return lastEdge;}}

    /*******************************************************************************
     * MÉTODO PÚBLICO checkMergingConditions()
     * Comprueba que sea factible unir dos rutas 
     ******************************************************************************/
    
    private static boolean checkMergingConditions(Test aTest, Inputs inputs, Route iR, Route jR, Edge ijEdge){
        if(iR == jR)
            return false;
        Node iNode = ijEdge.getOrigin();
        Node jNode = ijEdge.getEnd();
        if(iNode.getIsInterior() == true || jNode.getIsInterior() == true) return false; // Los nodos no pueden ser interiores
        if(inputs.getVehCap() < iR.getDemand() + jR.getDemand()) return false; // La demanda no puede exceder la capacidad del vehículo
        float maxRoute = aTest.getMaxRouteCosts();
        double newCost = iR.getCosts() + jR.getCosts() - ijEdge.getSavings();
        if(newCost > maxRoute) return false; // Los costes de la ruta no pueden ser superiores a un determinado valor  
        return true;}
}