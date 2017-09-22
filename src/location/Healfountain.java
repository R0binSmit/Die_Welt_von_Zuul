package location;

import java.util.HashMap;

import character.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Healfountain extends Landscape {

	public Healfountain(String name, String description, Image image, int x, int y, GraphicsContext gc,
			HashMap<LandscapeResponse, String> landscapeResponse) {
		super(name, description, image, x, y, gc, landscapeResponse);
	}

	@Override
	public void onEnterRoom(Player player) {
		
	}

	@Override
	public void onUse(Player player) {
		textbox.addText(getResponse(LandscapeResponse.USE_RESPONSE));
		player.setHP(150);
	}
}