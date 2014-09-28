package net.magenta.engine.message;

import net.magenta.engine.manager.EntityManager.EntityType;

public class RequestComponentMessage extends Message{
	private int entityID;
	private EntityType entityType;
	public RequestComponentMessage(int entityID, EntityType entityType){
		super(100);
		this.entityID = entityID;
		this.entityType = entityType;
	}
	public int getEntityID(){
		return this.entityID;
	}
	public EntityType getEntityType(){
		return this.entityType;
	}
	
}
