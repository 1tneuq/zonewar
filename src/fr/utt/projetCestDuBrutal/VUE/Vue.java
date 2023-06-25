package fr.utt.projetCestDuBrutal.VUE;

import fr.utt.projetCestDuBrutal.CONTROLEUR.*;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Vue extends JFrame{
  /**
   * Le gestionnaire
   */
  private GridBagConstraints gbc;
  /**
   * Le panneau pricipal qui se substitue à la fenetre
   */
  private JPanel panneauPrincipal;
  /**
   * Le panneau du titre
   */

  private PanneauJoueur panneauJoueur;

  private PanneauTroupes panneauTroupes;

  private PanneauConfiguration panneauConfiguration;

  private PanneauBoutons panneauBoutons;

  private ControleurBoutons controleBoutons;

  public Vue(int numeroJoueur, ControleurBoutons controleur){
    super("C'est du brutal !");
    this.setSize(1100, 800);
    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
    int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
    this.setLocation(x, y);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.panneauPrincipal = new JPanel(new GridBagLayout());
    this.gbc = new GridBagConstraints();

    this.panneauJoueur = new PanneauJoueur(numeroJoueur);
    this.panneauBoutons = new PanneauBoutons();
    this.panneauTroupes=new PanneauTroupes();
    this.panneauConfiguration = new PanneauConfiguration("En attente de sélection de combattant...",0, 0, 0, 0, 0, 400, false, 0, 0);

    gbc.gridx = 0;      // la plage de cellules commence à la première colonne
    gbc.gridy = 0;      // la plage de cellules commence à la deuxième ligne
    gbc.gridwidth = 2;  // la plage de cellules englobe deux colonnes
    gbc.gridheight = 1; // la plage de cellules englobe une seule ligne
    gbc.fill = GridBagConstraints.BOTH;     // n'occupe pas tout l'espace de la plage
    gbc.anchor = GridBagConstraints.NORTHWEST; // se place au centre de la plage
    gbc.weightx = 1.0;  // souhaite plus de largeur si possible
    gbc.weighty = 0;  // n'a pas besoin de hauteur supplémentaire
    gbc.insets = new Insets(10, 10, 0, 10); // laisse 5 pixels de vide autour du composant
    this.panneauPrincipal.add(this.panneauJoueur, gbc);

    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.gridwidth = 1;
    gbc.gridheight = 2;
    gbc.fill = GridBagConstraints.BOTH;
    gbc.anchor = GridBagConstraints.NORTHWEST;
    gbc.weightx = 1.0;
    gbc.weighty = 1.0;
    gbc.insets = new Insets(10, 10, 10, 5);    // laisse 5 pixels de vide autour du composant
    this.panneauPrincipal.add(this.panneauTroupes, gbc);

    gbc.gridx = 1;
    gbc.gridy = 2;
    gbc.gridwidth = 1;
    gbc.gridheight = 2;
    gbc.fill = GridBagConstraints.BOTH;
    gbc.anchor = GridBagConstraints.NORTHWEST;
    gbc.weightx = 1.0;  // souhaite plus de largeur si possible
    gbc.weighty = 1.0;  // n'a pas besoin de hauteur supplémentaire
    gbc.insets = new Insets(10,5 ,10,10);
    this.panneauPrincipal.add(this.panneauConfiguration, gbc);

    gbc.gridx = 0;
    gbc.gridy = 3;
    gbc.gridwidth = 2;
    gbc.gridheight = 2;
    gbc.fill = GridBagConstraints.BOTH;
    gbc.anchor = GridBagConstraints.NORTHWEST;
    gbc.weightx = 1.0;  // souhaite plus de largeur si possible
    gbc.weighty = 0;  // n'a pas besoin de hauteur supplémentaire
    gbc.insets = new Insets(0, 10, 10, 10);
    this.panneauPrincipal.add(this.panneauBoutons, gbc);

    this.add(this.panneauPrincipal);
    //A mettre dans le main
    this.setVisible(true);
    this.setResizable(false);

    this.controleBoutons = controleur;
    this.panneauBoutons.getBoutonLancer().addActionListener(this.controleBoutons);
    this.panneauBoutons.getBoutonQuitter().addActionListener(this.controleBoutons);
    this.panneauConfiguration.getBoutonValider().addActionListener(this.controleBoutons);
    Iterator<JButton> iterateur = this.panneauTroupes.getBoutonsSoldats().iterator();
    while(iterateur.hasNext()){
      iterateur.next().addActionListener(this.controleBoutons);
    }
  }

  public void actualiserVue(boolean estConfigure, String type, int force, int dext, int resis,int consti, int init, int points, boolean reserv, int strat, int affect){
    //this.panneauPrincipal.removeAll();
    //this.panneauPrincipal.repaint();
    this.panneauConfiguration.getBoutonValider().removeActionListener(this.controleBoutons);
    this.panneauConfiguration.actualiserPanneau(estConfigure, type, force,dext, resis, consti, init, points, reserv, strat, affect);
    this.panneauConfiguration.getBoutonValider().addActionListener(this.controleBoutons);
    //this.panneauPrincipal.revalidate();
  }

  public void actualiserTroupes(int etudiant){
    this.panneauTroupes.actualiser(etudiant);
  }

  public PanneauConfiguration getPanneauConfiguration(){
    return this.panneauConfiguration;
  }

  public PanneauTroupes getPanneauTroupes(){
    return this.panneauTroupes;
  }

  public PanneauJoueur getPanneauJoueur(){
    return this.panneauJoueur;
  }
  /*public static void main(String[] args) {
    (new Vue()).setVisible(true);
  }*/
}
