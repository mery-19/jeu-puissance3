
public class Puissance3 {

	private int[][] matriceJeu;
	private int width = 5;
	private int height = 5;

	public Puissance3() {
		super();
		matriceJeu = new int[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				matriceJeu[i][j] = 0;
			}
		}
	}

	public int[][] getMatriceJeu() {
		return matriceJeu;
	}

	public boolean jouer(boolean typeJoeur, int colonne, int[][] matrice) {

		int jeton = typeJoeur ? 1 : 2;

		if (colonne >= 0 && colonne <= width) {
			for (int i = height - 1; i >= 0; i--) {
				if (matrice[i][colonne] == 0) {
					matrice[i][colonne] = jeton;
					return true;
				}
			}
		}

		return false;
	}

	public boolean estFinJeu(boolean typeJoueur, int[][] matrice) {

		Noeud noeud = new Noeud(matrice, typeJoueur);
		boolean gagnantExist = (noeud.troisPionsAlignesLigne(typeJoueur) != 0)
				|| (noeud.troisPionsAlignesColonne(typeJoueur) != 0);
		boolean isRemplie = true;

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (matrice[i][j] == 0)
					isRemplie = false;
				break;
			}

		}

		return gagnantExist || isRemplie;
	}

	@Override
	public String toString() {
		String g = "";
		for (int[] is : matriceJeu) {
			for (int i : is) {
				g = g + " " + i;
			}

			g = g + "\n";
		}

		return "matriceJeu=\n" + g + "\n";
	}

	public void matrice(int[][] mSource, int[][] mDest) {
		for (int i = 0; i < mSource.length; i++) {
			for (int j = 0; j < mSource.length; j++) {
				mDest[i][j] = mSource[i][j];
			}
		}
	}

	public Coup alpha_beta(Noeud racine, int alpha, int beta, int profondeur) {

		int[][] m;
		int alpha_courant, beta_courant, meilleur_col = 0;
		Coup coup;

		if (profondeur == 1 || estFinJeu(racine.isMax(), racine.getMatrice())) {
			racine.evaluer();
			return new Coup(racine.getH(), -1);
		}
		if (racine.isMax()) {
			alpha_courant = alpha;
			for (int j = 0; j < width; j++) {
				m = new int[height][width];
				matrice(racine.getMatrice(), m);
				jouer(racine.isMax(), j, m);
				Noeud sucesseur = new Noeud(m, !racine.isMax());
				coup = alpha_beta(sucesseur, alpha_courant, beta, profondeur - 1);
				sucesseur.setH(coup.getEval());
				if (coup.getEval() > alpha_courant) {
					alpha_courant = coup.getEval();
					meilleur_col = j;
				}
				if (alpha_courant >= beta)
					return new Coup(alpha_courant, meilleur_col);
			}
			return new Coup(alpha_courant, meilleur_col);
		} else {
			beta_courant = beta;
			for (int j = 0; j < width; j++) {
				m = new int[height][width];
				matrice(racine.getMatrice(), m);
				jouer(racine.isMax(), j, m);
				Noeud sucesseur = new Noeud(m, !racine.isMax());
				coup = alpha_beta(sucesseur, alpha, beta_courant, profondeur - 1);
				sucesseur.setH(coup.getEval());
				if (coup.getEval() < beta_courant) {
					beta_courant = coup.getEval();
					meilleur_col = j;
				}
				if (beta_courant <= alpha) {
					return new Coup(beta_courant, meilleur_col);
				}
			}
			return new Coup(beta_courant, meilleur_col);
		}
	}
}
