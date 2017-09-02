package Verhalten;

import java.util.Random;

public class NPCAngriffVerhalten implements AngriffsVerhalten {
	private static NPCAngriffVerhalten instance = new NPCAngriffVerhalten();
	
	private NPCAngriffVerhalten(){
	}
	
	public boolean leichtVerletzen(){
		Random randomObj = new Random();
		int randomNumber = randomObj.nextInt(101);
		
		if(randomNumber <= 60){
			return true;
		}
		return false;
	}
	
	public boolean schwerVerletzen(){
		Random randomObj = new Random();
		int randomNumber = randomObj.nextInt(101);
		
		if(randomNumber <= 80){
			return true;
		}
		return false;
	}
	
	public static NPCAngriffVerhalten getInstance(){
		return instance;
	}
}
