package fr.utt.projetCestDuBrutal.MODELE;

public class Main{

  public static void main(String[] args) {
    //On met tout à null en mode configuration par interface graphique
    Partie partie = Partie.getInstance(null, null, null, null);
    //partie.jouer();
  }
}
