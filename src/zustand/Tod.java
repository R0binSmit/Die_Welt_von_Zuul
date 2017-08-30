package zustand;

public class Tod implements GesundheitsZustand {
	private static Tod instance = new Tod();

	private Tod() {
	}

	public static Tod getInstance() {
		return instance;
	}

	public GesundheitsZustand kleineHeilung() {
		return instance;
	}

	public GesundheitsZustand grosseHeilung() {
		return instance;
	}

	public GesundheitsZustand leichtVerletzen() {
		return instance;
	}

	public GesundheitsZustand schwerVerletzen() {
		return instance;
	}

	public GesundheitsZustand toeten() {
		return instance;
	}
	
	public GesundheitsZustand kleineWiederbelebung() {
		return Verwundet.getInstance();
	}
	
	public GesundheitsZustand grosseWiederbelebung() {
		return Gesund.getInstance();
	}
}
