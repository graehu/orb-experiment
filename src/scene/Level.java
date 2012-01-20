package scene;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.geom.Vector2f;

public class Level {
	
	private Level(){}
	private static Level m_instance;
	
	public static Level getInstance()
	{
		if(m_instance == null)
			m_instance = new Level();
		return m_instance;
	}

	public enum tileType
	{
		e_air,
		e_stone
	};
	public tileType getTileType(Vector2f _position)
	{
		if(m_map != null)
		{
			//FIXME: This assumes the tile spacing is 32x32
			int l_x = (int)_position.x/32;
			int l_y = (int)_position.y/32;
			//FIXME: This assumes the physical world is layer 0.
			int l_ID = m_map.getTileId(l_x, l_y, 0);
			
			String l_typeName = m_map.getTileProperty(l_ID, "type", null);
			switch(l_typeName)
			{
			case "air":
				return tileType.e_air;
			case "stone":
				return tileType.e_stone;
			}		
		}
		return tileType.e_air;
	}
	public void loadMap(String _mapName)
	{
		try
		{
			m_map = new TiledMap("data/maps/"+_mapName+".tmx");
		}
		catch(SlickException _exception) 
		{
			System.out.println("Level: "+_exception);
		}
		
	}
	//TODO: Write a camera class to pass here or something. Anything better really.
	public void render(int _x, int _y){if(m_map != null)m_map.render(_x, _y);}
	public int getHeight(){if(m_map != null)return m_map.getHeight();else return 0;}
	public int getWidth(){if(m_map != null)return m_map.getWidth();else return 0;}
	
	
	private TiledMap m_map;

}
