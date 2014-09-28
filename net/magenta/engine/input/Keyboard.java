package net.magenta.engine.input;

import java.util.HashMap;
import java.util.Map.Entry;

import net.magenta.engine.message.KeyboardMessage;

import com.badlogic.gdx.Input.Keys;

public class Keyboard {

	private static boolean singleton = false;
	
	public static enum ValidKey {
		Q, W, E, R, T, Y, U, I, O, P,
		A, S, D, F, G, H, J, K, L, Z, 
		X, C, V, B, N, M, ZERO, ONE, TWO,
		THREE, FOUR, FIVE, SIX, SEVEN, 
		EIGHT, NINE, SPACE, ESC, SHIFT_LEFT,
		SHIFT_RIGHT, ALT_LEFT, ALT_RIGHT, 
		NUMPAD_ZERO, NUMPAD_ONE, NUMPAD_TWO, 
		NUMPAD_THREE, NUMPAD_FOUR, NUMPAD_FIVE,
		NUMPAD_SIX, NUMPAD_SEVEN, NUMPAD_EIGTH,
		NUMPAD_NINE;
	};
	
	private Keyboard(boolean isSingleton){
		if (isSingleton){
			this.keyMap = getSupportedKeys();
		} else {
			this.keyMap = new HashMap<Integer, KeyState>();	
		}
		for(Integer key : this.keyMap.keySet()){
			System.out.println(key);
		}
	}
	
	private HashMap<Integer, KeyState> keyMap;
	
	public static Keyboard getInstance(){
		if (!Keyboard.singleton){
			Keyboard.singleton = true;
			return new Keyboard(Keyboard.singleton);
		} else {
			return null;
		}
	}
	public KeyboardMessage updateKeyboard(HashMap<Integer, Boolean> keyUpdate){
		boolean keyStateChange = false;
		Keyboard updated = copyKeyboard(this);
		for(Entry<Integer, Boolean> entry : keyUpdate.entrySet()){
			if (this.keyMap.containsKey(entry.getKey())){
				if (this.keyMap.get(entry.getKey()).getState() != entry.getValue()){
					updated.keyMap.get(entry.getKey()).setState(entry.getValue());
					keyStateChange = true;
				} else {
					keyUpdate.remove(entry.getKey());
				}
			} else {
				keyUpdate.remove(entry.getKey());
			}
		}
		return keyStateChange ? new KeyboardMessage(updated, keyUpdate) : null;
	}
	
	private Keyboard copyKeyboard(Keyboard source){
		Keyboard destination = new Keyboard(false);
		for(Entry<Integer, KeyState> entry : source.keyMap.entrySet()){
			destination.keyMap.put(entry.getKey(), entry.getValue());
		}
		return destination;
	}
	
	private HashMap<Integer, KeyState> getSupportedKeys(){
		return new HashMap<Integer, KeyState>(){{
			put(Keys.Q, new KeyState(ValidKey.Q));
			put(Keys.W, new KeyState(ValidKey.W));
			put(Keys.E, new KeyState(ValidKey.E));
			put(Keys.R, new KeyState(ValidKey.R));
			put(Keys.T, new KeyState(ValidKey.T));
			put(Keys.Y, new KeyState(ValidKey.Y));
			put(Keys.U, new KeyState(ValidKey.U)); 
			put(Keys.I, new KeyState(ValidKey.I)); 
			put(Keys.O, new KeyState(ValidKey.O));
			put(Keys.P, new KeyState(ValidKey.P)); 
			put(Keys.A, new KeyState(ValidKey.A)); 
			put(Keys.S, new KeyState(ValidKey.S)); 
			put(Keys.D, new KeyState(ValidKey.D)); 
			put(Keys.F, new KeyState(ValidKey.F)); 
			put(Keys.G, new KeyState(ValidKey.G));
			put(Keys.H, new KeyState(ValidKey.H)); 
			put(Keys.J, new KeyState(ValidKey.J)); 
			put(Keys.K, new KeyState(ValidKey.K));
			put(Keys.L, new KeyState(ValidKey.L)); 
			put(Keys.X, new KeyState(ValidKey.X)); 
			put(Keys.C, new KeyState(ValidKey.C));
			put(Keys.V, new KeyState(ValidKey.V)); 
			put(Keys.B, new KeyState(ValidKey.B)); 
			put(Keys.N, new KeyState(ValidKey.N)); 
			put(Keys.M, new KeyState(ValidKey.M)); 
			put(Keys.NUM_0, new KeyState(ValidKey.ZERO)); 
			put(Keys.NUM_1, new KeyState(ValidKey.ONE));
			put(Keys.NUM_2, new KeyState(ValidKey.TWO)); 
			put(Keys.NUM_3, new KeyState(ValidKey.THREE)); 
			put(Keys.NUM_4, new KeyState(ValidKey.FOUR));
			put(Keys.NUM_5, new KeyState(ValidKey.FIVE)); 
			put(Keys.NUM_6, new KeyState(ValidKey.SIX)); 
			put(Keys.NUM_7, new KeyState(ValidKey.SEVEN));
			put(Keys.NUM_8, new KeyState(ValidKey.EIGHT)); 
			put(Keys.NUM_9, new KeyState(ValidKey.NINE)); 
			put(Keys.SPACE, new KeyState(ValidKey.SPACE));  
			put(Keys.ESCAPE, new KeyState(ValidKey.ESC)); 
			put(Keys.SHIFT_LEFT, new KeyState(ValidKey.SHIFT_LEFT)); 
			put(Keys.SHIFT_RIGHT, new KeyState(ValidKey.SHIFT_RIGHT));
			put(Keys.ALT_LEFT, new KeyState(ValidKey.ALT_LEFT)); 
			put(Keys.ALT_RIGHT, new KeyState(ValidKey.ALT_RIGHT));
			put(Keys.NUMPAD_0, new KeyState(ValidKey.NUMPAD_ZERO));
			put(Keys.NUMPAD_1, new KeyState(ValidKey.NUMPAD_ONE));
			put(Keys.NUMPAD_2, new KeyState(ValidKey.NUMPAD_TWO));
			put(Keys.NUMPAD_3, new KeyState(ValidKey.NUMPAD_THREE));
			put(Keys.NUMPAD_4, new KeyState(ValidKey.NUMPAD_FOUR));
			put(Keys.NUMPAD_5, new KeyState(ValidKey.NUMPAD_FIVE));
			put(Keys.NUMPAD_6, new KeyState(ValidKey.NUMPAD_SIX));
			put(Keys.NUMPAD_7, new KeyState(ValidKey.NUMPAD_SEVEN));
			put(Keys.NUMPAD_8, new KeyState(ValidKey.NUMPAD_EIGTH));
			put(Keys.NUMPAD_9, new KeyState(ValidKey.NUMPAD_NINE));
		}}; 
	}
	
	class KeyState {
		private ValidKey key;
		private boolean state;
		
		public KeyState(ValidKey key){
			this.key = key;
			this.state = false;
		}
		public void setState(boolean state){
			this.state = state;
		}
		public boolean getState(){
			return this.state;
		}	
	}	
}
