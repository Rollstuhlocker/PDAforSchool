package mypackage;
import java.util.Scanner; //Klasse Scanner um das in die Konsole eingegebene Wort zu lesen

public class Ablauf {

	static Automat pda;
	static Scanner sc = new Scanner(System.in);
	static String s;

	public static void main(String[] args) {
		pda = new Automat();
		s = sc.nextLine(); //Die Eingabe in die Konsole wird ausgelesen 
		pda.eingabe(s); //Eingabe des Worts in den Automaten
		sc.close();
	}

}
