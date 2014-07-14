package ar.edu.unq.tpi.games.towerdefence.components.enemies;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.graphs.MapGraph;
import com.uqbar.vainilla.graphs.Node;
import com.uqbar.vainilla.graphs.Valuable;

import ar.edu.unq.tpi.games.towerdefence.components.rules.AbstractEnemyRule;

public class MoveEnemyRule extends AbstractEnemyRule {

	@Override
	public boolean mustApply(AbstractEnemy component, DeltaState deltaState) {
		return true;
	}

	@Override
	public void apply(AbstractEnemy component, DeltaState deltaState) {
		MapGraph<Valuable> mapGraph = component.getMapGraph();
		Node<Valuable> source = mapGraph.obtainNode(component.getX(), component.getY());
		Node<Valuable> nextPosition = component.obtainNextPosition();
		if (nextPosition != null) {
			source.setMinDistance(Integer.MAX_VALUE);
			source.setPrevious(null);
			component.setX((int)nextPosition.getColumn());
			component.setY((int)nextPosition.getRow());
			nextPosition.setMinDistance(Integer.MAX_VALUE);
			nextPosition.setPrevious(null);
		}
//		System.out.println(component.getX() + ";" + component.getY());
	}

}
