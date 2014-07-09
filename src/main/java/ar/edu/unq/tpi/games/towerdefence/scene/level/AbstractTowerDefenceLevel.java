package ar.edu.unq.tpi.games.towerdefence.scene.level;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.tpi.games.towerdefence.components.PointsCounter;
import ar.edu.unq.tpi.games.towerdefence.components.Scenary;
import ar.edu.unq.tpi.games.towerdefence.components.enemies.AbstractEnemy;
import ar.edu.unq.tpi.games.towerdefence.components.units.AbstractTower;

import com.uqbar.vainilla.GraphGameScene;

public abstract class AbstractTowerDefenceLevel extends GraphGameScene {

	private List<AbstractEnemy> enemies = new ArrayList<AbstractEnemy>();
	private Scenary scenary;
	private PointsCounter counter;
	private List<AbstractTower> towers = new ArrayList<AbstractTower>();

	@Override
	protected void initializeComponents() {
		this.initializeMap();
		this.initializeCounter();
	}

	private void initializeCounter() {
		this.counter = new PointsCounter();
		this.addComponent(this.counter);
	}

	protected void initializeMap() {
		this.scenary = new Scenary();
		this.addComponent(this.scenary);
	}

	protected void initializeEnemies() {

	}
	
	public void updateObservers(AbstractTower tower) {
		for (AbstractEnemy enemy : this.getEnemies()) {
			enemy.updateStatus(tower);
		}
		
	}

	
	public void updateObservers(){

	}

	public List<AbstractEnemy> getEnemies() {
		return this.enemies;
	}

	public void setEnemies(List<AbstractEnemy> enemies) {
		this.enemies = enemies;
	}

	public void addEnemy(AbstractEnemy enemy) {
		this.enemies.add(enemy);
		this.addComponent(enemy);
		this.updateObservers();
	}

	public void removeEnemy(AbstractEnemy enemy) {
		this.enemies.remove(enemy);
		// enemy.destroy();
	}

	public Scenary getScenary() {
		return this.scenary;
	}

	public void setScenary(Scenary scenary) {
		this.scenary = scenary;
	}

	public PointsCounter getCounter() {
		return this.counter;
	}

	public void setCounter(PointsCounter counter) {
		this.counter = counter;
	}

	public void addPoints() {
		this.counter.addPoints("hitPoints");
	}

	public void addKillPointBonus() {
		this.counter.addPoints("killPoints");
	}

	public List<AbstractTower> getTowers() {
		return towers;
	}

	public void setTowers(List<AbstractTower> towers) {
		this.towers = towers;
	}


}
