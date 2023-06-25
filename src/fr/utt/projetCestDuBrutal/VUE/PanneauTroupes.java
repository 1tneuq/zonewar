package fr.utt.projetCestDuBrutal.VUE;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Iterator;

public class PanneauTroupes extends GraphPanneau{
  public ArrayList<JButton> soldats;

  public PanneauTroupes(){
    this.setLayout(new GridLayout(4, 5));
    this.soldats = new ArrayList<JButton>();

    Image img1 = new ImageIcon("../../../../../res/maitre.png").getImage();
    Image img2 = new ImageIcon("../../../../../res/elite.png").getImage();
    Image img3 = new ImageIcon("../../../../../res/etudiant.png").getImage();
    URL url = null;
    try{
      url = new URL("https://dwarves.iut-fbleau.fr/~lacombe/Police/maitre.png");
    }catch(MalformedURLException e){
      e.printStackTrace();
    }
    img1 = new ImageIcon(url).getImage();
    try{
      url = new URL("https://dwarves.iut-fbleau.fr/~lacombe/Police/elite.png");
    }catch(MalformedURLException e){
      e.printStackTrace();
    }
    img2 = new ImageIcon(url).getImage();
    try{
      url = new URL("https://dwarves.iut-fbleau.fr/~lacombe/Police/etudiant.png");
    }catch(MalformedURLException e){
      e.printStackTrace();
    }
    img3 = new ImageIcon(url).getImage();

		for (int k = 0; k < 20; k++) {
      JButton bouton = this.genererBoutonSoldat(k);
      if(k >= 15 && k < 19){
        bouton.setIcon(new ImageIcon(img2));
      }
      if(k >= 19){
        bouton.setIcon(new ImageIcon(img1));
      }
      if(k <= 14){
        bouton.setIcon(new ImageIcon(img3));
      }
      this.soldats.add(bouton);
			this.add(bouton);
		}

    this.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, this.couleurBordurePanneau));
  }

  public ArrayList<JButton> getBoutonsSoldats(){
    return this.soldats;
  }

  public void actualiser(int etudiant){
    for(int i=0; i<20; i++){
      if(i == etudiant){
        this.soldats.get(i).setBackground(this.couleurSoldats);
        this.soldats.get(i).revalidate();
        this.soldats.get(i).repaint();
      }
    }
  }
}
