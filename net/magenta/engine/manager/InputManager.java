package net.magenta.engine.manager;

import java.util.HashMap;
import java.util.Map.Entry;

import net.magenta.engine.input.Keyboard;
import net.magenta.engine.input.Mouse;
import net.magenta.engine.message.InputMessage;
import net.magenta.engine.message.KeyboardMessage;
import net.magenta.engine.message.Message;
import net.magenta.engine.message.MouseMessage;
import net.magenta.engine.message.InputMessage.MessageType;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

public class InputManager extends Manager implements InputProcessor{

	private static boolean singleton = false;
	
	//Subscription Fields 
	private HashMap<Integer, Manager> keyboardSubscribers; 
	private HashMap<Integer, Manager> mouseSubscribers; 
	private Object keyboardSubscribersLock;
	private Object mouseSubscribersLock;
	
	//Keyboard Fields
	private HashMap<Integer, Keyboard.ValidKey> keyBindingMap;
	private CircularBuffer<Keyboard> keyboardBuffer; 
	private HashMap<Integer, Boolean> keyEvents;
	private Object keyboardLock;
	
	//Touch Fields
	private HashMap<Integer, Mouse.ValidMouse> mouseBindingMap;
	private HashMap<Integer, Boolean> mouseEvents;
	private CircularBuffer<Mouse> mouseBuffer; 
	private Object mouseLock;
	
	private InputManager(){
		this.keyBindingMap = new HashMap<Integer, Keyboard.ValidKey>();
		this.keyboardBuffer = initializeKeyboardBuffer(keyboardBuffer);
		this.keyEvents = new HashMap<Integer, Boolean>();
		this.keyboardLock = new Object();
		this.mouseBindingMap = new HashMap<Integer, Mouse.ValidMouse>();
		this.mouseEvents = new HashMap<Integer, Boolean>();
		this.mouseBuffer = initializeMouseBuffer(mouseBuffer);
		this.mouseLock = new Object();
		this.keyboardSubscribersLock = new Object();
		this.keyboardSubscribers = new HashMap<Integer, Manager>();
		this.mouseSubscribersLock = new Object();
		this.mouseSubscribers = new HashMap<Integer, Manager>();
		Gdx.input.setInputProcessor(this);
	}
	public static Manager getManagerInstance(){
		if (!singleton){
			singleton = true;
			return new InputManager();
		} else {
			return null;
		}
	}
	//********************************************************
	//	initializeKeyboardBuffer & initializeMouseBuffer
	//	- These functions are called on initialization of the CircularBuffer
	//	- instances, the functions insert and initial input state to the header
	//	- index of the buffers.
	//********************************************************
	private CircularBuffer<Keyboard> initializeKeyboardBuffer(CircularBuffer<Keyboard> keyboardBuffer){
		keyboardBuffer = new CircularBuffer<Keyboard>();
		keyboardBuffer.insert(Keyboard.getInstance());
		return keyboardBuffer;
	}
	private CircularBuffer<Mouse> initializeMouseBuffer(CircularBuffer<Mouse> mouseBuffer){
		mouseBuffer = new CircularBuffer<Mouse>();
		mouseBuffer.insert(Mouse.getInstance());
		return mouseBuffer;
	}
	
	//********************************************************
	//	updateKeyboard & updateMouse
	//	- These functions are called on each Manager Update call, since the 
	//	- input is handled asynchronously, a mutex lock is required for thread safety
	// 	- the new input is referenced with the last known state of an input interface
	//	- (Keyboard / Mouse) and only new status updates are consumed by the instances.
	//	- These consumed status updates are then broadcasted to subscribers.
	//********************************************************
	private void updateKeyboard(){
		synchronized(keyboardLock){
			if (!keyEvents.isEmpty()){
				KeyboardMessage updatedMessage;
				if ((updatedMessage = keyboardBuffer.recieve().updateKeyboard(keyEvents)) != null){
					keyboardBuffer.insert(updatedMessage.getKeyboard());
				}
				for(Entry<Integer, Manager> entry : keyboardSubscribers.entrySet()){
					entry.getValue().handleMessage(new InputMessage(updatedMessage.getKeyUpdateMap(), MessageType.Keyboard));				}
			//Debug 
				for(Entry<Integer, Boolean> entry : keyEvents.entrySet()){
					System.out.println(entry.getKey() +" " +entry.getValue());
				}
			//
				keyEvents.clear();
			}	
		}
	}
	private void updateMouse(){
		synchronized(mouseLock){
			if (!mouseEvents.isEmpty()){
				MouseMessage updatedMessage;
				//TO-DO: Finish Mouse Implementation
			}
		}
	}
	public boolean addKeyboardSubscriber(Integer guid, Manager manager){
		synchronized(keyboardSubscribersLock){
			if (!keyboardSubscribers.containsKey(guid) && !keyboardSubscribers.containsValue(manager)){
				keyboardSubscribers.put(guid, manager);
				return true;
			}	
		}
		return false;
	}
	public boolean removeKeyboardSubscriber(Integer guid){
		synchronized(keyboardSubscribersLock) {
			if (keyboardSubscribers.containsKey(guid)){
				keyboardSubscribers.remove(guid);
				return true;
			}	
		}
		return false;
	}
	public boolean addMouseSubscriber(Integer guid, Manager manager){
		synchronized(mouseSubscribersLock){
			if (!mouseSubscribers.containsKey(guid) && !mouseSubscribers.containsValue(manager)){
				mouseSubscribers.put(guid, manager);
				return true;
			}
		}
		return false;
	}
	public boolean removeMouseSubscriber(Integer guid){
		synchronized(mouseSubscribersLock){
			if (mouseSubscribers.containsKey(guid)){
				mouseSubscribers.remove(guid);
				return true;
			}
		}
		return false;
	}
	@Override public boolean update(){
		updateKeyboard();
		updateMouse();
		return true;
	}
	@Override public boolean keyDown(int keycode){
		synchronized (keyboardLock){
			if (!keyEvents.containsKey(keycode)){
				keyEvents.put(keycode, true);
				return true;
			}
		}
		return false;
	}
	@Override public boolean keyUp(int keycode){
		synchronized (keyboardLock){
			if (!keyEvents.containsKey(keycode)){
				keyEvents.put(keycode, false);
				return true;
			}
		}
		return false;
	}
	@Override public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override public void handleMessage(Message message){
		
	}
	protected class CircularBuffer<T>{
		private final int threshold = 2;
		private int header;
		private int footer;
		private T[] buffer;
		private int elementCount;
		public CircularBuffer(){
			this.header = 0;
			this.footer = 0;
			this.elementCount = 0;
			this.buffer = (T[])new Object[threshold];
		}
		public void insert(T input){
			int tempLocation = (header + 1) % threshold;
			if (tempLocation == footer){
				footer = (footer + 1) % threshold;
			}
			buffer[header] = input;
			header = tempLocation;
			elementCount++;
		}
		public T recieve(){
			T temp = null;
			if (elementCount > 0){
				temp = buffer[footer];
				footer = (footer + 1) % threshold;
				elementCount--;
			}
			return temp;
		}
	}
	@Override
	public boolean dispose() {
		// TODO Auto-generated method stub
		return false;
	}
}