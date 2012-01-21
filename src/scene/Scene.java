package scene;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import scene.entities.PlayerEntity;


/**
 * @author Almax
 *
 */
public class Scene 
{
	public Scene(GameContainer _gc, String _mapFileRef) throws SlickException
	{
		m_gc = _gc;
		Level.getInstance().loadMap(this, _mapFileRef);
	}
	
	public void update(float _dt)
	{
		for(int i = 0; i < m_entities.size(); i++)
		{
			Entity e1 = m_entities.get(i);
			e1.update(_dt);
			for(int j = i+1; j < m_entities.size(); j++)
			{
				Entity e2 = m_entities.get(j);
				if(collisiontest(e1,e2))
				{
					e1.onIntersection(e2);
					e2.onIntersection(e1);
				}
			}
		}
	}
        
    boolean collisiontest(Entity e1, Entity e2) //FIXME put in seperate function for profiling
    {
        return e1.m_shape.intersects(e2.m_shape);
    }
	
	public void render(Graphics _g)
	{
		//FIXME: HARDCODED SCREENSIZE
		int x = (int) (-m_currentPlayer.m_pos.x*32.0f + 400-32);
		int y = (int) (-m_currentPlayer.m_pos.y*32.0f + 320-32);
		Level.getInstance().render(x,y);
		for(Entity e : m_entities)
		{
			e.render();
			//render entity bodies
			//_g.
			_g.pushTransform();
				_g.scale(32, 32);
				_g.translate(x, y);
				_g.draw(e.m_shape);
			_g.popTransform();
		}
	}
	
	public GameContainer GetGC()
	{
		return m_gc;
	}
	
	protected void OnEntityConstruction(Entity _e)
	{
		m_entities.add(_e);
		if(_e instanceof PlayerEntity)
			m_currentPlayer = (PlayerEntity) _e;
	}
	
	GameContainer m_gc = null;
	private ArrayList<Entity> m_entities = new ArrayList<Entity>();
	private PlayerEntity m_currentPlayer = null;
}
