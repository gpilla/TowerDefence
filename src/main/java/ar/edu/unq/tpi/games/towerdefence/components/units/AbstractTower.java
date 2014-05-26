package ar.edu.unq.tpi.games.towerdefence.components.units;

import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.tpi.games.towerdefence.components.Bullet;
import ar.edu.unq.tpi.games.towerdefence.components.RangeShadow;
import ar.edu.unq.tpi.games.towerdefence.components.enemies.AbstractEnemy;
import ar.edu.unq.tpi.games.towerdefence.components.rules.AbstractTowerRule;
import ar.edu.unq.tpi.games.towerdefence.components.rules.LeftClickTowerRule;
import ar.edu.unq.tpi.games.towerdefence.components.rules.ShootingTowerRule;
import ar.edu.unq.tpi.games.towerdefence.scene.level.AbstractTowerDefenceLevel;
import ar.edu.unq.tpi.games.towerdefence.util.BulletPoolSingleton;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Appearance;
import com.uqbar.vainilla.colissions.CollisionDetector;
import com.uqbar.vainilla.sound.Sound;
import com.uqbar.vainilla.sound.SoundBuilder;
import com.uqbar.vainilla.utils.ResourceUtil;
import com.uqbar.vainilla.utils.Vector2D;

abstract public class AbstractTower extends GameComponent<AbstractTowerDefenceLevel> {
	
	private double shootingDelayCountDown;
	
	private int currentLevel = 1;
	private int maxLevel = 5;
	
	private AbstractEnemy target; 
	
	private List<AbstractTowerRule> rules = new ArrayList<AbstractTowerRule>();
	private RangeShadow rangeShadow;

	private Sound shootSound;
	
	private Vector2D vector  = new Vector2D(0, 1);

	public AbstractTower(Double position) {
		this.setX(position.getX() - getIntPropertyFromConfig("width")/2);
		this.setY(position.getY() - getIntPropertyFromConfig("height")/2);
		this.setZ(100);
		this.initRules();
		this.createRangeShadow();
		this.shootSound = new SoundBuilder().buildSound(getStringPropertyFromConfig("shoot.sound"));
	}
	
	private void createRangeShadow() {
		System.out.println("Creando sombra");
		double x = this.getCenter().getX();
		double y = this.getCenter().getY();
		
		rangeShadow = new RangeShadow(x, y, this.getBulletRange());
		rangeShadow.setZ(this.getZ()-1);
	}

	@Override
	protected void setScene(AbstractTowerDefenceLevel scene) {
		super.setScene(scene);
		//this.getScene().addComponent(rangeShadow);
	}
	private void initRules() {
		this.addRule(new ShootingTowerRule());
		this.addRule(new LeftClickTowerRule());
	}

	protected void addRule(AbstractTowerRule rule) {
		this.getRules().add(rule);
	}

	@Override
	public void update(DeltaState deltaState) {
		//this.setAppearance(this.getDefaultAppearance());
		super.update(deltaState);
		this.decreaseShootDelay(deltaState);
		for(AbstractTowerRule rule : this.getRules()) {
			if(rule.mustApply(this, deltaState)) {
				rule.apply(this, deltaState);
			}
		}
	}
	
	abstract protected Appearance getDefaultAppearance();
	
	public void shoot() {
		if (this.getShootingDelay() <= 0) {
			this.playShootSound();
			Bullet bullet = BulletPoolSingleton.getInstance().obtainBullet();
			bullet.setVector(this.getVector());
			bullet.setRange(this.getBulletRange());
			bullet.setX(this.getX() + this.getWidth()/2 -15);
			bullet.setY(this.getY() + this.getHeight()/2 -15);
			bullet.setSpeed(100);
			this.getScene().addComponent(bullet);
			
			this.setShootingDelay(getIntPropertyFromConfig("bullet.delay"));
		}
	}

	private void playShootSound() {
		this.shootSound.play(1);		
	}

	private void decreaseShootDelay(DeltaState deltaState) {
		if (this.getShootingDelay() > 0) {
			this.setShootingDelay(this.getShootingDelay() - deltaState.getDelta() * 250);
		}
	}

	public double getShootingDelay() {
		return shootingDelayCountDown;
	}

	public void setShootingDelay(double shootingDelay) {
		this.shootingDelayCountDown = shootingDelay;
	}

	public List<AbstractTowerRule> getRules() {
		return rules;
	}

	public void setRules(List<AbstractTowerRule> rules) {
		this.rules = rules;
	}

	public boolean enemyInRange(AbstractEnemy enemy) {
		return CollisionDetector.INSTANCE.collidesCircleAgainstRect(this.getCenter().getX() - this.getBulletRange()
				, this.getCenter().getY() - this.getBulletRange()
				, this.getBulletRange()
				, enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight());
	}

	public double getBulletRange() {
		return getIntPropertyFromConfig("bullet.range");
	}

	public AbstractEnemy getTarget() {
		return target;
	}

	public void setTarget(AbstractEnemy target) {
		this.target = target;
		double x = this.getTarget().getCenter().getX() - this.getCenter().getX();
		double y = this.getTarget().getCenter().getY() - this.getCenter().getY();
		this.setVector(new Vector2D(x, y).asVersor());
	}

	public int getCurrentLevel() {
		return currentLevel;
	}

	public void setCurrentLevel(int level) {
		this.currentLevel = level;
	}

	public int getMaxLevel() {
		return maxLevel;
	}

	public void goLevelUp() {
		if( this.getCurrentLevel() < this.getMaxLevel() ) {
			this.setCurrentLevel(getCurrentLevel() + 1); 
		}
	}
	
	protected double getIntPropertyFromConfig(String resource) {
		return ResourceUtil.getResourceInt(this.getClass().getSimpleName() + ".level" + this.getCurrentLevel() + "." + resource);
	}
	
	protected String getStringPropertyFromConfig(String resource) {
		return ResourceUtil.getResourceString(this.getClass().getSimpleName() + ".level" + this.getCurrentLevel() + "." + resource);
	}

	public Vector2D getVector() {
		return vector;
	}

	public void setVector(Vector2D vector) {
		this.vector = vector;
	}

	public void showRangeShadow() {
		rangeShadow.setVisible(true);
	}
	
	public void hideRangeShadow() {
		rangeShadow.setVisible(false);
	}
}
