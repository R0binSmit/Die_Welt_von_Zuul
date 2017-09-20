package main;

import java.util.LinkedList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class TextBox {
	private GraphicsContext gc;
	LinkedList<String> texte = new LinkedList<String>();

	public TextBox(GraphicsContext gc) {
		this.gc = gc;
	}

	public void addText() {
		texte.add("");
	}

	public void addText(String text) {
		addText(text, 50);
	}

	public void addText(String text, int maxSLength) {
		String[] texte = text.split(System.getProperty("line.separator"));
		for (int i = 1; i < texte.length; i++) {
			while (texte[i].length() > maxSLength) {
				int cut = texte[i].lastIndexOf(' ', maxSLength);
				texte[i] = texte[i].trim();
				String t = texte[i].substring(0, cut);
				texte[i] = texte[i].substring(cut);
				this.texte.add(t);
			}
			texte[i] = texte[i].trim();
			this.texte.add(texte[i]);
		}
	}

	public void refresh() {
		Paint p = gc.getFill();
		gc.setFill(Color.rgb(230, 230, 255));
		gc.fillRect(0, 0, 300, 210);
		gc.setFill(p);
		
		int y = 200;
		for (int i = texte.size() - 1; i >= 0; i--) {
			if (y < 15) {
				texte.remove(i);
				continue;
			}

			gc.fillText(texte.get(i), 10, y);
			y -= 15;
		}
	}
}