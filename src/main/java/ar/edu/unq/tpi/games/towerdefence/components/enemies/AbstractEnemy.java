package ar.edu.unq.tpi.games.towerdefence.components.enemies;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.tpi.games.towerdefence.components.rules.AbstractEnemyRule;
import ar.edu.unq.tpi.games.towerdefence.scene.level.AbstractTowerDefenceLevel;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Animation;
import com.uqbar.vainilla.appearances.Appearance;
import com.uqbar.vainilla.appearances.Sprite;
import com.uqbar.vainilla.sound.Sound;
import com.uqbar.vainilla.sound.SoundBuilder;

public abstract class AbstractEnemy extends GameComponent<AbstractTowerDefenceLevel> {

	private List<AbstractEnemyRule> rules = new ArrayList<AbstractEnemyRule>();
	
	private double lives;

	private final Sound explosionSound;

	private Animation explosionAppearance;
	
	private double destroyDelay = 0;
	
	public AbstractEnemy(double x, double y) {
		this.lives = this.getIntPropertyFromConfig("lives");
		this.setX(x);
		this.setY(y);
		this.setZ(100);
		this.initRules();
		this.setAppearance(this.getDefaultAppearance());
		this.explosionSound = new SoundBuilder().buildSound(this.getStringPropertyFromConfig("explosion.sound"));
		this.createExplosionAppearance();
	}
	
	abstract protected Appearance getDefaultAppearance();

	protected void initRules() {
		this.addRule(new MoveEnemyRule());
	}

	@Override
	public void update(DeltaState deltaState) {
		super.update(deltaState);
		if (this.destroyDelay == 0) {
			for(AbstractEnemyRule rule : this.getRules()) {
				if(rule.mustApply(this, deltaState)) {
					rule.apply(this, deltaState);
				}
			}
		} else {
			this.destroyDelay -= deltaState.getDelta();
			if( this.destroyDelay <= 0 ) {
				this.destroy();
			}
		}
	}

	public List<AbstractEnemyRule> getRules() {
		return this.rules;
	}

	public void setRules(List<AbstractEnemyRule> rules) {
		this.rules = rules;
	}
	
	private void addRule(AbstractEnemyRule rule) {
		this.rules.add(rule);
	}

	public void hit() {
		this.lives --; 
		this.getScene().addPoints();
		if( this.lives == 0 ) {
			this.getScene().addKillPointBonus();
			this.playExplosionSound();
			this.setAppearance(this.explosionAppearance);
			System.out.println(this.explosionAppearance.getDuration());
			this.setX(this.getCenter().getX()-25);
			this.setDestroyDelay(this.explosionAppearance.getDuration());
			this.getScene().removeEnemy(this);
		}
	} 	
	
	protected void createExplosionAppearance() {
		Sprite[] sprites = new Sprite[46];
		for (int i = 1; i <= 46; i++) {
			sprites[i-1] = Sprite.fromImage("images/explosion/"+i+".png").crop(25,65,70,63).scale(0.5);
		}
		this.explosionAppearance = new Animation(0.01, sprites);
	}

	// ---------------------------------------------------------------------------
	// Sonidos
	// ---------------------------------------------------------------------------
	
	private void playExplosionSound() {
		this.explosionSound.play((float) this.getIntPropertyFromConfig("explosion.sound.volume")/100);	
	}

	public double getDestroyDelay() {
		return this.destroyDelay;
	}

	public void setDestroyDelay(double d) {
		this.destroyDelay = d;
	}

}
