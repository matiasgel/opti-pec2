package edu.uoc.pec2; /**********************************************************************************
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
        CNode depot = inputs.getCNodes()[0];
        int index = 0;     
        List<CEdge> savings = new LinkedList<CEdge>();
        for(CEdge e : inputs.getSavings())
          savings.add(0, e);   
        while(savings.isEmpty() == false){  
        	CEdge ijCEdge = savings.get(index);
            verifyEdge(aTest, inputs, currentSol, depot, savings, ijCEdge);
        }
        	return currentSol;
    }

    public static void verifyEdge(Test aTest, Inputs inputs,
                                  Solution currentSol,
                                  CNode depot,
                                  List<CEdge> savings,
                                  CEdge ijCEdge) {
        savings.remove(ijCEdge);
        CNode iCNode = ijCEdge.getOrigin();
        CNode jCNode = ijCEdge.getEnd();
        Route iR = iCNode.getInRoute();
        Route jR = jCNode.getInRoute();
        boolean isMergePossible = false;
        isMergePossible = checkMergingConditions(aTest, inputs, iR, jR, ijCEdge);
        if(isMergePossible == true) merge(currentSol, depot, ijCEdge, iCNode, jCNode, iR, jR);
    }

    private static void merge(Solution currentSol, CNode depot, CEdge ijCEdge,
                              CNode iCNode, CNode jCNode, Route iR, Route jR) {
        CEdge iE = getEdge(iR, iCNode, depot);
        iR.getCEdges().remove(iE);
        iR.setCosts(iR.getCosts() - iE.getCosts());
        if(iR.getCEdges().size()>1) iCNode.setIsInterior(true);
        if(iR.getCEdges().get(0).getOrigin() != depot) iR.reverse();
        CEdge jE = getEdge(jR, jCNode, depot);
        jR.getCEdges().remove(jE);
        jR.setCosts(jR.getCosts() - jE.getCosts());
        if(jR.getCEdges().size() > 1) jCNode.setIsInterior(true);
        if(jR.getCEdges().get(0).getOrigin() == depot) jR.reverse();
        iR.getCEdges().add(ijCEdge);
        iR.setCosts(iR.getCosts() + ijCEdge.getCosts());
        iR.setDemand(iR.getDemand() + ijCEdge.getEnd().getDemand());
        jCNode.setInRoute(iR);
        for(CEdge e : jR.getCEdges()){
            iR.getCEdges().add(e);
            iR.setDemand(iR.getDemand() + e.getEnd().getDemand());
            iR.setCosts(iR.getCosts() + e.getCosts());
            e.getEnd().setInRoute(iR);}
        currentSol.setCosts(currentSol.getCosts() - ijCEdge.getSavings());
        currentSol.getRoutes().remove(jR);
    }


    /*******************************************************************************
     * MÉTODO PÚBLICO generateDummySol()
     * Devuelve una solución inicial dummy con una ruta por cliente 
     ******************************************************************************/
        
    public static Solution generateDummySol(Inputs inputs){
        Solution sol = new Solution();
        for(int i = 1; i < inputs.getCNodes().length; i++){
            CNode iCNode = inputs.getCNodes()[i];
            CEdge diCEdge = iCNode.getDiCEdge();
            CEdge idCEdge = iCNode.getIdCEdge();
            Route didRoute = new Route();
            didRoute.getCEdges().add(diCEdge);
            didRoute.setDemand(diCEdge.getEnd().getDemand());
            didRoute.setCosts(diCEdge.getCosts());
            didRoute.getCEdges().add(idCEdge);
            didRoute.setCosts(didRoute.getCosts() + idCEdge.getCosts());
            iCNode.setInRoute(didRoute);
            iCNode.setIsInterior(false);
            sol.getRoutes().add(didRoute);
            sol.setCosts(sol.getCosts() + didRoute.getCosts());
            sol.setDemand(sol.getDemand() + didRoute.getDemand());}
        return sol;}

    /*******************************************************************************
     * MÉTODO PÚBLICO getEdge()
     * Devuelve el arco de la ruta especificada que contiene el depot y un nodo dado 
     ******************************************************************************/
    
    private static CEdge getEdge(Route aRoute, CNode iCNode, CNode depot){
        CEdge firstCEdge = aRoute.getCEdges().get(0);
        CNode origin = firstCEdge.getOrigin();
        CNode end = firstCEdge.getEnd();
        if ((origin == iCNode && end == depot)||(origin == depot && end == iCNode))
            return firstCEdge;
        else{
            int lastIndex = aRoute.getCEdges().size() - 1;
            CEdge lastCEdge = aRoute.getCEdges().get(lastIndex);
            return lastCEdge;}}

    /*******************************************************************************
     * MÉTODO PÚBLICO checkMergingConditions()
     * Comprueba que sea factible unir dos rutas 
     ******************************************************************************/
    
    private static boolean checkMergingConditions(Test aTest, Inputs inputs, Route iR, Route jR, CEdge ijCEdge){
        if(iR == jR)
            return false;
        CNode iCNode = ijCEdge.getOrigin();
        CNode jCNode = ijCEdge.getEnd();
        if(iCNode.getIsInterior() == true || jCNode.getIsInterior() == true) return false; // Los nodos no pueden ser interiores
        if(inputs.getVehCap() < iR.getDemand() + jR.getDemand()) return false; // La demanda no puede exceder la capacidad del vehículo
        float maxRoute = aTest.getMaxRouteCosts();
        double newCost = iR.getCosts() + jR.getCosts() - ijCEdge.getSavings();
        if(newCost > maxRoute) return false; // Los costes de la ruta no pueden ser superiores a un determinado valor  
        return true;}
}