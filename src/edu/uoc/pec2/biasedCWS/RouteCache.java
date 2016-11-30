package edu.uoc.pec2.biasedCWS;

import edu.uoc.pec2.CEdge;
import edu.uoc.pec2.Route;
import edu.uoc.pec2.Solution;


import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * Created by Matias on 22/11/2016.
 */
public class RouteCache {

    private HashMap<String,Route> routes;

    public RouteCache(){
        this.routes=new HashMap<String,Route>();
    }

    /**
     * Busca la ruta parametro en la cache, si la encuentra verifica cual es la mejor. Si la ruta parametro
     * es la mejor, la guarda en el cache y la retorna. Si la ruta guardada es la mejor la retorna.
     * @param route ruta a buscar y comparar
     * @return la mejor ruta.
     */
    public Route getBestRoute(Route route){
        String hashCode = getHash(route);
        Route r=this.routes.get(hashCode);
        if (r!=null) {
            if (r.getCosts() < route.getCosts())
                return r;
        }else this.routes.put(hashCode,route);
        return route;
    }

    /**
     * Obtiene un hash para buscar en la cache. Para esto obtiene todos los nodos de la ruta, los ordena y
     * genera un string con los valores string de cada id
     * @param route la ruta a generar el hash
     * @return un string hash correspondiente a la ruta de entrada
     */
    private String getHash(Route route) {
        ArrayList<Integer> nodes=new ArrayList<Integer>();
        Integer[] hashInt=new Integer[route.getCEdges().size()];
        int i=0;
        for(CEdge edge:route.getCEdges())hashInt[i++]=edge.getOrigin().getId();
        return Arrays.stream(hashInt).sorted().map(Object::toString).collect(Collectors.joining("|"));
    }


    /**
     * Agrega todas las rutas al chache.
     * @param routes listado de rutas a incluir.
     */
    public void addAll(LinkedList<Route> routes) {
        for(Route r:routes){
            this.routes.put(this.getHash(r),r);
        }
    }

    /**
     * Trata de mejorar una solucion con las rutas guardadas en el cache.
     * @param newSol solucion a mejorar.
     * @return retorna una solucion con posibles mejoras.
     */
    public Solution improve(Solution newSol) {
        Boolean improved=false;
        LinkedList<Route> rs=new LinkedList<Route>();
        for (Route r:
             newSol.getRoutes()) {
            Route r1=this.getBestRoute(r);
            rs.add(r1);
            if(r1.getCosts()!=r.getCosts())
                improved=true;
        }
        if(improved) {
        //   System.out.println("ruta nueva");
            newSol.setRoutes(rs);
            newSol.calc();
        }
        return newSol;
    }
}
