package net.magenta.engine.message;

import java.util.LinkedList;
import java.util.Random;

public class GlobalIdentityManager {

	public static LinkedList<Integer> guidList = new LinkedList<Integer>();
	public static Random random = new Random();
	public static int assignGlobalIdentity(){
		int guid;
		for(;;){
			if (!guidList.contains(guid = random.nextInt())){
				guidList.add(guid);
				break;
			}
		}
		return guid;
	}
	
}
