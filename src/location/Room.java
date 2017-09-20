package location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import character.Enemy;
import character.NPC;
import character.Player;
import item.Item;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.Usefull;

/**
 * Diese Klasse modelliert Räume in der Welt von Zuul.
 * 
 * Ein "Raum" repräsentiert einen Ort in der virtuellen Landschaft des Spiels.
 * Ein Raum ist mit anderen Räumen über Ausgänge verbunden. Mögliche Ausgänge
 * liegen im Norden, Osten, Süden und Westen. Für jede Richtung hält ein Raum
 * eine Referenz auf den benachbarten Raum.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 2008.03.30
 */
public class Room {
	private String beschreibung;
	private Worldmap land;
	private HashMap<String, Room> ausgaenge = new HashMap<String, Room>();
	private LinkedList<Item> gegenstaende = new LinkedList<Item>();
	private ArrayList<Landscape> landschaft = new ArrayList<Landscape>();
	private LinkedList<Enemy> gegner = new LinkedList<Enemy>();
	private LinkedList<NPC> npc = new LinkedList<NPC>();
	private Image bg;
	protected GraphicsContext gc;

	/**
	 * Erzeuge einen Raum mit einer Beschreibung. Ein Raum hat anfangs keine
	 * Ausgänge.
	 * 
	 * @param beschreibung
	 *            enthält eine Beschreibung in der Form "in einer Küche" oder "auf
	 *            einem Sportplatz".
	 */
	public Room(String beschreibung, Worldmap land, Image bg, GraphicsContext gc) {
		this.beschreibung = beschreibung;
		this.land = land;
		this.bg = bg;
		this.gc = gc;
	}

	/**
	 * Definiere die Ausgänge dieses Raums. Jede Richtung führt entweder in einen
	 * anderen Raum oder ist 'null' (kein Ausgang).
	 * 
	 * @param norden
	 *            Der Nordeingang.
	 * @param osten
	 *            Der Osteingang.
	 * @param sueden
	 *            Der Südeingang.
	 * @param westen
	 *            Der Westeingang.
	 */
	public void setzeAusgang(String richtung, Room raum) {
		ausgaenge.put(richtung, raum);
	}
	
	public Landscape getClosestLandscape(Point2D pos, int maxDist) {
		Landscape closest = null;
		double minDist = Double.MAX_VALUE;
		for (Landscape landscape : landschaft) {
			if(Usefull.intersects(pos.getX(), pos.getY(), maxDist, maxDist, landscape.getX(), landscape.getY(), landscape.getW(), landscape.getH())) {
				double dist = pos.distance(new Point2D(landscape.getX(), landscape.getY()));
				if (dist < minDist) {
					closest = landscape;
					minDist = dist;
				}
			}
		}
		return closest;
	}
	
	public Item getClosestItem(Point2D pos, int maxDist) {
		Item closest = null;
		double minDist = Double.MAX_VALUE;
		for (Item item : gegenstaende) {
			if(Usefull.intersects(pos.getX(), pos.getY(), maxDist, maxDist, item.getX(), item.getY(), item.getWidth(), item.getHeight())) {
				double dist = pos.distance(new Point2D(item.getX(), item.getY()));
				if (dist < minDist) {
					closest = item;
					minDist = dist;
				}
			}
		}
		return closest;
	}
	
	public void show() {
		gc.drawImage(bg, 0, 0);
		
		for (Item gs : gegenstaende) {
			gs.show();
		}
		
		for (Landscape ls : landschaft) {
			ls.show();
		}
		
		for (NPC np :npc) {
			np.show();
		}
		
		for (Enemy g : gegner) {
			g.show();
		}
	}
	
	public void update(Player player) {
		for (Enemy g : gegner) {
			g.move(player.getPosition());
			g.update();
		}
	}

	public Room getExit(String richtung) {
		return ausgaenge.get(richtung);
	}

	public String ausgaengeToString() {
		StringBuilder sb = new StringBuilder("");
		for (String key : ausgaenge.keySet()) {
			sb.append(key);
			sb.append(" ");
		}
		return sb.toString();
	}

