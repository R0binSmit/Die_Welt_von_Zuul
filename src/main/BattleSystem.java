package main;

import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
import character.Character;
import location.Room;

public class BattleSystem {
	private final static String WELCOMETEXT = "Sie sind in ein Kampf verwickelt worden!";
	LinkedList<character.Character> spielerGroup = new LinkedList<character.Character>();
	LinkedList<character.Enemy> gegnerGroup = new LinkedList<character.Enemy>();
	Scanner scanner = new Scanner(System.in);

	public BattleSystem(LinkedList<character.Character> spielerGroup, LinkedList<character.Enemy> gegnerGroup) {
		this.spielerGroup = spielerGroup;
		this.gegnerGroup = gegnerGroup;
	}

	public boolean checkKampfStart(Room raum) {
		if (raum.getGegnerList().isEmpty() == false) {
			return true;
		}
		return false;
	}

	public void startKampf() {
		System.out.println(WELCOMETEXT);
		System.out.println(getGegnerBeschreibung());

		do {
			for(character.Character sp : spielerGroup){
				character.Enemy gegner = null;
				
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
					// TODO fix no more zustand.
					//sp.leichtVerletzen(gegnerGroup.get(gegnerIndex));
					break;
				case 2:
					//TODO fix no more zustand.
					//sp.schwerVerletzen(gegnerGroup.get(gegnerIndex));
					break;
				case 3:
					//TODO fix no more zustand.
					//sp.kleineHeilung(gegnerGroup.get(gegnerIndex));
					break;
				case 4:
					//TODO fix no more zustand. 
					//sp.grosseHeilung(gegnerGroup.get(gegnerIndex));
					break;
				default:
					//TODO fix no more zustand.
					//sp.leichtVerletzen(gegnerGroup.get(gegnerIndex));
				}
			}
			
			for(character.Character gg : gegnerGroup){
				character.Character choosSpieler = getRandomCharakterFromSpieler();				
				System.out.println(gg.getName() + " greift " + choosSpieler.getName());
				gegnerRandomAngriff(gg, choosSpieler);
			}
		} while (checkAllStatusDead(spielerGroup) == true || checkAllStatusDeadGegner(gegnerGroup) == true);
	}

	private String getGegnerBeschreibung() {
		StringBuilder sb = new StringBuilder();
		sb.append("Sie kämpfen gegen: ");
		
		//TODO fix no more zustand.
		/*for (character.Character gg : gegnerGroup) {
			sb.append(gg.getName() + " [" + gg.getZustand().getClass().getName() +"] ");
		}*/
		return sb.toString();
	}

	private boolean checkAllStatusDead(LinkedList<character.Character> character) {
		for (character.Character ch : character) {
			//TODO fix no more zustand.
			/*if (ch.getZustand() != Tod.getInstance()) {
				return false;
			}*/
		}
		return true;
	}
	
	private boolean checkAllStatusDeadGegner(LinkedList<character.Enemy> gegner) {
		for (character.Enemy ch : gegner) {
			//TODO fox no more zustand.
			/*if (ch.getZustand() != Tod.getInstance()) {
				return false;
			}*/
		}
		return true;
	}
	
	private character.Character getRandomCharakterFromSpieler(){
		Random randomObj = new Random();
		int spielerIndex = randomObj.nextInt(spielerGroup.size());
		return spielerGroup.get(spielerIndex);
	}
	
	private void gegnerRandomAngriff(character.Character gg, character.Character sp){
		Random randomObj = new Random();
		int randomNumber = randomObj.nextInt(101);
		//TODO fix no more zustand.
		/*f(randomNumber >= 75)
			gg.leichtVerletzen(sp);
		else
			gg.schwerVerletzen(sp);*/
	}
	
	private String getGegnerGroupToString()
	{
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i <= gegnerGroup.size() -1; i++){
			sb.append(i + ". " + gegnerGroup.get(i).getName());
		}
		return sb.toString();
	}
}
