package ar.edu.unq.tpi.games.towerdefence.components.rules;

import ar.edu.unq.tpi.games.towerdefence.components.enemies.AbstractEnemy;
import ar.edu.unq.tpi.games.towerdefence.components.units.AbstractTower;

import com.uqbar.vainilla.DeltaState;

public class ShootingTowerRule extends AbstractTowerRule {

	@Override
	public boolean mustApply(AbstractTower component, DeltaState deltaState) {
		for (AbstractEnemy enemy : component.getScene().getEnemies()) {
			if (component.enemyInRange(enemy)) {
				component.showRangeShadow();
				component.setTarget(enemy);
				return true;
			}
		}
		component.hideRangeShadow();
		return false;
	}

	@Override
	public void apply(AbstractTower component, DeltaState deltaState) {
		component.shoot();
	}

}
