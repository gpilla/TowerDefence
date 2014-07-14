package ar.edu.unq.tpi.games.towerdefence.components;

import java.awt.Color;
import java.awt.geom.Point2D.Double;

import ar.edu.unq.tpi.games.towerdefence.components.enemies.AbstractEnemy;
import ar.edu.unq.tpi.games.towerdefence.scene.level.AbstractTowerDefenceLevel;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Appearance;
import com.uqbar.vainilla.appearances.Rectangle;
import com.uqbar.vainilla.colissions.CollisionDetector;

public class Casttle extends GameComponent<AbstractTowerDefenceLevel> {
	
	public Casttle(Double position){
		super(position.getX(), position.getY());
		this.setAppearance(getDefaultAppearance());
		this.setZ(1000);
	}

	private Appearance getDefaultAppearance() {
		return new Rectangle(Color.YELLOW, 20, 20);
	}

	@Override
	public void update(DeltaState deltaState) {
		for (AbstractEnemy enemy : this.getScene().getEnemies()) {
			if (this.impactEnemy(enemy)) {
				this.getScene().removeEnemy(enemy);
				this.getScene().removeLife();
				enemy.destroy();
				break;
			}
		}
	}

	private boolean impactEnemy(AbstractEnemy enemy) {
		return CollisionDetector.INSTANCE.collidesRectAgainstRect(this.getX(), this.getY(), this.getAppearance().getWidth(), this.getHeight(), enemy.getX(), enemy.getY(), enemy.getAppearance().getWidth(), this.getAppearance().getHeight());
	}
	
}
