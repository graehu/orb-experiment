package scene.entities;

import org.newdawn.slick.geom.Shape;

import scene.Entity;
import scene.Scene;

public class LevelTile extends Entity {

	public LevelTile(Scene _scene, Shape _shape) {
		super(_scene);
		m_shape = _shape;
	}

	@Override
	protected void update(float _dt) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void render() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onIntersection(Entity _other) 
	{
		//nothing - static object
	}

}
