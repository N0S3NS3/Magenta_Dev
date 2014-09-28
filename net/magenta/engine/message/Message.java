package net.magenta.engine.message;

public abstract class Message {
	protected int messageID;
	protected Message(int messageID){
		this.messageID = messageID;
	}
	public int getMessageID(){
		return this.messageID;
	}
}
