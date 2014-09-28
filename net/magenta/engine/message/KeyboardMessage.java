package net.magenta.engine.message;

import java.util.HashMap;
import net.magenta.engine.input.Keyboard;

public class KeyboardMessage {

	private Keyboard keyboard;
	private HashMap<Integer, Boolean> keyUpdateMap;
	
	public KeyboardMessage(Keyboard keyboard, HashMap<Integer, Boolean> keyUpdateMap){
		this.keyboard = keyboard;
		this.keyUpdateMap = keyUpdateMap;
	}
	
	public Keyboard getKeyboard(){
		return this.keyboard;
	}
	public HashMap<Integer, Boolean> getKeyUpdateMap(){
		return this.keyUpdateMap;
	}
	
}
