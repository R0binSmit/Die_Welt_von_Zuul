package character;

import java.util.LinkedList;

import item.Item;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import location.Room;

public class Trader extends NPC {
	public Trader(String name, int maxTraglast, Room raum, int x, int y, Image image, GraphicsContext gc,
			LinkedList<Item> gegenstaende) {
		super(name, maxTraglast, raum, x, y, image, gc, gegenstaende);
	}

	/*public void interagieren(Player spieler) {
		System.out.println(text);
		Parser pr = new Parser();
		Befehl befehl;
		do {
			System.out.println(getInventory());
			befehl = pr.liefereBefehl();
			if (befehl.gibBefehlswort().equalsIgnoreCase("take")) {
				if (trade(this, spieler, befehl.gibZweitesWort())) {
					System.out.println("Handel durchgeführt");
				} else {
					System.out.println("Der Gegenstand existiert nicht oder ist zu teuer für sie");
				}
			} else if (befehl.gibBefehlswort().equalsIgnoreCase("drop")) {
				if (trade(spieler, this, befehl.gibZweitesWort())) {
					System.out.println("Handel durchgeführt");
				} else {
					System.out.println("Der Gegenstand existiert nicht oder ist zu teuer für sie");
				}
			} else if (befehl.gibBefehlswort().equalsIgnoreCase("quit")) {
				System.out.println("Handel beendet");
				break;
			} else {
				System.out.println("Diesen Befehl gibt es hier nicht!");
			}
		} while (true);
	}*/
	
	public boolean trade(Character verkauf, Character kauf, String gegenstand) {
		Item ware = verkauf.getGegenstand(gegenstand);
		if (ware != null && kauf.getGeld() >= ware.getPrice()) {
			kauf.pickUpItem(verkauf.gegenstandAblegen(ware.getName()));
			verkauf.setGeld(verkauf.getGeld() + ware.getPrice());
			kauf.setGeld(kauf.getGeld() - ware.getPrice());
			return true;
		}
		return false;
	}
}