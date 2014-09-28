package net.magenta.engine.factory;

import net.magenta.engine.component.Component;
import net.magenta.engine.component.InputComponent;


public class InputFactory implements Factory {
	
	private static boolean isSingleton = false;
	
	private InputFactory(){	}
	
	public static InputFactory getFactoryInstance(){
		if (!InputFactory.isSingleton){
			InputFactory.isSingleton = true;
			return new InputFactory();
		} else {
			return null;
		}
	}
	
	@Override public Component getComponentInstance(){
		return (Component) new InputComponent(900);
	}
}
