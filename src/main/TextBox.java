package main;

import java.util.LinkedList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class TextBox {
	private static TextBox textBox = new TextBox();
	private LinkedList<String> texts = new LinkedList<String>();
	private GraphicsContext gc;

	public static TextBox newTextBox() {
		return textBox;
	}

	private TextBox() {
	}

	public void addText() {
		texts.add("");
	}

	public void addText(String text) {
		addText(text, 50);
	}

	public void addText(String text, int maxSLength) {
		String[] texts = text.split(System.getProperty("line.separator"));
		for (int i = 0; i < texts.length; i++) {
			while (texts[i].length() > maxSLength) {
				int cut = texts[i].lastIndexOf(' ', maxSLength);
				texts[i] = texts[i].trim();
				String t = texts[i].substring(0, cut);
				texts[i] = texts[i].substring(cut);
				this.texts.add(t);
			}
			texts[i] = texts[i].trim();
			this.texts.add(texts[i]);
		}
	}

	public GraphicsContext getGc() {
		return gc;
	}

	public void show() {
		Paint p = gc.getFill();
		gc.setFill(Color.rgb(230, 230, 255, 0.5));
		gc.fillRect(0, 0, 300, 210);
		gc.setFill(p);

		int y = 200;
		for (int i = texts.size() - 1; i >= 0; i--) {
			if (y < 15) {
				texts.remove(i);
				continue;
			}

			gc.fillText(texts.get(i), 10, y);
			y -= 15;
		}
	}

	public void setGc(GraphicsContext gc) {
		this.gc = gc;
	}
}