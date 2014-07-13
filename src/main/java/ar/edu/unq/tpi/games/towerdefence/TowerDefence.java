package ar.edu.unq.tpi.games.towerdefence;

import java.awt.Cursor;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;

import ar.edu.unq.tpi.games.towerdefence.scene.level.TowerDefenceLevel1;
import ar.edu.unq.tpi.games.towerdefence.util.BulletPoolSingleton;

import com.uqbar.vainilla.DesktopGameLauncher;
import com.uqbar.vainilla.Game;
import com.uqbar.vainilla.utils.ClassLoaderResourcesProvider;
import com.uqbar.vainilla.utils.ResourceUtil;

public class TowerDefence extends Game {
	
	private static int SIZE_WIDTH = ResourceUtil.getResourceInt("TowerDefence.game.width");
	private static int SIZE_HEIGHT = ResourceUtil.getResourceInt("TowerDefence.game.height");
	
	
	
	public static void main(String[] args) throws Exception {
		TowerDefence.setGameLauncher(new DesktopGameLauncher(new TowerDefence()));
		getGameLauncher().launch();
	}
	
	public TowerDefence(){
		super();
		this.initializeBulletPool();
		this.playBackgroundMusic();
	}
	
	
	private void playBackgroundMusic() {
		try {
			File midiFile;
			midiFile = new File(new ClassLoaderResourcesProvider().getResource("sounds/bg.mid").getFile());
			Sequence song = MidiSystem.getSequence(midiFile);
			Sequencer midiPlayer = MidiSystem.getSequencer();
			midiPlayer.open();
			midiPlayer.setSequence(song);
			midiPlayer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
			midiPlayer.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	@Override
	protected void setUpScenes() {
		TowerDefenceLevel1 scene = new TowerDefenceLevel1();
		this.setCurrentScene(scene);
	}

	@Override
	public Dimension getDisplaySize() {
		return new Dimension(SIZE_WIDTH, SIZE_HEIGHT);
	}

	@Override
	public String getTitle() {
		return ResourceUtil.getResourceString("TowerDefence.title");
	}

	@Override
	protected void initializeResources() {
		
	}
	
	private void initializeBulletPool() {
		for(int i=0; i<25; i++){
			BulletPoolSingleton.getInstance().obtainBullet();
		}
	}
	
	public void setCursor(Cursor cursor) {
		TowerDefence.getGameLauncher().setCursor(cursor);
		TowerDefence.getGameLauncher().pack();
	}
}
