package net.magenta.engine.component;

import net.magenta.engine.message.Message;

public interface Component {

	public void update();
	public void dispose();
	public void handleMessage(Message message);
	
}
