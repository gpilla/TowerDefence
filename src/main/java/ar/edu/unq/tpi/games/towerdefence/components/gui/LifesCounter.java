package ar.edu.unq.tpi.games.towerdefence.components.gui;

import ar.edu.unq.tpi.games.towerdefence.scene.level.AbstractTowerDefenceLevel;

import com.uqbar.vainilla.appearances.Appearance;
import com.uqbar.vainilla.appearances.Label;
import com.uqbar.vainilla.components.AbstractCounter;

public class LifesCounter extends AbstractCounter<AbstractTowerDefenceLevel> {
	
	public LifesCounter() {
		this.setDescription("Vidas");
	}

	protected Appearance getDefaultAppearance() {
		return new Label(this.getFont(), this.getColor(), this.getDescription().toUpperCase() + ": " + (int) this.getValue() );
	}
	
}
