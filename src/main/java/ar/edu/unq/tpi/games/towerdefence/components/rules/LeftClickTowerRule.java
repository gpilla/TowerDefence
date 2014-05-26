package ar.edu.unq.tpi.games.towerdefence.components.rules;

import java.awt.geom.Point2D.Double;

import ar.edu.unq.tpi.games.towerdefence.components.units.AbstractTower;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.events.constants.MouseButton;

public class LeftClickTowerRule extends AbstractTowerRule {

	@Override
	public boolean mustApply(AbstractTower component, DeltaState deltaState) {
		if (deltaState.isMouseButtonReleased(MouseButton.RIGHT) && mouseOverComponent(component, deltaState)) {
			return true;
		}
		return false;
	}

	private boolean mouseOverComponent(AbstractTower component,	DeltaState deltaState) {
		Double position = deltaState.getLastMousePosition();;
		if (component.collidesWith(position)) {
			return true;
		}
		return false;
	}

	@Override
	public void apply(AbstractTower component, DeltaState deltaState) {
		component.goLevelUp();
	}

}
