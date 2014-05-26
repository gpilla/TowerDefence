package ar.edu.unq.tpi.games.towerdefence.components;

import java.awt.Color;

import ar.edu.unq.tpi.games.towerdefence.scene.level.AbstractTowerDefenceLevel;

import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Appearance;
import com.uqbar.vainilla.appearances.Circle;
import com.uqbar.vainilla.appearances.Invisible;

public class RangeShadow extends GameComponent<AbstractTowerDefenceLevel> {

	private double radius;
	
	public RangeShadow(double x, double y, double radius) {
		this.setX(x-radius);
		this.setY(y-radius);
		this.setRadius(radius);
		this.setAppearance(this.getDefaultAppearance());
	}

	private Appearance getDefaultAppearance() {
		return new Circle(Color.CYAN, (int) (this.radius * 2));
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public void setVisible(boolean visible) {
		if (visible) {
			this.setAppearance(this.getDefaultAppearance());
		} else {
			this.setAppearance(new Invisible());
		}
	}	
}
