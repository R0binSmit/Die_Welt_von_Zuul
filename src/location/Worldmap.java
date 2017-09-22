package location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

import character.Enemy;
import character.NPC;
import item.BasicItem;
import item.Defense;
import item.EnumDefense;
import item.Item;
import item.Weapon;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import main.Usefull;

public class Worldmap {
	private GraphicsContext gc;
	private ArrayList<Room> rooms = new ArrayList<Room>();
	private Room startPoint = null;

	public Worldmap(GraphicsContext gc) {
		this.gc = gc;
	}

	public Room getRandomRoom() {
		Random randomNumber = new Random();
		int roomNumber = randomNumber.nextInt(rooms.size());
		return rooms.get(roomNumber);
	}

	public Room getStartpoint() {
		return startPoint;
	}

	/**
	 * Erzeuge alle Räume und verbinde ihre Ausgänge miteinander.
	 */
	public void createRooms() {
		Room outside, cafeteria, lecturehall, computerRoom, buero, underground, worldsend, eden;// , hoersaal,
																								// cafeteria, labor,
																								// buero,
		// keller,
		// abstellkammer;
		NPC purrCat, goldHamster, raven, snake;
		Enemy karpfen, evilGoldHamster, stoneAngel;
		Item lachs, schuppenpanzer, heavensbane, angelsword, rabbit, goldKey;
		Defense hamsterhaut;
		Collector goldPlate, goldCage, angelComputer, typewriter;
		Teleporter panther;
		Healfountain fountain;
		HashMap<LandscapeResponse, String> landscapeResponse = new HashMap<LandscapeResponse, String>();
		LinkedList<Item> items = new LinkedList<Item>();
		ArrayList<Runnable> execute = new ArrayList<Runnable>();
		ArrayList<Room> destination = new ArrayList<Room>();

		outside = new Room(System.getProperty("line.separator") + "Haupteingang der Universität"
				+ System.getProperty("line.separator")
				+ "Das Gelände ist verlassen, in dieser dunklen Nacht. Die Gerüchte besagen in den Tiefen dieses Ortes würde die"
				+ System.getProperty("line.separator")
				+ "Menschheit antworten finden. Vielleicht sogar Erlösung. Bist du deswegen hier?", this,
				Usefull.linkToImage("/Bilder/Draussen.png"), gc);

		cafeteria = new Room(System.getProperty("line.separator") + "Cafeteria" + System.getProperty("line.separator")
				+ "Einst für den Festschmaus der Menschen bereitet, herrscht nun der König der Karpfen über diesen Ort!",
				this, Usefull.linkToImage("/Bilder/Cafeteria.png"), gc);

		lecturehall = new Room(System.getProperty("line.separator") + "Vorlesungssaal"
				+ System.getProperty("line.separator")
				+ "Einst ein Ort der Lehrsamkeit, herrscht nun ein gigantisches Meerschweinchen an diesem Ort, thronend auf einem Goldberg, der jeden Drachen vor Neid vom Himmel stürzen lassen würde.",
				this, Usefull.linkToImage("/Bilder/Vorlesungssaal.png"), gc);

		computerRoom = new Room(
				System.getProperty("line.separator") + "Rechnerraum" + System.getProperty("line.separator")
						+ "Ein einsamer Rechner summt leise in der Mitte des Raumes.",
				this, Usefull.linkToImage("/Bilder/Rechnerraum.png"), gc);

		buero = new Room(
				System.getProperty("line.separator") + "Büro" + System.getProperty("line.separator")
						+ "Ein majestätischer Rabe residiert nun in diesem Büro und rezitiert Gedichte.",
				this, Usefull.linkToImage("/Bilder/buero.png"), gc);

		underground = new Room(System.getProperty("line.separator") + "Keller" + System.getProperty("line.separator")
				+ "Ein einfacher Keller. Keine gigantischen Tiere, die sich Götter nennen und seltsame Forderungen stellen. Nur ein Keller. Die Tür im Osten trägt die Aufschrift:"
				+ System.getProperty("line.separator") + "'Vorsicht, bissiger Panther!'", this,
				Usefull.linkToImage("/Bilder/keller.png"), gc);

		worldsend = new Room(System.getProperty("line.separator") + "Ende der Welt"
				+ System.getProperty("line.separator")
				+ "Die Wirklichkeit zerfällt an diesem Ort. Ein einsamer Wächter ist auf der Jagd. Eden erwartet dich!",
				this, Usefull.linkToImage("/Bilder/world_end.png"), gc);

		eden = new Room(System.getProperty("line.separator") + "Der Garten Eden" + System.getProperty("line.separator")
				+ "Nach all den Gefahren und Widerständen stehst du nun im Garten Eden und erwartest die letzten Antworten von der Schlange!!",
				this, Usefull.linkToImage("/Bilder/eden.png"), gc);
		rooms.add(outside);
		rooms.add(cafeteria);
		rooms.add(lecturehall);
		rooms.add(computerRoom);
		rooms.add(buero);
		rooms.add(underground);
		rooms.add(worldsend);
		rooms.add(eden);
		startPoint = outside;

		// Ausgänge
		outside.setExit(Usefull.linkToImage("/Bilder/tuer1.png"), gc, new Point2D(-20, 400), new Point2D(700, 400),
				cafeteria);
		outside.setExit(Usefull.linkToImage("/Bilder/tuer1.png"), gc, new Point2D(730, 400), new Point2D(100, 400),
				lecturehall);
		outside.setExit(Usefull.linkToImage("/Bilder/tuer1.png"), gc, new Point2D(350, 750), new Point2D(350, 100),
				computerRoom);
		cafeteria.setExit(Usefull.linkToImage("/Bilder/tuer1.png"), gc, new Point2D(730, 400), new Point2D(100, 400),
				outside);
		lecturehall.setExit(Usefull.linkToImage("/Bilder/tuer1.png"), gc, new Point2D(-20, 400), new Point2D(700, 400),
				outside);
		computerRoom.setExit(Usefull.linkToImage("/Bilder/tuer1.png"), gc, new Point2D(320, -20), new Point2D(400, 700),
				outside);
		computerRoom.setExit(Usefull.linkToImage("/Bilder/tuer1.png"), gc, new Point2D(730, 400), new Point2D(100, 400),
				buero);
		computerRoom.setExit(Usefull.linkToImage("/Bilder/trapdoor.png"), gc, new Point2D(100, 700),
				new Point2D(700, 150), underground);
		buero.setExit(Usefull.linkToImage("/Bilder/tuer1.png"), gc, new Point2D(-20, 400), new Point2D(700, 400),
				computerRoom);
		underground.setExit(Usefull.linkToImage("/Bilder/treppe.png"), gc, new Point2D(650, -20), new Point2D(175, 650),
				computerRoom);
		underground.setExit(Usefull.linkToImage("/Bilder/tuer1.png"), gc, new Point2D(730, 600), new Point2D(175, 600),
				worldsend);
		worldsend.setExit(Usefull.linkToImage("/Bilder/tuer2.png"), gc, new Point2D(720, 0), new Point2D(50, 350),
				eden);

		// Gegenstände
		lachs = new BasicItem("Lachs", "Herrlicher, roter Lachs", 10, Usefull.linkToImage("/Bilder/lachs.png"), 0, 0,
				gc);
		rabbit = new BasicItem("Kanninchen", "Ein weißes Kanninchen", 1,
				Usefull.linkToImage("/Bilder/kanninchen.png"), 650, 600, gc);
		goldKey = new BasicItem("Goldschlüssel", "Ein goldener Schlüssel", 1000,
				Usefull.linkToImage("/Bilder/goldschluessel.png"), 400, 400, gc);
		schuppenpanzer = new Defense("Karpfenschuppenpanzer", "Ein Panzer aus Karpfenschuppen", 200,
				EnumDefense.BREASTPLATE, Usefull.linkToImage("/Bilder/assets/item/armor/normal/breastplate.png"), 0, 0,
				gc, 0.1);
		hamsterhaut = new Defense("Goldhaut", "Ein Panzer aus Goldfell", 200, EnumDefense.BREASTPLATE,
				Usefull.linkToImage("/Bilder/assets/item/armor/normal/breastplate.png"), 0, 0, gc, 0.9);
		heavensbane = new Weapon("Himmelsfluch", "Ein Zweig vom Baum der Erkenntnis", 200,
				Usefull.linkToImage("/Bilder/assets/item/weapon/stick_of_truth.png"), 0, 0, gc, 45);
		angelsword = new Weapon("Engelsschwert", "Schwert eines Engels", 200,
				Usefull.linkToImage("/Bilder/assets/item/weapon/sword.png"), 0, 0, gc, 20);
		lecturehall.addItem(new BasicItem("Buch", "Ein Quell immerwährender Weisheit. Theoretisch.", 100,
				Usefull.linkToImage("/Bilder/assets/item/books/book2.png"), 550, 250, gc));
		computerRoom.addItem(new BasicItem("Buch", "Ein Quell immerwährender Weisheit. Theoretisch.", 100,
				Usefull.linkToImage("/Bilder/assets/item/books/book3.png"), 440, 400, gc));

		// NPC
		purrCat = new NPC("Schnurrkatze", "Ein schnurrende Katze", outside, 700, 100,
				Usefull.linkToImage("/Bilder/schnurrkatze.png"), gc, null);
		purrCat.setText(System.getProperty("line.separator")
				+ "Die Katze schnurrt. Und spricht. Die Menschheit musste sich schnell an derlei Dinge gewöhnen."
				+ System.getProperty("line.separator") + "'Lachs', maunzt sie." + System.getProperty("line.separator")
				+ "'Der Mensch möge mir Lachs aus der Cafeteria im Westen bringen, damit ich ihm ein Geheimnis verrate."
				+ System.getProperty("line.separator") + "Lege er es auf meinen Teller. Nun hinfort!'"
				+ System.getProperty("line.separator"));
		outside.setNPC(purrCat);

		goldHamster = new NPC("Goldhamster", "Ein gigantisches Meerschweinchen", lecturehall, 700, 350,
				Usefull.linkToImage("/Bilder/hamster.png"), gc, null);
		goldHamster.setText(System.getProperty("line.separator")
				+ "Das Meerschweinchen kichert manisch und klimpert mit den Goldmünzen zu seinen Füßen."
				+ System.getProperty("line.separator")
				+ "'Ich bin der Güldengleiche Hamster, der Hamstergott', jauchzt das Meerschweinchen."
				+ System.getProperty("line.separator")
				+ "'Der Engel bewacht den Schlüssel, der meinen Erzfeind, das weiße Kanninchen, gefangen hält. Niemand kann mich stürzen!'"
				+ System.getProperty("line.separator") + "Das Meerscheinchen wühlt mit den Pfoten durch seinen Schatz."
				+ System.getProperty("line.separator"));
		lecturehall.setNPC(goldHamster);

		raven = new NPC("Rabe", "Ein majestätischer Rabe", buero, 420, 350,
				Usefull.linkToImage("/Bilder/rabe.png"), gc, null);
		raven.setText(System.getProperty("line.separator") + "Der Rabe beachtet dich nicht und rezitiert Gedichtzeilen:"
				+ System.getProperty("line.separator")
				+ "'Und es hebt sich meiner Seele Schatten nimmer, nimmer, Nimmermehr!"
				+ System.getProperty("line.separator") + "Wo kommt dieser Welten Fluch nur her?"
				+ System.getProperty("line.separator") + "Der Garten ging dereinst verloren!"
				+ System.getProperty("line.separator") + "Nun wollen alle ihn sich holen!"
				+ System.getProperty("line.separator") + "Bücher, drei, eins für jeden Sohn,"
				+ System.getProperty("line.separator") + "und die Schreibmaschine druckt des Engels Hohn!'"
				+ System.getProperty("line.separator") + "Der Rabe wiederholt diese Zeilen immer wieder.");
		buero.setNPC(raven);

		snake = new NPC("Schlange", "Die Schlange im Garten Eden", eden, 450, 500,
				Usefull.linkToImage("/Bilder/schlange.png"), gc, null);
		snake.setText(System.getProperty("line.separator") + "Die Schlange nickt dir anerkennend zu und zischelt:"
				+ System.getProperty("line.separator") + "'Endlich! Lange habe ich auf diesen Tag gewartet!"
				+ System.getProperty("line.separator")
				+ "Nachdem der Apfel verspeist wurde, ging der Garten verloren und die Menschheit mit ihm."
				+ System.getProperty("line.separator") + "Ich habe alle Welten abgesucht um euch Menschen zu finden!"
				+ System.getProperty("line.separator") + "Das Ende kam über eure Welt, als ich euch fand,"
				+ System.getProperty("line.separator")
				+ "doch nur so konntet ihr den Garten des Ursprungs wiederfinden,"
				+ System.getProperty("line.separator") + "einen Ort, der Heimat verheißt. Die Reise endet hier.'"
				+ System.getProperty("line.separator") + "Die Schlange lächelt.");
		eden.setNPC(snake);
		// Gegner
		items.add(lachs);
		items.add(schuppenpanzer);
		karpfen = new Enemy("Karpfenkönig", "Der König der Karpfen", cafeteria, 200, 700,
				Usefull.linkToImage("/Bilder/karpfenkoenig.png"), 3, 0.05, 70, gc, items);
		cafeteria.setEnemy(karpfen);

		items.clear();
		items.add(heavensbane);
		evilGoldHamster = new Enemy("Hamstergott", "Der Güldene Hamstergott ist ein Meerschweinchen", lecturehall, 700,
				350, Usefull.linkToImage("/Bilder/hamster.png"), 2, 0.1, 80, gc, items);
		evilGoldHamster.equipItem(hamsterhaut);

		items.clear();
		items.add(angelsword);
		items.add(goldKey);
		stoneAngel = new Enemy("Steinengel", "Ein Engel aus Stein", computerRoom, 100, 100,
				Usefull.linkToImage("/Bilder/engel.png"), 4, 0.125, 60, gc, items);

		// Landschaft
		landscapeResponse.put(LandscapeResponse.USE_RESPONSE, System.getProperty("line.separator")
				+ "Die Schnurrkatze blickt hungrig auf den Teller! Dann blickt sie dich an. Fordernd.");
		landscapeResponse.put(LandscapeResponse.COLLECTFINISH_RESPONSE, System.getProperty("line.separator")
				+ "Die Schnurrkatze verspeist den Lachs! Sie maunzt." + System.getProperty("line.separator")
				+ "''Traue niemals einer Katze, Mensch', schnurrt sie." + System.getProperty("line.separator")
				+ "'Besonders nicht dem Panther im Keller. Wie sehr er sich doch nach der Jagd sehnt. Besonders nach weichen, flauschigen Wesen! Jemand hat sogar einst eine Geschichte über ihn geschrieben."
				+ System.getProperty("line.separator") + "Nimm dies, lesen würde dir gut tun, nun hinfort!'");
		execute.add(() -> outside.removeLandscape("Goldteller"));
		execute.add(() -> outside.addItem(new BasicItem("Buch", "Ein Quell immerwährender Weisheit. Theoretisch.", 100,
				Usefull.linkToImage("/Bilder/assets/item/books/books1.png"), 600, 140, gc)));
		execute.add(() -> purrCat.setText(System.getProperty("line.separator")
				+ "'Der Panther im Keller sehnt sich nach der Jagd nach weißen, weichen, flauschigen Wesen. Nun hinfort!', maunzt die Schnurrkatze."));
		goldPlate = new Collector("Goldteller", "Ein goldener Teller",
				Usefull.linkToImage("/Bilder/goldteller.png"), 600, 160, gc, null, "Lachs", 0, 1, null);
		goldPlate.setLandscapeResponse(landscapeResponse);
		goldPlate.setExecute(execute);
		outside.BuildLandscape(goldPlate);

		execute.clear();
		landscapeResponse.clear();

		landscapeResponse.put(LandscapeResponse.USE_RESPONSE, System.getProperty("line.separator")
				+ "In dem Goldkäfig sitzt ein weißes, flauschiges Kanninchen. Es sieht dich flehend an und flüstert:"
				+ System.getProperty("line.separator") + "'Rette mich, Mensch, rette mich!"
				+ System.getProperty("line.separator") + "Der Rabe weiß, wie man den Engel ruft!'");
		landscapeResponse.put(LandscapeResponse.COLLECTFINISH_RESPONSE, System.getProperty("line.separator")
				+ "Du befreist das Kanninchen" + System.getProperty("line.separator")
				+ "''Wer verrät den Güldenen Hamstergott?', brüllt das Meerschweinchen."
				+ System.getProperty("line.separator") + "'Niemand kann mich stürzen! Niemand. Kann. Mich. Stürzen!'"
				+ System.getProperty("line.separator") + "Das Meerschweinchen stürmt auf dich zu!");
		execute.add(() -> lecturehall.removeNPC(goldHamster));
		execute.add(() -> lecturehall.setEnemy(evilGoldHamster));
		execute.add(() -> lecturehall.addItem(rabbit));
		goldCage = new Collector("Goldkäfig", "Ein goldener Käfig",
				Usefull.linkToImage("/Bilder/kaefig_mit_kanninchen.png"), 675, 550, gc, null, "Goldschlüssel", 0,
				1, null);
		execute.add(() -> goldCage.setImage(Usefull.linkToImage("/Bilder/kaefig.png")));
		goldCage.setExecute(execute);
		goldCage.setLandscapeResponse(landscapeResponse);
		lecturehall.BuildLandscape(goldCage);

		execute.clear();
		landscapeResponse.clear();

		landscapeResponse.put(LandscapeResponse.USE_RESPONSE,
				System.getProperty("line.separator") + "Der Rechner summt leise. Auf dem Bildschirm flackern die Worte:"
						+ System.getProperty("line.separator") + "'Insert Password To Enter Eden!'");
		landscapeResponse.put(LandscapeResponse.COLLECTFINISH_RESPONSE,
				System.getProperty("line.separator") + "Der Computer fiept:" + System.getProperty("line.separator")
						+ "'The PANTHER Shall Not Be TEMPTED! EDEN Must BE Protected!"
						+ System.getProperty("line.separator") + "Launch ANGEL Upon HUMAN! SEAL The RABBIT!'"
						+ System.getProperty("line.separator")
						+ "Aus dem Nichts manifestiert sich ein steinerner Engel!");
		execute.add(() -> computerRoom.setEnemy(stoneAngel));
		angelComputer = new Collector("Computer", "Ein Computer", Usefull.linkToImage("/Bilder/computer.png"),
				390, 375, gc, null, "Passwort", 0, 1, null);
		angelComputer.setExecute(execute);
		angelComputer.setLandscapeResponse(landscapeResponse);
		computerRoom.BuildLandscape(angelComputer);

		execute.clear();
		landscapeResponse.clear();

		landscapeResponse.put(LandscapeResponse.USE_RESPONSE,
				System.getProperty("line.separator") + "Die Schreibmaschine klickt leise.");
		landscapeResponse.put(LandscapeResponse.COLLECT_RESPONSE, System.getProperty("line.separator")
				+ "Die Schreibmaschine verschlingt das Buch und gibt ein hungriges Klicken von sich!");
		landscapeResponse.put(LandscapeResponse.COLLECTFINISH_RESPONSE,
				System.getProperty("line.separator") + "Die Schreibmaschine klickt froh. Sie schreibt die Worte:"
						+ System.getProperty("line.separator") + "'Generiere Passwort für den Zugang zu Eden!"
						+ System.getProperty("line.separator") + "Benutzung auf eigene Gefahr!'");
		execute.add(() -> buero.addItem(new BasicItem("Passwort", "Gewährt dieses Wort Zugang zu Eden?.", 20,
				Usefull.linkToImage("/Bilder/assets/item/books/book4.png"), 700, 400, gc)));
		typewriter = new Collector("Schreibmaschine", "Eine Schreibmaschine",
				Usefull.linkToImage("/Bilder/schreibmaschine.png"), 750, 375, gc, null, "Buch", 0, 3, null);
		typewriter.setExecute(execute);
		typewriter.setLandscapeResponse(landscapeResponse);
		buero.BuildLandscape(typewriter);

		execute.clear();
		landscapeResponse.clear();

		landscapeResponse.put(LandscapeResponse.USE_RESPONSE, "Du streichelst den Panther!");
		landscapeResponse.put(LandscapeResponse.ENTER_RESPONSE, System.getProperty("line.separator")
				+ "Ein Panther springt aus der Dunkelheit, packt dich im Nacken und verschleppt dich in einen anderen Raum!");
		landscapeResponse.put(LandscapeResponse.REMOVE_RESPONSE, System.getProperty("line.separator")
				+ "Ein Panther springt aus der Dunkelheit und faucht. Dein weißes Kanninchen hoppelt davon und der Panther jagt ihm nach!");
		destination.add(outside);
		destination.add(cafeteria);
		destination.add(lecturehall);
		destination.add(computerRoom);
		destination.add(buero);
		destination.add(underground);
		panther = new Teleporter("Panther", "Eine schwarze Raubkatze",
				Usefull.linkToImage("/Bilder/schnurrkatze.png"), 0, 0, gc, null, "Kanninchen", destination);
		panther.setLandscapeResponse(landscapeResponse);
		worldsend.BuildLandscape(panther);

		landscapeResponse.clear();

		landscapeResponse.put(LandscapeResponse.USE_RESPONSE, System.getProperty("line.separator")
				+ "Du nimmst einen Schluck aus dem Brunnen und fühlst dich erholt!");
		fountain = new Healfountain("Heilbrunnen",
				"Womöglich speist sich dieser Brunnen aus dem selben Quell wie der legendäre Jungbrunnen!",
				Usefull.linkToImage("/Bilder/brunnen.png"), 400, 400, gc, null);
		fountain.setLandscapeResponse(landscapeResponse);
		outside.BuildLandscape(fountain);
	}
}