	public void addItem(Item gegenstand) {
		gegenstaende.add(gegenstand);
	}

	public void setzeGegner(Enemy gegner) {
		this.gegner.add(gegner);
	}

	public void enterneGegner(Enemy gegner) {
		this.gegner.remove(gegner);
	}
	
	public void setzeNPC(NPC npc) {
		this.npc.add(npc);
	}

	public void enterneNPC(NPC npc) {
		this.npc.remove(npc);
	}
	
	public NPC getNPC(String name) {
		for (NPC np : npc) {
			if (np.getName().equalsIgnoreCase(name)) {
				return np;
			}
		}
		return null;
	}

	public void landschaftBauen(Landscape landscape) {
		landschaft.add(landscape);
		landscape.setRaum(this);
	}
	
	public void landschaftEntfernen(String name) {
		for (Landscape ls : landschaft) {
			if (ls.getName().equalsIgnoreCase(name)) {
				landschaft.remove(ls);
				break;
			}
		}
	}

	public Item removeItem(String name) {
		for (Item gs : gegenstaende) {
			if (gs.getName().equalsIgnoreCase(name)) {
				gegenstaende.remove(gs);
				return gs;
			}
		}
		return null;
	}

	public Item getGegenstand(String name) {
		for (Item gs : gegenstaende) {
			if (gs.getName().equalsIgnoreCase(name)) {
				return gs;
			}
		}
		return null;
	}

	public Landscape getLandschaft(String name) {
		for (Landscape ls : landschaft) {
			if (ls.getName().equalsIgnoreCase(name)) {
				return ls;
			}
		}
		return null;
	}

	public void onEnterRoomEvent(Player spieler) {
		for (int i = landschaft.size() - 1; i >= 0; i--) {
			Landscape ls = landschaft.get(i); 
			ls.onEnterRoom(spieler);
		}
	}

	public String gegenstaendeToString() {
		StringBuilder sb = new StringBuilder("");
		for (Item gegenstand : gegenstaende) {
			sb.append(gegenstand.getName());
			sb.append(" ");
		}
		return sb.toString();
	}

	public String landschaftToString() {
		StringBuilder sb = new StringBuilder("");
		for (Landscape landscape : landschaft) {
			sb.append(landscape.getName());
			sb.append(" ");
		}
		return sb.toString();
	}

	private String gegnerToString() {
		StringBuilder sb = new StringBuilder("");
		for (Enemy gn : gegner) {
			sb.append(gn.getName());
			sb.append(" ");
		}
		return sb.toString();
	}

	private String npcsToString() {
		StringBuilder sb = new StringBuilder("");
		for (NPC np : npc) {
			sb.append(np.getName());
			sb.append(" ");
		}
		return sb.toString();
	}

	public String getLongDesciption() {
		StringBuilder sb = new StringBuilder("");
		sb.append(gibBeschreibung());
		sb.append(System.getProperty("line.separator"));
		sb.append("Hier ist: ");
		sb.append(landschaftToString());
		sb.append(System.getProperty("line.separator"));
		sb.append("Gegenstände: ");
		sb.append(gegenstaendeToString());
		sb.append(System.getProperty("line.separator"));
		sb.append("Ausgänge: ");
		sb.append(ausgaengeToString());
		sb.append(System.getProperty("line.separator"));
		sb.append("Gegner: ");
		sb.append(gegnerToString());
		sb.append(System.getProperty("line.separator"));
		sb.append("NPC's: ");
		sb.append(npcsToString());
		return sb.toString();
	}

	/**
	 * @return Die Beschreibung dieses Raums.
	 */
	public String gibBeschreibung() {
		return beschreibung;
	}

	public Worldmap getLand() {
		return land;
	}

	public LinkedList<Enemy> getGegnerList() {
		return gegner;
	}

	public HashMap<String, Room> getAusgaenge() {
		return ausgaenge;
	}
}
