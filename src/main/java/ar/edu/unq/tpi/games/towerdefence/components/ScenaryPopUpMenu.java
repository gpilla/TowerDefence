package ar.edu.unq.tpi.games.towerdefence.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D.Double;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import ar.edu.unq.tpi.games.towerdefence.components.units.BasicTower;

public class ScenaryPopUpMenu extends JPopupMenu implements ActionListener{

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
        	this.scenary.addTower(new BasicTower(position));
        }        
    }
	
}
