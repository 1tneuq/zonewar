package fr.utt.projetCestDuBrutal.VUE;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class PanneauConfiguration extends GraphPanneau{
  private Choice affectation;
  private Choice strategie;
  private JLabel points;
  private JLabel force;
  private JLabel dexterite;
  private JLabel resistance;
  private JLabel constitution;
  private JLabel initiative;
  private JLabel ouAffecter;
  private JLabel quelleStartegie;
  private JCheckBox reserviste;
  private JTextField ajouterForce;
  private JTextField ajouterDexterite;
  private JTextField ajouterResistance;
  private JTextField ajouterConstitution;
  private JTextField ajouterInitiative;
  private JButton boutonValider;
  private boolean premierAffichage;

  public PanneauConfiguration(String type, int f, int d, int r, int c, int i, int p, boolean res, int s, int a){
    this.premierAffichage = true;
    this.initialisationComposants(type,f,d,r,c,i,p,res,s,a);
    this.ajoutComposants();
    this.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, this.couleurBordurePanneau));
  }

  public void initialisationComposants(String type, int f, int d, int r, int c, int i, int p, boolean res, int s, int a){
    this.boutonValider = genererBoutonValider();
    if(premierAffichage){
      this.boutonValider.setEnabled(false);
    }
    this.points = new JLabel(type + "  |  Points à distribuer: [" + p + "]");
    this.points.setFont(this.policeMontserratRegular);
    this.points.setFont(this.points.getFont().deriveFont(this.points.getFont().getSize() * 1.4F));
    this.force = new JLabel("Force actuelle: [" + f + "], ajouter: ");
    this.force.setFont(this.points.getFont());
    this.dexterite = new JLabel("Dexterite actuelle: [" + d + "], ajouter: ");
    this.dexterite.setFont(this.points.getFont());
    this.resistance = new JLabel("Resistance actuelle: [" + r + "], ajouter: ");
    this.resistance.setFont(this.points.getFont());
    this.constitution= new JLabel("Constitution actuelle: [" + c + "], ajouter: ");
    this.constitution.setFont(this.points.getFont());
    this.initiative = new JLabel("Initiative actuelle: [" + i + "], ajouter: ");
    this.initiative.setFont(this.points.getFont());
    this.reserviste = this.genererCheckbox(res);
    this.reserviste.setFont(this.points.getFont());
    this.ouAffecter = new JLabel("Zone d'affectation: ");
    this.ouAffecter.setFont(this.points.getFont());
    this.quelleStartegie = new JLabel("Strategie de combat: ");
    this.quelleStartegie.setFont(this.points.getFont());

    this.ajouterForce = new JTextField("00");
    this.ajouterForce.setFont(this.points.getFont());
    this.ajouterForce.setBackground(this.couleurFond);
    this.ajouterForce.setForeground(this.couleurTexte);
    this.ajouterForce.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(220,220, 220)));

    this.ajouterDexterite = new JTextField("00");
    this.ajouterDexterite.setFont(this.points.getFont());
    this.ajouterDexterite.setBackground(this.couleurFond);
    this.ajouterDexterite.setForeground(this.couleurTexte);
    this.ajouterDexterite.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(220,220, 220)));

    this.ajouterResistance = new JTextField("00");
    this.ajouterResistance.setFont(this.points.getFont());
    this.ajouterResistance.setBackground(this.couleurFond);
    this.ajouterResistance.setForeground(this.couleurTexte);
    this.ajouterResistance.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(220,220, 220)));

    this.ajouterConstitution = new JTextField("00");
    this.ajouterConstitution.setFont(this.points.getFont());
    this.ajouterConstitution.setBackground(this.couleurFond);
    this.ajouterConstitution.setForeground(this.couleurTexte);
    this.ajouterConstitution.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(220,220, 220)));

    this.ajouterInitiative = new JTextField("00");
    this.ajouterInitiative.setFont(this.points.getFont());
    this.ajouterInitiative.setBackground(this.couleurFond);
    this.ajouterInitiative.setForeground(this.couleurTexte);
    this.ajouterInitiative.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(220,220, 220)));

    this.affectation = new Choice();
    this.affectation.setFont(this.points.getFont());
    this.affectation.add("BDE");
		this.affectation.add("Bibliothèque");
		this.affectation.add("Quartier administratif");
		this.affectation.add("Halle industrielle");
		this.affectation.add("Halle sportive");
    this.affectation.select(a);

    this.strategie = new Choice();
    this.strategie.setFont(this.points.getFont());
    this.strategie.add("Offensive");
    this.strategie.add("Défensive");
    this.strategie.add("Aléatoire");
    this.strategie.select(s);
  }

  public void ajoutComposants(){
    this.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();

    gbc.fill = GridBagConstraints.VERTICAL;
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weightx = 1;
    gbc.weighty = 1;
    gbc.gridwidth = 2;
    gbc.gridheight = 1;
    this.add(this.points, gbc);

    gbc.fill = GridBagConstraints.BOTH;
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.weightx = 1;
    gbc.weighty = 1;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.insets = new Insets(0, 10, 0, 0);
    this.add(this.force, gbc);

    gbc.fill = GridBagConstraints.VERTICAL;
    gbc.gridx = 1;
    gbc.gridy = 1;
    gbc.weightx = 1;
    gbc.weighty = 1;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.insets = new Insets(0, 0, 0, 0);
    this.add(this.ajouterForce, gbc);

    gbc.fill = GridBagConstraints.BOTH;
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.weightx = 1;
    gbc.weighty = 1;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.insets = new Insets(0, 10, 0, 0);
    this.add(this.dexterite, gbc);

    gbc.fill = GridBagConstraints.VERTICAL;
    gbc.gridx = 1;
    gbc.gridy = 2;
    gbc.weightx = 1;
    gbc.weighty = 1;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.insets = new Insets(0, 0, 0, 0);
    this.add(this.ajouterDexterite, gbc);

    gbc.fill = GridBagConstraints.BOTH;
    gbc.gridx = 0;
    gbc.gridy = 3;
    gbc.weightx = 1;
    gbc.weighty = 1;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.insets = new Insets(0, 10, 0, 0);
    this.add(this.resistance,gbc);

    gbc.fill = GridBagConstraints.VERTICAL;
    gbc.gridx = 1;
    gbc.gridy = 3;
    gbc.weightx = 1;
    gbc.weighty = 1;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.insets = new Insets(0, 0, 0, 0);
    this.add(this.ajouterResistance, gbc);

    gbc.fill = GridBagConstraints.BOTH;
    gbc.gridx = 0;
    gbc.gridy = 4;
    gbc.weightx = 1;
    gbc.weighty = 1;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.insets = new Insets(0, 10, 0, 0);
    this.add(this.constitution,gbc);

    gbc.fill = GridBagConstraints.VERTICAL;
    gbc.gridx = 1;
    gbc.gridy = 4;
    gbc.weightx = 1;
    gbc.weighty = 1;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.insets = new Insets(0, 0, 0, 0);
    this.add(this.ajouterConstitution, gbc);

    gbc.fill = GridBagConstraints.BOTH;
    gbc.gridx = 0;
    gbc.gridy = 5;
    gbc.weightx = 1;
    gbc.weighty = 1;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.insets = new Insets(0, 10, 0, 0);
    this.add(this.initiative, gbc);

    gbc.fill = GridBagConstraints.VERTICAL;
    gbc.gridx = 1;
    gbc.gridy = 5;
    gbc.weightx = 1;
    gbc.weighty = 1;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.insets = new Insets(0, 0, 0, 0);
    this.add(this.ajouterInitiative, gbc);

    /*gbc.fill = GridBagConstraints.BOTH;
    gbc.gridx = 0;
    gbc.gridy = 6;
    gbc.weightx = 1;
    gbc.weighty = 1;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.insets = new Insets(0, 10, 0, 0);
    this.add(this.ouAffecter, gbc);*/

    /*gbc.fill = GridBagConstraints.VERTICAL;
    gbc.gridx = 1;
    gbc.gridy = 6;
    gbc.weightx = 1;
    gbc.weighty = 1;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.insets = new Insets(0, 0, 0, 0);
    this.add(this.affectation, gbc);*/

    gbc.fill = GridBagConstraints.BOTH;
    gbc.gridx = 0;
    gbc.gridy = 6;
    gbc.weightx = 1;
    gbc.weighty = 1;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.insets = new Insets(0, 10, 0, 0);
    this.add(this.quelleStartegie, gbc);

    gbc.fill = GridBagConstraints.VERTICAL;
    gbc.gridx = 1;
    gbc.gridy = 6;
    gbc.weightx = 1;
    gbc.weighty = 1;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.insets = new Insets(0, 0, 0, 0);
    this.add(this.strategie, gbc);

    gbc.fill = GridBagConstraints.NONE;
    gbc.gridx = 0;
    gbc.gridy = 7;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.gridwidth = 2;
    gbc.gridheight = 1;
    //gbc.insets = new Insets(0, 0, 0, 30);
    this.add(this.reserviste, gbc);

    gbc.gridx = 0;
    gbc.gridy = 8;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.gridwidth = 2;
    gbc.gridheight = 1;
    gbc.insets = new Insets(15, 0, 15, 0);
    this.add(this.boutonValider, gbc);
  }

  public void actualiserPanneau(boolean estConfigure, String type, int f, int d, int r,int c, int i, int p, boolean res, int s, int a){
    this.premierAffichage = false;
    this.removeAll();
    this.revalidate();
    this.repaint();
    this.initialisationComposants(type,f,d,r,c,i,p,res,s,a);
    this.ajoutComposants();
    if(estConfigure){
      this.griserPanneau();
    }
    this.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, this.couleurBordurePanneau));
  }

  public void griserPanneau() {
    this.setEnabled(false);
    for(Component c : this.getComponents()){
      c.setEnabled(false);
    }
  }

  public JButton getBoutonValider(){
    return this.boutonValider;
  }

  public Choice getAffectation() {
    return this.affectation;
  }

  public Choice getStrategie() {
    return this.strategie;
  }

  public JCheckBox getReserviste() {
    return this.reserviste;
  }

  public JTextField getAjouterForce() {
    return this.ajouterForce;
  }

  public JTextField getAjouterDexterite() {
    return this.ajouterDexterite;
  }

  public JTextField getAjouterResistance() {
    return this.ajouterResistance;
  }

  public JTextField getAjouterConstitution() {
    return this.ajouterConstitution;
  }


  public JTextField getAjouterInitiative() {
    return this.ajouterInitiative;
  }
}
