package roadgraph;

import java.util.ArrayList;
import java.util.HashSet;

import geography.GeographicPoint;

public class Intersection implements Comparable<Intersection>{
	GeographicPoint coords;
	ArrayList<Road> edges;
	double distance; 
	
	public Intersection(GeographicPoint coords, ArrayList<Road> edges) {
		this.coords = coords;
		this.edges = edges;
	}

	public int compareTo(Intersection newNode) {
		Double thisNodesDistance = this.getDistance();
		Double newNodeDistance = newNode.getDistance();
		return thisNodesDistance.compareTo(newNodeDistance);
	}
	
	
	public GeographicPoint getCoords() {
		return coords;
	}

	public void setCoords(GeographicPoint coords) {
		this.coords = coords;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public ArrayList<Road> getEdges() {
		return edges;
	}

	public void addEdges(Road road) {
		edges.add(road);
	}
	public Road removeEdges(int index) {
		return edges.remove(index);
	}
	
	public HashSet<GeographicPoint> getNeighbors(){
		HashSet<GeographicPoint> neighbors = new HashSet<GeographicPoint>();
		for(Road edge : edges) {
			neighbors.add(edge.getNeighbor(this));
		}
		return neighbors;
	}
	public Double getDistanceToOtherNode(Intersection other) {
        return coords.distance(other.getCoords());
    }

	
	
}
