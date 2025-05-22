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
    
    private HashMap<Integer, List<Integer>> buildAdjacencyMap(Graph graph) {
        // 1. Get vertices and edges list
        List<Vertex> vertices = graph.getVertexList();
        List<Edge>   edges = graph.getEdgeList();

        // 2. Create empty map for adjacency list
        HashMap<Integer, List<Integer>> adjMap = new HashMap<>();

        // 3. Fill keys and create empty vertices lists for each key
        for (Vertex v : vertices) {
            adjMap.put(v.getId(), new ArrayList<>());
        }

        // 4. Iterate through edges and add vertices to the appropriate lists
        for (Edge edge : edges) {
            int source = edge.getSource();
            int target = edge.getTarget();

            adjMap.get(source).add(target);

            /* The chromatic number does not depend on the direction of
               the edges, so for the convenience of the program, we add
               the opposite number for any edge. That is, we consider
               each graph to be undirected. */
            adjMap.get(target).add(source);
        }

        return adjMap;
    }

    /*
    @Override -- adds check of method signature.
    If not defined, unexpected errors may occur (a typo in the method name, for example).
    */
    @Override
    public int run(Graph graph) {
         // Hashmap to store adjacency list of graph
        HashMap<Integer, List<Integer>> adjMap = buildAdjacencyMap(graph);

        // Sort vertices by neighbors count (non-ascending order)
        List<Integer> sortedVertices = new ArrayList<>(adjMap.keySet());
        sortedVertices.sort((v1, v2) -> {
            int size1 = adjMap.get(v1).size();
            int size2 = adjMap.get(v2).size();
            return Integer.compare(size2, size1);
        });

        // Hashmap to store color for each vertex
        HashMap<Integer, Integer> vertexColors = new HashMap<>();
        for (Integer vId : sortedVertices) {
            vertexColors.put(vId, 0);
        }

        int maxColor = 0;

        for (Integer vId : sortedVertices) {
            // Get colors, used by neighbors
            Set<Integer> usedColors = new HashSet<>();
            for (Integer neighborId : adjMap.get(vId)) {
                Integer color = vertexColors.get(neighborId);
                if (color != null && color != 0) {
                    usedColors.add(color);
                }
            }

            // Finding the minimum acceptable color
            int color = 1;
            while (usedColors.contains(color)) {
                color++;
            }

            // Add found color in vertex colors map
            vertexColors.put(vId, color);
            if (color > maxColor) {
                maxColor = color;
            }
        }

        return maxColor;
    }
}
