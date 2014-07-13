package ar.edu.unq.tpi.games.towerdefence.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D.Double;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import com.uqbar.vainilla.graphs.Node;
import com.uqbar.vainilla.graphs.Valuable;

import ar.edu.unq.tpi.games.towerdefence.components.units.AbstractTower;
import ar.edu.unq.tpi.games.towerdefence.components.units.BasicTower;

public class ScenaryPopUpMenu extends JPopupMenu implements ActionListener{

	private static final long serialVersionUID = 1L;
	private Scenary scenary;
	private Double position;
	private JMenuItem basicTower;

	public ScenaryPopUpMenu(Scenary scenary, Double position) {
		this.scenary = scenary;
		this.position = position;
		addBasicTowerMenuItem();
	}
	
	
	private void addBasicTowerMenuItem() {
		Double towerPosition = this.scenary.getScene().getMapGraph().obtainPosition(this.position);
    	Node<Valuable> node = this.scenary.getScene().getMapGraph().obtainNode(towerPosition.getX(), towerPosition.getY());
    	
    	if (node==null || node.getElement().value()<=1) {
    		JMenuItem notAllowedMessage = new JMenuItem();
    		notAllowedMessage.setText("¡Camino! : No se puede poner una torre aquí.");
    		notAllowedMessage.addActionListener(this);
    		notAllowedMessage.setEnabled(false);
    		add(notAllowedMessage);	
    	} else {
	    	basicTower = new JMenuItem();
			basicTower.setText("Torre básica");
			basicTower.addActionListener(this);
			add(basicTower);
    	}
		
	}

	public void actionPerformed(ActionEvent e) {
        if (e.getSource() == basicTower) {
        	Double towerPosition = this.scenary.getScene().getMapGraph().obtainPosition(this.position);
    		AbstractTower tower= new BasicTower(towerPosition);
    		this.scenary.addTower(tower);
        }        
    }
	
}
