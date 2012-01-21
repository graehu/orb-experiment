package scene.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

import scene.Entity;
import scene.Level;
import scene.Scene;
import scene.controllers.PlayerController;

public class PlayerEntity extends CharacterEntity
{
	protected static final int SpriteSize = 64;
	
	public PlayerEntity(Scene _scene, String _imageRef) throws SlickException 
	{
		super(_scene);
		//FIXME:hardcoded shit!!
		m_shape = new Rectangle(2,2, 2, 2);
		m_pos.set(2,2);
		m_vel.y = 0.5f;
		if(_imageRef == null)
			m_image = new Image("data/sprites/PlayerDefault.png", new Color(1f,0f,1f));
		else
			m_image = new Image(_imageRef);
		
		m_controller = new PlayerController(this, _scene.GetGC().getInput());
	}

	@Override
	protected void update(float _dt) 
	{
		super.update(_dt);		
		
		if(!m_grounded)
		{
			m_vel.y += 80 * _dt;//apply gravity
			m_vel.y *= 0.97f;
			if(!m_movedThisFrame)
				m_vel.x *= 0.9f; //apply air drag
		}
		else if(!m_movedThisFrame || Math.abs(m_vel.x) > 20f)
			m_vel.x *= 0.8f; //apply friction
		
		//m_vel.sub(m_vel.copy().scale(1.7f * _dt)); //apply drag
		
		if(Math.abs(m_vel.x) > 1.0f * _dt)
			m_pos.x += m_vel.x * _dt;
		if(Math.abs(m_vel.y) > 50.0f * _dt)
			m_pos.y += m_vel.y * _dt;
		
		//update physics body
		m_shape.setLocation(m_pos);
		if(!m_collidedThisFrame || Math.abs(m_vel.y) > 0.1f)
			m_grounded = false;
		m_collidedThisFrame = false;
		m_movedThisFrame = false;
	}
	
	@Override
	protected void render() {
		// FIXME HARDCODED SCREENSIZE & PLAYER RENDERED IN CENTRE OF SCREEN
		m_image.draw(400-32,320-32);
	}

	@Override
	public void onInput(InputType _type, float _dt) 
	{
		switch(_type)
		{
		case MOVE_LEFT:
			if(m_grounded)
				m_vel.x -= 50.0f * _dt; 
			else
				m_vel.x -= 20.0f * _dt; //air touch
			if(m_vel.x < 0)//don't count changing direction moving
				m_movedThisFrame = true;
			break;
		case MOVE_RIGHT:
			if(m_grounded)
				m_vel.x += 50.0f * _dt; 
			else
				m_vel.x += 20.0f * _dt; //air touch
			
			if(m_vel.x > 0) //don't count changing direction moving
				m_movedThisFrame = true;
			break;
		case ACTION1: //jump
			if(m_grounded)
				m_vel.y = -2200.0f * _dt;
			break;
		}
	}
	
	Vector2f m_vel = new Vector2f();
	boolean m_grounded = false;
	boolean m_movedThisFrame = false;
	boolean m_collidedThisFrame = false;
	
	@Override
	protected void onIntersection(Entity _other) 
	{		
		m_collidedThisFrame = true;
		//FIXME: Assumes this is rectangle and other shape is circle or rectangle
		
		//determine depth and direction
		float xDepth = 0, yDepth = 0;
		int xDir = 1, yDir = 1;
		if(m_shape.getCenterY() < _other.m_shape.getCenterY()) 
		{
			yDepth = m_shape.getMaxY() - _other.m_shape.getMinY();
			yDir = -1;
		}
		else
			yDepth = m_shape.getMinY() - _other.m_shape.getMaxY();
		if(m_shape.getCenterX() < _other.m_shape.getCenterX()) 
		{
			xDepth = m_shape.getMaxX() - _other.m_shape.getMinX();
			xDir = -1;
		}
		else
			xDepth = m_shape.getMinX() - _other.m_shape.getMaxX();
		
		if(Math.abs(yDepth) < Math.abs(xDepth))
		{
			//push vertical
			{
				m_vel.y = 0; //yDir * Math.abs(m_vel.y) * _other.m_cR;
				if(yDir > 0)
					m_pos.y = _other.m_shape.getMaxY();
				else
				{
					m_pos.y = _other.m_shape.getMinY() - m_shape.getHeight();
					m_grounded = true;
				}
			}
		}
		else
		{
			//push horizontal
			{
				m_vel.x = 0;// xDir * Math.abs(m_vel.x) * _other.m_cR;
				if(xDir > 0)
					m_pos.x = _other.m_shape.getMaxX();
				else
					m_pos.x = _other.m_shape.getMinX() - m_shape.getWidth();
			}
			
		}
	}
}