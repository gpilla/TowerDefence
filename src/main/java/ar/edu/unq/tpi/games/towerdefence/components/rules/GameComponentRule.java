package ar.edu.unq.tpi.games.towerdefence.components.rules;

import ar.edu.unq.tpi.games.towerdefence.scene.level.AbstractTowerDefenceLevel;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;

public abstract class GameComponentRule<ComponentType extends GameComponent<AbstractTowerDefenceLevel>> {

	abstract public boolean mustApply(ComponentType component, DeltaState deltaState);

	abstract public void apply(ComponentType component, DeltaState deltaState);
		
}
