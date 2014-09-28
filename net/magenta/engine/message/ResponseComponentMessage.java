package net.magenta.engine.message;

import java.util.LinkedList;
import net.magenta.engine.component.Component;

public class ResponseComponentMessage {

	private int entityID;
	private LinkedList<Component> componentList;
	public ResponseComponentMessage(int entityID, LinkedList<Component> componentList){
		this.entityID = entityID;
		this.componentList = componentList;
	}
	public int getIntityID(){
		return this.entityID;
	}
	public LinkedList<Component> getComponentList(){
		return this.componentList;
	}
	
}
