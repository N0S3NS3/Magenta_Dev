package net.magenta.engine.message;

import java.util.HashMap;

import net.magenta.engine.input.Mouse;

public class MouseMessage {

	private Mouse mouse;
	private HashMap<Integer, Boolean> mouseUpdateMap;
	
	public MouseMessage(Mouse mouse, HashMap<Integer, Boolean> mouseUpdateMap){
		this.mouse = mouse;
		this.mouseUpdateMap = mouseUpdateMap;
	}
	
	public Mouse getMouse(){
		return this.mouse;
	}
	public HashMap<Integer, Boolean> getMouseUpdateMap(){
		return this.mouseUpdateMap;
	}
	
}