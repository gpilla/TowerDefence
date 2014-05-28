package ar.edu.unq.tpi.games.towerdefence.components.enemies;

import ar.edu.unq.tpi.games.towerdefence.scene.level.AbstractTowerDefenceLevel;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;

public class EnemyHorde extends GameComponent<AbstractTowerDefenceLevel>{
	
	private int units = 0;
	private int delay = 0;
	private int delayCountDown = 0;
	
	@Override
	public void update(DeltaState deltaState) {
		this.delayCountDown -= deltaState.getDelta();
		
		if(units > 0 && this.delayCountDown <= 0) {
			AbstractEnemy enemy = new BasicEnemy(this.getX(), this.getY());
			this.getScene().addEnemy(enemy);
			this.delayCountDown = this.getDelay();
			this.units--;
		}

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
