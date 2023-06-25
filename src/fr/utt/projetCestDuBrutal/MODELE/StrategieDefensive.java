package fr.utt.projetCestDuBrutal.MODELE;

import java.util.Random;
import java.util.List;
import java.util.LinkedList;

public class StrategieDefensive implements StrategieJeu {

    @Override
    public void executer(Etudiant utilisateur, LinkedList<Etudiant> gentils, LinkedList<Etudiant> mechants){
      this.soigner(utilisateur, gentils);
    }

    public boolean soigner(Etudiant utilisateur, LinkedList<Etudiant> allies){
      int x = (int) (Math.random() * (101 - 0)) + 0;
      double [] tab = {0.0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6};
      double y = tab[new Random().nextInt(tab.length)];

      if(x >= 0 && x <= (20 + 6 * utilisateur.getDexterite())){
        int[] lePlusFaible = {0, 30};
        if(allies.size()>0){
          for(int i=0; i<allies.size(); i++){
            if(lePlusFaible[1] > allies.get(i).getCredits()){
              lePlusFaible[0] = i;
              lePlusFaible[1] = allies.get(i).getCredits();
            }
          }
        int creditsAjoutes = (int) (y * (10 + allies.get(lePlusFaible[0]).getConstitution()));
        if((creditsAjoutes + allies.get(lePlusFaible[0]).getCredits()) > (30 + allies.get(lePlusFaible[0]).getConstitution())){
          int creditsDonnes = allies.get(lePlusFaible[0]).soinComplet();
          int creditsRendus = creditsDonnes - allies.get(lePlusFaible[0]).getCredits();
          System.out.println("Etudiant " + (utilisateur.getId() + 1) + " soigne allie " + (allies.get(lePlusFaible[0]).getId() + 1) + " et lui restaure " + creditsRendus +" credits !");
        }else{
          allies.get(lePlusFaible[0]).ajouterECTS(creditsAjoutes);
          System.out.println("Etudiant " + (utilisateur.getId() + 1) + " soigne allie " + (allies.get(lePlusFaible[0]).getId() + 1) + " et lui restaure " + creditsAjoutes + " credits !");
        }

        }
        return true;
      }
      return false;
    }
}
