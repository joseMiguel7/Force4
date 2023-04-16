/**
 * Implementation de la classe GrilleEtLigne
 *
 * @authors  (J. COLAS, J.M. JIMENEZ, J. SAYE)
 * @version (v10.0)
 */


package fr.epsi.poe;

public class GrilleEtLigne {

    private int ligneBoutons [] ;

    private int grille [][];

    public static final int NB_LIGNES=6;
    public static final int NB_COLONNES=7;
    public static final int NB_ITERATIONS_HORIZONTAL=4;
    public static final int NB_ITERATIONS_VERTICAL=3;
    public static final int QUATRE_CASES_ALIGNEES=4;

/**
 * Constructeur par defaut des objets de la classe GrilleEtLigne
 */
    public GrilleEtLigne() {
        // initialisation des variables d'instance
        this.ligneBoutons = new int [7];
        this.grille = new int [6] [7];

        }

    public void setLigneBoutons(int[] ligneBoutons) {
        this.ligneBoutons = ligneBoutons;
    }

    public int[][] getGrille() {
        return grille;
    }

    public void setGrille(int[][] grille) {
        this.grille = grille;
    }

    /**
     * description de la méthode trouverColonnePourPoserJeton:
     *      cette méthode va consulter le tableau ligneBoutons et
     *      trouver la premiere case qui contient un "un ou 1" ou
     *      un "moins 1 ou -1" on recupere l'index et la valeur et
     *      l'on appele la méthode poserBoutons
     */

    public boolean  trouverColonnePourPoserJeton() {

        for (int j=0; j < NB_COLONNES;j++) {
            if(this.ligneBoutons[j] == -1 ) {
                return poserJeton(j, -1);
            }
            else if (this.ligneBoutons[j] == 1 ) {
                return poserJeton(j, 1);
            }
        }
        return false;//la colonne est pleine

   }
    /**
     * Description de la méthode poserJeton: cette méthode regarde quelle ligne
     * est vide du bas vers le haut et pose un jeton de valeur à affecter
     * à la cellule de la matrice dans une colonne donnée
     * @param colonne la colonne d'insertion
     * @param valeur la valeur à insèrer
     */
    public boolean poserJeton(int colonne, int valeur) {

        for(int i=0; i < NB_LIGNES;i++){
            if (this.grille[i][colonne] == 0) {
                this.grille[i] [colonne] = valeur;
                return true;
            }
        }
        //si la colonne est pleine
        return false;
   }

    /**
     * Description de la méthode isHorizontalForce4
     * vérifie s'il existe une série de 4 cellules identiques, si la valeur absolue de
     * la somme est 4 alors la fonction retourne true
     * @return
     * un booléen qui atteste qu'un joueur a gagné avec quatre jetons à l'horizontale
     */
    public boolean isHorizontalForce4(){
        int somme = 0;

        for(int ligne=0; ligne < NB_LIGNES; ligne++){
            for(int colonne=0; colonne < NB_ITERATIONS_HORIZONTAL; colonne++) {
                for(int k=0; k < QUATRE_CASES_ALIGNEES; k++) {
                    somme += grille[ligne][colonne+k];
                }
                if((somme == -4) || (somme == 4)) return true;
                somme = 0;
            }
        }
        return false;
    }
    /**
     * Description de la méthode isVerticalForce4
     * même principe que pour horizontal
     * @return
     * un booléen qui atteste qu'un joueur a gagné avec quatre jetons à la verticale
     */
    public boolean isVerticalForce4(){
        int somme = 0;

        for(int colonne=0; colonne < NB_COLONNES; colonne++){
            for(int ligne=0; ligne< NB_ITERATIONS_VERTICAL; ligne++) {
                for(int k=0; k< QUATRE_CASES_ALIGNEES; k++) {
                    somme += grille[ligne+k][colonne];
                }
                if((somme== -4) || (somme== 4)) return true;
                somme=0;
            }
        }
        return false;
    }

    /**
     * Description de la méthode isDiagonaleBasGaucheToHautDroit
       même principe que pour horizontal mais en diagonale
     * @return
     * un booléen qui atteste qu'un joueur
     * a gagné avec quatre jetons dans la diagonale
     * en allant du bas à gauche vers le haut à droite
     */
    public boolean isDiagonaleBasGaucheToHautDroit(){
        int somme = 0;

        for(int i=0; i< NB_LIGNES; i++){
            for(int j=0; j< NB_COLONNES; j++) {
                for(int k=0; k< QUATRE_CASES_ALIGNEES; k++) {
                    if (((i+k)<6)&& ((j+k) < 7)) {
                        somme += grille[i + k][j + k];
                    }
                }
                if((somme== -4) || (somme== 4)) return true;
                somme=0;
            }
        }
        return false;
    }
    /**
     * Description de la méthode isDiagonaleBasDroitToHautGauche
     * comme l'autre isDiagonale...
     * @return
     * un booléen qui atteste qu'un joueur
     * a gagné avec quatre jetons dans la diagonale
     * allant du bas à droite vers le haut gauche
     */
    public boolean isDiagonaleBasDroitToHautGauche(){
        int somme = 0;

        for(int i=0; i< NB_LIGNES; i++){
            for(int j=NB_COLONNES-1; j>=0; j--) {
                for(int k=0; k< QUATRE_CASES_ALIGNEES; k++) {
                    if (((i+k)<6)&& ((j-k)>=0)) {
                        somme += grille[i + k][j - k];
                    }
                }
                if((somme== -4) || (somme== 4)) return true;
                //debug
                //System.out.println(somme);
                somme=0;
            }
        }
        return false;
    }
}
