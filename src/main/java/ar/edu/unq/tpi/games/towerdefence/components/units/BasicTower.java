package ar.edu.unq.tpi.games.towerdefence.components.units;

import java.awt.Color;
import java.awt.geom.Point2D.Double;

import com.uqbar.vainilla.appearances.Appearance;
import com.uqbar.vainilla.appearances.Rectangle;


public class BasicTower extends AbstractTower {

	public BasicTower(Double position) {
		super(position);
		this.setAppearance(this.getDefaultAppearance());
	}

	@Override
	protected Appearance getDefaultAppearance() {
		return new Rectangle(Color.BLUE, (int) this.getIntPropertyFromConfig("width"), (int) this.getIntPropertyFromConfig("width"));
//		double angle = this.getVector().asVersor().angle() % 2;
//		if( angle < 0 ) {
//			angle = 2 + angle;
//		}
//		double sprite = 36 / 2 * angle;
		//return TowerSpriteManager.INSTANCE.getSprite(36 - (int) sprite);
		//return TowerSpriteManager.INSTANCE.getSprite(0);
//		return new Animation(0.05, TowerSpriteManager.INSTANCE.getSprites());
	}
	
}
