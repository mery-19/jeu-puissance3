
public class Noeud {
	private int[][] matrice;
	private boolean max;
	private int h;

	public Noeud(int[][] matrice, boolean max) {
		super();
		this.matrice = matrice;
		this.max = max;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public int[][] getMatrice() {
		return matrice;
	}

	public boolean isMax() {
		return max;
	}

	public int troisPionsAlignesLigne(Boolean typeJoueur) {
//		return 1000 si 3 pions sont alignés en ligne pour typeJoueur
		String jeton = typeJoueur ? "1" : "2";
		String ligne;

		for (int i = 0; i < matrice.length; i++) {
			ligne = "";
			for (int j = 0; j < matrice.length; j++) {
				ligne += matrice[i][j];
			}
			if (ligne.contains(jeton + jeton + jeton)) {
				return 1000;
			}
		}
		return 0;
	}

	public int troisPionsAlignesColonne(Boolean typeJoueur) {
//		return 1000 si 3 pions sont alignés en colonne pour typeJoueur
		String jeton = typeJoueur ? "1" : "2";
		String colonne;
		for (int j = 0; j < matrice.length; j++) {
			colonne = "";
			for (int i = 0; i < matrice.length; i++) {
				colonne += matrice[i][j];
			}
			if (colonne.contains(jeton + jeton + jeton)) {
				return 1000;
			}
		}
		return 0;
	}

	public int troisPionsPossiblesLigne(Boolean typeJoueur) {
		int result = 0;
		String jeton = typeJoueur ? "1" : "2";

		for (int i = 0; i < matrice.length; i++) {

			String ligne = "";

			for (int j = 0; j < matrice.length; j++) {
				ligne += matrice[i][j];
			}

			if (ligne.contains("0" + jeton + jeton))
				result += 200;
			if (ligne.contains(jeton + "0" + jeton))
				result += 200;
			if (ligne.contains(jeton + jeton + "0"))
				result += 200;
			if (ligne.contains("0" + jeton))
				result += 30;
			if (ligne.contains("" + jeton + "0"))
				result += 30;

		}
		return result;
	}

	public int troisPionsPossiblesColonne(Boolean typeJoueur) {
		int result = 0;
		String jeton = typeJoueur ? "1" : "2";

		for (int j = 0; j < matrice.length; j++) {

			String colonne = "";

			for (int i = 0; i < matrice.length; i++) {
				colonne += matrice[i][j];
			}

			if (colonne.contains("0" + jeton + jeton))
				result += 200;
			if (colonne.contains(jeton + "0" + jeton))
				result += 200;
			if (colonne.contains(jeton + jeton + "0"))
				result += 200;
			if (colonne.contains("0" + jeton))
				result += 30;
			if (colonne.contains("" + jeton + "0"))
				result += 30;

		}
		return result;
	}

	public void evaluer() {
		h = -2 * troisPionsAlignesLigne(false) + troisPionsAlignesLigne(true) - 2 * troisPionsAlignesColonne(false)
				+ troisPionsAlignesColonne(true) - 2 * troisPionsPossiblesLigne(false) + troisPionsPossiblesLigne(true)
				- 2 * troisPionsPossiblesColonne(false) + troisPionsPossiblesColonne(true);
	}

	public String matriceToString(int[][] matrice) {
		String g = "";
		for (int[] is : matrice) {
			for (int i : is) {
				g = g + " " + i;
			}
			g = g + "\n";
		}
		return g;
	}

}
