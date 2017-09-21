package main;

import java.util.HashMap;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ZuulUI extends Application {
	private Canvas can;
	private Game game;
	private GraphicsContext gc;
	private HashMap<KeyCode, Boolean> keys = new HashMap<KeyCode, Boolean>();
	private Timeline tl;

	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Initialisierung der Schleife die 60 mal pro Sekunde unsere Updatemethode
	 * aufruft
	 */
	@Override
	public void init() throws Exception {
		tl = new Timeline(new KeyFrame(Duration.millis(1000 / 60), e -> {
			game.update(keys);
		}));
		tl.setCycleCount(Animation.INDEFINITE);
	}

	/**
	 * Hier werden die Hauptelemente von JavaFX initialisiert. Außerdem werden hier
	 * die Key und Mouse events abgefangen
	 */
	@Override
	public void start(Stage stage) throws Exception {
		Pane root = new Pane();
		Scene scene = new Scene(root, 800, 800);

		//Titel festlegen
		stage.setTitle("Die Welt von Zuul");

		//Canvas erstellen und zum Pane hinzufügen
		can = new Canvas(scene.getWidth(), scene.getHeight());
		gc = can.getGraphicsContext2D();
		root.getChildren().add(can);

		//Eventhandler um Mauseingaben abzufanden
		scene.setOnMousePressed(e -> {
			MouseButton mb = e.getButton();
			
			if (mb == MouseButton.PRIMARY) {
				keys.put(KeyCode.F20, true);
				game.processCommand(KeyCode.F20);
			}
			
			if (mb == MouseButton.SECONDARY) {
				keys.put(KeyCode.F21, true);
				game.processCommand(KeyCode.F21);
			}
		});

		//Eventhandler um zu Registrieren wenn eine Maustaste losgelassen wird
		scene.setOnMouseReleased(e -> {
			MouseButton mb = e.getButton();
			
			if (mb == MouseButton.PRIMARY) {
				keys.remove(KeyCode.F20);
			}
			
			if (mb == MouseButton.SECONDARY) {
				keys.remove(KeyCode.F21);
			}
		});

		//Evenhandler um Tastendrücke abzufangen
		scene.setOnKeyPressed(e -> {
			keys.put(e.getCode(), true);
			game.processCommand(e.getCode());
		});

		//Eventhandler um zu Registrieren wenn eine Keyboardtaste losgelassen wird
		scene.setOnKeyReleased(e -> {
			keys.remove(e.getCode());
		});

		//Stagegröße unveränderbar machen
		stage.setResizable(false);
		
		stage.setScene(scene);
		stage.show();

		//wichtige Klassen initialisieren
		game = new Game(gc);
		TextBox textbox = TextBox.newTextBox();
		textbox.setGc(gc);
		
		//Timeline starten
		tl.play();
	}
}