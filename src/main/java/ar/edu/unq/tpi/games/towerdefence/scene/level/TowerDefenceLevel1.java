package ar.edu.unq.tpi.games.towerdefence.scene.level;

import ar.edu.unq.tpi.games.towerdefence.components.enemies.EnemyHorde;

public class TowerDefenceLevel1 extends AbstractTowerDefenceLevel {
	
	@Override
	protected void initializeComponents() {
		super.initializeComponents();
		EnemyHorde enemyHorde = new EnemyHorde();
		enemyHorde.setX(scenary.getCenter().getX()/2);
		enemyHorde.setY(-100); // Una correccion que a pesar de su tamaño salga desde afuera de la pantall
		enemyHorde.setUnits(10);
		enemyHorde.setDelay(500);
		this.addComponent(enemyHorde);
	
	}

}
