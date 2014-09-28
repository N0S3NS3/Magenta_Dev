package net.magenta.engine.manager;

import java.util.HashMap;
import java.util.LinkedList;

import net.magenta.engine.component.Component;
import net.magenta.engine.component.DynamicComponent;
import net.magenta.engine.component.InputComponent;
import net.magenta.engine.component.RenderComponent;
import net.magenta.engine.factory.InputFactory;
import net.magenta.engine.manager.EntityManager.EntityType;
import net.magenta.engine.message.Message;
import net.magenta.engine.message.RequestComponentMessage;

public class ComponentManager extends Manager{

	/* When an entity requires components, the EntityManager will send 
	 * a message requesting specific components. The components required for
	 * the entity are returned via a ComponentMessage.
	 */
	public enum ComponentType {
		Render, 	//Render encapsulates the rendering of the entity.
		Input, 		//Input handles input messages and broadcasts messages to required components. 
		Dynamic, 	//Dynamic handles movement messages and encapsulates coordinate translation.
		Static, 	//Static maintains a static coordinate for the entity.
		Collision, 	//Collision handles the collision functionality.
		Path		//Path encapsulates the path finding of entities.
	}
	private HashMap<Integer, Manager> creationSubscribers;
	private HashMap<ComponentType, LinkedList<Integer>> componentSubscribers;
	
	@Override public boolean update(){
		return true;
	}
	@Override public void handleMessage(Message message){
		if (message instanceof RequestComponentMessage){
			message = (RequestComponentMessage) message;
			//message.
		}
	}
	private HashMap<ComponentType, LinkedList<Integer>> initializeComponentSubscribers(){
		return new HashMap<ComponentManager.ComponentType, LinkedList<Integer>>(){{
			put(ComponentType.Render, new LinkedList<Integer>());
			put(ComponentType.Input, new LinkedList<Integer>());
			put(ComponentType.Dynamic, new LinkedList<Integer>());
			put(ComponentType.Static, new LinkedList<Integer>());
			put(ComponentType.Collision, new LinkedList<Integer>());
			put(ComponentType.Collision, new LinkedList<Integer>());
		}};
	}
	private Component getComponent(ComponentType type){
		switch(type){
			case Render:
				return (Component) RenderComponent.getComponent();
			case Input:
				//return (Component) InputComponent.get 
			case Dynamic:
				return (Component) DynamicComponent.getComponent(); 
			default:
				return null;
		}
	}
	private LinkedList<Component> constructComponentList(int entityID, EntityType entityType){
		LinkedList<Component> componentList = new LinkedList<Component>();
		switch(entityType){
			case Player:
				componentList.add(getComponent(ComponentType.Render));
				componentList.add(getComponent(ComponentType.Input));
				componentList.add(getComponent(ComponentType.Dynamic));
				componentList.add(getComponent(ComponentType.Collision));
				return componentList;
			case Enemy:
				componentList.add(getComponent(ComponentType.Render));
				componentList.add(getComponent(ComponentType.Dynamic));
				componentList.add(getComponent(ComponentType.Collision));
				componentList.add(getComponent(ComponentType.Path));
				return componentList;
			case Clip:
				componentList.add(getComponent(ComponentType.Static));
				componentList.add(getComponent(ComponentType.Collision));
				return componentList;
			case Item:
				componentList.add(getComponent(ComponentType.Static));
				componentList.add(getComponent(ComponentType.Render));
				componentList.add(getComponent(ComponentType.Collision));
				return componentList;
			default:
				return null;
		}
	}
	@Override
	public boolean dispose() {
		// TODO Auto-generated method stub
		return false;
	}
}
