package net.magenta.engine.input;

public class Mouse {
	
	private static boolean isSingleton = false;
	
	public enum ValidMouse {
		MOUSE_ONE, MOUSE_TWO, MOUSE_THREE, 
		MOUSE_FOUR, MOUSE_FIVE, MOUSE_SIX
	}
	
	private Mouse(boolean isSingleton){
		if (!isSingleton){
			
		} else {
			
		}
	}
	public static Mouse getInstance(){
		if (!Mouse.isSingleton){
			Mouse.isSingleton = true;
			return new Mouse(Mouse.isSingleton);
		} else {
			return null;
		}
	}
}
