package scene;

import scene.entities.CharacterEntity;

public abstract class CharacterController 
{
	
	public CharacterController(CharacterEntity _entity)
	{
		m_entity = _entity;
	}
	
	public abstract void update(float _dt);
	
	protected CharacterEntity m_entity;
	
}
