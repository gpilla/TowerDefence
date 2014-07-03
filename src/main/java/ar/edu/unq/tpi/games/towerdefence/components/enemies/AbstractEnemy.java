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
import com.uqbar.vainilla.graphs.MapGraph;
import com.uqbar.vainilla.graphs.Node;
import com.uqbar.vainilla.graphs.Valuable;
import com.uqbar.vainilla.sound.Sound;
import com.uqbar.vainilla.sound.SoundBuilder;
import com.uqbar.vainilla.utils.Observer;
import com.uqbar.vainilla.utils.ResourceUtil;

public abstract class AbstractEnemy extends GameComponent<AbstractTowerDefenceLevel> implements Valuable {

	private List<AbstractEnemyRule> rules = new ArrayList<AbstractEnemyRule>();
	private double lives;
	private final Sound explosionSound;
	private Animation explosionAppearance;
	private double destroyDelay = 0;
	private double waitingTime = 0;
	private MapGraph<Valuable> mapGraph;
	private List<Node<Valuable>> path = new ArrayList<Node<Valuable>>();



	public AbstractEnemy(double x, double y) {
		this.lives = this.getIntPropertyFromConfig("lives");
		this.setX(x);
		this.setY(y);
		this.setZ(100);
		this.initRules();
		this.setAppearance(this.getDefaultAppearance());
		this.explosionSound = new SoundBuilder().buildSound(this.getStringPropertyFromConfig("explosion.sound"));
		this.createExplosionAppearance();
		this.setMapGraph(new MapGraph<Valuable>(
				ResourceUtil.getResourceInt("TowerDefenceLevel1.rows"), 
				ResourceUtil.getResourceInt("TowerDefenceLevel1.columns"), 
				ResourceUtil.getResourceInt("TowerDefenceLevel1.height"), 
				ResourceUtil.getResourceInt("TowerDefenceLevel1.width")));		
		
	}


	abstract protected Appearance getDefaultAppearance();

	protected void initRules() {
		this.addRule(new MoveEnemyRule());
	}

	@Override
	public void updateStatus(){
		this.getPath().clear();
	}
	
	@Override
	public void update(DeltaState deltaState) {
		super.update(deltaState);
		if (this.destroyDelay == 0) {
			if(this.canMove()){
				for(AbstractEnemyRule rule : this.getRules()) {
					if(rule.mustApply(this, deltaState)) {
						rule.apply(this, deltaState);
					}
				}
				this.setWaitingTime(0);
			}else{
				this.increaseWaitingTime(deltaState.getDelta());
			}
		} else {
			this.destroyDelay -= deltaState.getDelta();
			if( this.destroyDelay <= 0 ) {
				this.destroy();
			}
		}
	}
	
	private void increaseWaitingTime(double delta) {
		this.setWaitingTime(this.getWaitingTime() + delta * 550);
	}

	private boolean canMove() {
		return this.getWaitingTime()>1;
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
	
	public Node<Valuable> obtainNextPosition(Node<Valuable> target){
		
		if(this.getPath().size()==0){
			this.setPath(this.getMapGraph().getShortestPath(mapGraph.obtainNode(this.getX(),this.getY()),target));
			if(this.getPath().size()>0){
				return this.getPath().get(0);
			}else{
				return null;
			}
		}
		Node<Valuable> nextPosition = this.getPath().get(0);
		this.getPath().remove(0);
		return nextPosition;
		
	}
	
	protected void createExplosionAppearance() {
		Sprite[] sprites = new Sprite[46];
		for (int i = 1; i <= 46; i++) {
			sprites[i-1] = Sprite.fromImage("images/explosion/"+i+".png").crop(25,65,70,63).scale(0.5);
		}
		this.explosionAppearance = new Animation(0.01, sprites);
	}
	
	public int value(){
		return Integer.MAX_VALUE;
	}
	
	public void changeValue(int value){	
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

	protected double getWaitingTime() {
		return waitingTime;
	}


	protected void setWaitingTime(double waitingTime) {
		this.waitingTime = waitingTime;
	}


	protected MapGraph<Valuable> getMapGraph() {
		return mapGraph;
	}


	protected void setMapGraph(MapGraph<Valuable> mapGraph) {
		this.mapGraph = mapGraph;
	}


	protected List<Node<Valuable>> getPath() {
		return path;
	}


	protected void setPath(List<Node<Valuable>> path) {
		this.path = path;
	}
	
}
