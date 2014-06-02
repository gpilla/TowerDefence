package ar.edu.unq.tpi.games.towerdefence.graphs;

public class Node<T> {
	private T element;
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
	
	
	public T getElement() {
		return element;
	}
	public void setElement(T element) {
		this.element = element;
	}
	
	public String getKeyName() {
		return keyName;
	}
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}
	
}
