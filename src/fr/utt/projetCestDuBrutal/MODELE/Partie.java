package fr.utt.projetCestDuBrutal.MODELE;

import fr.utt.projetCestDuBrutal.VUE.*;
import fr.utt.projetCestDuBrutal.CONTROLEUR.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JButton;
import java.lang.Integer;
import javax.swing.JOptionPane;
import java.awt.Color;

public class Partie {

    private static Partie referenceInstance;

    private Joueur joueur1;

    private Joueur joueur2;

    private Plateau plateau;

    private Vue vue1;

    private Vue vue2;

    private int quelEtudiant;

    private int quelJoueur;

    private Partie(Programme programmeJoueur1, Programme programmeJoueur2, String nom1, String nom2) {
      this.joueur1 = new Joueur(1, programmeJoueur1, nom1);
      this.joueur2 = new Joueur(2, programmeJoueur2, nom2);
      this.quelJoueur = 1;
      this.initialiserVue();
    }

    public void jouer() {
      ArrayList<ZoneInfluence> zonesControlees = new ArrayList<ZoneInfluence>();
      boolean flag = false;
      while(!this.joueur1.aGagne(this.plateau.getZonesInfluence()) && !this.joueur2.aGagne(this.plateau.getZonesInfluence())){
        flag = this.plateau.melee(zonesControlees);
        if(flag == false){
          try{
            this.plateau.treveEtMouvement();
          }catch(IOException e){
            e.printStackTrace();
          }
        }
      }
      Iterator<ZoneInfluence> ite = this.plateau.getZonesInfluence().iterator();
      while(ite.hasNext()){
        ZoneInfluence zone = ite.next();
        System.out.println("\nZone " + zone.getNom() + ": [" + zone.getEtudiants1().size() + " etudiants joueur 1] " + "[" + zone.getEtudiants2().size() + " etudiants joueur 2] " );
      }
      if(this.joueur1.aGagne(this.plateau.getZonesInfluence())){
        System.out.println("\n\nLE JOUEUR " + this.joueur1.getNom() + " A GAGNE LA PARTIE !");
      }else{
        System.out.println("\n\nLE JOUEUR " + this.joueur2.getNom() + " A GAGNE LA PARTIE !");
      }
    }

    public static Partie getInstance(Programme programmeJoueur1, Programme programmeJoueur2, String nom1, String nom2) {
      /*if(programmeJoueur1 == programmeJoueur2){
        System.out.println("Erreur: les programmes des deux joueurs doivent être différents");
        System.exit(0);
      }*/
      if(referenceInstance == null){
        referenceInstance = new Partie(programmeJoueur1, programmeJoueur2, nom1, nom2);
      }
      return referenceInstance;
    }

    public void configurerPartie(){
      //this.joueur1.parametrer();
      //this.joueur2.parametrer();
      this.joueur1.parametrageAuto();
      this.joueur2.parametrageAuto();
      this.plateau = new Plateau(joueur1.getReservistes(), joueur2.getReservistes());

      //this.joueur1.repartir(this.plateau.getZonesInfluence());
      //this.joueur2.repartir(this.plateau.getZonesInfluence());
      this.joueur1.repartitionAuto(this.plateau.getZonesInfluence());
      this.joueur2.repartitionAuto(this.plateau.getZonesInfluence());
    }

    public void afficherCaracteristiques(JButton bouton){
      Joueur joueur;
      Vue vue;
      if(this.quelJoueur == 1){
        joueur = this.joueur1;
        vue = this.vue1;
      }else{
        joueur = this.joueur2;
        vue = this.vue2;
      }
      int numero = 20;
      Iterator<JButton> iterateur = vue.getPanneauTroupes().getBoutonsSoldats().iterator();
      while(iterateur.hasNext()){
        if(iterateur.next().getToolTipText() == bouton.getToolTipText()){
          numero = Integer.parseInt(bouton.getToolTipText());
        }
      }

      for(int i=0; i<20; i++){
        if(i == numero){
          this.quelEtudiant = i;
          Etudiant etu = joueur.getEtudiants().get(i);
          vue.actualiserVue(etu.getConfigure(), etu.getRole(), etu.getForce(), etu.getDexterite(), etu.getResistance(), etu.getConstitution(),  etu.getInitiative(), joueur1.getPoints(), etu.getReserviste(), etu.getNumStrategie(), 2);
        }
      }
    }

