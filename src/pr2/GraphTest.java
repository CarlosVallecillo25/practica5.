package pr2;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class GraphTest {

    @Test
    public void testAddVertex() {
        Graph<Integer> graph = new Graph<>();
        assertTrue(graph.addVertex(1));
        assertFalse(graph.addVertex(1));
    }

    @Test
    public void testShortestPath() {
        Graph<Integer> graph = new Graph<>();
        graph.addEdge(1, 2);
        graph.addEdge(1, 5);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(5, 4);

        List<Integer> expectedPath = Arrays.asList(1, 5, 4);
        assertEquals(expectedPath, graph.shortestPath(1, 4));
    }
}
