package main;

import java.util.LinkedList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * Stellt die Textbox die in der oberen linken Ecke gezeigt wird dar.
 */
public class TextBox {
	private static TextBox textBox = new TextBox();
	private LinkedList<String> texts = new LinkedList<String>();
	private GraphicsContext gc;

	/**
	 * Singleton pattern damit die Textbox leicht durch verschiedene Klassen
	 * angesprochen werden kann.
	 * 
	 * @return die textbox
	 */
	public static TextBox newTextBox() {
		return textBox;
	}

	/**
	 * Privater Constructor für das Singleton Pattern
	 */
	private TextBox() {
	}

	/**
	 * addText um schnell eine Leerzeile einzufügen
	 */
	public void addText() {
		texts.add("");
	}

	/**
	 * addText um einen normalen Text hinzuzufügen
	 * 
	 * @param text
	 *            Der Text der in der Textbox stehen soll
	 */
	public void addText(String text) {
		addText(text, 50);
	}

	/**
	 * addText um einen Text mit bestimmter Maximalzeilenlänge auszugeben
	 * 
	 * @param text
	 *            Der Text der ausgegeben werden soll
	 * @param maxSLength
	 *            Maximale Zeichen pro Zeile
	 */
	public void addText(String text, int maxSLength) {
		//Text in einzelne texte aufteilen falls so schon zeilenumbrüche drin sin
		String[] texts = text.split(System.getProperty("line.separator"));
		for (int i = 0; i < texts.length; i++) {
			//Während die Textlänge die Maximalen zeichen überschreitet wird er in Teile passender größe zerteilt
			while (texts[i].length() > maxSLength) {
				
				//Herausfinden wo das letzte Wort das noch innerhalb der Maximalzeichen ist endet
				int cut = texts[i].lastIndexOf(' ', maxSLength);
				
				//Leerzeichen am anfang und ende entfernen
				texts[i] = texts[i].trim();
				
				//Text vom anfang bis zum letzten Wort innerhalb der Maximalzeichen hinzufügen
				this.texts.add(texts[i].substring(0, cut));
				
				//Mit dem Text der noch nicht hinzugefügt wurde Weiterarbeiten
				texts[i] = texts[i].substring(cut);
			}
			//Leerzeichen am anfang und ende entfernen
			texts[i] = texts[i].trim();
			
			//Restlichen Text hinzufügen
			this.texts.add(texts[i]);
		}
	}

	//Textbox anzeigen
	public void show() {
		//Hintergrund Recheck einfügen
		Paint p = gc.getFill();
		gc.setFill(Color.rgb(230, 230, 255, 0.5));
		gc.fillRect(0, 0, 300, 210);
		gc.setFill(p);

		int y = 200;
		for (int i = texts.size() - 1; i >= 0; i--) {
			//Wenn die Texte nicht mehr angezeigt werden können entferne sie
			if (y < 15) {
				texts.remove(i);
				continue;
			}

			gc.fillText(texts.get(i), 10, y);
			y -= 15;
		}
	}
	
	public GraphicsContext getGc() {
		return gc;
	}

	public void setGc(GraphicsContext gc) {
		this.gc = gc;
	}
}