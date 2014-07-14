package ar.edu.unq.tpi.games.towerdefence.components.enemies;

import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.Iterator;

import ar.edu.unq.tpi.games.towerdefence.scene.level.AbstractTowerDefenceLevel;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;

public class EnemyHorde extends GameComponent<AbstractTowerDefenceLevel>{
	
	private int units = 0;
	private int delay = 0;
	private int delayCountDown = 0;
	private ArrayList<Double> spawnPoints;
	
	@Override
	public void onSceneActivated() {
		spawnPoints = this.getScene().getMapParser().getEnemySpawnPoints();
		for (Double spawnPoint : spawnPoints) {
			this.getScene().convertToScreenPoint(spawnPoint);
		}
		//this.getScene().getMapGraph();
	}
	
	@Override
	public void update(DeltaState deltaState) {
		this.delayCountDown -= deltaState.getDelta();
		if (units > 0 && this.delayCountDown <= 0) {
			int index = (int) (Math.random() * (spawnPoints.size()));
			Double spawnPoint = spawnPoints.get(index);
			AbstractEnemy enemy = new BasicEnemy(spawnPoint);
			enemy.setMapGraph(this.getScene().getMapGraph());
			this.getScene().addEnemy(enemy);
			this.resetDelayCountDown();
			this.units--;
		}
	}

	private void resetDelayCountDown() {
		this.delayCountDown = this.getDelay();
	}

	public int getUnits() {
		return units;
	}

	public void setUnits(int units) {
		this.units = units;
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	public int getDelayCountDown() {
		return delayCountDown;
	}

	public void setDelayCountDown(int delayCountDown) {
		this.delayCountDown = delayCountDown;
	}


}
