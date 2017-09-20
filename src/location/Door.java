package location;

import character.Player;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.IShowable;

public class Door implements IShowable{
	private Image image;
	private GraphicsContext gc;
	private Point2D position, nextPos;
	private Room nextRoom;
	
	public Door(Image image, GraphicsContext gc, Point2D position, Point2D nextPos, Room nextRoom) {
		super();
		this.image = image;
		this.gc = gc;
		this.position = position;
		this.nextPos = nextPos;
		this.nextRoom = nextRoom;
	}

	@Override
	public void show() {
		gc.drawImage(image, position.getX(), position.getY());
	}
	
	public void changeRoom(Player player) {
		player.setRoom(nextRoom);
		player.setPosition(nextPos);
		nextRoom.onEnterRoomEvent(player);
	}
	
	public double getX() {
		return position.getX();
	}
	
	public double getY() {
		return position.getY();
	}
	
	public double getWidth() {
		return image.getWidth();
	}
	
	public double getHeight() {
		return image.getHeight();
	}
}