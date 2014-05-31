package ar.edu.unq.tpi.games.towerdefence.graphs;

import java.util.ArrayList;
import java.util.List;

public class MapGraph<T> {
	private List<List<Node<T>>> nodes = new ArrayList<List<Node<T>>>();
	private int rows = 0;
	private int columns = 0;
	private double width = 0;
	private double height = 0;

	
	public MapGraph(){
		
	}
	
	public MapGraph(int rows, int columns, double width, double height){
		this.setRows(rows);
		this.setColumns(columns);
		this.setWidth(width);
		this.setHeight(height);
		for(int i=0; i<rows;i++){
			List<Node<T>> row = new ArrayList<Node<T>>();
			for(int j=0; j<columns;j++){
				row.add(new Node<T>(i + "," + j));
			}
			this.getNodes().add(row);
		}
	}
	
	public void addNode(double x, double y, T element){
		int col = this.obtainColNumber(x);
		List<Node<T>> column = this.getNodes().get(col);
		int row = this.obtainRowNumber(x);
		column.add(row, new Node<T>());
	}
	
	public int obtainColNumber(double x){
		return (int)(x/this.obtainVerticalStep());
	}

	public int obtainRowNumber(double x){
		return (int)(x/this.obtainVerticalStep());
	}
	
	public double obtainVerticalStep(){
		return this.getHeight()/this.getRows();
	}
	
	public int obtainHorizontalStep(){
		return (int)this.getWidth()/this.getColumns();
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

	protected List<List<Node<T>>> getNodes() {
		return nodes;
	}

	protected void setNodes(List<List<Node<T>>> nodes) {
		this.nodes = nodes;
	}
	
	
}
