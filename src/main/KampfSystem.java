package main;

import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

import character.Character;
import ort.Raum;
import zustand.Tod;

public class KampfSystem {
	private final static String WELCOMETEXT = "Sie sind in ein Kampf verwickelt worden!";
	LinkedList<Character> spielerGroup = new LinkedList<Character>();
	LinkedList<Character> gegnerGroup = new LinkedList<Character>();
	Scanner scanner = new Scanner(System.in);

	public KampfSystem() {

	}

	public boolean checkKampfStart(Raum raum) {
		if (raum.getGegnerList().size() > 0) {
			return true;
		}
		return false;
	}

	public void startKampf() {
		System.out.println(WELCOMETEXT);
		System.out.println(getGegnerBeschreibung());

		do {
			for(Character sp : spielerGroup){
				Character gegner = null;
				
				System.out.println(sp.getName() + " ist drann. Was möchtest Sie ausführen?");
				System.out.println("1. LeichterAngriff");
				System.out.println("2. SchwererAngriff");
				System.out.println("3. KleineHeilung");
				System.out.println("4. GrosseHeilung");
				int angriffNum = scanner.nextInt();
				
				System.out.println("Welchen Gegner möchten Sie angreifen?");
				System.out.println(getGegnerGroupToString());
				int gegnerIndex = scanner.nextInt();
				
				switch(angriffNum){
				case 1:
					sp.leichtVerletzen(gegnerGroup.get(gegnerIndex));
					break;
				case 2:
					sp.schwerVerletzen(gegnerGroup.get(gegnerIndex));
					break;
				case 3:
					sp.kleineHeilung(gegnerGroup.get(gegnerIndex));
					break;
				case 4:
					sp.grosseHeilung(gegnerGroup.get(gegnerIndex));
					break;
				default:
					sp.leichtVerletzen(gegnerGroup.get(gegnerIndex));
				}
			}
			
			for(Character gg : gegnerGroup){
				Character choosSpieler = getRandomCharakterFromSpieler();				
				System.out.println(gg.getName() + " greift " + choosSpieler.getName());
				gegnerRandomAngriff(gg, choosSpieler);
			}
			
			break;
		} while (checkAllStatusDead(spielerGroup) == true || checkAllStatusDead(gegnerGroup) == true);
	}

	private String getGegnerBeschreibung() {
		StringBuilder sb = new StringBuilder();
		sb.append("Sie kämpfen gegen: ");
		for (Character gg : gegnerGroup) {
			sb.append(gg.getName() + " ");
		}
		return sb.toString();
	}

	private boolean checkAllStatusDead(LinkedList<Character> character) {
		for (Character ch : character) {
			if (ch.getZustand() != Tod.getInstance()) {
				return false;
			}
		}
		return true;
	}
	
	private Character getRandomCharakterFromSpieler(){
		Random randomObj = new Random();
		int spielerIndex = randomObj.nextInt(spielerGroup.size() +1);
		return spielerGroup.get(spielerIndex);
	}
	
	private void gegnerRandomAngriff(Character gg, Character sp){
		Random randomObj = new Random();
		int randomNumber = randomObj.nextInt(101);
		
		if(randomNumber >= 75)
			gg.leichtVerletzen(sp);
		else
			gg.schwerVerletzen(sp);
	}
	
	private String getGegnerGroupToString()
	{
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i <= gegnerGroup.size(); i++){
			sb.append(i + ". " + gegnerGroup.get(i).getName());
		}
		return sb.toString();
	}
}
