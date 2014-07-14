package ar.edu.unq.tpi.games.towerdefence.components.enemies;

import com.uqbar.vainilla.appearances.Animation;
import com.uqbar.vainilla.appearances.Sprite;
import com.uqbar.vainilla.sound.Sound;
import com.uqbar.vainilla.sound.SoundBuilder;

public class EnemyHelper {
	
	private static Animation explosionAnimation = null;
	private static Sound explosionSound = null;
	
	public static Animation getExplosionAppearance() {
		if (explosionAnimation == null) {
			createExplosionAppearance();
		}
		return explosionAnimation;
	}
	
	private static void createExplosionAppearance() {
		Sprite[] sprites = new Sprite[46];
		for (int i = 1; i <= 46; i++) {
			sprites[i-1] = Sprite.fromImage("images/explosion/"+i+".png").crop(25,65,70,63).scale(0.5);
		}
		explosionAnimation = new Animation(0.01, sprites);
	}

	public static Sound getExplosionSound() {
		if ( explosionSound == null ) {
			explosionSound = new SoundBuilder().buildSound("/sounds/missile.wav");
		}
 		return explosionSound;
	}

}
