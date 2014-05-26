package ar.edu.unq.tpi.games.towerdefence.components.enemies;

import java.awt.Color;

import com.uqbar.vainilla.appearances.Appearance;
import com.uqbar.vainilla.appearances.Rectangle;

public class BasicEnemy extends AbstractEnemy {

	public BasicEnemy(double x, double y) {
		super(x, y);
	}

	@Override
	protected Appearance getDefaultAppearance() {
		return new Rectangle(Color.BLACK, (int) getIntPropertyFromConfig("width"), (int) getIntPropertyFromConfig("height"));
	}
	
}
