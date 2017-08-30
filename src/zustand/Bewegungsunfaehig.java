package zustand;
public class Bewegungsunfaehig implements GesundheitsZustand {
private static Bewegungsunfaehig instance = new Bewegungsunfaehig();
	
	private Bewegungsunfaehig() {
	}
	
	public static Bewegungsunfaehig getInstance() {
		return instance;
	}
	
	public GesundheitsZustand kleineHeilung() {
		return Verwundet.getInstance();
	}
	
	public GesundheitsZustand grosseHeilung() {
		return Gesund.getInstance();
	}

	public GesundheitsZustand leichtVerletzen() {
		return Tod.getInstance();
	}

	public GesundheitsZustand schwerVerletzen() {
		return Tod.getInstance();
	}
	
	public GesundheitsZustand toeten() {
		return Tod.getInstance();
	}
}