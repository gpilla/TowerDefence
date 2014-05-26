package ar.edu.unq.tpi.games.towerdefence.components;

import ar.edu.unq.tpi.games.towerdefence.components.enemies.AbstractEnemy;
import ar.edu.unq.tpi.games.towerdefence.scene.level.AbstractTowerDefenceLevel;
import ar.edu.unq.tpi.games.towerdefence.util.BulletPoolSingleton;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.MovableComponent;
import com.uqbar.vainilla.appearances.Sprite;
import com.uqbar.vainilla.colissions.CollisionDetector;

public class Bullet extends MovableComponent<AbstractTowerDefenceLevel> {
	
	private double range;

	public Bullet(){
		super();
		this.setAppearance(getDefaultAppearance());
		this.setSpeed(this.getIntPropertyFromConfig("speed"));
		this.setZ(1000);
	}

	private Sprite getDefaultAppearance() {
		return Sprite.fromImage(this.getStringPropertyFromConfig("sprite"));
	}

	@Override
	public void update(DeltaState deltaState) {
		double advance = (this.getIntPropertyFromConfig("speed")) * deltaState.getDelta();
		this.move(advance * this.getVector().getX(), advance
				* this.getVector().getY());
		for (AbstractEnemy enemy : this.getScene().getEnemies()) {
			if (this.impactEnemy(enemy)) {
				enemy.hit();
				BulletPoolSingleton.getInstance().returnBullet(this);
				break;
			}
		}
	
		this.move(advance * this.getVector().getX(), advance * this.getVector().getY());
		this.reduceRange(advance);
	}

	private boolean impactEnemy(AbstractEnemy enemy) {
		return CollisionDetector.INSTANCE.collidesCircleAgainstRect(this
				.getX(), this.getY(), 20,
				enemy.getX(), enemy.getY(), enemy
						.getAppearance().getWidth(), enemy
						.getAppearance().getHeight());
	}
	
	private void reduceRange(double advance) {
		if ( this.range > 0 ) {
			this.range -= advance;
		} else {
			this.destroy();
		}
	}

	public void reset(){
		this.setDestroyPending(false);
	}

	public double obtainAbsoluteX() {
		return this.getX() + this.getRadius() * 2;
	}

	public double obtainAbsoluteY() {
		return this.getY() + this.getRadius() * 2;
	}

	public int getCenterX() {
		return (int) (this.getX() + this.getRadius());
	}

	public int getCenterY() {
		return (int) (this.getY() + this.getRadius());
	}

	public void inverseHorizontalDirection() {
		this.getVector().setX(this.getVector().getX() * -1);
	}

	public void inverseVerticalDirection() {
		this.getVector().setY(this.getVector().getY() * -1);
	}

	public double getRadius() {
		return this.getIntPropertyFromConfig("radius");
	}

	public double getRange() {
		return range;
	}

	public void setRange(double range) {
		this.range = range;
	}

}
