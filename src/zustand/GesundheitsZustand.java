package zustand;

public interface GesundheitsZustand {
	GesundheitsZustand kleineHeilung();
	GesundheitsZustand grosseHeilung();
	GesundheitsZustand leichtVerletzen();
	GesundheitsZustand schwerVerletzen();
	GesundheitsZustand toeten();
	GesundheitsZustand kleineWiederbelebung();
	GesundheitsZustand grosseWiederbelebung();
	String getName();
}