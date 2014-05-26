package ar.edu.unq.tpi.games.towerdefence.scene.level;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.tpi.games.towerdefence.components.Scenary;
import ar.edu.unq.tpi.games.towerdefence.components.enemies.AbstractEnemy;

import com.uqbar.vainilla.GameScene;

public abstract class AbstractTowerDefenceLevel extends GameScene{

	private List<AbstractEnemy> enemies = new ArrayList<AbstractEnemy>();
	Scenary scenary;
	
	@Override
	protected void initializeComponents() {
		this.initializeMap();
	}

	protected void initializeMap() {
		scenary = new Scenary();
		this.addComponent(scenary);
	}
	
	protected void initializeEnemies() {
		
	}

	public List<AbstractEnemy> getEnemies() {
		return enemies;
	}

	public void setEnemies(List<AbstractEnemy> enemies) {
		this.enemies = enemies;
	}
	
	public void addEnemy(AbstractEnemy enemy) {
		this.enemies.add(enemy);
		this.addComponent(enemy);
	}

	public void removeEnemy(AbstractEnemy enemy) {
		this.enemies.remove(enemy);
//		enemy.destroy();
	}
	
}
