/**
 * Implémentation de la classe GrilleEtLigneTest
 * Tests des méthodes
 * @authors  (J. COLAS, J.M. JIMENEZ, J. SAYE)
 * @version (v1.0)
 */

package fr.epsi.poe;



import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GrilleEtLigneTest {
    // grille pour tester si il y a quatre jetons à l'horizontal
    int[][]  tab2DHorizontal= {
            {-1, 1, 1,  1, -1, 1, 0},/* ligne indice 0*/
            {-1,-1,-1, -1,  1, 0, 0},/* ligne indice 1*/
            { 0, 0,-1,  1,  1, 0, 0},  /* ligne indice 2*/
            { 0, 0,-1,  0,  0, 0, 0},  /* ligne indice 3*/
            { 0, 0, 1,  0,  0, 0, 0},  /* ligne indice 4*/
            { 0, 0, 0,  0,  0, 0, 0}   /* ligne indice 5*/
    };

    // grille pour tester si il y a quatre jetons à la verticale
    int[][]  tab2DVertical= {
            {-1, 1,  1,  1, -1, 1, 0},    /* ligne indice 0*/
            {-1, 1, -1, -1,  1, 0, 0},    /* ligne indice 1*/
            { 0,-1, -1,  1,  1, 0, 0},    /* ligne indice 2*/
            { 0,-1, -1, -1,  1, 0, 0},    /* ligne indice 3*/
            { 0, 0,  1,  0,  1, 0, 0},    /* ligne indice 4*/
            { 0, 0,  0,  0,  0, 0, 0}     /* ligne indice 5*/
    };

    // grille pour tester si il y a quatre jetons dans la diagonale
    // allant de bas gauche vers le haut droit
    int[][]  tab2DDiagonalBasGaucheToHautDroit= {
            {-1,  1, 1,  1, -1, 1, 0},    /* ligne indice 0*/
            {-1,  1, 1,  1, -1, 1, 0},    /* ligne indice 1*/
            { 1, -1,-1,  1, 1, -1, 0},    /* ligne indice 2*/
            {1,  -1,-1, -1, 1,  0, 0},    /* ligne indice 3*/
            {0,   0, 1, -1, 1, -1, 0},    /* ligne indice 4*/
            {0,   0, 0,  0, 0,  0, 0}       /* ligne indice 5*/
    };

    // grille pour tester si il y a quatre jetons dans la diagonale
    // allant du bas droit vers le haut gauche
    int[][]  tab2DDiagonalBasDroitToHautGauche= {
            {-1,  1, 1, 1, -1,  1, 0},    /* ligne indice 0*/
            {-1,  1, 1, 1, -1,  1, 0},    /* ligne indice 1*/
            {1,  -1,-1, 1,  1, -1, 0},    /* ligne indice 2*/
            {1,  -1,-1, 1,  1,  0, 0},     /* ligne indice 3*/
            {-1, -1, 1, 1,  1, -1, 0},   /* ligne indice 4*/
            { 0,  0, 0, 0,  0,  0, 0}       /* ligne indice 5*/
    };

    int [] [] tab2D;
    int [] [] tab2DMoins1;

    int [] tabLigneBoutons = {0,0,0,1,0,0,0};

    int [] tabLigneBoutonsMoins1 = {0,0,0,0,0,-1,0};

    GrilleEtLigne grilleEtLigne = new GrilleEtLigne();

    /**
     * Description Vérifier que l'on pose bien un '1' à la colonne choisie
     */
    @Test
    public void testTrouverColonnePourPoserJeton1()
    {
        // initialise la ligne ligneBoutons avec le tableau tabLigneBoutons
        grilleEtLigne.setLigneBoutons(tabLigneBoutons);
        // appel de la méthode trouverColonnePourPoserJeton
        // qui appele PoserJeton sur une colonne
        grilleEtLigne.trouverColonnePourPoserJeton();
        // on récupère la grille et on vérifie
        // si à la ligne 0 colonne 3 est bien occupé
        // par un jeton valant 1
        tab2D = grilleEtLigne.getGrille();
        assertEquals(1, tab2D[0] [3]);
        // On repose un jeton sur la même colonne
        // pour vérifier si il est bien sur la ligne suivante
        grilleEtLigne.trouverColonnePourPoserJeton();
        tab2D = grilleEtLigne.getGrille();
        assertEquals(1, tab2D[1] [3]);
    }

    /**
     * Description de la méthode testTrouverColonnePourPoserJetonMoins1:
     * trouver la ligne où le jeton va se poser
     *
     */
    @Test
    public void testTrouverColonnePourPoserJetonMoins1()
    {
        // dépose
        grilleEtLigne.setLigneBoutons(tabLigneBoutonsMoins1);
        grilleEtLigne.trouverColonnePourPoserJeton();
        tab2DMoins1 = grilleEtLigne.getGrille();
        assertEquals(-1, tab2DMoins1[0] [5]);
    }

    /**
     * Description de la méthode testIsHorizontalForce4:
     * vérification de 4 jetons identique alignés qui se suivent
     * horizontalement
     */
    @Test
    public void testIsHorizontalForce4() {
        grilleEtLigne.setGrille(tab2DHorizontal);
        boolean b = grilleEtLigne.isHorizontalForce4();
        assertTrue(b);
    }
    /**
     * Description de la méthode testIsVerticalForce4:
     * vérification de 4 jetons identique alignés qui se suivent
     * verticalement
     */
    @Test
    public void testIsVerticalForce4() {
        grilleEtLigne.setGrille(tab2DVertical);
        boolean b = grilleEtLigne.isVerticalForce4();
        assertTrue(b);
    }
    /**
     * Description de la méthode testIsDiagonaleBasGaucheToHautDroit:
     * vérification de 4 jetons identique alignés qui se suivent
     *  diagonale /
     */
    @Test
    public void testIsDiagonaleBasGaucheToHautDroit() {
        grilleEtLigne.setGrille(tab2DDiagonalBasGaucheToHautDroit);
        boolean b = grilleEtLigne.isDiagonaleBasGaucheToHautDroit();
        assertTrue(b);

    }

    /**
     * Description de la méthode testIsDiagonaleBasDroitToHautGauche:
     * vérification de 4 jetons identique alignés qui se suivent
     * diagonale \
     */
    @Test
    public void testIsDiagonaleBasDroitToHautGauche() {
        grilleEtLigne.setGrille(tab2DDiagonalBasDroitToHautGauche);
        boolean b = grilleEtLigne.isDiagonaleBasDroitToHautGauche();
        assertTrue(b);

    }

}

