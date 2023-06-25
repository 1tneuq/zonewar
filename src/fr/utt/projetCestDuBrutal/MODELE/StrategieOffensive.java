package fr.utt.projetCestDuBrutal.MODELE;

import java.util.Random;
import java.lang.Math;
import java.util.List;
import java.util.LinkedList;

public class StrategieOffensive implements StrategieJeu {

    @Override
    public void executer(Etudiant utilisateur, LinkedList<Etudiant> gentils, LinkedList<Etudiant> mechants) {
      this.attaquer(utilisateur, mechants);
    }

    public boolean attaquer(Etudiant utilisateur, LinkedList<Etudiant> ennemis) {
      int x = (int) (Math.random() * (101 - 0)) + 0;
      double y = new Random().nextDouble();
      int degatDeReference = 10;

      if(x >= 0 && x <= (40 * utilisateur.getDexterite())){
        int[] lePlusFaible = {0, 30};
        if(ennemis.size()>0){
          for(int i=0; i<ennemis.size(); i++){
            if(lePlusFaible[1] > ennemis.get(i).getCredits()){
              lePlusFaible[0] = i;
              lePlusFaible[1] = ennemis.get(i).getCredits();
            }
          }
          int coefficientDegats = Math.max(0, Math.min(100, 10*utilisateur.getForce()) - 5*(ennemis.get(lePlusFaible[0]).getResistance())) / 100;
          int creditsRetires = (int) (y * (1 + coefficientDegats) * degatDeReference);
          //System.out.println("Retires: " + (y * (1 + coefficientDegats) * degatDeReference));
          ennemis.get(lePlusFaible[0]).retirerECTS(creditsRetires);
          System.out.println("Etudiant " + (utilisateur.getId() + 1) + " attaque ennemi " + (ennemis.get(lePlusFaible[0]).getId() + 1) + " et lui enleve " + creditsRetires + " credits !");
          if(ennemis.get(lePlusFaible[0]).getCredits() <= 0){
            System.out.println("Ennemi " + (ennemis.get(lePlusFaible[0]).getId() + 1) + " est elimine !");
            ennemis.remove(lePlusFaible[0]);
          }
        }

        return true;
      }

      return false;
    }
}
