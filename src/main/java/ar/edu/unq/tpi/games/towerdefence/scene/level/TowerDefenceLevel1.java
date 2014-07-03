package ar.edu.unq.tpi.games.towerdefence.scene.level;



import com.uqbar.vainilla.graphs.MapGraph;
import com.uqbar.vainilla.graphs.Valuable;
import com.uqbar.vainilla.utils.ResourceUtil;

import ar.edu.unq.tpi.games.towerdefence.components.enemies.EnemyHorde;

public class TowerDefenceLevel1 extends AbstractTowerDefenceLevel {
	
	
	public TowerDefenceLevel1(){
		this.setMapGraph(new MapGraph<Valuable>(
				ResourceUtil.getResourceInt("TowerDefenceLevel1.rows"), 
				ResourceUtil.getResourceInt("TowerDefenceLevel1.columns"), 
				ResourceUtil.getResourceInt("TowerDefenceLevel1.height"), 
				ResourceUtil.getResourceInt("TowerDefenceLevel1.width")));
	}
	
	@Override
	protected void initializeComponents() {
		super.initializeComponents();
		EnemyHorde enemyHorde = new EnemyHorde();
		enemyHorde.setX(this.getScenary().getCenter().getX()/2);
		enemyHorde.setY(0); // Una correccion que a pesar de su tamaño salga desde afuera de la pantall
		enemyHorde.setUnits(20);
		enemyHorde.setDelay(100);
		this.addComponent(enemyHorde);
	
	}
	
}
