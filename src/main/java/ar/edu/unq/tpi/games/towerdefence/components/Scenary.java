package ar.edu.unq.tpi.games.towerdefence.components;

import java.awt.Color;
import java.awt.geom.Point2D.Double;

import ar.edu.unq.tpi.games.towerdefence.components.units.AbstractTower;
import ar.edu.unq.tpi.games.towerdefence.components.units.BasicTower;
import ar.edu.unq.tpi.games.towerdefence.scene.level.AbstractTowerDefenceLevel;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Appearance;
import com.uqbar.vainilla.appearances.Rectangle;
import com.uqbar.vainilla.events.constants.MouseButton;
import com.uqbar.vainilla.utils.ResourceUtil;

public class Scenary extends GameComponent<AbstractTowerDefenceLevel> {
	
	public Scenary() {
		this.setAppearance(this.getDefaultAppearance());
	}
	
	private Appearance getDefaultAppearance() {
		return new Rectangle(Color.GREEN, ResourceUtil.getResourceInt("TowerDefence.scenary.width"), ResourceUtil.getResourceInt("TowerDefence.scenary.height") );
	}

	@Override
	public void update(DeltaState deltaState) {
		super.update(deltaState);
		if ( deltaState.isMouseButtonReleased(MouseButton.LEFT) ) {
			this.addTower(deltaState.getLastMousePosition());;
		}
	}

	private void addTower(Double position) {
		AbstractTower tower = new BasicTower(position);
		this.getScene().addComponent(tower);
	}
	
}