package ar.edu.unq.tpi.games.towerdefence.components;

import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.List;
import ar.edu.unq.tpi.games.towerdefence.components.units.AbstractTower;
import ar.edu.unq.tpi.games.towerdefence.scene.level.AbstractTowerDefenceLevel;
import ar.edu.unq.tpi.games.towerdefence.util.TowerDefenceTerrainGenerator;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.Game;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Appearance;
import com.uqbar.vainilla.events.constants.MouseButton;

public class Scenary extends GameComponent<AbstractTowerDefenceLevel> {
	
	
	private List<AbstractTower> towers = new ArrayList<AbstractTower>();
	
	@Override
	public void onSceneActivated() {
		super.onSceneActivated();
		this.setAppearance(this.getDefaultAppearance());
	}
	
	private Appearance getDefaultAppearance() {
		TowerDefenceTerrainGenerator terrainGenerator = new TowerDefenceTerrainGenerator(this.getScene().getMapParser(), this.getGame().getDisplayHeight(), this.getGame().getDisplayWidth());
		return terrainGenerator.getSprite();
	}

	@Override
	public void update(DeltaState deltaState) {
		super.update(deltaState);
		if ( deltaState.isMouseButtonReleased(MouseButton.LEFT) ) {
			Double mousePosition = deltaState.getLastMousePosition();
			ScenaryPopUpMenu menu = new ScenaryPopUpMenu(this, mousePosition );
			menu.show(Game.getGameLauncher().getPlayerCanvas(), (int) mousePosition.getX(), (int) mousePosition.getY());
		}
	}

	public void addTower(AbstractTower tower) {
		this.getScene().addComponent(tower);
		this.getTowers().add(tower);
	}

	public List<AbstractTower> getTowers() {
		return towers;
	}

	public void setTowers(List<AbstractTower> towers) {
		this.towers = towers;
	}


}
