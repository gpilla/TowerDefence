package ar.edu.unq.tpi.games.towerdefence.scene.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.uqbar.vainilla.graphs.MapGraph;
import com.uqbar.vainilla.graphs.Valuable;
import com.uqbar.vainilla.utils.ClassLoaderResourcesProvider;

import ar.edu.unq.tpi.games.towerdefence.components.enemies.EnemyHorde;
import ar.edu.unq.tpi.games.towerdefence.util.TowerDefenceTerrainGenerator;

public class TowerDefenceLevel1 extends AbstractTowerDefenceLevel {
	
	
	
	public TowerDefenceLevel1(){
		
	}
	
	@Override
	public void onSetAsCurrent() {
		super.onSetAsCurrent();
		try {
			this.setMapGraph(new MapGraph<Valuable>(ImageIO.read(new ClassLoaderResourcesProvider().getResource("images/map1.png"))));
			TowerDefenceTerrainGenerator terrainGenerator = new TowerDefenceTerrainGenerator(this.getMapParser(), this.getGame().getDisplayHeight(), this.getGame().getDisplayWidth());
			BufferedImage densityMapGraph = terrainGenerator.getImage(this.getMapParser().getUnitDensityMap(), 10);
			MapGraph<Valuable> mapGraph = new MapGraph<Valuable>(densityMapGraph);
		
			this.setMapGraph(mapGraph);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void initializeComponents() {
		super.initializeComponents();
		EnemyHorde enemyHorde = new EnemyHorde();
		enemyHorde.setX(this.getScenary().getCenter().getX()/2);
		enemyHorde.setY(0); // Una correccion que a pesar de su tamaño salga desde afuera de la pantall
		enemyHorde.setUnits(10);
		enemyHorde.setDelay(500);
		this.addComponent(enemyHorde);
	}
	
}
