import java.util.*;

public class Graph<V> {
    private Map<V, Set<V>> adjacencyList = new HashMap<>();

    public boolean addVertex(V v) {
        if (!adjacencyList.containsKey(v)) {
            adjacencyList.put(v, new HashSet<>());
            return true;
        }
        return false;
    }

    public boolean addEdge(V v1, V v2) {
        addVertex(v1);
        addVertex(v2);
        if (!adjacencyList.get(v1).contains(v2)) {
            adjacencyList.get(v1).add(v2);
            return true;
        }
        return false;
    }

    public Set<V> obtainAdjacents(V v) throws Exception {
        if (!adjacencyList.containsKey(v)) {
            throw new Exception("Vertex not found in graph");
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
            sb.append(entry.getKey()).append(": ");
            sb.append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }

    public List<V> shortestPath(V v1, V v2) {
        Map<V, V> parentMap = new HashMap<>();
        Queue<V> queue = new LinkedList<>();
        Set<V> visited = new HashSet<>();

        queue.add(v1);
        visited.add(v1);
        boolean found = false;

        while (!queue.isEmpty()) {
            V current = queue.poll();
            if (current.equals(v2)) {
                found = true;
                break;
            }
            for (V neighbor : adjacencyList.get(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    parentMap.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }

        if (!found) {
            return null; // No path exists
        }

        // Reconstruct the path
        List<V> path = new ArrayList<>();
        V current = v2;
        while (!current.equals(v1)) {
            path.add(0, current);
            current = parentMap.get(current);
        }
        path.add(0, v1);
        return path;
    }

    // Test the implementation
    public static void main(String[] args) {
        Graph<Integer> g = new Graph<>();
        g.addEdge(1, 2);
        g.addEdge(1, 5);
        g.addEdge(2, 3);
        g.addEdge(3, 4);
        g.addEdge(5, 4);

        System.out.println(g);

        List<Integer> expectedPath = new ArrayList<>();
        expectedPath.add(1);
        expectedPath.add(5);
        expectedPath.add(4);

        System.out.println("Shortest path from 1 to 4: " + g.shortestPath(1, 4));
        System.out.println("Expected path: " + expectedPath);
    }
}
