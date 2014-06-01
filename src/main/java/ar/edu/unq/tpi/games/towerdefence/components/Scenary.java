package ar.edu.unq.tpi.games.towerdefence.components;

import java.awt.Color;
import java.awt.geom.Point2D.Double;

import ar.edu.unq.tpi.games.towerdefence.components.units.AbstractTower;
import ar.edu.unq.tpi.games.towerdefence.graphs.MapGraph;
import ar.edu.unq.tpi.games.towerdefence.scene.level.AbstractTowerDefenceLevel;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.Game;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Appearance;
import com.uqbar.vainilla.appearances.Rectangle;
import com.uqbar.vainilla.events.constants.MouseButton;
import com.uqbar.vainilla.utils.ResourceUtil;

public class Scenary extends GameComponent<AbstractTowerDefenceLevel> {
	
	private MapGraph<GameComponent<AbstractTowerDefenceLevel>> mapGraph;
	
	public Scenary() {
		this.setAppearance(this.getDefaultAppearance());
		this.setMapGraph(
				new MapGraph<GameComponent<AbstractTowerDefenceLevel>>(
						ResourceUtil.getResourceInt("TowerDefence.scenary.rows"), 
						ResourceUtil.getResourceInt("TowerDefence.scenary.columns"), 
						ResourceUtil.getResourceInt("TowerDefence.scenary.height"), 
						ResourceUtil.getResourceInt("TowerDefence.scenary.width")));
	}
	
	private Appearance getDefaultAppearance() {
		return new Rectangle(Color.GREEN, ResourceUtil.getResourceInt("TowerDefence.scenary.width"), ResourceUtil.getResourceInt("TowerDefence.scenary.height") );
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
	}

	public MapGraph<GameComponent<AbstractTowerDefenceLevel>> getMapGraph() {
		return mapGraph;
	}

	public void setMapGraph(MapGraph<GameComponent<AbstractTowerDefenceLevel>> mapGraph) {
		this.mapGraph = mapGraph;
	}
	
}
