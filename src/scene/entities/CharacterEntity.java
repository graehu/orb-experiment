package scene.entities;

import org.newdawn.slick.Image;

import scene.CharacterController;
import scene.Entity;
import scene.Scene;

public abstract class CharacterEntity extends Entity {

	public enum InputType
	{
		MOVE_RIGHT,
		MOVE_LEFT,
		ACTION1,
		ACTION2,
		ACTION3
	}
	
	public CharacterEntity(Scene _scene) {
		super(_scene);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void render() {
		// TODO Auto-generated method stub
		m_image.draw(0,0);
	}
	
	@Override
	protected void update(float _dt)
	{
		m_controller.update(_dt);
	}

	public abstract void onInput(InputType _type, float _dt);

	//member variables
	protected CharacterController m_controller;
	protected Image m_image;
}
