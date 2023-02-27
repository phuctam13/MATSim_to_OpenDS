package org.matsim.project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.w3c.dom.Node;

public class Vehicle {

	private List<List<String>> routes = new ArrayList<>();
	private int id;
	public Vehicle() {
		// TODO Auto-generated constructor stub
	}

	public int getID() {
		return id;
	}

	public void setID(int id) {
		this.id = id;
	}
	
	public void addNode(List<String> nodeID) {
//		List<String> supplierNames = Arrays.asList("sup1", "sup2", "sup3");
//		routes.add(supplierNames);
		routes.add(nodeID);
	}
	
	public List<List<String>> getRoutes(){
		return routes;
	}
}
