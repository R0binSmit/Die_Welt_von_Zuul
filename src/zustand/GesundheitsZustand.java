package zustand;

public interface GesundheitsZustand {
	GesundheitsZustand kleineHeilung();

	GesundheitsZustand grosseHeilung();

	GesundheitsZustand leichtVerletzen();

	GesundheitsZustand schwerVerletzen();

	GesundheitsZustand toeten();
}