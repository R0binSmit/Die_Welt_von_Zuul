package ort;

import java.util.ArrayList;
import java.util.HashMap;

import character.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Sammler extends Landscape {
	private String key;
	private ArrayList<Raum> destination;
	private ArrayList<Runnable> execute;

	public Sammler(String name, String beschreibung, Image image, int x, int y, GraphicsContext gc, HashMap<LandscapeResponse, String> landscapeResponse, String key,
			ArrayList<Runnable> execute) {
		super(name, beschreibung, image, x, y, gc, landscapeResponse);
		this.key = key;
		this.execute = execute;
	}

	public void onUse(Player spieler) {
		if (spieler.getGegenstand(key) == null) {
			System.out.println(getResponse(LandscapeResponse.USE_RESPONSE));
		}

		else {
			System.out.println(getResponse(LandscapeResponse.COLLECT_RESPONSE));

			for (Runnable run : execute) {
				run.run();
			}
		}
	}

	public void onEnterRoom(Player spieler) {

	}

}
