package fr.utt.projetCestDuBrutal.MODELE;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.Integer;

public class Plateau {

    private List<ZoneInfluence> zones;
    private List<Etudiant> reservistes1;
    private List<Etudiant> reservistes2;

    public Plateau(List<Etudiant> r1, List<Etudiant> r2){
      this.zones = new ArrayList<ZoneInfluence>();
      this.zones.add(new ZoneInfluence(NomZone.Bibliotheque));
      this.zones.add(new ZoneInfluence(NomZone.BDE));
      this.zones.add(new ZoneInfluence(NomZone.QuartierAdmin));
      this.zones.add(new ZoneInfluence(NomZone.HalleIndustrielle));
      this.zones.add(new ZoneInfluence(NomZone.HalleSportive));
      this.reservistes1 = r1;
      this.reservistes2 = r2;
    }

    public boolean melee(ArrayList<ZoneInfluence> zonesControlees) {
      //System.out.println("Melee");
      int taille = zonesControlees.size();
      if(taille < 5){
        while(zonesControlees.size() == taille){
          for(int i=0; i<5; i++){
            if(zonesControlees.contains(this.zones.get(i)) == false){
              if(this.zones.get(i).estControlee() != 0){
                zonesControlees.add(this.zones.get(i));
                break;
              }
              this.zones.get(i).lancerCombat();
              if(this.zones.get(i).estControlee() != 0){
                zonesControlees.add(this.zones.get(i));
                break;
              }
            }
          }
        }
        int[] index = {0,0};
        Iterator<ZoneInfluence> iter = zonesControlees.iterator();
        while(iter.hasNext()){
          ZoneInfluence zone = iter.next();
          if(zone.estControlee() == 1){
            index[0]++;
          }else if(zone.estControlee() == 2){
            index[1]++;
          }
        }
        if(index[0] == 3 || index[1] == 3){
          return true;
        }
        return false;
      }
      return true;
    }

