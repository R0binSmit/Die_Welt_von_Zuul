package location;

import java.util.ArrayList;
import java.util.HashMap;

import character.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Definiert Objekte der Klasse Collector, die Items vom Spieler annehmen und
 * eine Reaktion ausführen
 */
public class Collector extends Landscape {
	private int amount;
	private ArrayList<Runnable> execute;
	private String key;
	private int maxAmount;

	/**
	 * 
	 * @param name
	 *            Name des Objekts
	 * @param beschreibung
	 *            Beschreibung des Objekts
	 * @param image
	 *            Bild des Objekts in der 2D-Welt
	 * @param x
	 *            Position des Objekts in der 2D-Welt x-Achse
	 * @param y
	 *            Position des Objekts in der 2D-Welt y-Achse
	 * @param gc
	 *            Grafischer Kontext zum Darstellen des Objekts
	 * @param landscapeResponse
	 *            Liste, die die Enums möglicher Interaktionen und die dazugehörigen
	 *            Texte speichert
	 * @param key
	 *            Name des Items, welches der Collector annimmt
	 * @param amount
	 *            Menge der bisher gesammelten Items (für künftige Erweiterungen
	 *            möglicherweise nicht immer 0 bei Erstellung des Objekts)
	 * @param maxAmount
	 *            Zielwert für das Sammeln von Items
	 * @param execute
	 *            Liste von Befehlen, die in Worldmap übergeben wird
	 */
	public Collector(String name, String beschreibung, Image image, int x, int y, GraphicsContext gc,
			HashMap<LandscapeResponse, String> landscapeResponse, String key, int amount, int maxAmount,
			ArrayList<Runnable> execute) {
		super(name, beschreibung, image, x, y, gc, landscapeResponse);
		this.key = key;
		this.amount = amount;
		this.maxAmount = maxAmount;
		this.execute = execute;
	}

	@Override
	public void onEnterRoom(Player spieler) {

	}

	@Override
	/**
	 * @param Spieler
	 *            Spieler, der mit dem Objekt interagiert
	 */
	public void onUse(Player player) {
		/*
		 * Wenn der Spieler das gesuchte Item nicht trägt, aber noch gesammelt wird,
		 * gebe Text aus
		 */
		if (player.getItem(key) == null && amount < maxAmount) {
			textbox.addText(getResponse(LandscapeResponse.USE_RESPONSE));
		}

		/*
		 * Wenn das Sammeln abgeschlosen ist, tue nichts
		 */
		else if (amount >= maxAmount) {

		}

		/*
		 * Wenn Spieler das Objekt trägt und mit dieser Abgabe das Ziel noch nicht
		 * erreicht ist, gib Item ab und Text aus
		 */
		else if (amount + 1 < maxAmount) {
			player.dropItem(key);
			amount += 1;
			textbox.addText(getResponse(LandscapeResponse.COLLECT_RESPONSE));
			/*
			 * Wenn Spieler das Objekt trägt und mit dieser Abgabe das Ziel erreich ist, gib
			 * Item ab, gib Text aus, führe Befehle aus
			 */
		} else if (amount + 1 == maxAmount) {
			player.dropItem(key);
			amount += 1;
			textbox.addText(getResponse(LandscapeResponse.COLLECTFINISH_RESPONSE));

			for (Runnable runnable : execute) {
				runnable.run();
			}
		}
	}

	public ArrayList<Runnable> getExecute() {
		return execute;
	}

	/**
	 * Klont die übergebene ArrayList
	 * 
	 * @param execute
	 *            Liste aus Worlmap, die als Zwischenspeicher für Befehle dient, bis
	 *            diese hier übergeben werden
	 */
	public void setExecute(ArrayList<Runnable> execute) {
		this.execute = new ArrayList<Runnable>(execute);
	}

}
