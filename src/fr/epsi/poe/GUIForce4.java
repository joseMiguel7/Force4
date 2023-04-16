/**
 * Implémentation de la classe GUIForce4:
 * Instanciée par la classe Main, elle construit l'interface graphique
 * du jeu avec le joueur utilisateur.
 * @authors  (J. COLAS, J.M. JIMENEZ, J. SAYE)
 * @version (v10.0)
 */
package fr.epsi.poe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.StrictMath.abs;

class GUIForce4 extends JFrame implements ActionListener {

    private GrilleEtLigne grilleEtLigne;

    private static int couleur;
    private static boolean bPremierAppui;
    private static int n;
    private int nombreBoutonsLigne = 0;
    private int nombreBoutonsGrille = 0;

    private JButton[] ligneBoutons;
    private JButton[] grilleBoutons;

    private JTextField stringScoreRouge;
    private JButton scoreRouge;
    private JButton joueurPlus1;
    private JButton afficheCouleur;
    private JButton joueurMoins1;
    private JButton reset;
    private JButton scoreJaune;
    private JTextField stringScoreJaune;

    private int compteurRouge;
    private String strCompteurRouge;
    private String message;
    private int compteurJaune;
    private String strCompteurJaune;
    private String messageFinDeColonne;

    /**
     * CONSTRUCTEUR de l'UI de jeu: fenêtre, panels, dispositions,  boutons, champs texte,
     * ligne de boutons d'insertion (icône flèche down), grille de boutons
     * (icônes cadre bleu et jeton: blanc, rouge, jaune)
     * et liaison des listener aux composants concernés (boutons d'insertion, de début et reset)
     *
     * @param nombreBoutonsLigne nombre de boutons de la ligne d'insertion
     * @param nombreBoutonsGrille nombre de boutons nécessaires à la grille
     */

