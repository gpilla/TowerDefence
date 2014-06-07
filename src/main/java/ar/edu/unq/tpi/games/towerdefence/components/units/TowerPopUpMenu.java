package ar.edu.unq.tpi.games.towerdefence.components.units;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class TowerPopUpMenu extends JPopupMenu implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	JMenuItem levelUpTower;
	private final AbstractTower tower;
	private JMenuItem title;
    
    public TowerPopUpMenu(AbstractTower tower){
    	this.tower = tower;
    	this.setName("Lalalala");
    	this.addTitleMenuItem();
        this.addLevelUpMenuItem();
        
    }

	private void addTitleMenuItem() {
		this.title = new JMenuItem(this.tower.getName() + " (Nivel " + this.tower.getCurrentLevel() + ")");
		this.title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		this.title.setEnabled(false);
		this.add(this.title);
		this.addSeparator();
	}

	private void addLevelUpMenuItem() {
		this.levelUpTower = new JMenuItem();
        this.levelUpTower.addActionListener(this);
        if( this.tower.getCurrentLevel() < this.tower.getMaxLevel()) {
        	this.levelUpTower.setText("Mejorar torre a Nivel " + (this.tower.getCurrentLevel() + 1) + " (Costo: -" + this.tower.getLevelUpCost() + ")");
        } else {
        	this.levelUpTower.setText("Limite de mejoras alcanzado");
        	this.levelUpTower.setEnabled(false);
        }
        this.add(this.levelUpTower);
	}
    
    @Override
	public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.levelUpTower) {
        	this.tower.goLevelUp();
        }        
    }
    
}
