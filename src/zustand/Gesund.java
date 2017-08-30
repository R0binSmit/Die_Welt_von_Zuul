package zustand;
public class Gesund implements GesundheitsZustand {
	private static Gesund instance = new Gesund();
	
	private Gesund() {
	}
	
	public static Gesund getInstance() {
		return instance;
	}
	
	public GesundheitsZustand kleineHeilung() {
		return getInstance();
	}
	
	public GesundheitsZustand grosseHeilung() {
		return getInstance();
	}

	public GesundheitsZustand leichtVerletzen() {
		return Verwundet.getInstance();
	}

	public GesundheitsZustand schwerVerletzen() {
		return Bewegungsunfaehig.getInstance();
	}
	
	public GesundheitsZustand toeten() {
		return Tod.getInstance();
	}
}