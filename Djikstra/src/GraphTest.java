import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Tests the implementation of CS400Graph for the individual component of
 * Project Three: the implementation of Dijsktra's Shortest Path algorithm.
 */
public class GraphTest {

    private CS400Graph<String> graph;
    
    /**
     * Instantiate graph from last week's shortest path activity.
     */
    @BeforeEach
    public void createGraph() {
        graph = new CS400Graph<>();
        // insert vertices A-F
        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
        graph.insertVertex("D");
        graph.insertVertex("E");
        graph.insertVertex("F");
        // insert edges from Week 11. Shortest Path Activity
        graph.insertEdge("A","B",6);
        graph.insertEdge("A","C",2);
        graph.insertEdge("A","D",5);
        graph.insertEdge("B","E",1);
        graph.insertEdge("B","C",2);
        graph.insertEdge("C","B",3);
        graph.insertEdge("C","F",1);
        graph.insertEdge("D","E",3);
        graph.insertEdge("E","A",4);
        graph.insertEdge("F","A",1);
        graph.insertEdge("F","D",1);
    }

    /**
     * Checks the distance/total weight cost from the vertex A to F.
     */
    @Test
    public void testPathCostAtoF() {
        assertTrue(graph.getPathCost("A", "F") == 3);
    }

    /**
     * Checks the distance/total weight cost from the vertex A to D.
     */
    @Test
    public void testPathCostAtoD() {
        assertTrue(graph.getPathCost("A", "D") == 4);
    }

    /**
     * Checks the ordered sequence of data within vertices from the vertex 
     * A to D.
     */
    @Test
    public void testPathAtoD() {
        assertTrue(graph.shortestPath("A", "D").toString().equals(
            "[A, C, F, D]"
        ));
    }

    /**
     * Checks the ordered sequence of data within vertices from the vertex 
     * A to F.
     */
    @Test
    public void testPathAtoF() {
        assertTrue(graph.shortestPath("A", "F").toString().equals(
            "[A, C, F]"
        ));
    }
    
    /**
     * Confirms the distance I calculated for this node in last week's activity
     * is correct
     */
    @Test
    public void testDistanceDtoB() {
    	assertTrue(graph.getPathCost("D", "B") == 12);
    }
    
    /**
     * Confirms correct path from D to B
     */
    @Test
    public void testPathDtoB() {
    	assertTrue(graph.shortestPath("D", "B").toString().equals("[D, E, A, C, B]"));
    }
    
    /**
     * Confirms the path cost from D to B is correct
     */
    @Test
    public void testPathCostDtoB() {
    	assertTrue(graph.getPathCost("D", "B") == 12);
    }
    
    /**
     * Confirms if the path has the correct predecessor
     */
    @Test
    public void testPredecesserAtoF() {
    	List list = graph.shortestPath("F", "B");
    	assertTrue(list.get(list.size()-2).equals("C"));
    }
    
    /**
     * Confirms the correctness of path creation from B to E
     */
    @Test
    public void testPathandCostBtoE() {
    	assertTrue(graph.getPathCost("B", "E") == 1);
    	assertTrue(graph.shortestPath("B", "E").toString().equals("[B, E]"));
    }
    
    /**
     * Confirms that the Dijkstras algorithm throws a correct exception
     * when there is 
     */
    @Test
    public void throwException() {
    	try {
    		graph.getPathCost("C", "G");
    		assertTrue(false);
    	}
    	catch(NoSuchElementException e) {
    		assertTrue(true);
    	}
    	catch(Exception e1) {assertTrue(false);}
    }
}
