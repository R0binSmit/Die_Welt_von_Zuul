package character;

import java.util.LinkedList;

import gegenstand.Gegenstand;
import ort.Raum;
import zustand.Gesund;
import zustand.GesundheitsZustand;

public abstract class Character {
	protected Raum aktuellerRaum;
	protected GesundheitsZustand zustand;
	protected int maxTraglast, traglast;
	protected LinkedList<Gegenstand> gegenstaende;
	protected String name;

	@SuppressWarnings("unchecked")
	public Character(int maxTraglast, Raum raum, LinkedList<Gegenstand> gegenstaende) {
		zustand = Gesund.getInstance();
		this.aktuellerRaum = raum;
		this.maxTraglast = maxTraglast;
		this.gegenstaende = gegenstaende == null ? new LinkedList<Gegenstand>()
				: (LinkedList<Gegenstand>) gegenstaende.clone();
		traglast = ermittleGewicht();
	}
	
	public abstract void interagieren();
	
	public boolean gegenstandAufnehmen(Gegenstand gegenstand) {
		if (traglast + gegenstand.getGewicht() <= maxTraglast) {
			gegenstaende.add(gegenstand);
			aktuellerRaum.gegenstandAufheben(gegenstand.getName());
			traglast += gegenstand.getGewicht();
			return true;
		} else {
			return false;
		}
	}

	public Gegenstand gegenstandAblegen(String name) {
		Gegenstand gs = getGegenstand(name);
		if (gs != null) {
			gegenstaende.remove(gs);
			aktuellerRaum.gegenstandAblegen(gs);
			traglast = ermittleGewicht();
		}
		return gs;
	}

	public Gegenstand eat(String name) {
		Gegenstand essen = getGegenstand(name);
		if (essen != null && essen.isEssbar()) {
			gegenstaende.remove(essen);
			traglast = ermittleGewicht();
			if (essen.getName().equalsIgnoreCase("muffin")) {
				maxTraglast += 10;
			}
		}
		return essen;
	}

	public int ermittleGewicht() {
		int gewicht = 0;
		for (Gegenstand gs : gegenstaende) {
			gewicht += gs.getGewicht();
		}
		return gewicht;
	}

	public Gegenstand getGegenstand(String name) {
		for (Gegenstand gs : gegenstaende) {
			if (gs.getName().equalsIgnoreCase(name)) {
				return gs;
			}
		}
		return null;
	}

	public String showStatus() {
		int i = 1;
		StringBuilder sb = new StringBuilder();
		sb.append("Gewicht: ");
		sb.append(traglast);
		sb.append("/");
		sb.append(maxTraglast);
		sb.append(System.getProperty("line.separator"));
		sb.append("Zustand: ");
		sb.append(zustand.getClass().getName());
		sb.append(System.getProperty("line.separator"));
		for (Gegenstand gs : gegenstaende) {
			sb.append(i);
			sb.append(". Gegenstand: ");
			sb.append(gs.getName());
			sb.append(System.getProperty("line.separator"));
			sb.append(i);
			sb.append(". Gewicht: ");
			sb.append(gs.getGewicht());
			sb.append(System.getProperty("line.separator"));
			sb.append(System.getProperty("line.separator"));
			i++;
		}
		return sb.toString();
	}

	public void kleineHeilung(Character character) {
		character.zustand = character.zustand.kleineHeilung();
	}
	
	public void grosseHeilung(Character character) {
		character.zustand = character.zustand.grosseHeilung();
	}

	public void leichtVerletzen(Character character) {
		character.zustand = character.zustand.leichtVerletzen();
	}

	public void schwerVerletzen(Character character) {
		character.zustand = character.zustand.schwerVerletzen();
	}
	
	public void toeten(Character character) {
		character.zustand = character.zustand.toeten();
	}

	public Raum getAktuellerRaum() {
		return aktuellerRaum;
	}

	public void setAktuellerRaum(Raum aktuellerRaum) {
		this.aktuellerRaum = aktuellerRaum;
	}

	public GesundheitsZustand getZustand() {
		return zustand;
	}

	public int getMaxTraglast() {
		return maxTraglast;
	}

	public int getTraglast() {
		return traglast;
	}

	public LinkedList<Gegenstand> getGegenstaende() {
		return gegenstaende;
	}

	public String getName() {
		return name;
	}
}
