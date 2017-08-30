package zustand;
public class Verwundet implements GesundheitsZustand {
	private static Verwundet instance = new Verwundet();
	
	private Verwundet() {
	}
	
	public static Verwundet getInstance() {
		return instance;
	}
	
	@Override
	public GesundheitsZustand heilen() {
		return Gesund.getInstance();
	}

	@Override
	public GesundheitsZustand leichtVerletzen() {
		return Bewegungsunfaehig.getInstance();
	}

	@Override
	public GesundheitsZustand schwerVerletzen() {
		return Bewegungsunfaehig.getInstance();
	}
}