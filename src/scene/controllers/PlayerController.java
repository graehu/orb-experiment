package scene.controllers;

import org.newdawn.slick.Input;

import scene.CharacterController;
import scene.entities.CharacterEntity;
import scene.entities.PlayerEntity;

public class PlayerController extends CharacterController
{
	public PlayerController(PlayerEntity _player, Input _input)
	{
		super(_player);
		m_input = _input;
	}
	
	@Override
	public void update(float _dt) 
	{
		// TODO Auto-generated method stub
		if(m_input.isKeyDown(Input.KEY_A))
			m_entity.onInput(CharacterEntity.InputType.MOVE_LEFT, _dt);
		else if(m_input.isKeyDown(Input.KEY_D))
			m_entity.onInput(CharacterEntity.InputType.MOVE_RIGHT, _dt);
        if(m_input.isKeyPressed(Input.KEY_SPACE))         
        	m_entity.onInput(CharacterEntity.InputType.ACTION1, _dt);
	}
	
	protected Input m_input;
}