    public GUIForce4(int nombreBoutonsLigne, int nombreBoutonsGrille) {

        this.nombreBoutonsLigne = nombreBoutonsLigne;
        this.nombreBoutonsGrille = nombreBoutonsGrille;
        grilleEtLigne = new GrilleEtLigne();
        bPremierAppui = false;
        n=0;
        strCompteurRouge = new String();
        compteurRouge = 0;
        strCompteurJaune = new String();
        compteurJaune = 0;

        //Fenêtre
        super.setTitle("FORCE 4");
        super.setSize(800, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        //Panneaux
        Container contenu = this.getRootPane().getContentPane();

        JPanel panneauLigneBoutons = new JPanel();
        panneauLigneBoutons.setLayout(new GridLayout(1, 7));
        contenu.add(panneauLigneBoutons, BorderLayout.NORTH);

        JPanel panneauGrilleBoutons = new JPanel();
        panneauGrilleBoutons.setLayout(new GridLayout(6, 7));
        grilleBoutons = new JButton[nombreBoutonsGrille];
        contenu.add(panneauGrilleBoutons, BorderLayout.CENTER);

        JPanel panneauJeuInferieur = new JPanel();
        panneauJeuInferieur.setLayout(new FlowLayout());
        contenu.add(panneauJeuInferieur, BorderLayout.SOUTH);

        //affichage champ score rouge
        stringScoreRouge = new JTextField("0");
        stringScoreRouge.setPreferredSize(new Dimension(20,40));
        stringScoreRouge.setBackground(Color.red);
        //changement de la font de sa police
        Font font = new Font("Courier", Font.BOLD,12);
        stringScoreRouge.setFont(font);
        panneauJeuInferieur.add(stringScoreRouge);

        //Bouton score rouge
        scoreRouge = new JButton("Score");
        scoreRouge.setBackground(Color.red);
        scoreRouge.setPreferredSize(new Dimension(100, 40));
        panneauJeuInferieur.add(scoreRouge);

        // bouton "Début" (joueur +1 rouge)
        joueurPlus1 = new JButton("Début");
        joueurPlus1.setPreferredSize(new Dimension(100, 40));
        joueurPlus1.setBackground(Color.red);
        panneauJeuInferieur.add(joueurPlus1);
        //Ajout d'un listener sur ce bouton "Début" rouge
        joueurPlus1.addActionListener(this);

        //bouton "Couleur à jouer"
        afficheCouleur = new JButton("Couleur à jouer");
        afficheCouleur.setBackground(Color.blue);
        afficheCouleur.setPreferredSize(new Dimension(150, 40));
        panneauJeuInferieur.add(afficheCouleur);

        //Bouton Reset
        reset = new JButton("Reset");
        reset.setPreferredSize(new Dimension(70, 40));
        panneauJeuInferieur.add(reset);
        //Ajout d'un listener sur ce bouton "Reset"
        reset.addActionListener(this);

        // bouton "Début" (joueur -1 jaune)
        joueurMoins1 = new JButton("Début");
        joueurMoins1.setPreferredSize(new Dimension(100, 40));
        joueurMoins1.setBackground(Color.yellow);
        panneauJeuInferieur.add(joueurMoins1);
        //Ajout d'un listener sur ce bouton "Début" jaune
        joueurMoins1.addActionListener(this);

        //Bouton score rouge
        scoreJaune = new JButton("Score");
        scoreJaune.setBackground(Color.yellow);
        scoreJaune.setPreferredSize(new Dimension(100, 40));
        panneauJeuInferieur.add(scoreJaune);

        //affichage champ score jaune
        stringScoreJaune = new JTextField("0");
        stringScoreJaune.setPreferredSize(new Dimension(20,40));
        stringScoreJaune.setBackground(Color.yellow);
        //changement de la font de sa police
        stringScoreJaune.setFont(font);
        panneauJeuInferieur.add(stringScoreJaune);

        //création des boutons "bleu à flèche bas" d'insertion de jeton avec leurs listeners
        ligneBoutons = new JButton[nombreBoutonsLigne];
        for (int i = 0; i < nombreBoutonsLigne; i++) {
            ligneBoutons[i] = new JButton("", new ImageIcon("flecheok.png"));

            panneauLigneBoutons.add(ligneBoutons[i]);
            ligneBoutons[i].addActionListener(this);
        }

        //création des boutons de grille "cadre bleu, jeton blanc" de début de jeu
        for (int k = 0; k < nombreBoutonsGrille; k++) {
            Icon blanc = new ImageIcon("blanc.GIF");
            grilleBoutons[k] = new JButton();
            grilleBoutons[k].setIcon(blanc);
            panneauGrilleBoutons.add(grilleBoutons[k]);
        }
    }

    /**
     * Description de la méthode actionPerformed
     * on regarde d'où vient l'événement
     * @param  evt
     * Cet événement  provient d'un des boutons d'insert, de début et de reset
     *
     */
    public void actionPerformed(ActionEvent evt) {
        //Object source = evt.getSource();
        JButton source = (JButton)evt.getSource();
        //on va comparer l'objet JButton avec ses caractéristiques particulières de couleur:
        //bPremierAppui est false dans le constructeur

        if (source == joueurPlus1) {
            if (!bPremierAppui) {
                couleur = 1;
                bPremierAppui=true;
                n=2; // -1 puissance 2 est egale a 1
                this.afficheCouleur.setBackground(Color.red);
            }
            //affichage des événements pour débug
            //System.out.println("Source = joueurPlus1 ou rouge");

        } else if (source == joueurMoins1) {
            if (!bPremierAppui) {
                couleur = -1;
                bPremierAppui=true;
                n=1;
                afficheCouleur.setBackground(Color.yellow);
            }
            //affichage des événements pour débug
            //System.out.println("Source = joueurMoins1 ou jaune");

        } else if (source == reset) {
            resetGrilleEtLigne();
            strCompteurJaune = Integer.toString(0);
            strCompteurRouge = Integer.toString(0);
            stringScoreRouge.setText("0");
            stringScoreJaune.setText("0");
            // debug
            // System.out.println("Source = reset");
        }
        else {
            if(bPremierAppui == true) {

                couleur = power(-1, n);

                if (couleur == 1) {
                    this.afficheCouleur.setBackground(Color.yellow);
                } else if (couleur == -1) {
                    this.afficheCouleur.setBackground(Color.red);
                }
                int[] tabInt = new int[]{0, 0, 0, 0, 0, 0, 0};
                for (int i = 0; i < 7; i++) {
                    if (source == ligneBoutons[i]) {
                        tabInt[i] = couleur;
                        //debug
                        //System.out.println("Action sur bouton" + (i + 1));
                    }
                }
                n++;
                this.positionnerJeton(tabInt);
            }
        }
    }
    /**
     * description de la méthode positionnerJeton
     *
     * @param li
     * tableau d'entiers
     */
    public void positionnerJeton(int[] li) {
        int[][] intTab2D ;

        grilleEtLigne.setLigneBoutons(li);
        if(grilleEtLigne.trouverColonnePourPoserJeton()){

            intTab2D = grilleEtLigne.getGrille();

            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 7; j++) {
                    if (intTab2D[i][j] == 1) {
                        Icon rouge = new ImageIcon("rouge.GIF");
                        grilleBoutons[abs((-i) * 7 + 35 + j)].setIcon(rouge);
                    } else if (intTab2D[i][j] == -1) {
                        Icon jaune = new ImageIcon("jaune.GIF");
                        grilleBoutons[abs((-i) * 7 + 35 + j)].setIcon(jaune);
                    }

                }

            }
        }else {
            messageFinDeColonne = "Changer de colonne";
            JOptionPane.showMessageDialog(null,messageFinDeColonne);
            System.out.println("Retour boite de dial "+JOptionPane.CLOSED_OPTION);
            System.out.println("Retour boite de dial "+JOptionPane.OK_OPTION);
            if((JOptionPane.CLOSED_OPTION == -1)||(JOptionPane.OK_OPTION == 0)){

                //debug
                //System.out.println(this.afficheCouleur.getBackground());

                //si on récupère une couleur de bouton 'affiche couleur' rouge on affiche jaune
                //et inversement puis on modifie n de 1 pour afficher la bonne couleur de jeton

                if(this.afficheCouleur.getBackground().equals(Color.RED)){
                    this.afficheCouleur.setBackground(Color.YELLOW);
                }else{
                    this.afficheCouleur.setBackground(Color.RED);
                }
                n--;

            }
        }



