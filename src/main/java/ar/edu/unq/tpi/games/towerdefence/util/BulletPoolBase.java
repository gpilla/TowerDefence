package ar.edu.unq.tpi.games.towerdefence.util;

import nf.fr.eraasoft.pool.PoolException;
import nf.fr.eraasoft.pool.PoolableObjectBase;
import ar.edu.unq.tpi.games.towerdefence.components.Bullet;

public class BulletPoolBase extends PoolableObjectBase<Bullet> 
{

	@Override
	public void activate(Bullet bullet) throws PoolException {
		if(bullet==null){
			bullet = new Bullet();
		}
		bullet.reset();
	}

	@Override
	public Bullet make() throws PoolException {
		return new Bullet();
	}
	

}
