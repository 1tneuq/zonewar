package fr.utt.projetCestDuBrutal.VUE;

import javax.swing.*;
import java.awt.*;
import java.io.*;
/**
 * <b>PanneauTitre est la classe qui gere le titre de l'application</b>
 * <p>
 *
 * @author Quentin LACOMBE
 * @version 1.0
 */
public class PanneauJoueur extends GraphPanneau{

  private JLabel joueur;

  private JLabel programme;

  private Choice choixProgramme;

  private JTextField nomJoueur;
  /**
   * PanneauTitre.
   * <p>
   * Ce constructeur construit le panneau du titre
   * </p>
   */
  public PanneauJoueur(int joueur){
    this.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();

    this.joueur = new JLabel("Joueur " + joueur + ": ");
    this.joueur.setFont(this.policeMontserratRegular);
    this.joueur.setFont(this.joueur.getFont().deriveFont(this.joueur.getFont().getSize() * 1.4F));
    c.fill = GridBagConstraints.BOTH;
    c.weightx = 0;
    c.weighty = 1;
    c.gridx = 0;
    c.gridwidth = 1;
    c.insets = new Insets(10, 0, 10, 0);
    this.add(this.joueur, c);

    this.nomJoueur = new JTextField("nom du joueur");
    this.nomJoueur.setFont(this.joueur.getFont());
    this.nomJoueur.setBackground(this.couleurFond);
    this.nomJoueur.setForeground(this.couleurTexte);
    this.nomJoueur.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(220,220, 220)));
    c.weightx = 0;
    c.weighty = 1;
    c.gridx = 1;
    c.insets = new Insets(0, 0, 0, 25);
    this.add(this.nomJoueur,c);

    this.programme = new JLabel("Programme: ");
    this.programme.setFont(this.joueur.getFont());
    c.weightx = 0;
    c.weighty = 1;
    c.gridx = 2;
    c.insets = new Insets(0, 25, 0, 0);
    this.add(this.programme,c);

    this.choixProgramme = new Choice();
		this.choixProgramme.setFont(this.joueur.getFont());
		this.choixProgramme.add("BDE");
    this.choixProgramme.add("ISI");
		this.choixProgramme.add("GM");
		this.choixProgramme.add("A2I");
		this.choixProgramme.add("RT");
		this.choixProgramme.add("MTE");
		this.choixProgramme.add("GI");
		this.choixProgramme.add("MM");
    c.weightx = 0;
    c.weighty = 1;
    c.gridx = 3;
    c.insets = new Insets(0, 0, 0, 0);
		this.add(this.choixProgramme,c);

    this.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, this.couleurBordurePanneau));
  }

  public JTextField getNomJoueur(){
    return this.nomJoueur;
  }

  public Choice getProgramme(){
    return this.choixProgramme;
  }
}
