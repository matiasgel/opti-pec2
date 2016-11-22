/**********************************************************************************
* Implementación de la heurística CWS (Clarke and Wright, 1964) para resolver el CVRP. 
*
* Máster en Ingeniería Computacional y Matemática
* Optimización Combinatoria
* Profesor: Angel Alejandro Juan 
* Consultoras: Eva Vallada y Laura Calvet
* Curso: 2016/2017
**********************************************************************************/

Carpetas:
- inputs: contiene 20 instancias (Golden et al., 1998) que incluyen un archivo con la información de los clientes (KellyXX_input_vehicles.txt) y otro con la de los vehículos (KellyXX_input_nodes.txt).  
- outputs: contendrá los outputs generados por el programa.
- tests: describe las pruebas a realizar.
- src: incluye el código.

La carpeta src contiene:
- Inputs: representa la información de las instancias.
- InputsManager: ofrece métodos relacionados con los inputs.
- Test: detalla la información de las ejecuciones a realizar.
- TestsManager: ofrece métodos relacionados con los tests.
- Outputs: guarda los outputs del programa.
- Node: recoge las características de los clientes.
- Edge: representa las características de los arcos.
- Route: representa la información de una ruta.
- Solution: representa una solución.
- Algorithm: contiene la estructura del procedimiento.
- AlgorithmTester: incluye el método main.  
- CWS: contiene los principales métodos.
- ElapsedTime: clase relacionada con el tiempo de ejecución.

Clarke, G., and Wright, J.W. (1964). Scheduling of Vehicles from a Central Depot to a Number of Delivery Points. Operations Research, 12, 568-581.
Golden, B. L., Wasil, E.A., Kelly, J.P., and Chao, I.M. (1998). The impact of metaheuristics on solving the vehicle routing problem: algorithms, problem sets, and computational results. In: Crainic, T.G., Laporte, G. (eds). Chapter 2 in Fleet management and logistics. Kluwer, Boston, MA.
 






