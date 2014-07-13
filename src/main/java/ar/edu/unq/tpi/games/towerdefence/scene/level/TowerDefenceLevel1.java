package ar.edu.unq.tpi.games.towerdefence.scene.level;




import java.io.IOException;

import javax.imageio.ImageIO;

import com.uqbar.vainilla.graphs.MapGraph;
import com.uqbar.vainilla.graphs.Valuable;
import com.uqbar.vainilla.utils.ClassLoaderResourcesProvider;
import com.uqbar.vainilla.utils.ResourceUtil;

import ar.edu.unq.tpi.games.towerdefence.components.enemies.EnemyHorde;

public class TowerDefenceLevel1 extends AbstractTowerDefenceLevel {
	
	
	
	public TowerDefenceLevel1(){
		try {
			this.setMapGraph(new MapGraph<Valuable>(ImageIO.read(new ClassLoaderResourcesProvider().getResource("images/map1.png"))));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	protected void initializeComponents() {
		super.initializeComponents();
		EnemyHorde enemyHorde = new EnemyHorde();
		enemyHorde.setX(this.getScenary().getCenter().getX()/2);
		enemyHorde.setY(0); // Una correccion que a pesar de su tamaño salga desde afuera de la pantall
		enemyHorde.setUnits(20);
		enemyHorde.setDelay(500);
		this.addComponent(enemyHorde);
	
	}
	
}
