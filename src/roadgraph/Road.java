package roadgraph;

import geography.GeographicPoint;

public class Road {
	//private GeographicPoint fromNode;
	private GeographicPoint toNode;
	private String name;
	private String type;
	private double length;
	
	
	
	public Road(GeographicPoint to, String name, String type, double length) {
		this.toNode = to;
		this.name = name;
		this.type = type;
		this.length = length;
	}
	
	/*
	public GeographicPoint getFromNode() {
		return fromNode;
	}
	*/

	public GeographicPoint getToNode() {
		return toNode;
	}

	public String getName() {
		return name;
	}


	public String getType() {
		return type;
	}

	

	public double getLength() {
		return length;
	}


	
}
