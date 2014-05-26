package ar.edu.unq.tpi.games.towerdefence.components.units;

import com.uqbar.vainilla.appearances.Appearance;
import com.uqbar.vainilla.appearances.Sprite;
import com.uqbar.vainilla.utils.Vector2D;

public class TowerSpriteManager {

	public static final TowerSpriteManager INSTANCE = new TowerSpriteManager();
	
	private Sprite[] sprites = new Sprite[36];

	public TowerSpriteManager() {
		System.out.println("Cargando sprites de la torre.");
		this.init();
		System.out.println("Finalizada la carga de sprites de la torre.");
	}
	
	private void init() {
		generateSprites();
	}

	private void generateSprites() {
		int[ ][ ] num={
				{256,0},
				{320,0},
				{384,0},
				{448,0},
				{512,0},
				{576,0},
				{640,0},
				{704,0},
				{0,64},
				{64,64},
				{128,64},
				{192,64},
				{256,64},
				{320,64},
				{384,64},
				{448,64},
				{512,64},
				{576,64},
				{640,64},
				{704,64},
				{0,128},
				{64,128},
				{128,128},
				{192,128},
				{256,128},
				{320,128},
				{384,128},
				{448,128},
				{512,128},
				{576,128},
				{640,128},
				{704,128},
				{0,0},
				{64,0},
				{128,0},
				{192,0},
			};
		int x;
		int y;
		for (int i = 0; i < num.length; i++) {
			x = num[i][0];
			y = num[i][1];
			this.sprites[i] = Sprite.fromImage("images/towerSprite.png").crop(x, y, 64, 64);
		}
	}

	public Appearance getSprite(int i) {
		return getSprites()[i];
	}
	
	public Appearance getSpriteFromVector(Vector2D vector) {
//		System.out.println("Angulo:" + vector.angle());
//		System.out.println((35/360 * (vector.angle() * 180/Math.PI)));
		int sprite = (int) (35/360 * (vector.angle() * 180/Math.PI));
//		System.out.println("Sprite" + sprite);
		return getSprites()[sprite];
	}

	public Sprite[] getSprites() {
		return sprites;
	}

	public void setSprites(Sprite[] sprites) {
		this.sprites = sprites;
	}
	
}