    public void configurerCombattant(JButton bouton){
      Joueur joueur;
      Vue vue;
      if(this.quelJoueur == 1){
        joueur = this.joueur1;
        vue = this.vue1;
      }else{
        joueur = this.joueur2;
        vue = this.vue2;
      }
      for(int i=0; i<20; i++){
        if(i == this.quelEtudiant){
          Etudiant etu = joueur.getEtudiants().get(i);
          if(Joueur.estUnNombre(vue.getPanneauConfiguration().getAjouterForce().getText())
          && Joueur.estUnNombre(vue.getPanneauConfiguration().getAjouterDexterite().getText())
          && Joueur.estUnNombre(vue.getPanneauConfiguration().getAjouterResistance().getText())
          && Joueur.estUnNombre(vue.getPanneauConfiguration().getAjouterConstitution().getText())
          && Joueur.estUnNombre(vue.getPanneauConfiguration().getAjouterInitiative().getText())){
            if(Integer.parseInt(vue.getPanneauConfiguration().getAjouterForce().getText()) >= 0
            && Integer.parseInt(vue.getPanneauConfiguration().getAjouterDexterite().getText()) >= 0
            && Integer.parseInt(vue.getPanneauConfiguration().getAjouterResistance().getText()) >= 0
            && Integer.parseInt(vue.getPanneauConfiguration().getAjouterInitiative().getText()) >= 0
            && Integer.parseInt(vue.getPanneauConfiguration().getAjouterConstitution().getText()) >= 0){
              if((joueur.getPoints() - Integer.parseInt(vue.getPanneauConfiguration().getAjouterForce().getText())
              - Integer.parseInt(vue.getPanneauConfiguration().getAjouterDexterite().getText())
              - Integer.parseInt(vue.getPanneauConfiguration().getAjouterResistance().getText())
              - Integer.parseInt(vue.getPanneauConfiguration().getAjouterInitiative().getText())
              - Integer.parseInt(vue.getPanneauConfiguration().getAjouterConstitution().getText())) >= 0){
                if(etu.getForce() + Integer.parseInt(vue.getPanneauConfiguration().getAjouterForce().getText()) <= 10
                && etu.getDexterite() + Integer.parseInt(vue.getPanneauConfiguration().getAjouterDexterite().getText()) <= 10
                && etu.getResistance() + Integer.parseInt(vue.getPanneauConfiguration().getAjouterResistance().getText()) <= 10
                && etu.getConstitution() + Integer.parseInt(vue.getPanneauConfiguration().getAjouterConstitution().getText()) <= 30
                && etu.getInitiative() + Integer.parseInt(vue.getPanneauConfiguration().getAjouterInitiative().getText()) <= 10){
                  if(joueur.getReservistes().size() < 5){
                    if(joueur.getNombreEtudiantsConfigures() < 15){
                      //
                      etu.setForce(Integer.parseInt(vue.getPanneauConfiguration().getAjouterForce().getText()));
                      joueur.setPoints(joueur.getPoints() - Integer.parseInt(vue.getPanneauConfiguration().getAjouterForce().getText()));
                      etu.setDexterite(Integer.parseInt(vue.getPanneauConfiguration().getAjouterDexterite().getText()));
                      joueur.setPoints(joueur.getPoints() - Integer.parseInt(vue.getPanneauConfiguration().getAjouterDexterite().getText()));
                      etu.setResistance(Integer.parseInt(vue.getPanneauConfiguration().getAjouterResistance().getText()));
                      joueur.setPoints(joueur.getPoints() - Integer.parseInt(vue.getPanneauConfiguration().getAjouterResistance().getText()));
                      etu.setConsitution(Integer.parseInt(vue.getPanneauConfiguration().getAjouterConstitution().getText()));
                      joueur.setPoints(joueur.getPoints() - Integer.parseInt(vue.getPanneauConfiguration().getAjouterConstitution().getText()));
                      etu.setInitiative(Integer.parseInt(vue.getPanneauConfiguration().getAjouterInitiative().getText()));
                      joueur.setPoints(joueur.getPoints() - Integer.parseInt(vue.getPanneauConfiguration().getAjouterInitiative().getText()));
                      etu.setStrategie(vue.getPanneauConfiguration().getStrategie().getSelectedIndex());
                      etu.setReserviste(vue.getPanneauConfiguration().getReserviste().isSelected());
                      etu.setConfigure(true);
                      vue.actualiserVue(etu.getConfigure(), etu.getRole(), etu.getForce(), etu.getDexterite(), etu.getResistance(), etu.getConstitution(), etu.getInitiative(), joueur.getPoints(), etu.getReserviste(), etu.getNumStrategie(), 2);
                      System.out.println(etu);
                      this.validerCombattant();
                    }else if(joueur.getReservistes().size() < 20 - joueur.getNombreEtudiantsConfigures()){
                      if(vue.getPanneauConfiguration().getReserviste().isSelected()){
                        etu.setForce(Integer.parseInt(vue.getPanneauConfiguration().getAjouterForce().getText()));
                        joueur.setPoints(joueur.getPoints() - Integer.parseInt(vue.getPanneauConfiguration().getAjouterForce().getText()));
                        etu.setDexterite(Integer.parseInt(vue.getPanneauConfiguration().getAjouterDexterite().getText()));
                        joueur.setPoints(joueur.getPoints() - Integer.parseInt(vue.getPanneauConfiguration().getAjouterDexterite().getText()));
                        etu.setResistance(Integer.parseInt(vue.getPanneauConfiguration().getAjouterResistance().getText()));
                        joueur.setPoints(joueur.getPoints() - Integer.parseInt(vue.getPanneauConfiguration().getAjouterResistance().getText()));
                        etu.setConsitution(Integer.parseInt(vue.getPanneauConfiguration().getAjouterConstitution().getText()));
                        joueur.setPoints(joueur.getPoints() - Integer.parseInt(vue.getPanneauConfiguration().getAjouterConstitution().getText()));
                        etu.setInitiative(Integer.parseInt(vue.getPanneauConfiguration().getAjouterInitiative().getText()));
                        joueur.setPoints(joueur.getPoints() - Integer.parseInt(vue.getPanneauConfiguration().getAjouterInitiative().getText()));
                        etu.setStrategie(vue.getPanneauConfiguration().getStrategie().getSelectedIndex());
                        etu.setReserviste(vue.getPanneauConfiguration().getReserviste().isSelected());
                        etu.setConfigure(true);
                        vue.actualiserVue(etu.getConfigure(), etu.getRole(), etu.getForce(), etu.getDexterite(), etu.getResistance(), etu.getConstitution(), etu.getInitiative(), joueur.getPoints(), etu.getReserviste(), etu.getNumStrategie(), 2);
                        System.out.println(etu);
                        this.validerCombattant();
                      }else{
                        JOptionPane.showMessageDialog(vue, "Erreur: tous les combattants restants doivent être réservistes !");
                      }
                    }
                  }else{
                    if(vue.getPanneauConfiguration().getReserviste().isSelected()){
                      JOptionPane.showMessageDialog(vue, "Erreur: il ne peut y avoir que 5 réservistes !");
                    }else{
                      etu.setForce(Integer.parseInt(vue.getPanneauConfiguration().getAjouterForce().getText()));
                      joueur.setPoints(joueur.getPoints() - Integer.parseInt(vue.getPanneauConfiguration().getAjouterForce().getText()));
                      etu.setDexterite(Integer.parseInt(vue.getPanneauConfiguration().getAjouterDexterite().getText()));
                      joueur.setPoints(joueur.getPoints() - Integer.parseInt(vue.getPanneauConfiguration().getAjouterDexterite().getText()));
                      etu.setResistance(Integer.parseInt(vue.getPanneauConfiguration().getAjouterResistance().getText()));
                      joueur.setPoints(joueur.getPoints() - Integer.parseInt(vue.getPanneauConfiguration().getAjouterResistance().getText()));
                      etu.setConsitution(Integer.parseInt(vue.getPanneauConfiguration().getAjouterConstitution().getText()));
                      joueur.setPoints(joueur.getPoints() - Integer.parseInt(vue.getPanneauConfiguration().getAjouterConstitution().getText()));
                      etu.setInitiative(Integer.parseInt(vue.getPanneauConfiguration().getAjouterInitiative().getText()));
                      joueur.setPoints(joueur.getPoints() - Integer.parseInt(vue.getPanneauConfiguration().getAjouterInitiative().getText()));
                      etu.setStrategie(vue.getPanneauConfiguration().getStrategie().getSelectedIndex());
                      etu.setReserviste(vue.getPanneauConfiguration().getReserviste().isSelected());
                      etu.setConfigure(true);
                      vue.actualiserVue(etu.getConfigure(), etu.getRole(), etu.getForce(), etu.getDexterite(), etu.getResistance(), etu.getConstitution(), etu.getInitiative(), joueur.getPoints(), etu.getReserviste(), etu.getNumStrategie(), 2);
                      System.out.println(etu);
                      this.validerCombattant();
                    }
                  }
                }else{
                  JOptionPane.showMessageDialog(vue, "Erreur: Force>10 ou Dextérité>10 ou Resistance>10 ou Constitution>30 ou Initiative>10");
                }
              }else{
                JOptionPane.showMessageDialog(vue, "Erreur: le nombre de points ne doit pas être dépassé !");
              }
            }else{
              JOptionPane.showMessageDialog(vue, "Erreur: l'utilisateur doit entrer un nombre positif !");
            }
          }else{
            JOptionPane.showMessageDialog(vue, "Erreur: l'utilisateur doit entrer un nombre !");
          }
        }
      }
    }

