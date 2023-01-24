import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * This is the Algorithm Engineer Test class to test the AirportGraph class
 * 
 * @author Cole Johnstone
 *
 */
public class AlgorithmEngineerTests{
	
	private IAirport port1;
	private IAirport port2;
	private IAirport port3;
	private IAirport port4;
	private IAirport port5;
	private IFlight flight1;
	private IFlight flight2;
	private IFlight flight3;
	private IFlight flight4;
	private IFlight flight5;
	private IFlight flight6;
	private IFlight flight7;
	private AirportGraph graph;
	
	/**
	 * This method creates the graph this class will use for all of its tests
	 */
	@BeforeEach
	public void createGraph() {
		graph = new AirportGraph();
		port1 = new Airport1AE();
		port2 = new Airport2AE();
		port3 = new Airport3AE();
		port4 = new Airport4AE();
		port5 = new Airport5AE();
		flight1 = new Flight1AE();
		flight2 = new Flight2AE();
		flight3 = new Flight3AE();
		flight4 = new Flight4AE();
		flight5 = new Flight5AE();
		flight6 = new Flight6AE();
		flight7 = new Flight7AE();
		
        // insert vertices A-F
        graph.insertVertex(port1);
        graph.insertVertex(port2);
        graph.insertVertex(port3);
        graph.insertVertex(port4);
        graph.insertVertex(port5);
        // insert edges from Week 11. Shortest Path Activity
        graph.insertEdge(port1,port2,flight1);
        graph.insertEdge(port1,port3,flight2);
        graph.insertEdge(port4,port1,flight3);
        graph.insertEdge(port3,port4,flight4);
        graph.insertEdge(port5,port3,flight5);
        graph.insertEdge(port4,port5,flight6);
        graph.insertEdge(port2,port4,flight7);
	}
	
	/**
	 * This method tests if insertEdge throws the right exception when there are all null inputs
	 * and tests the creation of a new edge without either vertex in the current graph
	 */
	@Test
	public void test1() {
		try{graph.insertEdge(null, null, null);}
		catch(NullPointerException e1) {
			IAirport port6 = new Airport6AE();
			IAirport port7 = new Airport7AE();
			graph.insertEdge(port6, port7, new Flight8AE());
			List<IAirport> list = graph.shortestPath(port6,port7);
			String string = "";
			for(int i = 0; i < list.size(); i++) {
				string += list.get(i).getCity()+" ";
			}
			assertTrue(string.equals("Milwaukee Chicago "));
		}
		catch(Exception e2) {assertTrue(false);}
	}
	
	/**
	 * This method tests if it finds the correct shortest path between two vertices
	 */
	@Test
	public void test2() {
		List<IAirport> list = graph.shortestPath(port1,port5);
		String string = "";
		for(int i = 0; i < list.size(); i++) {
			string += list.get(i).getCity()+" ";
		}
		//System.out.println(string);
		assertTrue(string.equals("San Francisco Las Vegas Tampa Bay Detroit "));
	}
	
	/**
	 * This method tests if the algorithm throws the correct exception when there is no path
	 */
	@Test
	public void test3() {
		try {
			IAirport port6 = new Airport6AE();
			graph.shortestPath(port1,port6);
		}
		catch(NoSuchElementException e) {assertTrue(true);}
		catch(Exception e1) {assertTrue(false);}
	}
	
	/**
	 * This method tests if the cheapestFlight method returns the correct list of flights
	 */
	@Test
	public void test4() {
		List<IFlight> list = graph.cheapestFlight(port1,port5);
		assertTrue(list.size() == 3);
		assertTrue(list.get(0) == flight1);
		assertTrue(list.get(1) == flight7);
		assertTrue(list.get(2) == flight6);
	}
	
	/**
	 * This method tests if the new insertEdge method correctly implements the flights and costs
	 * aka weights of those flights
	 */
	@Test
	public void test5() {
		assertTrue(graph.getPathCost(port3, port5) == 6.0);
	}
}
