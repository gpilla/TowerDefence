package ar.edu.unq.tpi.games.towerdefence.scene.level;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.tpi.games.towerdefence.components.Scenary;
import ar.edu.unq.tpi.games.towerdefence.components.enemies.AbstractEnemy;
import ar.edu.unq.tpi.games.towerdefence.components.gui.MoneyCounter;
import ar.edu.unq.tpi.games.towerdefence.components.gui.PointsCounter;
import ar.edu.unq.tpi.games.towerdefence.util.TowerDefenceImageMapParser;

import com.uqbar.vainilla.GameScene;

public abstract class AbstractTowerDefenceLevel extends GameScene {

	private List<AbstractEnemy> enemies = new ArrayList<AbstractEnemy>();
	protected Scenary scenary;
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
		this.scenary = new Scenary(mapParser);
		this.addComponent(this.scenary);
	}

	protected void initializeEnemies() {

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

}
