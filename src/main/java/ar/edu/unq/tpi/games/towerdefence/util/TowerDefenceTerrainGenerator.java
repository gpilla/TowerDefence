package ar.edu.unq.tpi.games.towerdefence.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.uqbar.vainilla.appearances.Sprite;
import com.uqbar.vainilla.utils.ClassLoaderResourcesProvider;
import com.uqbar.vainilla.utils.ResourceProvider;

public class TowerDefenceTerrainGenerator {

	public static ResourceProvider defaultResourceProvider = new ClassLoaderResourcesProvider();

	private Color[][] terrainGrid;
	private int cellSize;
	private int width;
	private int height;

	private ArrayList<Color> terrainAllowedColors = new ArrayList<Color>();

	public TowerDefenceTerrainGenerator(TowerDefenceImageMapParser mapParser, int height, int width) {
		this.terrainGrid = mapParser.getTerrainGrid();
		//this.terrainGrid = mapParser.getUnitDensityMap();
		this.width = width;
		this.height = height;
		
		this.addTerrainAllowedColor(Color.BLACK);
		this.addTerrainAllowedColor(new Color(-16767233));
		this.cellSize = height / mapParser.getHeight();
	}

	public Sprite getSprite() {
		return new Sprite(this.getImage());
	}
	
	public BufferedImage getImage(Color[][] terrainGrid, int cellSize) {
		BufferedImage image = null;
		image = new BufferedImage(terrainGrid[0].length * cellSize, terrainGrid.length * cellSize, BufferedImage.TYPE_INT_BGR);
		
		try {
			Graphics graphics = image.getGraphics();
			
			Color terrainColor;
			
			for (int i = 0; i < terrainGrid.length; i++) {
				int x = i * cellSize;
				for (int j = 0; j < terrainGrid[0].length; j++) {
					int y = j * cellSize;
					terrainColor = terrainGrid[i][j];
					graphics.setColor(terrainColor);
					graphics.fillRect(y, x, cellSize, cellSize);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return image;
	}
	
	public BufferedImage getImage() {
		return this.getImage(this.terrainGrid, this.cellSize);
	}

	public int getWidth() {
		return terrainGrid[1].length * this.cellSize;
	}

	public int getHeight() {
		return terrainGrid.length * this.cellSize;
	}

	public ArrayList<Color> getTerrainAllowedColors() {
		return terrainAllowedColors;
	}

	public void setTerrainAllowedColors(ArrayList<Color> terrainAllowedColors) {
		this.terrainAllowedColors = terrainAllowedColors;
	}
	
	public void addTerrainAllowedColor(Color allowedColor) {
		this.terrainAllowedColors.add(allowedColor);
	}
}