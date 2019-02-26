/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package force4;
import java.lang.Math;
/**
 *
 * @author josem
 */
public class GrilleEtLigne {
    
    
    private int ligne [] ;
     
    private int grille [][];
    
    public static final int NB_LIGNES=6;
    public static final int NB_COLONNES=7;
    public static final int NB_ITERATIONS_HORIZONTAL=4;
    public static final int NB_ITERATIONS_VERTICAL=3;
    public static final int QUATRE_CASES_ALIGNEES=4;
    
    /**
     * Constructeur par défaut des objets de la classe GrilleEtLigne
     */
    public GrilleEtLigne() {
        // initialisation des variables d'instance
        this.ligne = new int [7];
        this.grille = new int [6] [7];

    }
    
    /**
     * Constructeur par copie des objets de la classe GrilleEtLigne
     */
    public GrilleEtLigne(GrilleEtLigne gel ) {
         grille = new int [6] [7];
        // initialisation des variables d'instance
        for(int i=0; i<NB_LIGNES; i++) {
            for(int j=0; j<NB_COLONNES; j++) {
                this.grille[i] [j] = gel.grille[i] [j];
                
            }
        }
    }

    public int[][] getGrille() {
        return grille;
    }
    
    
    
    
    
    boolean isHorizontalForce4(){
        int somme = 0;
        for(int i=0; i< NB_LIGNES; i++){
            for(int j=0; j< NB_ITERATIONS_HORIZONTAL; j++) {
                for(int k=0; k< QUATRE_CASES_ALIGNEES; k++) {
                    somme += grille[i][j+k];
                }
                if((somme== -4) || (somme== 4)) return true;
                somme = 0;
            }
        }
    return false;
    }
    
    boolean isVerticalForce4(){
        int somme = 0;
        for(int i=0; i< NB_COLONNES; i++){
            for(int j=0; j< NB_ITERATIONS_VERTICAL; j++) {
                for(int k=0; k< QUATRE_CASES_ALIGNEES; k++) {
                    somme += grille[j+k][j];
                }
                if((somme== -4) || (somme== 4)) return true;
                somme=0;
            }
        }
    return false;
    }
    
    boolean isDiagonaleBasGaucheToHautDroit(){
        int somme = 0;
        for(int i=0; i< NB_LIGNES; i++){
            for(int j=0; j< NB_COLONNES; j++) {
                for(int k=0; k< QUATRE_CASES_ALIGNEES; k++) {
                    if ((i<3)&& (j<4))
                        somme += grille[i+k][j+k];
                }
                if((somme== -4) || (somme== 4)) return true;
                somme=0;
            }
        }
    return false;
    }
    
    boolean isDiagonaleBasDroitToHautGauche(){
        int somme = 0;
        for(int i=0; i< NB_LIGNES; i++){
            for(int j=NB_COLONNES-1; j>0; j--) {
                for(int k=0; k< QUATRE_CASES_ALIGNEES; k++) {
                    if ((i<3)&& (j<4))
                        somme += grille[i+k][j-k];
                }
                if((somme== -4) || (somme== 4)) return true;
                somme=0;
            }
        }
    return false;
    }
    
}
