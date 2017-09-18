package ort;

import java.util.HashMap;

import character.Spieler;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import main.ZuulUI;

public abstract class Landscape {
	protected String name;
	protected String beschreibung;
	protected Raum raum;
	protected HashMap<LandscapeResponse, String> landscapeResponse;
	protected Point2D pos;
	protected Image image;

	public Landscape(String name, String beschreibung, Image image, int x, int y, HashMap<LandscapeResponse, String> landscapeResponse) {
		this.name = name;
		this.beschreibung = beschreibung;
		this.landscapeResponse = landscapeResponse == null ? new HashMap<LandscapeResponse, String>()
				: landscapeResponse;
		this.image = image;
		pos = new Point2D(x, y);
	}

	public abstract void onEnterRoom(Spieler spieler);

	public abstract void onUse(Spieler spieler);
	
	public void show() {
		double x = pos.getX() - image.getWidth() * 0.5;
		double y = pos.getY() - image.getHeight() * 0.5;
		ZuulUI.gc.drawImage(image, x, y);
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

	public Raum getRaum() {
		return raum;
	}

	public void setRaum(Raum raum) {
		this.raum = raum;
	}
	
	public String getResponse(LandscapeResponse lr) {
		return landscapeResponse.get(lr);
	}
}
