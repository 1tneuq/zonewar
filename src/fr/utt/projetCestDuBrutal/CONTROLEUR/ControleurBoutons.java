package fr.utt.projetCestDuBrutal.CONTROLEUR;

import fr.utt.projetCestDuBrutal.MODELE.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
/*
 * <b>ControleurBoutons est la classe qui gere les actions relatives aux boutons quitter, sauvegarder et connexion</b>
 * <p>
 * Ce ControleurBoutons a une VueLogiciel, qu'il "ecoute", et un Modele
 *<p>
 *
 * @author Quentin LACOMBE
 * @version 1.0
 */
public class ControleurBoutons implements ActionListener{
  /**
   * Vue de l'application
   */
  /**
   * Modele de l'application
   */
  private Partie partie;
  /**
   * ControleurBoutons.
   * <p>
   * Constructeur pour les boutons quitter et sauvegarder
   * </p>
   *
   * @param view
   *            La vue dans que le contoleur ecoute
   *
   * @param model
   *            Le modele qui gere les donnees de l'application
   *
   */
  public ControleurBoutons(Partie p){
    this.partie = p;
  }
  /**
   * ControleurBoutons.
   * <p>
   * Constructeur exclusif au bouton Connexion car sur la fenetre de connexion on n'a pas encore besoin du modele
   * </p>
   *
   * @param vue
   *            La vue dans que le contoleur ecoute
   *
   */

  /**
   * Lorsque le bouton "Sauvegarder" est pressé, sauvegarde les actions effectuees par la tourelle
   * Lorsque le bouton "Quiter" est pressé, ferme l'application
   * Lorsque le bouton "Connexion" est pressé, envoie les infos ip et port au logiciel pour qu'il se connecte à la tourelle
   *
   * @param e
   *          un evenement
   *
   */
  @Override
  public void actionPerformed(ActionEvent e){
    JButton bouton = (JButton)e.getSource();
    if(bouton.getText().equals("QUITTER")){
      this.partie.quitterConfiguration();
    }else if(bouton.getText().equals("LANCER PARTIE")){
      //System.out.println("Lancer partie");
      this.partie.lancerPartie();
    }else if(bouton.getText().equals("VALIDER")){
      //System.out.println("Valider");
      this.partie.configurerCombattant(bouton);
    }else{
      //System.out.println("Combattant");
      this.partie.afficherCaracteristiques(bouton);
      //this.vue.actualiserVue("Maitre Gobi",5, 5, 15, 10, 130, true, 0, 1);
    }
  }
}
