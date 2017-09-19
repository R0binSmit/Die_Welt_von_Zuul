package character;

import java.util.LinkedList;

import Verhalten.AngriffsVerhalten;
import Verhalten.NPCAngriffVerhalten;
import equipment.Equipment;
import item.Item;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import ort.Raum;

public abstract class Character {
	protected Raum aktuellerRaum;
	protected int maxTraglast, traglast, geld;
	protected LinkedList<Item> gegenstaende;
	protected AngriffsVerhalten angriffsVerhalten;
	protected Point2D pos;
	protected Image image;
	protected Equipment equipment = new Equipment();

	protected String name;
	protected String beschreibung;
	protected GraphicsContext gc;

	@SuppressWarnings("unchecked")
	public Character(String name, int maxTraglast, Raum raum, int x, int y, Image image, GraphicsContext gc,
			LinkedList<Item> gegenstaende) {
		this.name = name;
		this.aktuellerRaum = raum;
		this.maxTraglast = maxTraglast;
		this.gegenstaende = gegenstaende == null ? new LinkedList<Item>() : (LinkedList<Item>) gegenstaende.clone();
		traglast = ermittleGewicht();
		angriffsVerhalten = NPCAngriffVerhalten.getInstance();
		pos = new Point2D(x, y);
		this.image = image;
		this.gc = gc;
	}

	public void show() {
		double x = pos.getX() - image.getWidth() * 0.5;
		double y = pos.getY() - image.getHeight() * 0.5;
		gc.drawImage(image, x, y);
		
		int dist = 10;
		for (Item item : gegenstaende) {
			item.showAt(dist, 750);
		}
		
		equipment.show();
	}

	public abstract void interagieren(Player spieler);

	public boolean gegenstandAufnehmen(Item gegenstand) {
		if (traglast + gegenstand.getGewicht() <= maxTraglast) {
			gegenstaende.add(gegenstand);
			traglast += gegenstand.getGewicht();
			return true;
		} else {
			return false;
		}
	}

	public Item gegenstandAblegen(String name) {
		Item gs = getGegenstand(name);
		if (gs != null) {
			gegenstaende.remove(gs);
			traglast = ermittleGewicht();
		}
		return gs;
	}

	public Item eat(String name) {
		Item essen = getGegenstand(name);
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
		for (Item gs : gegenstaende) {
			gewicht += gs.getGewicht();
		}
		return gewicht;
	}

	public Item getGegenstand(String name) {
		for (Item gs : gegenstaende) {
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
		for (Item gs : gegenstaende) {
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
		// TODO fix no more zustand.
		// sb.append(zustand.getName());
		sb.append(System.getProperty("line.separator"));
		return sb.toString();
	}

	public Raum getAktuellerRaum() {
		return aktuellerRaum;
	}

	public void setAktuellerRaum(Raum aktuellerRaum) {
		this.aktuellerRaum = aktuellerRaum;
	}

	public int getMaxTraglast() {
		return maxTraglast;
	}

	public int getTraglast() {
		return traglast;
	}

	public LinkedList<Item> getGegenstaende() {
		return gegenstaende;
	}

	public String getName() {
		return name;
	}

	public String getBeschreibung() {
		return beschreibung;
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
