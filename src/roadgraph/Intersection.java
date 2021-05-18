package roadgraph;

import java.util.ArrayList;

import geography.GeographicPoint;

public class Intersection {
	GeographicPoint coords;
	ArrayList<Road> edges;
	
	public Intersection(GeographicPoint coords, ArrayList<Road> edges) {
		this.coords = coords;
		this.edges = edges;
	}

	public GeographicPoint getCoords() {
		return coords;
	}

	public void setCoords(GeographicPoint coords) {
		this.coords = coords;
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
}
