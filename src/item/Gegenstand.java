package item;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import main.ZuulUI;

public abstract class Gegenstand {
	private String name, beschreibung;
	private int gewicht, preis;
	private boolean essbar = false;
	private Image image;
	private Point2D pos;
	
	public Gegenstand(String name, String beschreibung, int gewicht, int preis, Image image, Point2D pos, boolean essbar) {
		this.name = name;
		this.beschreibung = beschreibung;
		this.gewicht = gewicht;
		this.essbar = essbar;
		this.preis = preis;
		this.image = image;
		this.pos = pos;
	}

	public String toString() {
		return "Name: " + name + System.getProperty("line.separator") + "Beschreibung: " + beschreibung
				+ System.getProperty("line.separator") + "Gewicht: " + gewicht;
	}
	
	public void show() {
		double x = pos.getX() - image.getWidth() * 0.5;
		double y = pos.getY() - image.getHeight() * 0.5;
		ZuulUI.gc.drawImage(image, x, y);
	}

	public String getName() {
		return name;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public int getGewicht() {
		return gewicht;
	}

	public boolean isEssbar() {
		return essbar;
	}

	public void setEssbar(boolean essbar) {
		this.essbar = essbar;
	}

	public int getPreis() {
		return preis;
	}

	public void setPreis(int preis) {
		this.preis = preis;
	}
}