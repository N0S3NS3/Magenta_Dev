package net.magenta.engine.entity;

import java.util.HashMap;

import net.magenta.engine.component.Component;

public abstract class Entity {
	protected int entityGUID;
	protected HashMap<Integer, Component> componentMap;
	
	protected Entity(int guid){
		this.entityGUID = guid;
		this.componentMap = new HashMap<Integer, Component>();
	}
	public boolean addComponent(int componentID, Component component){
		if (!componentMap.containsKey(componentID) && !componentMap.containsValue(component)){
			componentMap.put(componentID, component);
			return true;
		}
		return false;
	}
	public boolean removeComponent(int componentID){
		if (this.componentMap.containsKey(componentID)){
			this.componentMap.remove(componentID);
			return true;
		}
		return false;
	}
	public int getGUID(){
		return this.entityGUID;
	}
	public abstract void update();
	
}
