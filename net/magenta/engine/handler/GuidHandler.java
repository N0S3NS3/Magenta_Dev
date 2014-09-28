package net.magenta.engine.handler;

import java.util.HashMap;
import java.util.Random;


public class GuidHandler {
	private HashMap<Integer, Boolean> guidMap;
	private final int guidThreshold = 10000; 
	private final int managerNotation = 00;
	private final int entityNotation = 000;
	private final int componentNotation = 0000;
	private Random random; 
	
	private GuidHandler(){
		this.guidMap = new HashMap<Integer, Boolean>();
		this.random = new Random();
	}
	
	public int getGUID(){
		return -1;
	}
	
	
	
	
}
