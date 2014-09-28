package net.magenta.engine.manager;

import java.util.LinkedList;
import java.util.Queue;

//import net.magenta.engine.gamebase.GlobalIdentity;
import net.magenta.engine.handler.GuidHandler;
import net.magenta.engine.message.*;

public abstract class Manager/* extends GlobalIdentity*/ {
	protected Queue<Message> messageQueue; 
	protected Manager(){
		this.messageQueue = new LinkedList<Message>();
	}
	abstract public boolean update();
	abstract public boolean dispose();
	abstract public void handleMessage(Message message);
}
