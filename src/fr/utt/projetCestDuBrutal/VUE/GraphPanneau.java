package fr.utt.projetCestDuBrutal.VUE;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URL;
/**
 * <b>GraphPanneau est la classe dont tous les 5 panneaux de la vue vont heriter
 * et qui définit leur aspect graphique</b>
 * <p>
 * Cette classe est "constituee" de 4 couleurs et d'une police.
 * <p>
 *
 * @author Quentin LACOMBE
 * @version 1.0
 */
public abstract class GraphPanneau extends JPanel{
  /**
   * Couleur des boutons verts
   */
  protected Color couleurBoutonVert;
  /**
   * Couleur des boutons marrons/rouges
   */
  protected Color couleurBoutonMarron;
  /**
   * Couleur des textes preremplis dans les champs, soit du gris clair
   */
  protected Color couleurTexte;
  /**
   * Couleur des bordures des panneux, soit du gris fonce
   */
  protected Color couleurBordurePanneau;
  /**
   * Couleur du fond, soit un blanc un peu grise
   */
  protected Color couleurFond;
  /**
   * Police Montserrat Regular
   */
  protected Color couleurSoldats;

  protected Font policeMontserratRegular;
  /**
   * Url qui contient la police Montserrat Regular
   */
  protected URL fontUrl;
  /**
   * GraphPanneau.
   * <p>
   * Ce constructeur construit le patern de base des 4 panneuax de la vue.
   * Tout ce qui touche a leur esthetique est configure ici.
   * </p>
   */
  public GraphPanneau(){
    //Les couleurs
    this.couleurBoutonMarron = new Color(147, 102, 57);
    this.couleurBoutonVert = new Color(115, 124, 81);
    this.couleurTexte = new Color(128, 128, 128);
    this.couleurSoldats = new Color(148, 148, 55);
    this.couleurBordurePanneau = new Color(105, 105, 105);
    this.couleurFond = new Color(238,238,238);
    //La police Montserrat
    try{
      //this.fichierPoliceMontserratRegular = new File("../../../../../res/polices/Montserrat-Regular.ttf");
      this.fontUrl = new URL("https://dwarves.iut-fbleau.fr/~lacombe/Police/Montserrat-Regular.ttf");
      this.policeMontserratRegular = Font.createFont(Font.TRUETYPE_FONT, this.fontUrl.openStream());
      this.policeMontserratRegular = this.policeMontserratRegular.deriveFont(15.f);
    } catch(Exception exceptionPolice){
      exceptionPolice.printStackTrace();
    }
  }

  public JButton genererBoutonSoldat(int numero){
    JButton bouton = new JButton();
    bouton.setToolTipText("" + numero);
    bouton.setFont(this.policeMontserratRegular);
    bouton.setBorderPainted(false);
    bouton.setBackground(this.couleurFond);
    bouton.setFocusPainted(false);
    bouton.setPreferredSize(new Dimension(60, 110));
    return bouton;
  }

  public JButton genererBoutonQuitter(){
    JButton bouton = new JButton("QUITTER");
    bouton.setFont(this.policeMontserratRegular);
    bouton.setBorderPainted(false);
    bouton.setBackground(this.couleurBoutonMarron);
    bouton.setForeground(this.couleurFond);
    bouton.setFocusPainted(false);
    bouton.setPreferredSize(new Dimension(240, 50));
    return bouton;
  }

  public JButton genererBoutonLancer(){
    JButton bouton = new JButton("LANCER PARTIE");
    bouton.setFont(this.policeMontserratRegular);
    bouton.setBorderPainted(false);
    bouton.setBackground(this.couleurSoldats);
    bouton.setForeground(this.couleurFond);
    bouton.setFocusPainted(false);
    bouton.setPreferredSize(new Dimension(240, 50));
    return bouton;
  }

  public JButton genererBoutonValider(){
    JButton bouton = new JButton("VALIDER");
    bouton.setFont(this.policeMontserratRegular);
    bouton.setBorderPainted(false);
    bouton.setBackground(this.couleurSoldats);
    bouton.setForeground(this.couleurFond);
    bouton.setFocusPainted(false);
    bouton.setPreferredSize(new Dimension(140, 45));
    return bouton;
  }

  public JCheckBox genererCheckbox(boolean coche){
    JCheckBox cb =  new JCheckBox("Réserviste", coche);
    cb.setBackground(this.couleurFond);
    return cb;
  }
}