    public void quitterConfiguration(){
      if(this.quelJoueur == 1){
        this.vue1.setVisible(false);
        this.vue1.dispose();
      }else{
        this.vue2.setVisible(false);
        this.vue2.dispose();
      }
    }

    public int getNumJoueur(){
      return this.quelJoueur;
    }

    public void changerNumJoueur(){
      if(this.quelJoueur == 1){
        this.quelJoueur = 2;
      }else{
        this.quelJoueur = 1;
      }
    }

    public void validerCombattant(){
      if(this.quelJoueur == 1){
        this.vue1.actualiserTroupes(this.quelEtudiant);
      }else{
        this.vue2.actualiserTroupes(this.quelEtudiant);
      }
    }

    public void initialiserVue(){
      if(this.quelJoueur == 1){
        this.vue1 = new Vue(1, new ControleurBoutons(this));
      }else{
        this.vue2 = new Vue(2, new ControleurBoutons(this));
      }
    }

    public void lancerPartie(){
      if((this.quelJoueur == 1 && this.joueur1.getNombreEtudiantsConfigures() == 20) || (this.quelJoueur == 2 && this.joueur2.getNombreEtudiantsConfigures() == 20)){
        if(this.quelJoueur == 1){
          this.joueur1.setNom(this.vue1.getPanneauJoueur().getNomJoueur().getText());
          this.quitterConfiguration();
          this.quelJoueur++;
          this.initialiserVue();
        }else{
          this.joueur2.setNom(this.vue2.getPanneauJoueur().getNomJoueur().getText());
          this.quitterConfiguration();
          this.plateau = new Plateau(joueur1.getReservistes(), joueur2.getReservistes());
          //this.joueur1.repartir(this.plateau.getZonesInfluence());
          //this.joueur2.repartir(this.plateau.getZonesInfluence());
          this.joueur1.repartitionAuto(this.plateau.getZonesInfluence());
          this.joueur2.repartitionAuto(this.plateau.getZonesInfluence());
          this.jouer();
        }
      }else{
        if(this.quelJoueur == 1){
          JOptionPane.showMessageDialog(this.vue1, "Erreur: tous les combattants ne sont pas configurés !");
        }else{
          JOptionPane.showMessageDialog(this.vue2, "Erreur: tous les combattants ne sont pas configurés !");
        }
      }
    }
}
