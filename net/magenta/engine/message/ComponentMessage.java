package net.magenta.engine.message;

import java.util.LinkedList;

import net.magenta.engine.component.Component;

public class ComponentMessage extends Message{
	private int entityGUID;
	private LinkedList<Component> componentList;
	public ComponentMessage(int entityGUID, LinkedList<Component> componentList){
		super(100);
		this.entityGUID = entityGUID;
		this.componentList = componentList;
	}
	public LinkedList<Component> getComponentList(){
		return this.componentList;
	}
	public int getEntityGUID(){
		return this.entityGUID;
	}
}