        if(this.estCoupGagnant()) {
            if (couleur == 1){
                message = "Le joueur au jeton rouge a gagné";
                compteurRouge++;
                strCompteurRouge = Integer.toString(compteurRouge);
                stringScoreRouge.setText(strCompteurRouge);
            }
            else if(couleur == -1){
                message = "Le joueur au jeton jaune a gagné";
                compteurJaune++;
                strCompteurJaune = Integer.toString(compteurJaune);
                stringScoreJaune.setText(strCompteurJaune);
            }
            //affichage de la boite de dialogue "Le joueur au jeton jaune/rouge a gagné"
            JOptionPane.showMessageDialog(null, message);
            //programme stoppe jusqu'au clic ok puis:
            this.resetGrilleEtLigne();
        }
    }
    /**
     *  vérifie si un coup est gagnant à l'horizontal,
     *  à la verticale et sur les deux diagonales
     *
     * @return b booléen qui atteste qu'il y a un coup gagnant si true
     */
    public boolean estCoupGagnant(){
        boolean b = false;

        if (grilleEtLigne.isHorizontalForce4()) b=true;
        if (grilleEtLigne.isVerticalForce4()) b = true;
        if (grilleEtLigne.isDiagonaleBasGaucheToHautDroit()) b= true;
        if (grilleEtLigne.isDiagonaleBasDroitToHautGauche()) b= true;

        return b;
    }

    /**
     *
     */
    public void resetGrilleEtLigne() {
        int [] tabReset = new int[7];
        int [][] tab2DReset = new int[6][7];
        n=2;
        for(int k=0; k<7; k++){
            tabReset[k]=0;
        }
        grilleEtLigne.setLigneBoutons(tabReset);

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                tab2DReset[i][j] = 0;
                Icon blanc = new ImageIcon("blanc.GIF");
                grilleBoutons[abs((-i) * 7 + 35 + j)].setIcon(blanc);
            }
        }
        grilleEtLigne.setGrille(tab2DReset);
        bPremierAppui = false;
        this.afficheCouleur.setBackground(Color.blue);
    }

    static int power(int x, int y)
    {
        if (y == 0)
            return 1;
        else if (y % 2 == 0)
            return power(x, y / 2) * power(x, y / 2);
        else
            return x * power(x, y / 2) * power(x, y / 2);
    }

}




