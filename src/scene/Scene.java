package scene;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;


/**
 * @author Almax
 *
 */
public class Scene 
{
	public TiledMap m_TiledMap = null;
	
	public Scene(GameContainer _gc, String _mapFileRef) throws SlickException
	{
		m_gc = _gc;
		m_TiledMap = new TiledMap("data/maps/"+_mapFileRef);
	}
	
	public void update(float _dt)
	{
		for(Entity e : m_entities)
			e.update(_dt);
	}
	
	public void render()
	{
		m_TiledMap.render(0, 0);
		
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
