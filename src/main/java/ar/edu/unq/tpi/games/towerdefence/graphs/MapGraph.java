package ar.edu.unq.tpi.games.towerdefence.graphs;

import java.lang.reflect.Array;


public class MapGraph<T> {
	private Node<T> matrix[][];
	private int rows = 0;
	private int columns = 0;
	private double width = 0;
	private double height = 0;

	
	public MapGraph(){
		
	}
	
	@SuppressWarnings("unchecked")
	public MapGraph(int rows, int columns, double height, double width){
		this.setRows(rows);
		this.setColumns(columns);
		this.setWidth(width);
		this.setHeight(height);
		this.matrix = (Node<T>[][])Array.newInstance(Node.class, rows,columns);
	}
	
	public void addNode(double x, double y, T element){
		int col = this.obtainColNumber(x);
		int row = this.obtainRowNumber(y);
		Node<T> node = this.getMatrix()[col][row];
		if(node==null){
			node = new Node<T>(element, row + "-" + col);
		}else{
			node.setElement(element);
		}
		this.getMatrix()[col][row] = node;
	}
	
	public Node<T> obtainNode(double x, double y){
		int col = this.obtainColNumber(x);
		int row = this.obtainRowNumber(y);
		return this.getMatrix()[row][col];
	}
	
	public int obtainColNumber(double x){
		if(x>=this.getWidth()){
			return this.getColumns() - 1;
		}
		return (int)(x/this.obtainHorizontalStep());
	}

	public int obtainRowNumber(double y){
		if(y>=this.getHeight()){
			return this.getRows()-1;
		}
		return (int)(y/this.obtainVerticalStep());
	}
	
	public double obtainVerticalStep(){
		return this.getHeight()/this.getRows();
	}
	
	
	public double obtainHorizontalStep(){
		return this.getWidth()/this.getColumns();
	}
	
	protected int getColumns() {
		return columns;
	}

	protected void setColumns(int columns) {
		this.columns = columns;
	}

	protected int getRows() {
		return rows;
	}

	protected void setRows(int rows) {
		this.rows = rows;
	}

	protected double getWidth() {
		return width;
	}
	protected void setWidth(double width) {
		this.width = width;
	}
	protected double getHeight() {
		return height;
	}
	protected void setHeight(double height) {
		this.height = height;
	}

	protected Node<T>[][] getMatrix() {
		return matrix;
	}

	protected void setMatrix(Node<T>[][] matrix) {
		this.matrix = matrix;
	}
	
	
}
