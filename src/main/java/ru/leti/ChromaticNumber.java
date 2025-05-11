/*
 * Problem of finding chromatic number of graph is NP-hard problem, so it has no
 * algorithms which will work equally good for all cases. So was chosen algorithm
 * of ...
 * Sources:
 */

/* Logic grouping of classes (like namespaces in C++) */
package ru.leti;

// Import current project modules
import ru.leti.wise.task.graph.model.Graph;
import ru.leti.wise.task.graph.model.Vertex;
import ru.leti.wise.task.graph.model.Edge;
import ru.leti.wise.task.graph.model.Color;
import ru.leti.wise.task.plugin.graph.GraphCharacteristic;

// Import Java built-in modules
import java.util.List;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Comparator;

public class ChromaticNumber implements GraphCharacteristic {
    
    private TreeMap<Integer, List<Integer>> buildAdjacencyTreeMap(Graph graph) {
        // 1. Get vertices and edges list
        List<Vertex> vertices = graph.getVertexList();
        List<Edge>   edges = graph.getEdgeList();

        // 2. Create empty red-black tree for adjacancy list
        TreeMap<Integer, List<Integer>> adjMap = new TreeMap<>(
            Comparator.reverseOrder()
        );

        // 3. Fill keys and create empty vertices lists for each key
        for (Vertex v : vertices) {
            adjMap.put(v.getId(), new ArrayList<>());
        }

        // 4. Iterate through edges and add vertices to the appropriate lists
        for (Edge edge : edges) {
            int source = edge.getSource();
            int target = edge.getTarget();

            adjMap.get(source).add(target);

            if (graph.isDirect() == false) {
                adjMap.get(target).add(source);
            }
        }

        return adjMap;
    }

    /*
    @Override -- adds check of method signature.
    If not defined, unexpected errors may occur (a typo in the method name, for example).
    */
    @Override
    public int run(Graph graph) {
        /*
         * Get map with following structure:
         * key: vertex id
         * value: list of adjacency vertices id's
         */
        TreeMap<Integer, List<Integer>> adjMap = buildAdjacencyTreeMap(graph);

        /* 
         * Fill map with following structure:
         * key: vertex id
         * value: vertex color id
         */
        HashMap<Integer, Integer> vertexColors = new HashMap<>();
        for (int vId : adjMap.keySet()) {
            vertexColors.put(vId, 0);
        }

        // int startColor = 1;
        // int maxColor = 1;

        // while (adjMap.size() != 0) {
        //     for (int vId : adjMap.getSet()) {
        //         // Optimization to firstly color first vertex in graph
        //         if (maxColor == startColor) {
        //             vertexColors.get(vId) = startColor;
        //             adjMap.remove(vId);
        //             maxColor++;
        //             continue;
        //         }

                
        //         for (int adjId : adjMap.get(vId)) {
                    
        //         }
        //     }
        // }
        int maxColor = 0;

        for (Integer vId : vertexColors.keySet()) {
            Set<Integer> usedColors = new HashSet<>();
            for (Integer neighborId : adjMap.get(vId)) {
                Integer color = vertexColors.get(neighborId);
                if (color != null && color != 0) {
                    usedColors.add(color);
                }
            }

            int color = 1;
            while (usedColors.contains(color)) {
                color++;
            }

            vertexColors.put(vId, color);
            if (color > maxColor) {
                maxColor = color;
            }
        }

        return maxColor;
    }
    
    // public static void main (String args[]) {
    //     System.out.println("Hello world from ChromaticNumber!");
    //     var graph = FileLoader.loadGraphFromJson("src/test/resources/graph-data-1745663537246.json");
    //     System.out.println(run(graph));
    // }
}
