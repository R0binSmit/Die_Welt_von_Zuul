package location;

import java.util.HashMap;

import character.Player;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.TextBox;

/**
 * 
 * Diese Klasse definiert das abstrakte Landscape-Objekt von dem andere
 * Landscape-Klassen erben können
 *
 */
public abstract class Landscape {
	protected String desciption;
	protected GraphicsContext gc;
	protected Image image;
	protected HashMap<LandscapeResponse, String> landscapeResponse;
	protected String name;
	protected Point2D position;
	protected Room room;
	protected TextBox textbox = TextBox.newTextBox();

	/**
	 * 
	 * @param name
	 *            Name des Objekts
	 * @param description
	 *            Beschreibung des Objekts
	 * @param image
	 *            Bild des Objekts in der 2D-Welt
	 * @param x
	 *            Position des Objekts in der 2D-Welt x-Achse
	 * @param y
	 *            Position des Objekts in der 2D-Welt y-Achse
	 * @param gc
	 *            Grafischer Kontext zum Zeichnen des Objekts
	 * @param landscapeResponse
	 *            Liste, die die Enums möglicher Interaktionen und die dazugehörigen
	 *            Texte speichert
	 */
	public Landscape(String name, String description, Image image, int x, int y, GraphicsContext gc,
			HashMap<LandscapeResponse, String> landscapeResponse) {
		this.name = name;
		this.desciption = description;
		this.landscapeResponse = landscapeResponse == null ? new HashMap<LandscapeResponse, String>()
				: landscapeResponse;
		this.image = image;
		position = new Point2D(x, y);
		this.gc = gc;
	}

	public String getDescription() {
		return desciption;
	}

	public double getHeight() {
		return image.getHeight();
	}

	public String getName() {
		return name;
	}

	public Room getRoom() {
		return room;
	}

	public String getResponse(LandscapeResponse landscapeResponse) {
		return this.landscapeResponse.get(landscapeResponse);
	}

	public double getWidth() {
		return image.getWidth();
	}

	public double getX() {
		return position.getX();
	}

	public double getY() {
		return position.getY();
	}

	/**
	 * Jedes Landscape-Objekt soll die onEnter-Methode implementieren, damit der
	 * Raum alle seine Landscape-Objekte durchlaufen und diese Funktion aufrufen
	 * kann
	 * 
	 * @param spieler
	 *            Spieler, der den Raum betreten hat
	 */
	public abstract void onEnterRoom(Player spieler);

	/**
	 * Beim Interagieren mit dem Objekt
	 * 
	 * @param spieler
	 *            Spieler, der interagiert
	 */
	public abstract void onUse(Player spieler);

	public void setDescription(String beschreibung) {
		this.desciption = beschreibung;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRoom(Room raum) {
		this.room = raum;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public HashMap<LandscapeResponse, String> getLandscapeResponse() {
		return landscapeResponse;
	}

	/**
	 * Klont die aus dem Worldmap-Objekt übergebene HashMap
	 * 
	 * @param landscapeResponse
	 *            HashMap aus Worldmap, die als Zwischenspeicher für Enums und Texte
	 *            fungiert, bis diese hier übergeben werden
	 */
	public void setLandscapeResponse(HashMap<LandscapeResponse, String> landscapeResponse) {
		this.landscapeResponse = new HashMap<LandscapeResponse, String>(landscapeResponse);
	}

	/**
	 * Stellt das Objekt in der 2D-Landschaft dar
	 */
	public void show() {
		double x = position.getX() - image.getWidth() * 0.5;
		double y = position.getY() - image.getHeight() * 0.5;
		gc.drawImage(image, x, y);
	}
}
