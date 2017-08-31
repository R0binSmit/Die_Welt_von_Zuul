package main;

import java.util.LinkedList;

import character.Character;
import character.Gegner;
import character.Spieler;
import ort.Raum;
import zustand.Tod;

public class KampfSystem {
	private final static String WELCOMETEXT = "Sie sind in ein Kampf verwickelt worden!"; 
	LinkedList<Character> spieler = new LinkedList<Character>();
	LinkedList<Character> gegner = new LinkedList<Character>();
	
	public KampfSystem() {
		
	}
	
	public boolean checkKampfStart(Raum raum) {
		if(raum.getGegnerList().size() > 0)
			return true;
		return false;
	}
	
	public void startKampf() {
		System.out.println(WELCOMETEXT);
		System.out.println(getGegnerBeschreibung());
		
		do {
			
		}while(checkAllStatusDead(spieler) == true || checkAllStatusDead(gegner) == true);
	}
	
	private String getGegnerBeschreibung() {
		StringBuilder sb = new StringBuilder();
		sb.append("Sie kämpfen gegen: ");
		for(Character gg : gegner) {
			sb.append(gg.getName() + " ");
		}
		return sb.toString();
	}
	
	private boolean checkAllStatusDead(LinkedList<Character> character) {
		for(Character ch : character) {
			if(ch.getZustand() != Tod.getInstance()) {
				return false;
			}
		}
		return true;
	}
}
