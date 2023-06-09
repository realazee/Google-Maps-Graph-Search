/**
 * @author UCSD MOOC development team and YOU
 * 
 * A class which reprsents a graph of geographic locations
 * Nodes in the graph are intersections between 
 *
 */
package roadgraph;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.function.Consumer;

import geography.GeographicPoint;
import util.GraphLoader;

/**
 * @author UCSD MOOC development team and YOU
 * 
 * A class which represents a graph of geographic locations
 * Nodes in the graph are intersections between 
 *
 */
public class MapGraph {
	//TODO: Add your member variables here in WEEK 3
	private HashMap<GeographicPoint, Intersection> vertices;
	private int numEdges;
	private int numVertices;
	
	
	
	
	/** 
	 * Create a new empty MapGraph 
	 */
	public MapGraph()
	{
		// TODO: Implement in this constructor in WEEK 3
		vertices = new HashMap<GeographicPoint, Intersection>();
		numEdges = 0;
		numVertices = 0;
		
	}
	
	/**
	 * Get the number of vertices (road intersections) in the graph
	 * @return The number of vertices in the graph.
	 */
	public int getNumVertices()
	{
		//TODO: Implement this method in WEEK 3
		return numVertices;
	}
	
	/**
	 * Return the intersections, which are the vertices in this graph.
	 * @return The vertices in this graph as GeographicPoints
	 */
	public Set<GeographicPoint> getVertices()
	{
		
		return vertices.keySet();
	}
	
	/**
	 * Get the number of road segments in the graph
	 * @return The number of edges in the graph.
	 */
	public int getNumEdges()
	{
		
		return numEdges;
	}

	
	
	/** Add a node corresponding to an intersection at a Geographic Point
	 * If the location is already in the graph or null, this method does 
	 * not change the graph.
	 * @param location  The location of the intersection
	 * @return true if a node was added, false if it was not (the node
	 * was already in the graph, or the parameter is null).
	 */
	public boolean addVertex(GeographicPoint location)
	{
		// TODO: Implement this method in WEEK 3
		if(location == null || vertices.get(location) != null) {
			return false;
		}
		vertices.put(location, new Intersection(location, new ArrayList<Road>()));
		numVertices++;
		return true;
	}
	
	/**
	 * Adds a directed edge to the graph from pt1 to pt2.  
	 * Precondition: Both GeographicPoints have already been added to the graph
	 * @param from The starting point of the edge
	 * @param to The ending point of the edge
	 * @param roadName The name of the road
	 * @param roadType The type of the road
	 * @param length The length of the road, in km
	 * @throws IllegalArgumentException If the points have not already been
	 *   added as nodes to the graph, if any of the arguments is null,
	 *   or if the length is less than 0.
	 */
	public void addEdge(GeographicPoint from, GeographicPoint to, String roadName,
			String roadType, double length) throws IllegalArgumentException {

		//TODO: Implement this method in WEEK 3
		if(from == null || to == null || roadName == null || roadType == null || length < 0) {
			throw new IllegalArgumentException();
		}
		if(vertices.get(from) == null || vertices.get(to) == null) {
			throw new IllegalArgumentException();
		}
		
		vertices.get(from).addEdges(new Road(from,to, roadName, roadType, length));
		numEdges++;
		
	}
	

	/** Find the path from start to goal using breadth first search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest (unweighted)
	 *   path from start to goal (inclu ding both start and goal).
	 */
	public List<GeographicPoint> bfs(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
        Consumer<GeographicPoint> temp = (x) -> {};
        return bfs(start, goal, temp);
	}
	
