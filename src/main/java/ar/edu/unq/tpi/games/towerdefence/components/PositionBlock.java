package ar.edu.unq.tpi.games.towerdefence.components;

import java.awt.Color;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.appearances.Rectangle;
import com.uqbar.vainilla.events.constants.MouseButton;

public class PositionBlock extends GameComponent<GameScene> {

	
	private int width;
	private int height;

	public PositionBlock(int width, int height) {
		this.width = width;
		this.height = height;
		this.setAppearance(getDefaultAppearance());
	}

	private Rectangle getDefaultAppearance() {
		return new Rectangle(Color.RED, this.width, this.height);
	}
	
	@Override
	public void update(DeltaState deltaState) {
		super.update(deltaState);
		if ( deltaState.isMouseButtonReleased(MouseButton.LEFT) ) {
			
		}
	}
	
}
