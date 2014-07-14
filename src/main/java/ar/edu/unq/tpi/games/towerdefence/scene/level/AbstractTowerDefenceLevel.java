package ar.edu.unq.tpi.games.towerdefence.scene.level;

import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.tpi.games.towerdefence.components.Casttle;
import ar.edu.unq.tpi.games.towerdefence.components.Scenary;
import ar.edu.unq.tpi.games.towerdefence.components.enemies.AbstractEnemy;
import ar.edu.unq.tpi.games.towerdefence.components.gui.LifesCounter;
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
	private LifesCounter lifesCounter;
	
	private TowerDefenceImageMapParser mapParser;	

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
		
		this.lifesCounter = new LifesCounter();
		this.lifesCounter.setX(500);
		this.addComponent(this.lifesCounter);
	}

	protected void initializeMap() {
		//this.setMapParser(new TowerDefenceImageMapParser("images/maps/" + this.getClass().getSimpleName() + ".png"));
		this.setMapParser(new TowerDefenceImageMapParser("images/maps/miniMapa.png"));
		initializeScenary();
		initializeCasttles();
	}

	private void initializeCasttles() {
		ArrayList<Double> casttlesPositions = this.getMapParser().getEnemyTargetPoints();
		
		for (Double position : casttlesPositions) {
			Casttle casttle = new Casttle(this.convertToScreenPoint(position));
			this.addComponent(casttle);
		}
	}

	private void initializeScenary() {
		this.scenary = new Scenary();
		this.addComponent(this.scenary);
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

	public TowerDefenceImageMapParser getMapParser() {
		return mapParser;
	}

	public void setMapParser(TowerDefenceImageMapParser mapParser) {
		this.mapParser = mapParser;
	}
	
	public double convertToScreenX(double x) {
		return getXDisplayProportion() * x;
	}

	public double getXDisplayProportion() {
		return this.getGame().getDisplayWidth() / this.getMapParser().getWidth();
	}
	
	public double convertToScreenY(double y) {
		return getYDisplayProportion() * y;
	}

	public int getYDisplayProportion() {
		return this.getGame().getDisplayHeight() / this.getMapParser().getHeight();
	}

	public Double convertToScreenPoint(Double point) {
		point.x = point.x * getXDisplayProportion();
		point.y = point.y * getYDisplayProportion();
		return point;
	}

	public void removeLife() {
		this.lifesCounter.removeToValue(1);
	}
	
}
