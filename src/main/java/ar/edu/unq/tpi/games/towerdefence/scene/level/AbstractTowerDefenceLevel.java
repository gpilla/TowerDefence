package ar.edu.unq.tpi.games.towerdefence.scene.level;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.tpi.games.towerdefence.components.Scenary;
import ar.edu.unq.tpi.games.towerdefence.components.enemies.AbstractEnemy;
import ar.edu.unq.tpi.games.towerdefence.components.gui.MoneyCounter;
import ar.edu.unq.tpi.games.towerdefence.components.gui.PointsCounter;
import ar.edu.unq.tpi.games.towerdefence.util.TowerDefenceImageMapParser;
import ar.edu.unq.tpi.games.towerdefence.components.units.AbstractTower;


import com.uqbar.vainilla.GraphGameScene;

public abstract class AbstractTowerDefenceLevel extends GraphGameScene {

	private List<AbstractEnemy> enemies = new ArrayList<AbstractEnemy>();

	protected Scenary scenary;
	
	private List<AbstractTower> towers = new ArrayList<AbstractTower>();
	
	private PointsCounter pointsCounter;
	private MoneyCounter moneyCounter;

	@Override
	protected void initializeComponents() {
		this.initializeMap();
		this.initializeCounters();
	}

	private void initializeCounters() {
		this.pointsCounter = new PointsCounter();
		this.addComponent(this.pointsCounter);
		
		this.moneyCounter = new MoneyCounter();
		this.moneyCounter.setX(200);
		this.addComponent(this.moneyCounter);
	}

	protected void initializeMap() {
		TowerDefenceImageMapParser mapParser = new TowerDefenceImageMapParser("images/maps/" + this.getClass().getSimpleName() + ".png");
		mapParser.getTerrainGrid();
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
		return this.pointsCounter;
	}

	public void setCounter(PointsCounter counter) {
		this.pointsCounter = counter;
	}
	
	public void addPoints(double points) {
		this.pointsCounter.addToValue(points);
	}
	
	public void addMoney(double money) {
		this.moneyCounter.addToValue(money);
	}

	public List<AbstractTower> getTowers() {
		return towers;
	}

	public void setTowers(List<AbstractTower> towers) {
		this.towers = towers;
	}


}
