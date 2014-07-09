package ar.edu.unq.tpi.games.towerdefence.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D.Double;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

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
		basicTower = new JMenuItem();
		basicTower.setText("Torre básica");
		basicTower.addActionListener(this);
		add(basicTower);
	}

	public void actionPerformed(ActionEvent e) {
        if (e.getSource() == basicTower) {
        	Double towerPosition = this.scenary.getScene().getMapGraph().obtainPosition(this.position);
        	AbstractTower tower= new BasicTower(towerPosition);
        	//TODO: hacer a scenary un observer de la grilla o viceversa
        	if(this.scenary.getScene().getMapGraph().addNode(towerPosition.getX(), towerPosition.getY(), tower)){
//        		System.out.println("Se agrego una torre y:" + this.scenary.getScene().getMapGraph().obtainRowNumber(towerPosition.getY()));
//        		System.out.println("Se agrego una torre x:" + this.scenary.getScene().getMapGraph().obtainColNumber(towerPosition.getX()));
        		this.scenary.addTower(tower);
        		this.scenary.getScene().updateObservers(tower);
        	}
        	
        }        
    }
	
}
