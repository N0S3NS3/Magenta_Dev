package net.magenta.engine.message;

import java.util.HashMap;

import net.magenta.engine.message.Message;

public class InputMessage extends Message {
	public static enum MessageType {
		Keyboard, Mouse
	};
	//All Input Messages will utilize an Integer which denotes the 
	//Key or Mouse and a boolean for the state.
	
	protected HashMap<Integer, Boolean> inputBody;
	protected MessageType messageType;
	
	public InputMessage(HashMap<Integer, Boolean> input, MessageType messageType){
		super(100);
		this.inputBody = new HashMap<Integer, Boolean>();
		this.messageType = messageType;
	}
	
	public HashMap<Integer, Boolean> getInputBody(){
		return this.inputBody;
	}
	public MessageType getMessageType(){
		return this.messageType;
	}
}
