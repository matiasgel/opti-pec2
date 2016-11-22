/**********************************************************************************
* Implementaci�n de la heur�stica CWS (Clarke and Wright, 1964) para resolver el CVRP. 
*
* M�ster en Ingenier�a Computacional y Matem�tica
* Optimizaci�n Combinatoria
* Profesor: Angel Alejandro Juan 
* Consultoras: Eva Vallada y Laura Calvet
* Curso: 2016/2017
**********************************************************************************/

Carpetas:
- inputs: contiene 20 instancias (Golden et al., 1998) que incluyen un archivo con la informaci�n de los clientes (KellyXX_input_vehicles.txt) y otro con la de los veh�culos (KellyXX_input_nodes.txt).  
- outputs: contendr� los outputs generados por el programa.
- tests: describe las pruebas a realizar.
- src: incluye el c�digo.

La carpeta src contiene:
- Inputs: representa la informaci�n de las instancias.
- InputsManager: ofrece m�todos relacionados con los inputs.
- Test: detalla la informaci�n de las ejecuciones a realizar.
- TestsManager: ofrece m�todos relacionados con los tests.
- Outputs: guarda los outputs del programa.
- Node: recoge las caracter�sticas de los clientes.
- Edge: representa las caracter�sticas de los arcos.
- Route: representa la informaci�n de una ruta.
- Solution: representa una soluci�n.
- Algorithm: contiene la estructura del procedimiento.
- AlgorithmTester: incluye el m�todo main.  
- CWS: contiene los principales m�todos.
- ElapsedTime: clase relacionada con el tiempo de ejecuci�n.

Clarke, G., and Wright, J.W. (1964). Scheduling of Vehicles from a Central Depot to a Number of Delivery Points. Operations Research, 12, 568-581.
Golden, B. L., Wasil, E.A., Kelly, J.P., and Chao, I.M. (1998). The impact of metaheuristics on solving the vehicle routing problem: algorithms, problem sets, and computational results. In: Crainic, T.G., Laporte, G. (eds). Chapter 2 in Fleet management and logistics. Kluwer, Boston, MA.
 






