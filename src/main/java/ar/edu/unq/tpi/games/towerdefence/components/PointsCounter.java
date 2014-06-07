package ar.edu.unq.tpi.games.towerdefence.components;

import java.awt.Color;
import java.awt.Font;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.appearances.Label;

public class PointsCounter extends GameComponent<GameScene> {
	
	private int points;
	
	public PointsCounter() {
		this.points = 0;
		this.setAppearance(new Label(new Font(Font.SANS_SERIF, Font.BOLD, 20), Color.YELLOW, "Puntos: " + this.getPoints()));
	}

	@Override
	public void update(DeltaState deltaState) {
		Label label = (Label) this.getAppearance();
		label.setText("Puntos: " + this.getPoints());
	}

	public int getPoints() {
		return this.points;
	}
	
	public void addPoints(int points) {
		this.points += points;
	}
	public void addPoints(String points) {
		this.points += this.getIntPropertyFromConfig(points);
	}
}
