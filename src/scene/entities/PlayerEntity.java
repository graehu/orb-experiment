package scene.entities;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import scene.Scene;
import scene.controllers.PlayerController;

public class PlayerEntity extends CharacterEntity
{
	public PlayerEntity(Scene _scene, String _imageRef) throws SlickException 
	{
		super(_scene);
		m_pos.set(20,20);
		m_vel.y = 20;
		if(_imageRef == null)
			m_image = new Image("data/sprites/PlayerDefault.png");
		else
			m_image = new Image(_imageRef);
		
		m_controller = new PlayerController(this, _scene.GetGC().getInput());
	}

	@Override
	protected void update(float _dt) 
	{
		super.update(_dt);		
		
		m_vel.y += 2000 * _dt;
		m_vel.sub(m_vel.copy().scale(1.7f * _dt));
		
		//apply drag
		if (m_vel.lengthSquared() < 1f)
		{
			m_vel.set(0,0);
		}
		
		
		//TODO: HARDCODED VALUES HERE FOR SCREEN DIMENTIONS! OH NOES!
		if(m_pos.y + m_vel.y * _dt > 640 - 128)
		{
			m_vel.y = -Math.abs(m_vel.y) * 0.4f;
		}
		else if(m_pos.y + m_vel.y * _dt < 0)
		{
			m_vel.y = Math.abs(m_vel.y) * 0.4f;
		}
		if(m_pos.x + m_vel.x * _dt > 800 - 128)
		{
			m_vel.x = -Math.abs(m_vel.x) * 0.4f;
		}
		else if(m_pos.x + m_vel.x * _dt < 0)
		{
			m_vel.x = Math.abs(m_vel.x) * 0.4f;
		}
		
		if(Math.abs(m_vel.y) > 1f)
			m_pos.add(m_vel.copy().scale(_dt));
	}

	@Override
	public void onInput(InputType _type, float _dt) 
	{
		switch(_type)
		{
		case MOVE_LEFT:
			m_vel.x += -850.0f * _dt; 
			break;
		case MOVE_RIGHT:
			m_vel.x += 850.0f * _dt;
			break;
		case ACTION1: //jump
			m_vel.y = -850.0f * _dt * 100;
			break;
		}
	}
	
	Vector2f m_vel = new Vector2f();
	Vector2f m_acc = new Vector2f();
}
