package ar.edu.unq.tpi.games.towerdefence.components.enemies;

import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.tpi.games.towerdefence.components.rules.AbstractEnemyRule;
import ar.edu.unq.tpi.games.towerdefence.components.units.AbstractTower;
import ar.edu.unq.tpi.games.towerdefence.scene.level.AbstractTowerDefenceLevel;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Animation;
import com.uqbar.vainilla.appearances.Appearance;
import com.uqbar.vainilla.graphs.BasicValuable;
import com.uqbar.vainilla.graphs.MapGraph;
import com.uqbar.vainilla.graphs.Node;
import com.uqbar.vainilla.graphs.Valuable;
import com.uqbar.vainilla.sound.Sound;

public abstract class AbstractEnemy extends GameComponent<AbstractTowerDefenceLevel> implements Valuable {

	private List<AbstractEnemyRule> rules = new ArrayList<AbstractEnemyRule>();
	
	private Double mapPosition;
	
	private double lives;
	
	private Sound explosionSound;
	private Animation explosionAppearance;
	private double destroyDelay = 0;
	private double waitingTime = 0;
	
	private MapGraph<Valuable> mapGraph;
	private List<Node<Valuable>> path = null;

	public AbstractEnemy(Double position) {
		super();
		this.setX(position.getX());
		this.setY(position.getY());
		this.setZ(100);
		
		//this.setMapPosition(position);
		
		this.lives = this.getIntPropertyFromConfig("lives");
		this.initializeAppearanceAndSound();
		this.initRules();
	}
	
	private void initializeAppearanceAndSound() {
		this.explosionSound = EnemyHelper.getExplosionSound();
		this.setAppearance(this.getDefaultAppearance());
		this.explosionAppearance = EnemyHelper.getExplosionAppearance();
	}

//	public void initGraph(){
//		try {
//			this.setMapGraph(new MapGraph<Valuable>(ImageIO.read(new ClassLoaderResourcesProvider().getResource("images/map1.png"))));
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		this.initTowers();
//	}

//	private void initTowers() {
//		if(this.getScene()!=null){
//			for(AbstractTower tower : this.getScene().getTowers()){
//				this.updateStatus(tower);
//			}
//		}else{
//			System.out.println("NULLLL");
//		}
//		
//	}

	abstract protected Appearance getDefaultAppearance();

	protected void initRules() {
		this.addRule(new MoveEnemyRule());
	}
	
	public void updateStatus(AbstractTower tower) {
		int column = this.getMapGraph().obtainColNumber(tower.getX());
		int row = this.getMapGraph().obtainRowNumber(tower.getY());
		System.out.println("AbstracyEnemy - Se agrego una torre y:" + row);
		System.out.println("AbstracyEnemy - Se agrego una torre x:" + column);
		Valuable valuable = new BasicValuable(Integer.MAX_VALUE);
		this.getMapGraph().addNode(row, column, valuable);
	}
	
	
	@Override
	public void updateStatus(){
		//this.getPath().clear();
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
		this.setWaitingTime(this.getWaitingTime() + delta * 250);
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
		this.lives--; 
		if( this.lives == 0 ) {
			this.killAction();
		} else {
			this.hitAction();
		}
	}

	private void hitAction() {
		this.getScene().addPoints(this.getIntPropertyFromConfig("hit.points"));
		this.getScene().addMoney(this.getIntPropertyFromConfig("hit.money"));
	}

	private void killAction() {
		this.explosionAnimation();
		this.getScene().removeEnemy(this);
		this.getScene().addPoints(this.getIntPropertyFromConfig("kill.points"));
		this.getScene().addMoney(this.getIntPropertyFromConfig("kill.money"));
	}

	private void explosionAnimation() {
		this.playExplosionSound();
		this.setAppearance(this.explosionAppearance);
		System.out.println(this.explosionAppearance.getDuration());
		this.setX(this.getCenter().getX()-25);
		this.setDestroyDelay(this.explosionAppearance.getDuration());
	} 	
	
	public Node<Valuable> obtainNextPosition(){
		
		if (this.getPath() == null) {
			this.generatePath();
		}
		
		if ( this.getPath().size() > 0 ) {
			Node<Valuable> nextPosition = this.getPath().get(0);
			this.getPath().remove(0);
			return nextPosition;
		}else{
			return null;
		}
		
	}

	private void generatePath() {
		Node<Valuable> target = this.obtainTarget();
		this.setPath(this.getMapGraph().getShortestPath(mapGraph.obtainNode(this.getX(),this.getY()),target));
	}
	
	private Node<Valuable> obtainTarget() {
		ArrayList<Double> targets = this.getScene().getMapParser().getEnemyTargetPoints();
		//List <Point> targets = this.getMapGraph().getColorsMap().get(1237980);
		int index = (int) (Math.random() * (targets.size()));
		Double target = targets.get(index);
		double x = target.getX() * 10; // FIXME
		double y = target.getY() * 10; // FIXME
		return this.getMapGraph().obtainNode(x, y);
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

	public Double getMapPosition() {
		return mapPosition;
	}

	public void setMapPosition(Double mapPosition) {
		this.mapPosition = mapPosition;
	}

}
