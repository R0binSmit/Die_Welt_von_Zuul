package zustand;
public class Bewegungsunfaehig implements GesundheitsZustand {
private static Bewegungsunfaehig instance = new Bewegungsunfaehig();
	
	private Bewegungsunfaehig() {
	}
	
	public static Bewegungsunfaehig getInstance() {
		return instance;
	}
	
	@Override
	public GesundheitsZustand heilen() {
		return Verwundet.getInstance();
	}

	@Override
	public GesundheitsZustand leichtVerletzen() {
		return getInstance();
	}

	@Override
	public GesundheitsZustand schwerVerletzen() {
		return getInstance();
	}
}