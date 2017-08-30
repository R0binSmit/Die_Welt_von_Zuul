package zustand;
public class Gesund implements GesundheitsZustand {
	private static Gesund instance = new Gesund();
	
	private Gesund() {
	}
	
	public static Gesund getInstance() {
		return instance;
	}
	
	@Override
	public GesundheitsZustand heilen() {
		return getInstance();
	}

	@Override
	public GesundheitsZustand leichtVerletzen() {
		return Verwundet.getInstance();
	}

	@Override
	public GesundheitsZustand schwerVerletzen() {
		return Bewegungsunfaehig.getInstance();
	}
}