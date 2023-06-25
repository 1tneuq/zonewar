package fr.utt.projetCestDuBrutal.MODELE;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ZoneInfluence {
    private int controleePar;

    private int creditsJoueur1;

    private int creditsJoueur2;

    private boolean combatEnCours;

    private LinkedList<Etudiant> etudiants1;

    private LinkedList<Etudiant> etudiants2;

    private NomZone nomZone;

    public ZoneInfluence(NomZone nom) {
      this.nomZone = nom;
      this.etudiants1 = new LinkedList<Etudiant>();
      this.etudiants2 = new LinkedList<Etudiant>();
    }

    public void ajouterEtudiant(Etudiant etudiant, int numJoueur){
      if(numJoueur == 1){
        this.etudiants1.add(etudiant);
      }else{
        this.etudiants2.add(etudiant);
      }
    }

    public void retirerEtudiant(Etudiant etudiant, int numJoueur){
      if(numJoueur == 1){
        this.etudiants1.remove(etudiant);
      }else{
        this.etudiants2.remove(etudiant);
      }
    }

    public int estControlee() {
      if(this.etudiants1.size() == 0){
        return 2;
      }
      if(this.etudiants2.size() == 0){
        return 1;
      }
      return 0;
    }

    public void lancerCombat() {
      System.out.println("\nZone " + this.nomZone + ": [" + this.etudiants1.size() + " etudiants joueur 1] " + "[" + this.etudiants2.size() + " etudiants joueur 2] " );
      //System.out.println("---------------------Le combat dans la zone \"" + this.nomZone + "\" a commence----------------------");

      if(this.estControlee() == 0){
        System.out.println("---------------------Equipe du joueur 1 agit---------------------------------");
        for(int i = 0; i<this.etudiants1.size(); i++){
          this.etudiants1.get(0).getStrategie().executer(this.etudiants1.get(0), this.etudiants1, this.etudiants2);
          this.etudiants1.offer(this.etudiants1.poll());
        }
      }

      if(this.estControlee() == 0){
        System.out.println("---------------------Equipe du joueur 2 agit---------------------------------");
        for(int i = 0; i<this.etudiants2.size(); i++){
          this.etudiants2.get(0).getStrategie().executer(this.etudiants2.get(0), this.etudiants2, this.etudiants1);
          this.etudiants2.offer(this.etudiants2.poll());
        }
      }

      System.out.println("-----------------------------------------------------------------------------");
    }

    public NomZone getNom(){
      return this.nomZone;
    }

    public LinkedList<Etudiant> getEtudiants1(){
      return this.etudiants1;
    }

    public LinkedList<Etudiant> getEtudiants2(){
      return this.etudiants2;
    }

}
