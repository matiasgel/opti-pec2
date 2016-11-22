/**********************************************************************************
* Implementación de la heurística CWS para resolver el CVRP. 
*
* Máster en Ingeniería Computacional y Matemática
* Optimización Combinatoria
**********************************************************************************/

import java.io.File;
import java.util.ArrayList;

public class AlgorithmTester{
	
    /*******************************************************************************
    * Variables de instancia 
    ******************************************************************************/
	
    final static String inputFolder = "inputs";
    final static String outputFolder = "outputs";
    final static String testFolder = "tests";
    final static String fileNameTest = "test2Run.txt";
    final static String sufixFileNodes = "_input_nodes.txt";
    final static String sufixFileVehicules = "_input_vehicles.txt";

    /*******************************************************************************
    * MÉTODO PÚBLICO main 
    ******************************************************************************/
    
    public static void main( String[] args ){
        System.out.println("** STARTING **");
        String testsFilePath = testFolder + File.separator + fileNameTest;
        System.out.println(testsFilePath);
        ArrayList<Test> testsList = TestsManager.getTestsList(testsFilePath);
        int nTests = testsList.size();
        System.out.println("Number of tests " +nTests);
        // Para cada test ...
        for( int k = 0; k < nTests; k++ ){   
        	Test aTest = testsList.get(k);
            System.out.println("\n# Test " + (k + 1) + " of " + nTests);
            String inputNodesPath = inputFolder + File.separator + aTest.getInstanceName() + sufixFileNodes;
            String inputVehPath = inputFolder + File.separator + aTest.getInstanceName() + sufixFileVehicules;
            Inputs inputs = InputsManager.readInputs(inputNodesPath, inputVehPath, aTest);
            InputsManager.generateDepotEdges(inputs);
            InputsManager.generateSavingsList(inputs);
            Algorithm algorithm = new Algorithm(aTest, inputs);
            Outputs output = algorithm.solve();

            String outputsFilePath = outputFolder + File.separator + aTest.getInstanceName() + ".txt";
            output.sendToFile(outputsFilePath);}
        	System.out.println("\n** END (refresh the outputs file to see the solutions) **");}
}