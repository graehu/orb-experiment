package scene;

import java.util.ArrayList;
import java.util.Arrays;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;

import scene.entities.LevelTile;

public class Level {
	
	private Level(){}
	private static Level m_instance = new Level();
	
	public static Level getInstance()
	{
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
			int l_x = (int)_position.x/m_map.getTileWidth();
			int l_y = (int)_position.y/m_map.getTileHeight();
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
	public void loadMap(Scene _scene, String _mapName)
	{
		try
		{
			//load tileMap
			m_map = new TiledMap("data/maps/"+_mapName+".tmx");
			m_map.getTileSet(0).tiles.setImageColor(255, 0, 255);
			
			//setup arrays for shape generation
			ArrayList<Shape> shapes = new ArrayList<Shape>();
			boolean[][] processedTiles = new boolean[m_map.getWidth()][m_map.getHeight()];
			for(boolean[] a : processedTiles)
				Arrays.fill(a, false);

			for(int i = 0; i < m_map.getWidth(); i++) //FIXME: hardcoded mapsize
			{
				for(int j = 0; j < m_map.getHeight(); j++) //FIXME: hardcoded mapsize
				{
					if(processedTiles[i][j] == true)
						continue;
					else
						processedTiles[i][j] = true;
					
					int l_ID = m_map.getTileId(i, j, 0); //FIXME: This assumes the physical world is layer 0.
					String l_typeName = m_map.getTileProperty(l_ID, "type", "");
					switch(l_typeName)
					{
					case "stone":
						Rectangle stone = new Rectangle(i, j, 1, 1);
						Shape poly = new Polygon(stone.getPoints());
						for(int k = i; k < m_map.getWidth(); k++)
						{//iterate horizontally and union adjacent tiles
							String type = m_map.getTileProperty(m_map.getTileId(k, j, 0), "type", "");
							if(type.equals("stone")) //FIXME: This assumes the physical world is layer 0.
							{
								processedTiles[k][j] = true;
								Shape[] s = poly.union(new Rectangle(k, j, 1, 1)); 
								poly = s[0];
							}
							else break; //break if none ajacent
						}
//                        shapes.add(new Rectangle(   poly.getX(), 
//                                                    poly.getY(), 
//                                                    poly.getMaxX()-poly.getMinX(), 
//                                                    poly.getMaxY()-poly.getMinY()));
						shapes.add(poly);
					}							
				}
			}
			
			//firstStone.prune();
			//new LevelTile(_scene, firstStone);
			for(Shape s : shapes)
			{
				s.prune();
				new LevelTile(_scene, s);
			}
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
