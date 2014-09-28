package net.magenta.engine.message;

import java.util.LinkedList;

import net.magenta.engine.component.Component;

public class EntityCreationMessage {
	public enum EntityType {
		Player, Enemy, Item, Collision
	}
	private int entityID;
	private EntityType entityType;
	private LinkedList<Component> componentList;
	public EntityCreationMessage(EntityType entityType, int entityID, LinkedList<Component> componentList){
		this.entityType = entityType;
		this.entityID = entityID;
		this.componentList = componentList;
	}
}
