package Verhalten;

import java.util.Random;

public class NPCAngriffVerhalten implements AngriffsVerhalten {
	private static NPCAngriffVerhalten instance = new NPCAngriffVerhalten();

	public static NPCAngriffVerhalten getInstance() {
		return instance;
	}

	private NPCAngriffVerhalten() {
	}

	@Override
	public boolean leichtVerletzen() {
		Random randomObj = new Random();
		int randomNumber = randomObj.nextInt(101);

		if (randomNumber <= 60) {
			return true;
		}
		return false;
	}

	@Override
	public boolean schwerVerletzen() {
		Random randomObj = new Random();
		int randomNumber = randomObj.nextInt(101);

		if (randomNumber <= 80) {
			return true;
		}
		return false;
	}
}
