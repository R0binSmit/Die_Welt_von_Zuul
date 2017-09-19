package main;

import javafx.scene.image.Image;

public class Usefull {
	public static boolean intersects(double x1, double y1, double w1, double h1, double x2, double y2, double w2, double h2) {
		return ((x1 + w1 > x2 && x1 < x2 + w2) && (y1 + h1 > y2 && y1 < y2 + h2));
	}
	
	public static Image linkToImage(String link) {
		return new Image(ZuulUI.class.getResourceAsStream(link));
	}
}