	/** Find the path from start to goal using breadth first search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest (unweighted)
	 *   path from start to goal (including both start and goal).
	 */
	public List<GeographicPoint> bfs(GeographicPoint start, 
			 					     GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		// TODO: Implement this method in WEEK 3
		
		Queue<Intersection> queue = new LinkedList<Intersection>();
		HashSet<Intersection> visited = new HashSet<Intersection>();
		HashMap<Intersection, Intersection> parent = new HashMap<Intersection, Intersection>();
		Intersection curr;
		List<Road> edges;
		
		queue.add(vertices.get(start));
		visited.add(vertices.get(start));
		while(!queue.isEmpty()) {
			curr = queue.remove();
			if(curr == vertices.get(goal)) {
				List<GeographicPoint> output = new LinkedList<GeographicPoint>();
				nodeSearched.accept(curr.getCoords());
				output.add(0, goal);
				while(curr != vertices.get(start)) {
					output.add(0, parent.get(curr).getCoords());
					curr = parent.get(curr);
				}
				return output;
			}
			edges = vertices.get(curr.getCoords()).getEdges();
			for(int i = 0; i < edges.size(); i++) {
				Intersection n = vertices.get(edges.get(i).getToNode());//getting neighbors is simply getting the toNode of the road.
				if(!visited.contains(n)) {
					visited.add(n);
					parent.put(n, curr);
					queue.add(n);
				}
			}
			
		
			
		}
	
		
		
		// Hook for visualization.  See writeup.
		

		return null;
	}
	

	/** Find the path from start to goal using Dijkstra's algorithm
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> dijkstra(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
		// You do not need to change this method.
		
		
        Consumer<GeographicPoint> temp = (x) -> {};
        return dijkstra(start, goal, temp);
	}
	
	/** Find the path from start to goal using Dijkstra's algorithm
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> dijkstra(GeographicPoint start, 
										  GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		
		PriorityQueue<Intersection> priQ = new PriorityQueue<Intersection>();
		HashMap<Intersection, Intersection> parent = new HashMap<Intersection,Intersection>();
		HashSet<Intersection> checked = new HashSet<>();
		
		for(Intersection node : vertices.values()) {
			node.setDistance(1000000000);
		}
		
		Intersection startPoint = vertices.get(start);
		Intersection endPoint = vertices.get(goal);
		startPoint.setDistance(0);
		
		priQ.add(startPoint);
		Intersection curr;
		//List<Road> edges;
		
		while(!priQ.isEmpty()) {
			curr = priQ.remove();
			checked.add(curr);
			nodeSearched.accept(curr.getCoords());
			if(curr == vertices.get(goal)) {
				List<GeographicPoint> output = new LinkedList<GeographicPoint>();
				
				while(!endPoint.equals(startPoint)) {
					output.add(0, endPoint.getCoords());
					endPoint = parent.get(endPoint);
				}
				output.add(0, startPoint.getCoords());
				return output;
			}
			
			HashMap<Intersection, Double> distance = new HashMap<>();
			//edges = vertices.get(curr.getCoords()).getEdges();
			
			for(Road edge : curr.getEdges()) {
				distance.put(vertices.get(edge.getNeighbor(curr)), edge.getLength());
			}
			for(GeographicPoint node : curr.getNeighbors()) {
				if(checked.contains(vertices.get(node))){
					continue;
				}
				Double distanceOfNode = curr.getDistance() + distance.get(vertices.get(node));
				if(distanceOfNode < vertices.get(node).getDistance()) {
					vertices.get(node).setDistance(distanceOfNode);
					parent.put(vertices.get(node), curr);
					priQ.add(vertices.get(node));
					
				}
			}
			
			
			
		}
		
		
		return null;
	}
	

	/** Find the path from start to goal using A-Star search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> aStarSearch(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
        Consumer<GeographicPoint> temp = (x) -> {};
        return aStarSearch(start, goal, temp);
	}
	
	/** Find the path from start to goal using A-Star search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> aStarSearch(GeographicPoint start, 
											 GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		
				PriorityQueue<Intersection> priQ = new PriorityQueue<Intersection>();
				HashMap<Intersection, Intersection> parent = new HashMap<Intersection,Intersection>();
				HashSet<Intersection> checked = new HashSet<>();
				
				for(Intersection node : vertices.values()) {
					node.setDistance(1000000000);
				}
				
				Intersection startPoint = vertices.get(start);
				Intersection endPoint = vertices.get(goal);
				startPoint.setDistance(0);
				
				priQ.add(startPoint);
				Intersection curr;
				//List<Road> edges;
				
				while(!priQ.isEmpty()) {
					curr = priQ.remove();
					checked.add(curr);
					nodeSearched.accept(curr.getCoords());
					if(curr == vertices.get(goal)) {
						List<GeographicPoint> output = new LinkedList<GeographicPoint>();
						
						while(!endPoint.equals(startPoint)) {
							output.add(0, endPoint.getCoords());
							endPoint = parent.get(endPoint);
						}
						output.add(0, startPoint.getCoords());
						return output;
					}
					
					HashMap<Intersection, Double> distance = new HashMap<>();
					//edges = vertices.get(curr.getCoords()).getEdges();
					
					for(Road edge : curr.getEdges()) {
						distance.put(vertices.get(edge.getNeighbor(curr)), edge.getLength());
					}
					for(GeographicPoint node : curr.getNeighbors()) {
		                if(checked.contains(vertices.get(node))){
		                    continue;
		                }
		                Double distanceNode = curr.getDistance() + distance.get(vertices.get(node));
		                Double prioritized = vertices.get(node).getDistanceToOtherNode(vertices.get(goal));

		                if(distanceNode + prioritized < vertices.get(node).getDistance()) {
		                    vertices.get(node).setDistance(distanceNode);
		                    parent.put(vertices.get(node), curr);
		                    priQ.add(vertices.get(node));

		                }
		            }
					
					
					
				}
				
				// Hook for visualization.  See writeup.
				//nodeSearched.accept(next.getLocation());
				
				return null;

	}

	
	
	public static void main(String[] args)
	{
		System.out.print("Making a new map...");
		MapGraph firstMap = new MapGraph();
		System.out.print("DONE. \nLoading the map...");
		GraphLoader.loadRoadMap("data/testdata/simpletest.map", firstMap);
		System.out.println("DONE.");
		
		// You can use this method for testing.  
		
		
		/* Here are some test cases you should try before you attempt 
		 * the Week 3 End of Week Quiz, EVEN IF you score 100% on the 
		 * programming assignment.
		 */
		
