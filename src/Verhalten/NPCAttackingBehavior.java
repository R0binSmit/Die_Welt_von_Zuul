package Verhalten;

import java.util.Random;

public class NPCAttackingBehavior implements AttackingBehavior {
	private static NPCAttackingBehavior instance = new NPCAttackingBehavior();

	public static NPCAttackingBehavior getInstance() {
		return instance;
	}

	private NPCAttackingBehavior() {
	}

	@Override
	public boolean hurtSlightly() {
		Random randomObj = new Random();
		int randomNumber = randomObj.nextInt(101);

		if (randomNumber <= 60) {
			return true;
		}
		return false;
	}

	@Override
	public boolean hurtBadly() {
		Random randomObj = new Random();
		int randomNumber = randomObj.nextInt(101);

		if (randomNumber <= 80) {
			return true;
		}
		return false;
	}
}
