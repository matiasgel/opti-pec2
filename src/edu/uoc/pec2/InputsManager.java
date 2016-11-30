package edu.uoc.pec2; /**********************************************************************************
* Implementación de la heurística CWS para resolver el CVRP. 
*
* Máster en Ingeniería Computacional y Matemática
* Optimización Combinatoria
**********************************************************************************/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class InputsManager{

	/******************************************************************************* 
     * MÉTODO PÚBLICO readInputs()
     * Lee y guarda los inputs 
     ******************************************************************************/

    public static Inputs readInputs(String nodesFilePath, String vehiclesFilePath, Test aTest){
        Inputs inputs = null;
        try{   
            BufferedReader br = new BufferedReader(new FileReader(nodesFilePath));
            String f = null;
            int nNodes = 0;
            while((f = br.readLine())!=null){ // Cuenta el número de clientes
            	if(f.charAt(0) != '#') nNodes++;}
            inputs = new Inputs(nNodes, aTest);
            FileReader reader = new FileReader(nodesFilePath);
            Scanner in = new Scanner(reader);
            in.useLocale(Locale.US);
            String s = null;
            int k = 0;
            while(in.hasNextLine()){ // Lee los datos de cada cliente
            	s = in.next();
                if(s.charAt(0) == '#') in.nextLine();
                else{   
                	float x = Float.parseFloat(s); // Coordenada x
                    float y = in.nextFloat(); // Coordenada y
                    float demand = in.nextFloat(); // Demanda
                    CNode CNode = new CNode(k, x, y, demand);
                    inputs.getCNodes()[k] = CNode;
                    k++;}}
            in.close();
            reader = new FileReader(vehiclesFilePath);
            in = new Scanner(reader);
            in.useLocale(Locale.US);
            br.close();
            while(in.hasNextLine()){ // Lee los datos de los vehículos   
            	s = in.next();
                if(s.charAt(0) == '#') in.nextLine();
                else{   
                	float vCap = Float.parseFloat(s);
                    inputs.setVehCap(vCap);}}
            in.close();}
        catch (IOException exception){   
        	System.out.println("Error processing inputs files: " + exception);}
        return inputs;}
	
	/******************************************************************************* 
     * MÉTODO PÚBLICO generateSavingsList()
     * Crea la lista de savings 
     ******************************************************************************/
    
    public static void generateSavingsList(Inputs inputs){    	
        int nNodes = inputs.getCNodes().length;
        CEdge[] savingsArray = new CEdge[(nNodes - 1) * (nNodes - 2) / 2];
        CNode depot = inputs.getCNodes()[0];
        int k = 0;
        for(int i = 1; i < nNodes - 1; i++){
           for(int j = i + 1; j < nNodes; j++){
                CNode iCNode = inputs.getCNodes()[i];
                CNode jCNode = inputs.getCNodes()[j];
                CEdge ijCEdge = new CEdge(iCNode, jCNode);
                ijCEdge.setCosts(ijCEdge.calcCosts(iCNode, jCNode));
                ijCEdge.setSavings(ijCEdge.calcSavings(iCNode, jCNode, depot));
                CEdge jiCEdge = new CEdge(jCNode, iCNode);
                jiCEdge.setCosts(jiCEdge.calcCosts(jCNode, iCNode));
                jiCEdge.setSavings(jiCEdge.calcSavings(jCNode, iCNode, depot));
                ijCEdge.setInverse(jiCEdge);
                jiCEdge.setInverse(ijCEdge);
                savingsArray[k] = ijCEdge;
                k++;
           }
        }
        Arrays.sort(savingsArray);
        List<CEdge> sList = Arrays.asList(savingsArray);
        LinkedList<CEdge> savingsList = new LinkedList<CEdge>(sList);
        inputs.setList(savingsList);}

	/******************************************************************************* 
     * MÉTODO PÚBLICO generateDepotEdges()
     * Crea los arcos que contienen el depot (nodes[0]) 
     ******************************************************************************/
    
    public static void generateDepotEdges(Inputs inputs){
        CNode[] CNodes = inputs.getCNodes();
        CNode depot = CNodes[0];
        for(int i = 1; i < CNodes.length; i++){
            CNode iCNode = CNodes[i];
            CEdge diCEdge = new CEdge(depot, iCNode);
            iCNode.setDiCEdge(diCEdge);
            diCEdge.setCosts(diCEdge.calcCosts(depot, iCNode));
            CEdge idCEdge = new CEdge(iCNode, depot);
            iCNode.setIdCEdge(idCEdge);
            idCEdge.setCosts(idCEdge.calcCosts(depot, iCNode));
            idCEdge.setInverse(diCEdge);
            diCEdge.setInverse(idCEdge);}}
}