package scene;

import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

public abstract class Entity 
{	
	/**
	 * @param _scene scene to instance in
	 */
	public Entity(Scene _scene)
	{
		_scene.OnEntityConstruction(this);
	}
	
	public void moveBy(Vector2f _delta) 
	{
		m_pos = m_pos.add(_delta);
	}
	public void moveTo(Vector2f _pos) 
	{
		m_pos.set(_pos);
	}
	
	protected abstract void update(float _dt);
	
	protected abstract void render();
	
	protected abstract void onIntersection(Entity _other);
	
	public float m_cR = 0.1f;
	public Shape m_shape = null;
	protected Vector2f m_pos = new Vector2f();
	
	
}
