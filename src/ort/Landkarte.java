package ort;

import java.util.ArrayList;
import java.util.Random;

import gegenstand.Crafting;
import gegenstand.Gegenstand;
import gegenstand.Nahrung;
import gegenstand.Waffe;

public class Landkarte {
	private Raum startpoint = null;
	private ArrayList<Raum> raeume = new ArrayList<Raum>();

	public Landkarte() {

	}

	/**
	 * Erzeuge alle R�ume und verbinde ihre Ausg�nge miteinander.
	 */
	public void raeumeAnlegen() {
		Raum draussen, hoersaal, cafeteria, labor, buero, keller, abstellkammer;
		Gegenstand regenschirm, tasse, messer, erlenmeyerkolben, ventilator, peitsche, muffin;
		Landscape panther;

		// die R�ume erzeugen
		draussen = new Raum("vor dem Haupteingang der Universit�t", this);
		raeume.add(draussen);
		startpoint = draussen;
		hoersaal = new Raum("in einem Vorlesungssaal", this);
		raeume.add(hoersaal);
		cafeteria = new Raum("in der Cafeteria der Uni", this);
		raeume.add(cafeteria);
		labor = new Raum("in einem Rechnerraum", this);
		raeume.add(labor);
		buero = new Raum("im Verwaltungsb�ro der Informatik", this);
		raeume.add(buero);
		keller = new Raum("im Keller des Rechenzentrums", this);
		raeume.add(keller);
		abstellkammer = new Raum("Die Abstellkammer", this);
		raeume.add(abstellkammer);

		regenschirm = new Waffe("Schirmy", "Ein pinker Regenschirm", 5);
		tasse = new Crafting("Tasse", "Auf Ihr Steht: '#1 Dad'", 2);
		messer = new Waffe("Messer", "Es hat 'Made with Kinderarbeit' aufgedruckt", 1);
		erlenmeyerkolben = new Crafting("Erlenmeyerkolben", "Die Fl�ssigkeit darin riecht Alkoholisch", 3);
		ventilator = new Crafting("Ventilator", "F�r die schwitzige Jahreszeit", 30);
		peitsche = new Waffe("Peitsche", "Sie hat 'BDSM' eingraviert", 10);
		muffin = new Nahrung("Muffin", "Er glitzert :O", 3);
		muffin.setEssbar(true);

		panther = new Teleporter("Panther", "Eine schwarze Raubkatze");

		// die Ausg�nge initialisieren
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