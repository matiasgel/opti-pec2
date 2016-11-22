package edu.uoc.pec2;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;

import java.util.Random;


/**
 * Created by Matias on 21/11/2016.
 */
public class CustomGraph {
    Graph graph;
    public CustomGraph(String name) {
         graph = new SingleGraph(name);
    }
    public void addNode(CNode cNode){
        graph.addNode(Integer.toString(cNode.getId()));
        Node node=graph.getNode(Integer.toString(cNode.getId()));
        node.setAttribute("x",cNode.getX());
        node.setAttribute("y",cNode.getY());
    }
    public void addEdge(CEdge cEdge){

        graph.addEdge(Integer.toString(cEdge.getOrigin().getId())+Integer.toString(cEdge.getEnd().getId()),
                cEdge.getOrigin().getId(),
                cEdge.getEnd().getId());
    }
    public void saveToFile(){
        graph.clearSinks();
        Viewer viewer=
        graph.display(false);
       // viewer.disableAutoLayout();


    }

    public void add(Solution cwsSol,Inputs inputs) {
        for (CNode node:inputs.getCNodes()) {
            this.addNode(node);
        }
        for (Route route: cwsSol.getRoutes()){
            for (CEdge edge:route.getCEdges())this.addEdge(edge);
        }
    }
}
