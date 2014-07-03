package ar.edu.unq.tpi.games.towerdefence.scene.level;



import ar.edu.unq.tpi.games.towerdefence.components.enemies.EnemyHorde;

public class TowerDefenceLevel1 extends AbstractTowerDefenceLevel {
	
	
	public TowerDefenceLevel1(){

	}
	
	@Override
	protected void initializeComponents() {
		super.initializeComponents();
		EnemyHorde enemyHorde = new EnemyHorde();
		enemyHorde.setX(this.getScenary().getCenter().getX()/2);
		enemyHorde.setY(0); // Una correccion que a pesar de su tamaño salga desde afuera de la pantall
		enemyHorde.setUnits(2);
		enemyHorde.setDelay(50);
		this.addComponent(enemyHorde);
	
	}
	
}
