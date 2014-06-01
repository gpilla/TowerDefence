package ar.edu.unq.tpi.games.towerdefence.components.units;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class TowerPopUpMenu extends JPopupMenu implements ActionListener {
	
    JMenuItem levelUpTower;
	private AbstractTower tower;
	private JMenuItem title;
    
    public TowerPopUpMenu(AbstractTower tower){
    	this.tower = tower;
    	this.setName("Lalalala");
    	addTitleMenuItem();
        addLevelUpMenuItem();
        
    }

	private void addTitleMenuItem() {
		title = new JMenuItem(this.tower.getName() + " (Nivel " + this.tower.getCurrentLevel() + ")");
		title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		title.setEnabled(false);
		this.add(title);
		this.addSeparator();
	}

	private void addLevelUpMenuItem() {
		levelUpTower = new JMenuItem();
        levelUpTower.addActionListener(this);
        if( tower.getCurrentLevel() < tower.getMaxLevel()) {
        	levelUpTower.setText("Mejorar torre a Nivel " + (tower.getCurrentLevel() + 1) + " (Costo: -" + this.tower.getLevelUpCost() + ")");
        } else {
        	levelUpTower.setText("Limite de mejoras alcanzado");
        	levelUpTower.setEnabled(false);
        }
        add(levelUpTower);
	}
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == levelUpTower) {
        	tower.goLevelUp();
        }        
    }
    
}
