package ar.edu.unq.tpi.games.towerdefence.util;

import java.awt.Color;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;

import com.uqbar.vainilla.maps.AbstractImageMapParser;

public class TowerDefenceImageMapParser extends AbstractImageMapParser {

	private static Color[][] unitDensityMap = null;
	
	public TowerDefenceImageMapParser(String file) {
		super(file);
	}

	public ArrayList<Double> getEnemySpawnPoints() {
		ArrayList<Double> spawnPoints = new ArrayList<Double>(); 
		for (int i = 0; i < this.getWidth(); i++) {
			for (int j = 0; j < this.getHeight(); j++) {
				if(this.getColor(i, j).equals(Color.RED)) {
					spawnPoints.add(new Double(i, j));
				}
			}
		}
		return spawnPoints;
	}
	
	public ArrayList<Double> getEnemyTargetPoints() {
		ArrayList<Double> targetPoints = new ArrayList<Double>();
		for (int i = 0; i < this.getWidth(); i++) {
			for (int j = 0; j < this.getHeight(); j++) {
				if(this.getColor(i, j).getRGB() == -10240) {
					targetPoints.add(new Double(i, j));
				}
			}
		}
		return targetPoints;
	}
	
	/**
	 * Genera el mapa de densidad de las unidades.
	 * 
	 * Solo se permiten unidades sobre lo verde clarito
	 * 
	 * @return
	 */
	public void generateUnitDensityMapGrid() {
		Color[][] densityMapGrid = this.getTerrainGrid();
		for (int i = 0; i < densityMapGrid.length; i++) {
			for (int j = 0; j < densityMapGrid[0].length; j++) {
				if (densityMapGrid[i][j].getRGB() == -5898369) {
					densityMapGrid[i][j] = Color.BLACK;
				} else {
					densityMapGrid[i][j] = Color.WHITE;
				}
			}
		}
		System.out.println("Density map" + densityMapGrid);
		TowerDefenceImageMapParser.unitDensityMap = densityMapGrid;
	}
	
	public void fillPositionDensityMap(Double position) {
		
	}
	
	public void clearPositionDensityMap(Double position) {
	
	}

	public Color[][] getUnitDensityMap() {
		if( TowerDefenceImageMapParser.unitDensityMap == null) {
			generateUnitDensityMapGrid();
		}
		return TowerDefenceImageMapParser.unitDensityMap;
	}
	
}