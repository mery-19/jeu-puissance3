
import java.util.Scanner;

public class Main {

	static Scanner sc = new Scanner(System.in);
	static Noeud noeud;
	static Coup coup;
	static Puissance3 puissance3 = new Puissance3();
	static int colonne = 0;
	static boolean stop_game = false;

	public static void main(String[] args) {

		System.out.println("Qui va commencer en premier ?");
		System.out.println("-1- L’humain qui commence.\n-2- La machine qui commence.");
		int i = sc.nextInt();

		while (i != 1 && i != 2) {
			System.out.println("Choisir 1 ou 2 :");
			i = sc.nextInt();
		}

		if (i == 1)
			userStart();
		else if (i == 2)
			machineStart();

		// test_noeud();
	}

	public static void test_noeud() {

		int[][] matrice = { { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 }, { 0, 1, 0, 0, 0 },
				{ 1, 2, 0, 2, 0 } };
		Noeud n = new Noeud(matrice, true);
		System.out.println(n.toString());
		System.out.println("pions possible ligne:");
		System.err.println("ligne possible pour joueur 1: " + n.troisPionsPossiblesLigne(true));
		System.err.println("ligne possible pour joueur 2: " + n.troisPionsPossiblesLigne(false));
		System.err.println("colonne possible pour joueur 1: " + n.troisPionsPossiblesColonne(true));
		System.err.println("colonne possible pour joueur 2: " + n.troisPionsPossiblesColonne(false));
		n.evaluer();
		System.out.println("h= " + n.getH());

	}

	static void userStart() {

		System.out.println(puissance3.toString());
		while (true) {
			/************ User Start *************/
			userPart();
			if (stop_game)
				break;

			/************ Machine start *************/
			machinePart();
			if (stop_game)
				break;

		}
	}

	static void machineStart() {

		System.out.println(puissance3.toString());
		while (true) {
			/************ Machine start *************/
			machinePart();
			if (stop_game)
				break;

			/************ User Start *************/
			userPart();
			if (stop_game)
				break;
		}
	}

	static void userPart() {
		do {
			System.out.print("-Humain role- Donner le numéro de colonne (1 -> 5): ");
			colonne = sc.nextInt() - 1;
		} while ((colonne > 4) || (colonne < 0));

		puissance3.jouer(false, colonne, puissance3.getMatriceJeu());
		System.out.println(puissance3.toString());

		if (puissance3.estFinJeu(false, puissance3.getMatriceJeu())) {
			System.out.println("Vous avez gagné (*_*)");
			stop_game = true;
		}
	}

	static void machinePart() {
		System.out.print("-Machine role- ");
		noeud = new Noeud(puissance3.getMatriceJeu(), true);
		coup = puissance3.alpha_beta(noeud, Integer.MIN_VALUE, Integer.MAX_VALUE, 4);
		noeud.setH(coup.getEval());

		puissance3.jouer(true, coup.getColonne(), puissance3.getMatriceJeu());
		System.out.println(puissance3.toString());

		if (puissance3.estFinJeu(true, puissance3.getMatriceJeu())) {
			System.out.println(puissance3.toString());
			System.out.println("J'ai gagné (^_^)");
			stop_game = true;
		}
	}
}
