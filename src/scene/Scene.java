package scene;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;


/**
 * @author Almax
 *
 */
public class Scene 
{
	public Scene(GameContainer _gc, String _mapFileRef) throws SlickException
	{
		m_gc = _gc;
		Level.getInstance().loadMap(_mapFileRef);
	}
	
	public void update(float _dt)
	{
		for(Entity e : m_entities)
			e.update(_dt);
	}
	
	public void render()
	{
		Level.getInstance().render((int)m_entities.get(0).m_pos.x*-1, (int)m_entities.get(0).m_pos.y*-1);
		
		for(Entity e : m_entities)
			e.render();
	}
	
	public GameContainer GetGC()
	{
		return m_gc;
	}
	
	protected void OnEntityConstruction(Entity _e)
	{
		m_entities.add(_e);
	}
	
	GameContainer m_gc = null;
	private List<Entity> m_entities = new ArrayList<Entity>();
}
