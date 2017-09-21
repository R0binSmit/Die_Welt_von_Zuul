package Verhalten;

import java.util.Random;

public class PlayerAttackingBehavior implements AttackingBehavior {
	private static PlayerAttackingBehavior instance = new PlayerAttackingBehavior();

	public static PlayerAttackingBehavior getInstance() {
		return instance;
	}

	private PlayerAttackingBehavior() {
	}

	@Override
	public boolean hurtSlightly() {
		Random randomObj = new Random();
		int randomNumber = randomObj.nextInt(101);

		if (randomNumber <= 30) {
			return true;
		}
		return false;
	}

	@Override
	public boolean hurtBadly() {
		Random randomObj = new Random();
		int randomNumber = randomObj.nextInt(101);

		if (randomNumber <= 50) {
			return true;
		}
		return false;
	}
}
