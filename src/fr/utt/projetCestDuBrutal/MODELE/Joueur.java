package fr.utt.projetCestDuBrutal.MODELE;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Iterator;
import java.lang.Integer;
import java.lang.Math;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Joueur {
    private int points;

    private String nom;

    private int joueurID;

    private List<Etudiant> etudiants;

    private Programme programme;

    private BufferedReader br;

    public Joueur(int id, Programme programme, String nomJoueur) {
      this.joueurID = id;
      this.nom = nomJoueur;
      this.programme = programme;
      this.points = 400;
      this.etudiants = new ArrayList<Etudiant>();
      this.br = new BufferedReader(new InputStreamReader(System.in));

      for(int i=0; i<20; i++){
        if(i<15){
          this.etudiants.add(i, new Etudiant(i));
        }
        else if(i<19){
          this.etudiants.add(i, new Elite(i));
        }else{
          this.etudiants.add(i, new MaitreGobi(i));
        }
      }
    }

    public void parametrer() {
      Iterator<Etudiant> iterateur = this.etudiants.iterator();
      System.out.println("\n---------------Parametrage des troupes du joueur " + this.joueurID + "---------------");
      int index = 0;
      //
      while(iterateur.hasNext()){
        System.out.println("");
        System.out.println(iterateur.next() + " | nombre de points restants: " + this.points);
        for(int i=0; i<5; i++){
          if(this.points > 0){
            try {
              this.points -= this.demanderCaracteristique(i, index);
            }
            catch(IOException e) {
              e.getMessage();
              e.printStackTrace();
            }
          }else{
            this.remplirParZero(i, index);
          }
        }
        this.demanderStrategie(index);
        index++;
      }
      //
      System.out.println("\nRecapitulatif de vos troupes: \n");
      this.listerTroupes();
      this.demanderReservistes();
      System.out.println("\n-----------------------------------------------------------------\n");
    }

    public void repartir(List<ZoneInfluence> listeZones){
      Iterator<Etudiant> iterateur = this.etudiants.iterator();
      int index = 0;
      String ligneLueSurLaConsole = "";
      System.out.println("\n---------------Repartition des troupes du joueur " + this.joueurID + "---------------\n");

      while(iterateur.hasNext()){
        Etudiant etu = iterateur.next();
        if(etu.getReserviste() == false){
          System.out.println(etu);
          do{
            System.out.print("-Entrer la zone d'affectation [0:Bibliotheque 1:BDE 2:QuartierAdmin 3:HalleIndustrielle 4:HalleSportive]: ");
            try{
              ligneLueSurLaConsole = this.br.readLine();
            }catch(IOException e){
              e.getMessage();
              e.printStackTrace();
            }
          }while(Integer.parseInt(ligneLueSurLaConsole) != 0 && Integer.parseInt(ligneLueSurLaConsole) != 1 && Integer.parseInt(ligneLueSurLaConsole) != 2 && Integer.parseInt(ligneLueSurLaConsole) != 3 && Integer.parseInt(ligneLueSurLaConsole) != 4);
          etu.affecter(listeZones.get(Integer.parseInt(ligneLueSurLaConsole)), this.joueurID);
          System.out.println("");
        }
        index++;
      }

      System.out.println("-----------------------------------------------------------------\n");
    }

    public int demanderCaracteristique(int caracteristique, int index) throws IOException{
      String ligneLueSurLaConsole = "";
      boolean flag = false;

      do{
        switch(caracteristique) {
          case 0:
            System.out.print("-Ajouter Force [max 10, " + this.points + " points restants]: ");
            ligneLueSurLaConsole = this.br.readLine();
            if(this.estUnNombre(ligneLueSurLaConsole)){
              if(this.points - Integer.parseInt(ligneLueSurLaConsole) >= 0 && Integer.parseInt(ligneLueSurLaConsole) >= 0){
                 flag = this.etudiants.get(index).setForce(Integer.parseInt(ligneLueSurLaConsole));
              }
            }
            break;

          case 1:
            System.out.print("-Ajouter Dexterite [max 10, " + this.points + " points restants]: ");
            ligneLueSurLaConsole = this.br.readLine();
            if(this.estUnNombre(ligneLueSurLaConsole)){
              if(this.points - Integer.parseInt(ligneLueSurLaConsole) >= 0 && Integer.parseInt(ligneLueSurLaConsole) >= 0){
                 flag = this.etudiants.get(index).setDexterite(Integer.parseInt(ligneLueSurLaConsole));
              }
            }
            break;

          case 2:
            System.out.print("-Ajouter Resistance [max 10, " + this.points + " points restants]: ");
            ligneLueSurLaConsole = this.br.readLine();
            if(this.estUnNombre(ligneLueSurLaConsole)){
              if(this.points - Integer.parseInt(ligneLueSurLaConsole) >= 0 && Integer.parseInt(ligneLueSurLaConsole) >= 0){
                 flag = this.etudiants.get(index).setResistance(Integer.parseInt(ligneLueSurLaConsole));
              }
            }
            break;

          case 3:
            System.out.print("-Ajouter Constitution [max 30 ," + this.points + " points restants]: ");
            ligneLueSurLaConsole = this.br.readLine();
            if(this.estUnNombre(ligneLueSurLaConsole)){
              if(this.points - Integer.parseInt(ligneLueSurLaConsole) >= 0 && Integer.parseInt(ligneLueSurLaConsole) >= 0){
                 flag = this.etudiants.get(index).setConsitution(Integer.parseInt(ligneLueSurLaConsole));
              }
            }
            break;

          case 4:
            System.out.print("-Ajouter Initiative [max 10, " + this.points + " points restants]: ");
            ligneLueSurLaConsole = this.br.readLine();
            if(this.estUnNombre(ligneLueSurLaConsole)){
              if(this.points - Integer.parseInt(ligneLueSurLaConsole) >= 0 && Integer.parseInt(ligneLueSurLaConsole) >= 0){
                 flag = this.etudiants.get(index).setInitiative(Integer.parseInt(ligneLueSurLaConsole));
              }
            }
            break;
        }
      }while(flag == false);

      return Integer.parseInt(ligneLueSurLaConsole);
    }


    public int remplirParZero(int caracteristique, int index){
      switch(caracteristique) {
        case 0:
          System.out.println("-Ajouter Force: 0 [auto]");
          break;
        case 1:
          System.out.println("-Ajouter Dexterite: 0 [auto]");
          break;
        case 2:
          System.out.println("-Ajouter Resistance: 0 [auto]");
          break;
        case 3:
          System.out.println("-Ajouter Constitution: 0 [auto]");
          break;
        case 4:
          System.out.println("-Ajouter Initiative: 0 [auto]");
          break;
      }

      return 0;
    }


    public void demanderReservistes(){
      int[] numReservistes = {0, 0, 0, 0, 0};
      String ligneLueSurLaConsole = "-1";

      for(int i=0; i<5; i++){
        if(i == 0 ){
          do{
            System.out.print("-Entrer le numero du 1er joueur reserviste [entre 1 et 20]: ");
            try{
              ligneLueSurLaConsole = this.br.readLine();
            }catch(IOException e){
              e.getMessage();
              e.printStackTrace();
            }
            if(this.estUnNombre(ligneLueSurLaConsole)){
              if(!(Integer.parseInt(ligneLueSurLaConsole) < 1 || Integer.parseInt(ligneLueSurLaConsole) > 20)){
                numReservistes[i] = Integer.parseInt(ligneLueSurLaConsole);
                break;
              }
            }
          }while(true);
        }

        else if(i == 1){
          do{
            System.out.print("-Entrer le numero du 2eme joueur reserviste [entre 1 et 20]: ");
            try{
              ligneLueSurLaConsole = this.br.readLine();
            }catch(IOException e){
              e.getMessage();
              e.printStackTrace();
            }
            if(this.estUnNombre(ligneLueSurLaConsole)){
              if(!(Integer.parseInt(ligneLueSurLaConsole) < 1 || Integer.parseInt(ligneLueSurLaConsole) > 20 || Integer.parseInt(ligneLueSurLaConsole) == numReservistes[0])){
                numReservistes[i] = Integer.parseInt(ligneLueSurLaConsole);
                break;
              }
            }
          }while(true);
        }

        else if(i == 2){
          do{
            System.out.print("-Entrer le numero du 3eme joueur reserviste [entre 1 et 20]: ");
            try{
              ligneLueSurLaConsole = this.br.readLine();
            }catch(IOException e){
              e.getMessage();
              e.printStackTrace();
            }
            if(this.estUnNombre(ligneLueSurLaConsole)){
              if(!(Integer.parseInt(ligneLueSurLaConsole) < 1 || Integer.parseInt(ligneLueSurLaConsole) > 20 || Integer.parseInt(ligneLueSurLaConsole) == numReservistes[0] || Integer.parseInt(ligneLueSurLaConsole) == numReservistes[1])){
                numReservistes[i] = Integer.parseInt(ligneLueSurLaConsole);
                break;
              }
            }
          }while(true);
        }

        else if(i == 3){
          do{
            System.out.print("-Entrer le numero du 4eme joueur reserviste [entre 1 et 20]: ");
            try{
              ligneLueSurLaConsole = this.br.readLine();
            }catch(IOException e){
              e.getMessage();
              e.printStackTrace();
            }
            if(this.estUnNombre(ligneLueSurLaConsole)){
              if(!(Integer.parseInt(ligneLueSurLaConsole) < 1 || Integer.parseInt(ligneLueSurLaConsole) > 20 || Integer.parseInt(ligneLueSurLaConsole) == numReservistes[0] || Integer.parseInt(ligneLueSurLaConsole) == numReservistes[1] || Integer.parseInt(ligneLueSurLaConsole) == numReservistes[2])){
                numReservistes[i] = Integer.parseInt(ligneLueSurLaConsole);
                break;
              }
            }
          }while(true);
        }

        else if(i == 4){
          do{
            System.out.print("-Entrer le numero du 5eme joueur reserviste [entre 1 et 20]: ");
            try{
              ligneLueSurLaConsole = this.br.readLine();
            }catch(IOException e){
              e.getMessage();
              e.printStackTrace();
            }
            if(this.estUnNombre(ligneLueSurLaConsole)){
              if(!(Integer.parseInt(ligneLueSurLaConsole) < 1 || Integer.parseInt(ligneLueSurLaConsole) > 20 || Integer.parseInt(ligneLueSurLaConsole) == numReservistes[0] || Integer.parseInt(ligneLueSurLaConsole) == numReservistes[1] || Integer.parseInt(ligneLueSurLaConsole) == numReservistes[2] || Integer.parseInt(ligneLueSurLaConsole) == numReservistes[3])){
                numReservistes[i] = Integer.parseInt(ligneLueSurLaConsole);
                break;
              }
            }
          }while(true);
        }
      }

      int index = 0;
      Iterator<Etudiant> it = this.etudiants.iterator();
      while(it.hasNext()){
        if(index == numReservistes[0] - 1 || index == numReservistes[1] - 1 || index == numReservistes[2] - 1){
          it.next().setReserviste(true);
        }else{
          it.next().setReserviste(false);
        }
        index++;
      }
    }

    public void demanderStrategie(int index){
      String ligneLueSurLaConsole = "";

      do{
        System.out.print("-Entrer Strategie [0:Offensive 1:Defensive 2:Aleatoire]: ");
        try{
          ligneLueSurLaConsole = this.br.readLine();
        }catch(IOException e){
          e.getMessage();
          e.printStackTrace();
        }
        if(this.estUnNombre(ligneLueSurLaConsole)){
          if(!(Integer.parseInt(ligneLueSurLaConsole) !=0 && Integer.parseInt(ligneLueSurLaConsole) !=1 && Integer.parseInt(ligneLueSurLaConsole) !=2)){
            this.etudiants.get(index).setStrategie(Integer.parseInt(ligneLueSurLaConsole));
            System.out.println("");
            break;
          }
        }
      }while(true);
    }

    public void listerTroupes(){
      //System.out.println("---------------Rappel des troupes du joueur " + this.joueurID + "---------------");
      Iterator<Etudiant> iterateur = this.etudiants.iterator();
      while(iterateur.hasNext()){
        System.out.println(iterateur.next());
        //System.out.println(iterateur.next() + "  |  " + iterateur.next());
      }
      //System.out.println("----------------------------------------------------------------------");
    }

    public void parametrageAuto(){
      Iterator<Etudiant> iterateur = this.etudiants.iterator();
      System.out.println("\n---------------Parametrage des troupes du joueur " + this.joueurID + "---------------");
      int index = 0;
      //
      while(iterateur.hasNext()){
        Etudiant etudiant = iterateur.next();
        etudiant.setForce(4);
        etudiant.setResistance(4);
        etudiant.setInitiative(4);
        etudiant.setConsitution(4);
        etudiant.setDexterite(4);
        etudiant.setStrategie((int) (Math.random() * (3 - 0)) + 0);
        if(index == 0 || index == 1 || index == 2 || index == 3 || index == 4){
          etudiant.setReserviste(true);
        }
        index++;
      }
      //
      System.out.println("\nRecapitulatif de vos troupes: \n");
      this.listerTroupes();
      System.out.println("\n-----------------------------------------------------------------\n");
    }

    public void repartitionAuto(List<ZoneInfluence> listeZones){
      System.out.println("\n---------------Repartition des troupes du joueur " + this.joueurID + "---------------\n");

      this.etudiants.get(5).affecter(listeZones.get(0), this.joueurID);
      this.etudiants.get(6).affecter(listeZones.get(1), this.joueurID);
      this.etudiants.get(7).affecter(listeZones.get(2), this.joueurID);
      this.etudiants.get(8).affecter(listeZones.get(3), this.joueurID);
      this.etudiants.get(9).affecter(listeZones.get(4), this.joueurID);
      this.etudiants.get(10).affecter(listeZones.get(0), this.joueurID);
      this.etudiants.get(11).affecter(listeZones.get(1), this.joueurID);
      this.etudiants.get(12).affecter(listeZones.get(2), this.joueurID);
      this.etudiants.get(13).affecter(listeZones.get(3), this.joueurID);
      this.etudiants.get(14).affecter(listeZones.get(4), this.joueurID);
      this.etudiants.get(15).affecter(listeZones.get(0), this.joueurID);
      this.etudiants.get(16).affecter(listeZones.get(1), this.joueurID);
      this.etudiants.get(17).affecter(listeZones.get(2), this.joueurID);
      this.etudiants.get(18).affecter(listeZones.get(3), this.joueurID);
      this.etudiants.get(19).affecter(listeZones.get(4), this.joueurID);

      System.out.println("\n-----------------------------------------------------------------\n");
    }

    public List<Etudiant> getReservistes(){
      List<Etudiant> listeEtu = new ArrayList<Etudiant>();
      Iterator<Etudiant> iterateur = this.etudiants.iterator();
      while(iterateur.hasNext()){
        Etudiant etu = iterateur.next();
        if(etu.getReserviste() == true){
          listeEtu.add(etu);
        }
      }
      return listeEtu;
    }

    public boolean aGagne(List<ZoneInfluence> zones) {
      int compteur = 0;
      Iterator<ZoneInfluence> iterateur = zones.iterator();
      while(iterateur.hasNext()){
        ZoneInfluence zone = iterateur.next();
        if(zone.estControlee() == this.joueurID){
          compteur++;
        }
        if(compteur >= 3){
          return true;
        }
      }
      if(compteur >= 3){
        return true;
      }
      return false;
    }

    public static boolean estUnNombre(String string) {
      int nombre;
      if(string == null || string.equals("")){
        return false;
      }
      try{
        nombre = Integer.parseInt(string);
        return true;
      }catch(NumberFormatException e){
        //System.out.println("/!\\ L'entree utilisateur doit etre un nombre /!\\");
      }
      return false;
    }

    public int getNombreEtudiantsConfigures(){
      int compteur = 0;
      Iterator<Etudiant> iterateur = this.etudiants.iterator();
      while(iterateur.hasNext()){
        Etudiant etudiant = iterateur.next();
        if(etudiant.getConfigure()){
          compteur++;
        }
      }
      return compteur;
    }

    public List<Etudiant> getEtudiants(){
      return this.etudiants;
    }

    public String getNom(){
      return this.nom;
    }

    public void setNom(String nomJ){
       this.nom = nomJ;
    }
    
    public int getPoints(){
      return this.points;
    }

    public void setPoints(int points){
      this.points = points;
    }
}
