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
 * Diese Klasse modelliert RÃ¤ume in der Welt von Zuul.
 * 
 * Ein "Raum" reprÃ¤sentiert einen Ort in der virtuellen Landschaft des Spiels.
 * Ein Raum ist mit anderen RÃ¤umen Ã¼ber AusgÃ¤nge verbunden. MÃ¶gliche
 * AusgÃ¤nge liegen im Norden, Osten, SÃ¼den und Westen. FÃ¼r jede Richtung
 * hÃ¤lt ein Raum eine Referenz auf den benachbarten Raum.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 2008.03.30
 */
public class Room {
	private ArrayList<Door> exits = new ArrayList<Door>();
	private String description;
	private Image backGround;
	private GraphicsContext gc;
	private LinkedList<Item> items = new LinkedList<Item>();
	private LinkedList<Enemy> enemys = new LinkedList<Enemy>();
	private Worldmap land;
	private ArrayList<Landscape> landscape = new ArrayList<Landscape>();
	private LinkedList<NPC> npc = new LinkedList<NPC>();
	private TextBox textbox = TextBox.newTextBox();

	/**
	 * Erzeuge einen Raum. Ein Raum hat anfangs keine AusgÃ¤nge.
	 * 
	 * @param description
	 *            enthält eine Beschreibung
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
	 * Gegenstand zum Raum hinzufügen
	 * 
	 * @param item
	 *            Der Gegenstand der hinzugefügt werden soll
	 */
	public void addItem(Item item) {
		items.add(item);
	}

	/**
	 * Gegner aus dem Raum entfernen
	 * 
	 * @param enemy
	 *            der zu erntfernende Gegner
	 */
	public void removeEnemy(Enemy enemy) {
		this.enemys.remove(enemy);
	}

	/**
	 * NPC aus dem Raum entfernen
	 * 
	 * @param enemys
	 *            der zu erntfernende NPC
	 */
	public void removeNPC(NPC npc) {
		this.npc.remove(npc);
	}

	public ArrayList<Door> getAusgaenge() {
		return exits;
	}

	/**
	 * Methode, die einem das Item zurückgibt, die am nächsten zu einer bestimmten
	 * Position ist
	 * 
	 * @param position
	 *            Die Position von der aus die Entfernung gemmessen wird
	 * @param maxDist
	 *            Die Maximaldistanz, die das Item entfernt liegen darf
	 * @return Das Item, das am nächsten an dem Punkt dran ist
	 */
	public Item getClosestItem(Point2D position, int maxDist) {
		Item closest = null;
		double minDist = Double.MAX_VALUE;
		for (Item item : items) {
			if (Usefull.intersects(position.getX(), position.getY(), maxDist, maxDist, item.getX(), item.getY(), item.getWidth(),
					item.getHeight())) {
				double dist = position.distance(new Point2D(item.getX(), item.getY()));
				if (dist < minDist) {
					closest = item;
					minDist = dist;
				}
			}
		}
		return closest;
	}
	
	/**
	 * Methode, die einem den NPC zurückgibt, die am nächsten zu einer bestimmten
	 * Position ist
	 * 
	 * @param position
	 *            Die Position von der aus die Entfernung gemmessen wird
	 * @param maxDist
	 *            Die Maximaldistanz, die das Item entfernt liegen darf
	 * @return Das Item, das am nächsten an dem Punkt dran ist
	 */
	public NPC getClosestNPC(Point2D position, int maxDist) {
		NPC closest = null;
		double minDist = Double.MAX_VALUE;
		for(NPC currentNpc : npc) {
			if(Usefull.intersects(position.getX(), position.getY(), maxDist, maxDist, currentNpc.getPosition().getX(), currentNpc.getPosition().getY(), currentNpc.getWidth(),
					currentNpc.getHeight())) {
				double dist = position.distance(new Point2D(currentNpc.getPosition().getX(), currentNpc.getPosition().getY()));
				if (dist < minDist) {
					closest = currentNpc;
					minDist = dist;
				}
			}
		}
		return closest;
	}

	/**
	 * Methode, die einem die Landscape zurückgibt, die am nächsten zu einer bestimmten
	 * Position ist
	 * 
	 * @param position
	 *            Die Position von der aus die Entfernung gemmessen wird
	 * @param maxDist
	 *            Die Maximaldistanz, die die Landscape entfernt liegen darf
	 * @return Die Landscape, die am nächsten an dem Punkt dran ist
	 */
	public Landscape getClosestLandscape(Point2D position, int maxDist) {
		Landscape closest = null;
		double minDist = Double.MAX_VALUE;
		for (Landscape landscape : landscape) {
			if (Usefull.intersects(position.getX(), position.getY(), maxDist, maxDist, landscape.getX(), landscape.getY(),
					landscape.getWidth(), landscape.getHeight())) {
				double dist = position.distance(new Point2D(landscape.getX(), landscape.getY()));
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

	public void setExit(Image image, GraphicsContext gc, Point2D position, Point2D nextPlayerPosition, Room nextRoom) {
		exits.add(new Door(image, gc, position, nextPlayerPosition, nextRoom));
	}

	public void setEnemy(Enemy gegner) {
		this.enemys.add(gegner);
	}

	public void setNPC(NPC npc) {
		this.npc.add(npc);
	}

	/**
	 * Anzeigen des Raums und der Objekte darin
	 */
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

	/**
	 * Update der Dinge die sich im Raum befinden
	 * @param player
	 * Spieler mit dem Updates gemacht werden
	 */
	public void update(Player player) {
		//Alle Gegner zum Bewegen bringen
		for (int i = enemys.size() - 1; i >= 0; i--) {
			Enemy g = enemys.get(i);
			g.move(player);
			if (!g.update()) {
				g.dropItems();
				enemys.remove(g);
			}
		}

		//Bei allen Türen abfragen ob der Spieler darauf steht, und ihn bewegen wenn ja
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
