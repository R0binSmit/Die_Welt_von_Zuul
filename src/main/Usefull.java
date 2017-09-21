package main;

import javafx.scene.image.Image;

/**
 * @author Frank Eine Klasse für nützliche Klassen die allerdings nich zu einer
 *         Bestimmten Klasse gehören
 */
public class Usefull {
	/**
	 * prüfen ob zwei Rechtecke sich überschneiden
	 * 
	 * @param x1
	 *            X Position des ersten Rechecks
	 * @param y1
	 *            Y Position des ersten Rechecks
	 * @param w1
	 *            Breite des ersten Rechtecks
	 * @param h1
	 *            Höhe des ersten Rechtecks
	 * @param x2
	 *            X Position des zweiten Rechecks
	 * @param y2
	 *            Y Position des zweiten Rechecks
	 * @param w2
	 *            Breite des zweiten Rechecks
	 * @param h2
	 *            Höhe des zweiten Rechecks
	 * @return Überschneiden sie sich true/false
	 */
	public static boolean intersects(double x1, double y1, double w1, double h1, double x2, double y2, double w2,
			double h2) {
		return ((x1 + w1 > x2 && x1 < x2 + w2) && (y1 + h1 > y2 && y1 < y2 + h2));
	}

	/**
	 * Das Bild das auf dem Eingabepfad liegt zurückgeben
	 * 
	 * @param link
	 *            Pfad des Bildes
	 * @return Das Bild
	 */
	public static Image linkToImage(String link) {
		return new Image(ZuulUI.class.getResourceAsStream(link));
	}

	/**
	 * Einen Wert von einem Wertebereich zu einem anderem Mappen
	 * 
	 * @param value
	 *            Wert der gemappt werden soll
	 * @param min
	 *            Minimum des Wertes im alten Bereich
	 * @param max
	 *            Maximum des Wertes im alten Bereich
	 * @param nMin
	 *            Minimum des Wertes im neuen Bereich
	 * @param nMax
	 *            Maximum des Wertes im neuen Bereich
	 * @return den Wert des Wertes im neuen Bereich
	 */
	public static double map(double value, double min, double max, double nMin, double nMax) {
		return ((value - min) / (max - min)) * (nMax - nMin) + nMin;
	}
}