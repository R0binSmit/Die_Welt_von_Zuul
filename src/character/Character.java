package character;

import java.util.LinkedList;

import Verhalten.AngriffsVerhalten;
import Verhalten.NPCAngriffVerhalten;
import gegenstand.Brustplatte;
import gegenstand.Gegenstand;
import gegenstand.Hand;
import gegenstand.Helm;
import gegenstand.Hose;
import gegenstand.Schuhe;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import main.ZuulUI;
import ort.Raum;
import zustand.Gesund;
import zustand.GesundheitsZustand;

public abstract class Character {
	protected Raum aktuellerRaum;
	protected GesundheitsZustand zustand;
	protected int maxTraglast, traglast, geld;
	protected LinkedList<Gegenstand> gegenstaende;
	protected AngriffsVerhalten angriffsVerhalten;
	protected Point2D pos;
	protected Image image;
	
	protected String name;
	protected String beschreibung;
	
	protected Hand left;
	protected Hand right;
	protected Helm helm;
	protected Brustplatte brust;
	protected Hose hose;
	protected Schuhe schuhe;

	@SuppressWarnings("unchecked")
	public Character(String name, int maxTraglast, Raum raum, int x, int y, Image image, LinkedList<Gegenstand> gegenstaende) {
		zustand = Gesund.getInstance();
		this.name = name;
		this.aktuellerRaum = raum;
		this.maxTraglast = maxTraglast;
		this.gegenstaende = gegenstaende == null ? new LinkedList<Gegenstand>()
				: (LinkedList<Gegenstand>) gegenstaende.clone();
		traglast = ermittleGewicht();
		angriffsVerhalten = NPCAngriffVerhalten.getInstance();
		pos = new Point2D(x, y);
		this.image = image;
	}
	
	public void show() {
		double x = pos.getX() - image.getWidth() * 0.5;
		double y = pos.getY() - image.getHeight() * 0.5;
		ZuulUI.gc.drawImage(image, x, y);
	}

	public abstract void interagieren(Spieler spieler);

	public boolean gegenstandAufnehmen(Gegenstand gegenstand) {
		if (traglast + gegenstand.getGewicht() <= maxTraglast) {
			gegenstaende.add(gegenstand);
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
		} else {
			essen = null;
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

	public String getInventory() {
		StringBuilder sb = new StringBuilder();
		int i = 1;
		sb.append("Gegenstände im Inventar: ");
		sb.append(System.getProperty("line.separator"));
		for (Gegenstand gs : gegenstaende) {
			sb.append(i);
			sb.append(". Gegenstand: ");
			sb.append(System.getProperty("line.separator"));
			sb.append("Name: ");
			sb.append(gs.getName());
			sb.append(System.getProperty("line.separator"));
			sb.append("Gewicht: ");
			sb.append(gs.getGewicht());
			sb.append(System.getProperty("line.separator"));
			sb.append("Preis: ");
			sb.append(gs.getPreis());
			sb.append(System.getProperty("line.separator"));
			sb.append(System.getProperty("line.separator"));
			i++;
		}
		return sb.toString();
	}
	
	public String getStatus() {
		StringBuilder sb = new StringBuilder();
		sb.append("Gewicht: ");
		sb.append(traglast);
		sb.append("/");
		sb.append(maxTraglast);
		sb.append(System.getProperty("line.separator"));
		sb.append("Geld: ");
		sb.append(geld);
		sb.append(System.getProperty("line.separator"));
		sb.append("Zustand: ");
		sb.append(zustand.getName());
		sb.append(System.getProperty("line.separator"));
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
	
	public void kleineWiederbelebung(Character character) {
		character.zustand = character.zustand.kleineHeilung();
	}
	
	public void grosseWiederbelebung(Character character){
		character.zustand = character.zustand.grosseHeilung();
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

	public String getBeschreibung() {
		return beschreibung;
	}

	public Hand getLeft() {
		return left;
	}

	public Hand getRight() {
		return right;
	}

	public Helm getHelm() {
		return helm;
	}

	public Brustplatte getBrust() {
		return brust;
	}

	public Hose getHose() {
		return hose;
	}

	public Schuhe getSchuhe() {
		return schuhe;
	}

	public void setZustand(GesundheitsZustand zustand) {
		this.zustand = zustand;
	}

	public void setTraglast(int traglast) {
		this.traglast = traglast;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	public void setLeft(Hand left) {
		this.left = left;
	}

	public void setRight(Hand right) {
		this.right = right;
	}

	public void setHelm(Helm helm) {
		this.helm = helm;
	}

	public void setBrust(Brustplatte brust) {
		this.brust = brust;
	}

	public void setHose(Hose hose) {
		this.hose = hose;
	}

	public void setSchuhe(Schuhe schuhe) {
		this.schuhe = schuhe;
	}

	public int getGeld() {
		return geld;
	}

	public void setGeld(int geld) {
		this.geld = geld;
	}

	public Point2D getPos() {
		return pos;
	}
	
	public double getW() {
		return image.getWidth();
	}
	
	public double getH() {
		return image.getHeight();
	}

	public void setPos(Point2D pos) {
		this.pos = pos;
	}
}
