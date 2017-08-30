package zustand;

public class Verwundet implements GesundheitsZustand {
	private static Verwundet instance = new Verwundet();

	private Verwundet() {
	}

	public static Verwundet getInstance() {
		return instance;
	}

	public GesundheitsZustand kleineHeilung() {
		return Gesund.getInstance();
	}

	public GesundheitsZustand grosseHeilung() {
		return Gesund.getInstance();
	}

	public GesundheitsZustand leichtVerletzen() {
		return Bewegungsunfaehig.getInstance();
	}

	public GesundheitsZustand schwerVerletzen() {
		return Tod.getInstance();
	}

	public GesundheitsZustand toeten() {
		return Tod.getInstance();
	}
}