package pr2;

import java.util.*;

public class Graph<V> {
    private Map<V, Set<V>> adjacencyList = new HashMap<>();

    public boolean addVertex(V v) {
        if (adjacencyList.containsKey(v)) {
            return false;
        }
        adjacencyList.put(v, new HashSet<>());
        return true;
    }

    public boolean addEdge(V v1, V v2) {
        addVertex(v1);
        addVertex(v2);
        return adjacencyList.get(v1).add(v2);
    }

    public Set<V> obtainAdjacents(V v) throws Exception {
        if (!adjacencyList.containsKey(v)) {
            throw new Exception("Vertex not found");
        }
        return adjacencyList.get(v);
    }

    public boolean containsVertex(V v) {
        return adjacencyList.containsKey(v);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<V, Set<V>> entry : adjacencyList.entrySet()) {
            sb.append(entry.getKey()).append(" -> ").append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }

    public List<V> shortestPath(V v1, V v2) {
        if (!adjacencyList.containsKey(v1) || !adjacencyList.containsKey(v2)) {
            return null;
        }
        Queue<V> queue = new LinkedList<>();
        Map<V, V> previous = new HashMap<>();
        Set<V> visited = new HashSet<>();
        queue.add(v1);
        visited.add(v1);

        while (!queue.isEmpty()) {
            V current = queue.poll();
            if (current.equals(v2)) {
                List<V> path = new ArrayList<>();
                for (V at = v2; at != null; at = previous.get(at)) {
                    path.add(at);
                }
                Collections.reverse(path);
                return path;
            }
            for (V neighbor : adjacencyList.get(current)) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                    previous.put(neighbor, current);
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        // Ejemplo de uso
        Graph<Integer> graph = new Graph<>();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addEdge(1, 2);

        System.out.println(graph); // Imprime la representación del grafo
    }
}


