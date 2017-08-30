package befehlsVerarbeitung;
/*
 * Diese Klasse hält eine Aufzählung aller Befehlswörter, die dem
 * Spiel bekannt sind. Mit ihrer Hilfe werden eingetippte Befehle
 * erkannt.
 *
 * @author  Michael Kölling und David J. Barnes
 * @version 2008.03.30
 */

public class Befehlswoerter {
	// ein konstantes Array mit den gültigen Befehlswörtern
	private static final String gueltigeBefehle[] = { "go", "quit", "help",
			"look", "take", "drop", "eat", "inventory", "heal", "hurt", "stab" };

	/**
	 * Konstruktor - initialisiere die Befehlswörter.
	 */
	public Befehlswoerter() {
		// nichts zu tun momentan...
	}
	
	public String getBefehle() {
		StringBuilder sb = new StringBuilder("");
		for (int i = 0; i < gueltigeBefehle.length; i++) {
			sb.append(gueltigeBefehle[i]);
			sb.append(" ");
		}
		return sb.toString();
	}

	/**
	 * Prüfe, ob eine gegebene Zeichenkette ein gültiger Befehl ist.
	 * 
	 * @return 'true', wenn die gegebene Zeichenkette ein gültiger Befehl ist,
	 *         'false' sonst.
	 */
	public boolean istBefehl(String eingabe) {
		for (int i = 0; i < gueltigeBefehle.length; i++) {
			if (gueltigeBefehle[i].equalsIgnoreCase(eingabe))
				return true;
		}
		// Wenn wir hierher gelangen, wurde die Eingabe nicht
		// in den Befehlswörter gefunden.
		return false;
	}
}