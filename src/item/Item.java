package item;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Item implements IItem {
	protected String name, beschreibung;
	protected int gewicht, preis;
	protected boolean essbar = false;
	protected Image image;
	protected Point2D pos;
	protected GraphicsContext gc;
	
	public Item(String name, String beschreibung, int gewicht, int preis, Image image, Point2D pos, GraphicsContext gc, boolean essbar) {
		this.name = name;
		this.beschreibung = beschreibung;
		this.gewicht = gewicht;
		this.essbar = essbar;
		this.preis = preis;
		this.image = image;
		this.pos = pos;
		this.gc = gc;
	}

	public String toString() {
		return "Name: " + name + System.getProperty("line.separator") + "Beschreibung: " + beschreibung
				+ System.getProperty("line.separator") + "Gewicht: " + gewicht;
	}
	
	public void show() {
		gc.drawImage(image, pos.getX(), pos.getY());
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