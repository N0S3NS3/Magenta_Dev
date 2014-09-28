package net.magenta.engine.manager;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Map.Entry;

import sun.security.jca.GetInstance;
import net.magenta.engine.component.Component;
import net.magenta.engine.entity.Entity;
import net.magenta.engine.message.ComponentMessage;
import net.magenta.engine.message.Message;
import net.magenta.engine.message.InputMessage;
import net.magenta.engine.message.KeyboardMessage;

public class EntityManager extends Manager {

	/* EntityManager will utilize the inherited GUID values to it's advantage
	 * when dealing with mass incoming messages. Since each entity is a summation
	 * of the components they are utilizing, when a message is received from an 
	 * external manager, the EntityManager will be able to check if an entity 
	 * requires that message. If so, then it will broadcast it to the designated 
	 * entities.
	 * 
	 * Another potential solution would to have each message have a unique message
	 * identity, which would notate the type of message. If the EntityManager maintains
	 * a collection of Entities per message ID, passing messages would be streamlined. 
	 */
	
	private static boolean isSingleton = false;
	
	private HashMap<Integer, Entity> entityMap;
	
	public enum EntityType{
		Player, Enemy, Item, Clip
	}
	
	protected EntityManager(){
		this.entityMap = new HashMap<Integer, Entity>();
	}
	
	public static Manager getManagerInstance(){
		if (!EntityManager.isSingleton){
			EntityManager.isSingleton = true;
			return new EntityManager();
		} else {
			return null;
		}
	}
	private void createEntity(){
		
	}
	private void deleteEntity(){
		
	}
	@Override public boolean update(){
		while(!this.messageQueue.isEmpty()){
			
			
		}
		return true;
	}
	@Override public void handleMessage(Message message){
		if (message instanceof InputMessage){
			//Find designated entities subscribed to InputMessage
		}
		if (message instanceof ComponentMessage){
			ComponentMessage componentMsg = (ComponentMessage) message;
			attachComponentList(componentMsg.getEntityGUID(), componentMsg.getComponentList());
		}
		//Consolidate These Messages based on generic inheritance
		//Entity Manager does not need to know the type of Input Message
		//Instead, check if it's of type InputMessage, then distribute it 
		//to the entity which utilizes those components 
	}
	private boolean createEntity(EntityType type){
		switch(type){
		case Player:
			
			break;
		case Enemy:
			break;
//		case Collision:
//			break;
		default:
				
		}
		return true;
	}
	private boolean attachComponentList(int entityGUID, LinkedList<Component> componentList){
		if (this.entityMap.containsKey(entityGUID)){
			for(Component component : componentList){
				this.entityMap.get(entityGUID).addComponent(200, component);
			}
			return true;
		} 
		return false;
	}

	@Override
	public boolean dispose() {
		// TODO Auto-generated method stub
		return false;
	}

}
