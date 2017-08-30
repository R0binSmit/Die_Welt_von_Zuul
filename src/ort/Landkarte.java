package ort;

import gegenstand.Gegenstand;

import java.util.ArrayList;
import java.util.Random;

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
		Gegenstand regenschirm, tasse, messer, erlenmeyerkolben, ventilator, peitsche, muffin;
		Landscape panther;

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

		regenschirm = new Gegenstand("Schirmy", "Ein pinker Regenschirm", 5);
		tasse = new Gegenstand("Tasse", "Auf Ihr Steht: '#1 Dad'", 2);
		messer = new Gegenstand("Messer", "Es hat 'Made with Kinderarbeit' aufgedruckt", 1);
		erlenmeyerkolben = new Gegenstand("Erlenmeyerkolben", "Die Flüssigkeit darin riecht Alkoholisch", 3);
		ventilator = new Gegenstand("Ventilator", "Für die schwitzige Jahreszeit", 30);
		peitsche = new Gegenstand("Peitsche", "Sie hat 'BDSM' eingraviert", 10);
		muffin = new Gegenstand("Muffin", "Er glitzert :O", 3);
		muffin.setEssbar(true);

		panther = new Teleporter("Panther", "Eine schwarze Raubkatze");

		// die Ausgänge initialisieren
		draussen.setzeAusgang("east", hoersaal);
		draussen.setzeAusgang("south", labor);
		draussen.setzeAusgang("west", cafeteria);
		draussen.gegenstandAblegen(regenschirm);

		hoersaal.setzeAusgang("west", draussen);
		hoersaal.gegenstandAblegen(tasse);

		cafeteria.setzeAusgang("east", draussen);
		cafeteria.gegenstandAblegen(messer);

		labor.setzeAusgang("north", draussen);
		labor.setzeAusgang("east", buero);
		labor.setzeAusgang("down", keller);
		labor.gegenstandAblegen(erlenmeyerkolben);
		labor.gegenstandAblegen(muffin);

		buero.setzeAusgang("west", labor);
		buero.gegenstandAblegen(ventilator);

		keller.setzeAusgang("up", labor);
		keller.setzeAusgang("east", abstellkammer);
		keller.gegenstandAblegen(peitsche);

		abstellkammer.setzeAusgang("west", keller);
		abstellkammer.landschaftBauen(panther);
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
