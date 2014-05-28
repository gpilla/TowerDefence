package ar.edu.unq.tpi.games.towerdefence.graphs;

import java.util.HashMap;
import java.util.Map;

public class Node<T> {
	private T element;
	private Map<String,Node<T>> adjacentNodes = new HashMap<String, Node<T>>();
	private String keyName="";
	
	public Node(){
		
	}
	
	public Node(String keyName){
		this.setKeyName(keyName);
	}
	
	public Node(T element, String keyName){
		this.setElement(element);
		this.setKeyName(keyName);
	}
	
	public Node<T> addAdjancentNode(Node<T> node){
		this.getAdjacentNodes().put(node.getKeyName(),node);
		return this;
	}
	
	
	public T getElement() {
		return element;
	}
	public void setElement(T element) {
		this.element = element;
	}
	public Map<String, Node<T>> getAdjacentNodes() {
		return adjacentNodes;
	}
	public void setAdjacentNodes(Map<String, Node<T>> adjacentNodes) {
		this.adjacentNodes = adjacentNodes;
	}
	public String getKeyName() {
		return keyName;
	}
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}
	
}
