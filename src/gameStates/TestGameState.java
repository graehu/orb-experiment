package gameStates;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import scene.Scene;
import scene.entities.PlayerEntity;

public class TestGameState extends BasicGameState 
{
	private Scene m_scene;
	
	@Override
	public void init(GameContainer _gc, StateBasedGame _sbg)
			throws SlickException {
		// TODO Auto-generated method stub
		m_scene = new Scene(_gc, "default");
		
		PlayerEntity player = new PlayerEntity(m_scene, null);
	}

	@Override
	public void render(GameContainer _gc, StateBasedGame _sbg, Graphics _g)
			throws SlickException {
		m_scene.render();
	}

	@Override
	public void update(GameContainer _gc, StateBasedGame _sbg, int _dt)
			throws SlickException {
		m_scene.update(Math.min(0.16f, _dt / 1000.0f));
		
		if(_gc.getInput().isKeyPressed(Input.KEY_ESCAPE))
			_gc.exit();
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

}
