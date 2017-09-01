package ort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import gegenstand.Crafting;
import gegenstand.Gegenstand;

public class Landkarte {
	private Raum startpoint = null;
	private ArrayList<Raum> raeume = new ArrayList<Raum>();

	public Landkarte() {

	}

	/**
	 * Erzeuge alle Räume und verbinde ihre Ausgänge miteinander.
	 */
	public void raeumeAnlegen() {
		Raum draussen, hoersaal, cafeteria, labor, buero, keller, abstellkammer;
		Gegenstand kanninchen;
		Landscape panther;
		ArrayList<Raum> destination = new ArrayList<Raum>();
		HashMap<LandscapeResponse, String> landscapeResponse = new HashMap<LandscapeResponse, String>();

		// die Räume erzeugen
		draussen = new Raum("vor dem Haupteingang der Universität", this);
		raeume.add(draussen);
		startpoint = draussen;
		hoersaal = new Raum("in einem Vorlesungssaal", this);
		raeume.add(hoersaal);
		cafeteria = new Raum("in der Cafeteria der Uni", this);
		raeume.add(cafeteria);
		labor = new Raum("in einem Rechnerraum", this);
		raeume.add(labor);
		buero = new Raum("im Verwaltungsbüro der Informatik", this);
		raeume.add(buero);
		keller = new Raum("im Keller des Rechenzentrums", this);
		raeume.add(keller);
		abstellkammer = new Raum("Die Abstellkammer", this);
		raeume.add(abstellkammer);

		// die Ausgänge initialisieren
		draussen.setzeAusgang("east", hoersaal);
		draussen.setzeAusgang("south", labor);
		draussen.setzeAusgang("west", cafeteria);
		hoersaal.setzeAusgang("west", draussen);
		cafeteria.setzeAusgang("east", draussen);
		labor.setzeAusgang("north", draussen);
		labor.setzeAusgang("east", buero);
		buero.setzeAusgang("west", labor);
		buero.setzeAusgang("down", keller);
		keller.setzeAusgang("up", buero);
		keller.setzeAusgang("east", abstellkammer);
		abstellkammer.setzeAusgang("west", keller);

		// Gegenstände
		kanninchen = new Crafting("Kanninchen", "Ein weißes Kanninchen", 5, 1000);

		// Gegenstände ablegen
		cafeteria.gegenstandAblegen(kanninchen);

		// Landschaft initialisieren
		landscapeResponse.put(LandscapeResponse.USE_RESPONSE, "Du streichelst den Panther!");
		landscapeResponse.put(LandscapeResponse.ENTER_RESPONSE,
				"Ein Panther springt aus der Dunkelheit, packt dich im Nacken und verschleppt dich in einen anderen Raum!");
		landscapeResponse.put(LandscapeResponse.REMOVE_RESPONSE,
				"Ein Panther springt aus der Dunkelheit und faucht. Dein weißes Kanninchen hoppelt davon und der Panther jagt ihm nach!");
		destination.add(draussen);
		destination.add(cafeteria);
		destination.add(hoersaal);
		destination.add(labor);
		destination.add(buero);
		destination.add(keller);
		panther = new Teleporter("Panther", "Eine schwarze Raubkatze", landscapeResponse, "Kanninchen", destination);

		// Landschaft setzen
		abstellkammer.landschaftBauen(panther);

		/*
		 * Raum draussen, hoersaal, cafeteria, labor, buero, keller, abstellkammer;
		 * Gegenstand regenschirm, tasse, messer, erlenmeyerkolben, ventilator,
		 * peitsche, muffin, kanninchen; Landscape panther; HashMap<LandscapeResponse,
		 * String> landscapeResponse = new HashMap<LandscapeResponse, String>();
		 * 
		 * // die Räume erzeugen draussen = new
		 * Raum("vor dem Haupteingang der Universität", this); raeume.add(draussen);
		 * startpoint = draussen; hoersaal = new Raum("in einem Vorlesungssaal", this);
		 * raeume.add(hoersaal); cafeteria = new Raum("in der Cafeteria der Uni", this);
		 * raeume.add(cafeteria); labor = new Raum("in einem Rechnerraum", this);
		 * raeume.add(labor); buero = new Raum("im Verwaltungsbüro der Informatik",
		 * this); raeume.add(buero); keller = new Raum("im Keller des Rechenzentrums",
		 * this); raeume.add(keller); abstellkammer = new Raum("Die Abstellkammer",
		 * this); raeume.add(abstellkammer);
		 * 
		 * regenschirm = new Waffe("Schirmy", "Ein pinker Regenschirm", 5, 10); tasse =
		 * new Crafting("Tasse", "Auf Ihr Steht: '#1 Dad'", 2); messer = new
		 * Waffe("Messer", "Es hat 'Made with Kinderarbeit' aufgedruckt", 1, 50);
		 * erlenmeyerkolben = new Crafting("Erlenmeyerkolben",
		 * "Die Flüssigkeit darin riecht Alkoholisch", 3); ventilator = new
		 * Crafting("Ventilator", "Für die schwitzige Jahreszeit", 30); peitsche = new
		 * Waffe("Peitsche", "Sie hat 'BDSM' eingraviert", 10, 20); muffin = new
		 * Nahrung("Muffin", "Er glitzert :O", 3); muffin.setEssbar(true); kanninchen =
		 * new Crafting("Kanninchen", "Ein weißes Kanninchen", 5);
		 * 
		 * landscapeResponse.put(LandscapeResponse.USE_RESPONSE,
		 * "Du streichelst den Panther!");
		 * landscapeResponse.put(LandscapeResponse.ENTER_RESPONSE,
		 * "Ein Panther springt aus der Dunkelheit, packt dich im Nacken und verschleppt dich in einen anderen Raum!"
		 * ); landscapeResponse.put(LandscapeResponse.REMOVE_RESPONSE,
		 * "Ein Panther springt aus der Dunkelheit und faucht. Dein weißes Kanninchen hoppelt davon und der Panther jagt ihm nach!"
		 * ); panther = new TeleporterTrap("Panther", "Eine schwarze Raubkatze",
		 * landscapeResponse, "Kanninchen");
		 * 
		 * // die Ausgänge initialisieren draussen.setzeAusgang("east", hoersaal);
		 * draussen.setzeAusgang("south", labor); draussen.setzeAusgang("west",
		 * cafeteria); draussen.gegenstandAblegen(regenschirm);
		 * 
		 * hoersaal.setzeAusgang("west", draussen); hoersaal.gegenstandAblegen(tasse);
		 * 
		 * cafeteria.setzeAusgang("east", draussen);
		 * cafeteria.gegenstandAblegen(messer);
		 * 
		 * labor.setzeAusgang("north", draussen); labor.setzeAusgang("east", buero);
		 * labor.setzeAusgang("down", keller);
		 * labor.gegenstandAblegen(erlenmeyerkolben); labor.gegenstandAblegen(muffin);
		 * labor.gegenstandAblegen(kanninchen);
		 * 
		 * buero.setzeAusgang("west", labor); buero.gegenstandAblegen(ventilator);
		 * 
		 * keller.setzeAusgang("up", labor); keller.setzeAusgang("east", abstellkammer);
		 * keller.gegenstandAblegen(peitsche); keller.setzeGegner(new Gegner("Blubb"
		 * ,10, keller, null));
		 * 
		 * abstellkammer.setzeAusgang("west", keller);
		 * abstellkammer.landschaftBauen(panther);
		 */
	}

	public Raum getStartpoint() {
		return startpoint;
	}

	public Raum getRandomRoom() {
		Random randomNumber = new Random();
		int roomNumber = randomNumber.nextInt(raeume.size());

		return raeume.get(roomNumber);
	}
}
