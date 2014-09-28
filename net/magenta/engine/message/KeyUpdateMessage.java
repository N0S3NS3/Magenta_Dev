package net.magenta.engine.message;

import java.util.HashMap;

public class KeyUpdateMessage{

	HashMap<Integer, Boolean> keyMap;
	
	public KeyUpdateMessage(HashMap<Integer, Boolean> keyMap){
		this.keyMap = keyMap;
	}
	
	public HashMap<Integer, Boolean> getKeyMap(){
		return this.keyMap;
	}

	
}
