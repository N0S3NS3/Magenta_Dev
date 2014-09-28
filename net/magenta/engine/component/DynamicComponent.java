package net.magenta.engine.component;

import net.magenta.engine.message.Message;

public class DynamicComponent implements Component {

	private int coordinateX;
	private int coordinateY;
	
	protected DynamicComponent() {
		
	}
	protected DynamicComponent(int coordinateX, int coordinateY){
		
	}
	
	public static DynamicComponent getComponent(){
		return new DynamicComponent();
	}
	
	@Override public void update(){
		
	}
	@Override public void dispose(){
		
	}
	@Override public void handleMessage(Message message){
		
	}
	
}
