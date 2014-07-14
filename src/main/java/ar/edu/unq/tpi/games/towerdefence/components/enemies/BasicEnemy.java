package ar.edu.unq.tpi.games.towerdefence.components.enemies;

import java.awt.Color;
import java.awt.geom.Point2D.Double;

import com.uqbar.vainilla.appearances.Appearance;
import com.uqbar.vainilla.appearances.Rectangle;

public class BasicEnemy extends AbstractEnemy {

	public BasicEnemy(Double spawnPoint) {
		super(spawnPoint);
	}

	@Override
	protected Appearance getDefaultAppearance() {
		return new Rectangle(Color.BLACK, (int) getIntPropertyFromConfig("width"), (int) getIntPropertyFromConfig("height"));
	}
	
}