		MapGraph simpleTestMap = new MapGraph();
		GraphLoader.loadRoadMap("data/testdata/simpletest.map", simpleTestMap);
		
		GeographicPoint testStart = new GeographicPoint(1.0, 1.0);
		GeographicPoint testEnd = new GeographicPoint(8.0, -1.0);
		
		System.out.println("Test 1 using simpletest: Dijkstra should be 9 and AStar should be 5");
		List<GeographicPoint> testroute = simpleTestMap.dijkstra(testStart,testEnd);
		List<GeographicPoint> testroute2 = simpleTestMap.aStarSearch(testStart,testEnd);
		
		
		MapGraph testMap = new MapGraph();
		GraphLoader.loadRoadMap("data/maps/utc.map", testMap);
		
		// A very simple test using real data
		testStart = new GeographicPoint(32.869423, -117.220917);
		testEnd = new GeographicPoint(32.869255, -117.216927);
		System.out.println("Test 2 using utc: Dijkstra should be 13 and AStar should be 5");
		testroute = testMap.dijkstra(testStart,testEnd);
		testroute2 = testMap.aStarSearch(testStart,testEnd);
		
		
		// A slightly more complex test using real data
		testStart = new GeographicPoint(32.8674388, -117.2190213);
		testEnd = new GeographicPoint(32.8697828, -117.2244506);
		System.out.println("Test 3 using utc: Dijkstra should be 37 and AStar should be 10");
		testroute = testMap.dijkstra(testStart,testEnd);
		testroute2 = testMap.aStarSearch(testStart,testEnd);
		
		
		
		/* Use this code in Week 3 End of Week Quiz */
		MapGraph theMap = new MapGraph();
		System.out.print("DONE. \nLoading the map...");
		GraphLoader.loadRoadMap("data/maps/utc.map", theMap);
		System.out.println("DONE.");

		GeographicPoint start = new GeographicPoint(32.8648772, -117.2254046);
		GeographicPoint end = new GeographicPoint(32.8660691, -117.217393);
		
		
		List<GeographicPoint> route = theMap.dijkstra(start,end);
		List<GeographicPoint> route2 = theMap.aStarSearch(start,end);

		
		
	}
	
}
