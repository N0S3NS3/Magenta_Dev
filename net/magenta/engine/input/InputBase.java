package net.magenta.engine.input;

import net.magenta.engine.message.Message;

public abstract class InputBase {

	private int inputGUID;
	
	protected InputBase(int inputGUID){
		this.inputGUID = inputGUID;
	}
	public int getGUID(){
		return this.inputGUID;
	}
	public abstract void recieveInputMessage(Message message);
}
