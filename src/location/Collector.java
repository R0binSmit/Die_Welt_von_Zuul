package location;

import java.util.ArrayList;
import java.util.HashMap;

import character.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Collector extends Landscape {
	private int amount;
	private ArrayList<Runnable> execute;
	private String key;
	private int maxAmount;

	public Collector(String name, String beschreibung, Image image, int x, int y, GraphicsContext gc,
			HashMap<LandscapeResponse, String> landscapeResponse, String key, int amount, int maxAmount,
			ArrayList<Runnable> execute) {
		super(name, beschreibung, image, x, y, gc, landscapeResponse);
		this.key = key;
		this.amount = amount;
		this.execute = execute;
	}

	@Override
	public void onEnterRoom(Player spieler) {

	}

	@Override
	public void onUse(Player player) {
		if (player.getItem(key) == null) {
			textbox.addText(getResponse(LandscapeResponse.USE_RESPONSE));
		}

		else if (amount + 1 < maxAmount) {
			player.dropItem(key);
			amount += 1;
			textbox.addText(getResponse(LandscapeResponse.COLLECT_RESPONSE));
		} else if (amount + 1 == maxAmount) {
			player.dropItem(key);
			amount += 1;
			System.out.println(getResponse(LandscapeResponse.COLLECTFINISH_RESPONSE));

			for (Runnable runnable : execute) {
				runnable.run();
			}
		}
	}

}
