package ar.edu.unq.tpi.games.towerdefence.components.rules;

import java.awt.geom.Point2D.Double;

import ar.edu.unq.tpi.games.towerdefence.components.units.AbstractTower;
import ar.edu.unq.tpi.games.towerdefence.components.units.TowerPopUpMenu;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.Game;
import com.uqbar.vainilla.events.constants.MouseButton;

public class RightClickTowerRule extends AbstractTowerRule {

	@Override
	public boolean mustApply(AbstractTower component, DeltaState deltaState) {
		if (deltaState.isMouseButtonReleased(MouseButton.LEFT) && this.mouseOverComponent(component, deltaState)) {
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
		//component.goLevelUp();
		Double mousePosition = deltaState.getLastMousePosition();
		TowerPopUpMenu menu = new TowerPopUpMenu(component);
		component.getGame();
		menu.show(Game.getGameLauncher().getPlayerCanvas(), (int) mousePosition.getX(), (int) mousePosition.getY()); 
	}

}
