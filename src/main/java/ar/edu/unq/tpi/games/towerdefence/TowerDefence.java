package ar.edu.unq.tpi.games.towerdefence;

import java.awt.Cursor;
import java.awt.Dimension;

import ar.edu.unq.tpi.games.towerdefence.scene.level.TowerDefenceLevel1;
import ar.edu.unq.tpi.games.towerdefence.util.BulletPoolSingleton;

import com.uqbar.vainilla.DesktopGameLauncher;
import com.uqbar.vainilla.Game;
import com.uqbar.vainilla.utils.ResourceUtil;

public class TowerDefence extends Game {
	
	private static int SIZE_WIDTH = ResourceUtil.getResourceInt("TowerDefence.game.width");
	private static int SIZE_HEIGHT = ResourceUtil.getResourceInt("TowerDefence.game.height");
	
	public static void main(String[] args) throws Exception {
		TowerDefence.setGameLauncher(new DesktopGameLauncher(new TowerDefence()));
		getGameLauncher().launch();
	}
	
	public TowerDefence(){
		super();
		this.initializeBulletPool();
	}
	
	
	@Override
	protected void setUpScenes() {
		TowerDefenceLevel1 scene = new TowerDefenceLevel1();
		this.setCurrentScene(scene);
	}

	@Override
	public Dimension getDisplaySize() {
		return new Dimension(SIZE_WIDTH, SIZE_HEIGHT);
	}

	@Override
	public String getTitle() {
		return ResourceUtil.getResourceString("TowerDefence.title");
	}

	@Override
	protected void initializeResources() {
		
	}
	
	private void initializeBulletPool() {
		for(int i=0; i<25; i++){
			BulletPoolSingleton.getInstance().obtainBullet();
		}
	}
	
	public void setCursor(Cursor cursor) {
		TowerDefence.getGameLauncher().setCursor(cursor);
		TowerDefence.getGameLauncher().pack();
	}
}