    public void treveEtMouvement() throws IOException{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      //System.out.println("Treve et mouvement");
      System.out.println("\n\n--------------------------Treve des batailles--------------------------------");
      Iterator<ZoneInfluence> iterateur = this.zones.iterator();

      while(iterateur.hasNext()){
        ZoneInfluence zone = iterateur.next();
        String reponse = "";
        System.out.println("\nZone " + zone.getNom() + ": [" + zone.getEtudiants1().size() + " etudiants joueur 1] " + "[" + zone.getEtudiants2().size() + " etudiants joueur 2] \n" );

        for(int i=1; i<3; i++){
          System.out.println("----------------------Mouvement des troupes du joueur " + i + "----------------------");
          if(zone.estControlee() == i) {
            if(zone.getEtudiants1().size() > 1 || zone.getEtudiants2().size() > 1){
              do{
                System.out.print("-Repartir les troupes restantes sur d'autres zones ? 0:[OUI] 1:[NON]: ");
                reponse = br.readLine();
                if(Joueur.estUnNombre(reponse)){
                  if(!(Integer.parseInt(reponse) != 0 && Integer.parseInt(reponse) != 1)){
                    break;
                  }
                }
              }while(true);
              if(i == 1){
                if(Integer.parseInt(reponse) == 0){
                  //
                  Iterator<Etudiant> iter = zone.getEtudiants1().iterator();
                  while(iter.hasNext() && zone.getEtudiants1().size() > 1){
                    Etudiant e = iter.next();
                    System.out.println(e);
                    do{
                      System.out.print("-Entrer la strategie que l'etudiant va suivre lors de son redeploiement 0:[Offensive] 1:[Defensive] 2:[Aleatoire]: ");
                      reponse = br.readLine();
                      if(Joueur.estUnNombre(reponse)){
                        if(!(Integer.parseInt(reponse) != 0 && Integer.parseInt(reponse) != 1 && Integer.parseInt(reponse) != 2)){
                          break;
                        }
                      }
                    }while(true);
                    e.supprimerStrategie();
                    e.setStrategie(Integer.parseInt(reponse));
                    do{
                      System.out.print("-Entrer le numero de la zone ou redeployer l'etudiant [0:Bibliotheque 1:BDE 2:QuartierAdmin 3:HalleIndustrielle 4:HalleSportive]: ");
                      reponse = br.readLine();
                      if(Joueur.estUnNombre(reponse)){
                        if(!(Integer.parseInt(reponse) != 0 && Integer.parseInt(reponse) != 1 && Integer.parseInt(reponse) != 2 && Integer.parseInt(reponse) != 3 && Integer.parseInt(reponse) != 4 || this.zones.get(Integer.parseInt(reponse)).estControlee() != 0 || this.zones.get(Integer.parseInt(reponse)).getNom() == zone.getNom())){
                          break;
                        }
                      }
                    }while(true);
                    e.affecter(this.zones.get(Integer.parseInt(reponse)), i);
                    iter.remove();
                  }
                  //
                }
              }else{
                if(Integer.parseInt(reponse) == 0){
                  Iterator<Etudiant> iter = zone.getEtudiants2().iterator();
                  while(iter.hasNext() && zone.getEtudiants2().size() > 1){
                    Etudiant e = iter.next();
                    System.out.println(e);
                    do{
                      System.out.print("-Entrer la strategie que l'etudiant va suivre lors de son redeploiement 0:[Offensive] 1:[Defensive] 2:[Aleatoire]: ");
                      reponse = br.readLine();
                      if(Joueur.estUnNombre(reponse)){
                        if(!(Integer.parseInt(reponse) != 0 && Integer.parseInt(reponse) != 1 && Integer.parseInt(reponse) != 2)){
                          break;
                        }
                      }
                    }while(true);
                    e.supprimerStrategie();
                    e.setStrategie(Integer.parseInt(reponse));
                    do{
                      System.out.print("-Entrer le numero de la zone ou redeployer l'etudiant [0:Bibliotheque 1:BDE 2:QuartierAdmin 3:HalleIndustrielle 4:HalleSportive]: ");
                      reponse = br.readLine();
                      if(Joueur.estUnNombre(reponse)){
                        if(!(Integer.parseInt(reponse) != 0 && Integer.parseInt(reponse) != 1 && Integer.parseInt(reponse) != 2 && Integer.parseInt(reponse) != 3 && Integer.parseInt(reponse) != 4 || this.zones.get(Integer.parseInt(reponse)).estControlee() != 0 || this.zones.get(Integer.parseInt(reponse)).getNom() == zone.getNom())){
                          break;
                        }
                      }
                    }while(true);
                    e.affecter(this.zones.get(Integer.parseInt(reponse)), i);
                    iter.remove();
                  }
                }
              }
              if(i==1){
                i++;
              }
            }
          }
          else{
            System.out.println("Selectionner les etudiants reservistes a redeployer sur la zone: ");
            if(i == 1){
              //
              Iterator<Etudiant> it = this.reservistes1.iterator();
              int in = 0;
              while(it.hasNext()){
                Etudiant etu = it.next();
                System.out.println(etu);
                do{
                  System.out.print("-Redeployer ? 0:[OUI] 1:[NON]: ");
                  reponse = br.readLine();
                  if(Joueur.estUnNombre(reponse)){
                    if(!(Integer.parseInt(reponse) != 0 && Integer.parseInt(reponse) != 1)){
                      break;
                    }
                  }
                }while(true);
                if(Integer.parseInt(reponse) == 0){
                  ///////////////////////////////////////////
                  etu.affecter(zone, i);
                  it.remove();
                }
              }
              //
            }else{
              Iterator<Etudiant> it = this.reservistes2.iterator();
              int in = 0;
              while(it.hasNext()){
                Etudiant etu = it.next();
                System.out.println(etu);
                do{
                  System.out.print("-Redeployer ? 0:[OUI] 1:[NON]: ");
                  reponse = br.readLine();
                  if(Joueur.estUnNombre(reponse)){
                    if(!(Integer.parseInt(reponse) != 0 && Integer.parseInt(reponse) != 1)){
                      break;
                    }
                  }
                }while(true);
                if(Integer.parseInt(reponse) == 0){
                  ///////////////////////////////////////////
                  etu.affecter(zone, i);
                  it.remove();
                }
              }
            }
          }
          System.out.println("-----------------------------------------------------------------------------\n");
        }
      }

      System.out.println("-----------------------------------------------------------------------------\n");

      /////////////////
      /*Iterator<ZoneInfluence> ite = this.zones.iterator();
      while(ite.hasNext()){
        ZoneInfluence zone = ite.next();
        System.out.println("\nZone " + zone.getNom() + ": [" + zone.getEtudiants1().size() + " etudiants joueur 1] " + "[" + zone.getEtudiants2().size() + " etudiants joueur 2] " );
      }*/
    }

    public List<ZoneInfluence> getZonesInfluence(){
      return this.zones;
    }
}
