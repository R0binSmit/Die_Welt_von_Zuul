package location;

import java.util.HashMap;

import character.Player;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.TextBox;

public abstract class Landscape {
	protected String name;
	protected String beschreibung;
	protected Room raum;
	protected HashMap<LandscapeResponse, String> landscapeResponse;
	protected Point2D pos;
	protected Image image;
	protected GraphicsContext gc;
	protected TextBox tb = TextBox.newTextBox(gc);

	public Landscape(String name, String beschreibung, Image image, int x, int y, GraphicsContext gc, HashMap<LandscapeResponse, String> landscapeResponse) {
		this.name = name;
		this.beschreibung = beschreibung;
		this.landscapeResponse = landscapeResponse == null ? new HashMap<LandscapeResponse, String>()
				: landscapeResponse;
		this.image = image;
		pos = new Point2D(x, y);
		this.gc = gc;
	}

	public abstract void onEnterRoom(Player spieler);

	public abstract void onUse(Player spieler);
	
	public void show() {
		double x = pos.getX() - image.getWidth() * 0.5;
		double y = pos.getY() - image.getHeight() * 0.5;
		gc.drawImage(image, x, y);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	public Room getRaum() {
		return raum;
	}

	public void setRaum(Room raum) {
		this.raum = raum;
	}
	
	public String getResponse(LandscapeResponse lr) {
		return landscapeResponse.get(lr);
	}
	
	public double getX() {
		return pos.getX();
	}
	
	public double getY() {
		return pos.getY();
	}
	
	public double getW() {
		return image.getWidth();
	}
	
	public double getH() {
		return image.getHeight();
	}
}
