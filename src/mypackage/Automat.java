package mypackage;

import java.util.Stack;

/*
 * Der PDA akzeptiert die Sprache L(G)=a^n b^n c^(m+n) d
 * Die Anfrage ob ein Wort akzeptiert wird kann entweder über einen Boolean abgefragt werden oder wird über die Konsole zurück gegeben 
 * 
 * Zuletzt bearbeitet: 20.02.2021
 */

public class Automat {
    
    

    private Stack<Character> myStack = new Stack<Character>(); // Keller des Automaten
    private boolean z0Bool = true; // Booleans um klar zu haben, in welchem Zustand der Automat ist. Dabei ist z0
				   // der Start und z3 der einzige akzeptierte Zielzustand
    private boolean z1Bool = false;
    private boolean z2Bool = false;
    private boolean z3Bool = false;
    private boolean schleifenbeender = false; // Beendet die Schleife mit der das eingegebene Wort "untersucht" wird
    /*
     * Konstruktor, der beim Aufruf Nutzer*innen des Programms auffordert das Wort
     * in die Konsole einzugeben
     */

    public Automat() {
	myStack.push('#'); // Start Symbol im Stack
	System.out.println("Mach Deine Eingaben");
    }

    /*
     * Im Methodenkopf der Methode istWortKorrekt muss das Wort angegeben werden.
     * Über die Wenn das Wort im Alphabet liegt, gibt die Methode true zurück
     */
    public boolean istWortKorrekt(String s) {
	while (schleifenbeender == false) { // Schleife, in der das Wort untersucht wird, bis klar ist, dass es in die
					    // Sprache passt oder eben nicht

	    if (s.isEmpty()) { // Notwendige if Abfrage, weil ein leeres Wort oder nur der Buchstabe a für
			       // einen Fehler sorgen würden (bei einem leeren String kann kein char abgefragt
			       // werden)
		s = "y";
	    }

	    char ch = s.charAt(0); // Erster Buchstabe des Worts wird untersucht
	    s = s.substring(1, s.length()); // Rest der Worts wird zum neuen Wort

	    if (ch == 'a') {
		if (z0Bool == true) { // Abfrage verhindert, dass nachdem ein b eingegeben wurde kein a mehr folgen
				      // kann
		    this.z0();
		}
	    } else if (ch == 'b') {
		if (z0Bool == true || z1Bool == true) {
		    if (myStack.peek() == 'A') { // Abfrage verhindert, dass b nicht erster Buchstabe es Wortes ist
			this.z1();
		    } else {
			schleifenbeender = true; // Wird ein b an einer falschen Stelle eingegeben, wird die loop
						 // abgebrochen
		    }
		}

	    } else if (ch == 'c') {
		if (z1Bool == true || z2Bool == true) {
		    this.z2();
		} else {
		    schleifenbeender = true;
		}

	    } else if (ch == 'd' && z2Bool == true && s.isEmpty()) { // isEmpty() verhindert, dass d als erster
								     // Buchstabe genennt wird und dass mehr als ein D
								     // eingegeben wird
		this.z3();

	    } else {
		schleifenbeender = true;
	    }
	}

	if (z3Bool == true) { // Wenn er Endzustand erreicht ist, wird true zurück gegeben
	    return true;
	} else {
	    return false; // Wort liegt nicht in der Sprache: false wird zurück gegeben
	}

    }

    /*
     * Im Methodenkopf der Methode Eingabe muss das Wort angegeben werden. Über die
     * Konsole wird angezeigt, ob es akzeptiert wird oder nicht
     */
    public void eingabe(String s) {
	if (this.istWortKorrekt(s)) { // Wenn er Endzustand erreicht ist, wird dies über die Konsole mitgeteilt
	    System.out.println("Das Wort wurde akzeptiert!");
	} else {
	    System.out.println("Das Wort liegt nicht in der akzeptierten Sprache"); // Wort liegt nicht in der Sprache
	}

    }

    private void z0() {
	myStack.push('A'); // A in den Stack, wenn ein a eingegeben wird
    }

    private void z1() { // A in den Stack, wenn ein B eingegeben wird
	myStack.push('A');
	z0Bool = false;
	z1Bool = true; // Zustands wechsel
    }

    private void z2() {

	myStack.pop(); // A wird aus dem Stack entfernt
	z1Bool = false;
	z2Bool = true;
    }

    private void z3() {
	switch (myStack.peek()) {
	case 'A':
	    schleifenbeender = true;
	    break;
	case '#':
	    z3Bool = true;
	    schleifenbeender = true;
	    break; // Wird ein d eingegeben wird geprüft ob der Stack leer ist(Bis auf #). Wenn Ja:
		   // Wort passt, wenn Nein: Wort passt nicht
	}
    }

}