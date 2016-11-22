/**********************************************************************************
* Implementaci�n de la heur�stica CWS para resolver el CVRP. 
*
* M�ster en Ingenier�a Computacional y Matem�tica
* Optimizaci�n Combinatoria
**********************************************************************************/

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class TestsManager{
	
	/******************************************************************************* 
     * M�TODO P�BLICO getTestsList()
     * Lee y guarda los tests 
     ******************************************************************************/
    
	public static ArrayList<Test> getTestsList(String testsFilePath){   
	ArrayList<Test> list = new ArrayList<Test>();
        try{   
		 FileReader reader = new FileReader(testsFilePath);
            Scanner in = new Scanner(reader);
            in.useLocale(Locale.US);
            while(in.hasNextLine()){   
			String s = in.next();
                if(s.charAt(0) == '#') // Comentario
                    in.nextLine(); 
                else{   
                	String instanceName = s;
                    float maxRouteCosts = in.nextFloat();
                    Test aTest = new Test(instanceName, maxRouteCosts);
                    list.add(aTest);}}
            in.close();}
        catch (IOException exception){   
        	System.out.println("Error processing tests file: " + exception);}
        return list;}
}