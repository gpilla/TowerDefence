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
	
	public MapGraph(int rows, int columns, double height, double width){
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
		int row = this.obtainRowNumber(y);
		column.add(row, new Node<T>());
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

	protected List<List<Node<T>>> getNodes() {
		return nodes;
	}

	protected void setNodes(List<List<Node<T>>> nodes) {
		this.nodes = nodes;
	}
	
	
}
