package fr.utt.projetCestDuBrutal.VUE;

import javax.swing.*;
import java.awt.*;
import java.io.*;
/**
 * <b>PanneauBoutons est la classe qui gere le panneau contenant les boutons sauvegarder et quitter</b>
 * <p>
 *
 * @author Quentin LACOMBE
 * @version 1.0
 */
public class PanneauBoutons extends GraphPanneau{
  /**
   * Bouton quitter
   */
  private JButton quitter;
  /**
   * Bouton sauvegarder
   */
  private JButton lancer;
  /**
   * PanneauBoutons.
   * <p>
   * Ce constructeur construit le panneau qui contient les deux boutons
   * </p>
   */
  public PanneauBoutons(){
    this.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    this.quitter = this.genererBoutonQuitter();
    this.lancer = this.genererBoutonLancer();
    gbc.gridx = 1;      // la plage de cellules commence à la première colonne
    gbc.gridy = 0;      // la plage de cellules commence à la deuxième ligne
    gbc.gridwidth = 1;  // la plage de cellules englobe deux colonnes
    gbc.gridheight = 1; // la plage de cellules englobe une seule ligne
    //gbc.fill = GridBagConstraints.BOTH;     // n'occupe pas tout l'espace de la plage
    gbc.anchor = GridBagConstraints.SOUTH; // se place au centre de la plage
    gbc.weightx = 0;  // souhaite plus de largeur si possible
    gbc.weighty = 0;  // n'a pas besoin de hauteur supplémentaire
    gbc.insets = new Insets(20, 40, 20, 0);     // laisse 5 pixels de vide autour du composant
    this.add(this.quitter, gbc);
    gbc.gridx = 0;      // la plage de cellules commence à la première colonne
    gbc.gridwidth = 1;  // la plage de cellules englobe deux colonnes
    gbc.gridheight = 1; // la plage de cellules englobe une seule ligne
    //gbc.fill = GridBagConstraints.BOTH;     // n'occupe pas tout l'espace de la plage
    gbc.anchor = GridBagConstraints.SOUTH; // se place au centre de la plage
    gbc.weightx = 0;  // souhaite plus de largeur si possible
    gbc.weighty = 0;  // n'a pas besoin de hauteur supplémentaire
    gbc.insets = new Insets(20, 0, 20, 40);    // laisse 5 pixels de vide autour du composant
    this.add(this.lancer, gbc);

    this.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, this.couleurBordurePanneau));
  }
  /**
   * Renvoie le bouton quitter
   *
   * @return quitter
   *                Le bouton quitter
   */
  public JButton getBoutonQuitter(){
    return this.quitter;
  }
  /**
   * Renvoie le bouton sauvegarder
   *
   * @return quitter
   *                Le bouton lancer
   */
  public JButton getBoutonLancer(){
    return this.lancer;
  }
}
