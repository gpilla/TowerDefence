package ar.edu.unq.tpi.games.towerdefence.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D.Double;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

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
		basicTower = new JMenuItem();
		basicTower.setText("Torre básica");
		basicTower.addActionListener(this);
		add(basicTower);
	}

	public void actionPerformed(ActionEvent e) {
        if (e.getSource() == basicTower) {
        	Double towerPosition = this.scenary.getMapGraph().obtainPosition(this.position);
        	BasicTower tower= new BasicTower(towerPosition);
        	//TODO: hacer a scenary un observer de la grilla o viceversa
        	if(this.scenary.getMapGraph().addNode(towerPosition.x, towerPosition.y, tower)){
        		this.scenary.addTower(tower);
        	}
        }        
    }
	
}
