package location;

import java.util.ArrayList;
import java.util.LinkedList;

import character.Enemy;
import character.NPC;
import character.Player;
import item.Item;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.TextBox;
import main.Usefull;

/**
 * Diese Klasse modelliert Räume in der Welt von Zuul.
 * 
 * Ein "Raum" repräsentiert einen Ort in der virtuellen Landschaft des Spiels.
 * Ein Raum ist mit anderen Räumen über Ausgänge verbunden. Mögliche
 * Ausgänge liegen im Norden, Osten, Süden und Westen. Für jede Richtung
 * hält ein Raum eine Referenz auf den benachbarten Raum.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 2008.03.30
 */
public class Room {
	private ArrayList<Door> exits = new ArrayList<Door>();
	private String description;
	private Image backGround;
	protected GraphicsContext gc;
	private LinkedList<Item> items = new LinkedList<Item>();
	private LinkedList<Enemy> enemys = new LinkedList<Enemy>();
	private Worldmap land;
	private ArrayList<Landscape> landscape = new ArrayList<Landscape>();
	private LinkedList<NPC> npc = new LinkedList<NPC>();
	private TextBox textbox = TextBox.newTextBox();

	/**
	 * Erzeuge einen Raum. Ein Raum hat anfangs keine
	 * Ausgänge.
	 * 
	 * @param description
	 *            enth�lt eine Beschreibung
	 * @param land
	 *            Die Klasse die die Story und Objekte verwaltet
	 * @param gc
	 *            Graphicscontext mit dem der Raumhintergrund dargestellt wird
	 */
	public Room(String description, Worldmap land, Image backGround, GraphicsContext gc) {
		this.description = description;
		this.land = land;
		this.backGround = backGround;
		this.gc = gc;
	}

	/**
	 * Gegenstand zum Raum hinzuf�gen
	 * @param item
	 * Der Gegenstand der hinzugef�gt werden soll
	 */
	public void addItem(Item item) {
		items.add(item);
	}

	/**
	 * Gegner aus dem Raum entfernen
	 * @param enemy
	 * der zu erntfernende Gegner
	 */
	public void removeEnemy(Enemy enemy) {
		this.enemys.remove(enemy);
	}

	/**
	 * NPC aus dem Raum entfernen
	 * @param enemys
	 * der zu erntfernende NPC
	 */
	public void removeNPC(NPC npc) {
		this.npc.remove(npc);
	}

	public ArrayList<Door> getAusgaenge() {
		return exits;
	}

	public Item getClosestItem(Point2D pos, int maxDist) {
		Item closest = null;
		double minDist = Double.MAX_VALUE;
		for (Item item : items) {
			if (Usefull.intersects(pos.getX(), pos.getY(), maxDist, maxDist, item.getX(), item.getY(), item.getWidth(),
					item.getHeight())) {
				double dist = pos.distance(new Point2D(item.getX(), item.getY()));
				if (dist < minDist) {
					closest = item;
					minDist = dist;
				}
			}
		}
		return closest;
	}

	public Landscape getClosestLandscape(Point2D pos, int maxDist) {
		Landscape closest = null;
		double minDist = Double.MAX_VALUE;
		for (Landscape landscape : landscape) {
			if (Usefull.intersects(pos.getX(), pos.getY(), maxDist, maxDist, landscape.getX(), landscape.getY(),
					landscape.getWidth(), landscape.getHeight())) {
				double dist = pos.distance(new Point2D(landscape.getX(), landscape.getY()));
				if (dist < minDist) {
					closest = landscape;
					minDist = dist;
				}
			}
		}
		return closest;
	}

	public Item getItem(String name) {
		for (Item gs : items) {
			if (gs.getName().equalsIgnoreCase(name)) {
				return gs;
			}
		}
		return null;
	}

	public LinkedList<Enemy> getGegnerList() {
		return enemys;
	}

	public Worldmap getLand() {
		return land;
	}

	public Landscape getLandschaft(String name) {
		for (Landscape ls : landscape) {
			if (ls.getName().equalsIgnoreCase(name)) {
				return ls;
			}
		}
		return null;
	}

	public NPC getNPC(String name) {
		for (NPC np : npc) {
			if (np.getName().equalsIgnoreCase(name)) {
				return np;
			}
		}
		return null;
	}

	/**
	 * @return Die Beschreibung dieses Raums.
	 */
	public String getDescription() {
		return description;
	}

	public void BuildLandscape(Landscape landscape) {
		this.landscape.add(landscape);
		landscape.setRoom(this);
	}

	public void removeLandscape(String name) {
		for (Landscape ls : landscape) {
			if (ls.getName().equalsIgnoreCase(name)) {
				landscape.remove(ls);
				break;
			}
		}
	}

	public void onEnterRoomEvent(Player spieler) {
		for (int i = landscape.size() - 1; i >= 0; i--) {
			Landscape ls = landscape.get(i);
			ls.onEnterRoom(spieler);
		}
	}

	public Item removeItem(String name) {
		for (Item gs : items) {
			if (gs.getName().equalsIgnoreCase(name)) {
				items.remove(gs);
				return gs;
			}
		}
		return null;
	}

	/**
	 * Definiere die Ausgänge dieses Raums. Jede Richtung führt entweder in einen
	 * anderen Raum oder ist 'null' (kein Ausgang).
	 */
	public void setExit(Image image, GraphicsContext gc, Point2D position, Point2D nextPlayerPosition, Room nextRoom) {
		exits.add(new Door(image, gc, position, nextPlayerPosition, nextRoom));
	}

	public void setEnemy(Enemy gegner) {
		this.enemys.add(gegner);
	}

	public void setNPC(NPC npc) {
		this.npc.add(npc);
	}

	public void show() {
		gc.drawImage(backGround, 0, 0);

		for (Item gs : items) {
			gs.show();
		}

		for (Landscape ls : landscape) {
			ls.show();
		}

		for (NPC np : npc) {
			np.show();
		}

		for (Enemy g : enemys) {
			g.show();
		}

		for (Door door : exits) {
			door.show();
		}
	}

	public void update(Player player) {
		for (int i = enemys.size() - 1; i >= 0; i--) {
			Enemy g = enemys.get(i);
			g.move(player);
			if (!g.update()) {
				g.dropItems();
				enemys.remove(g);
			}
		}

		Point2D pos = player.getPosition();
		pos = new Point2D(pos.getX() - player.getWidth() / 2, pos.getY() - player.getHeight() / 2);

		for (Door door : exits) {
			if (Usefull.intersects(door.getX(), door.getY(), door.getWidth(), door.getHeight(), pos.getX(), pos.getY(),
					player.getWidth(), player.getHeight())) {
				door.changeRoom(player);
				textbox.addText(player.getRoom().getDescription());
				break;
			}
		}
	}
}
