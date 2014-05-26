package ar.edu.unq.tpi.games.towerdefence.components.enemies;

import com.uqbar.vainilla.DeltaState;

import ar.edu.unq.tpi.games.towerdefence.components.rules.AbstractEnemyRule;

public class MoveEnemyRule extends AbstractEnemyRule {

	@Override
	public boolean mustApply(AbstractEnemy component, DeltaState deltaState) {
		return true;
	}

	@Override
	public void apply(AbstractEnemy component, DeltaState deltaState) {
		component.move(0, deltaState.getDelta() * 100);
	}

}
