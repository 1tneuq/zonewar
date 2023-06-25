package fr.utt.projetCestDuBrutal.MODELE;
/**
 * Classe représentant un étudiant.
 *
 * Un étudiant est caractérisé par :
 * - un identifiant (int)
 * - un rôle (String)
 * - un nombre de crédits ECTS (int)
 * - une dextérité (int)
 * - une force (int)
 * - une résistance (int)
 * - une constitution (int)
 * - une initiative (int)
 * - un booléen indiquant s'il est réserviste ou non (boolean)
 * - une stratégie de jeu (StrategieJeu)
 *
 * Un étudiant peut :
 * - être affecté à une zone d'influence (void affecter(ZoneInfluence zone, int idJoueur))
 * - ajouter des crédits ECTS (void ajouterECTS(int creditECTS))
 * - subir un soin complet (int soinComplet())
 * - perdre des crédits ECTS (void retirerECTS(int credits))
 * - augmenter sa dextérité (boolean setDexterite(int pointsDexterite))
 * - augmenter sa force (boolean setForce(int pointsForce))
 * - augmenter sa résistance (boolean setResistance(int pointsResistance))
 * - augmenter sa constitution (boolean setConsitution(int pointsConstitution))
 * - augmenter son initiative (boolean setInitiative(int pointsInitiative))
 * - devenir réserviste ou non (void setReserviste(boolean reserviste))
 * - changer sa stratégie de jeu (void setStrategie(int strategie))
 * - supprimer sa stratégie de jeu (void supprimerStrategie())
 */
public class Etudiant {
  /** Identifiant de l'étudiant. */
  private int id;

  /** Rôle de l'étudiant dans le jeu. */
  protected String role;

  /** Nombre de crédits ECTS de l'étudiant. */
  protected int creditECTS;

  /** Niveau de dextérité de l'étudiant. */
  protected int dexterite;

  /** Niveau de force de l'étudiant. */
  protected int force;

  /** Niveau de résistance de l'étudiant. */
  protected int resistance;

  /** Niveau de constitution de l'étudiant. */
  protected int constitution;

  /** Niveau d'initiative de l'étudiant. */
  protected int initiative;

  /** Indique si l'étudiant est réserviste. */
  protected boolean reserviste;

  /** Stratégie de jeu de l'étudiant. */
  protected StrategieJeu strategieJeu;

  /** Si l'étudiant est déja configuré dans la vue. */
  protected boolean estConfigure;


  /**
   * Constructeur de la classe Etudiant.
   *
   * @param id l'identifiant de l'étudiant
   */
  public Etudiant(int id) {
    this.id = id;
    this.role = "Etudiant";
    this.dexterite = 0;
    this.force = 0;
    this.resistance = 0;
    this.constitution = 0;
    this.initiative = 0;
    this.reserviste = false;
    this.creditECTS = 30;
    this.estConfigure = false;
  }

  /**
   * Affecte l'étudiant à une zone d'influence.
   *
   * @param zone la zone d'influence
   * @param idJoueur l'identifiant du joueur propriétaire de l'étudiant
   */
  public void affecter(ZoneInfluence zone, int idJoueur) {
    zone.ajouterEtudiant(this, idJoueur);
  }

  /**
   * Ajoute des crédits ECTS à l'étudiant.
   *
   * @param creditECTS le nombre de crédits ECTS à ajouter
   */
  public void ajouterECTS(int creditECTS) {
    this.creditECTS += creditECTS;
  }

  /**
   * Fait profiter d'un soin complet à l'étudiant.
   *
   * @return le nombre de crédits ECTS de l'étudiant après le soin
   */
  public int soinComplet(){
    this.creditECTS = 30 + this.constitution;
    return this.creditECTS;
  }

  /**
   * Retire des crédits ECTS à l'étudiant.
   *
   * @param credits le nombre de crédits ECTS à retirer
   */
  public void retirerECTS(int credits){
      this.creditECTS -= credits;
  }

  /**
   * Modifie le niveau de dextérité de l'étudiant.
   *
   * @param pointsDexterite le nombre de points de dextérité à ajouter
   * @return true si la modification a réussi, false sinon
   */
  public boolean setDexterite(int pointsDexterite){
      if(this.dexterite + pointsDexterite <= 10){
        this.dexterite += pointsDexterite;
        return true;
      }
      return false;
  }

  /**
   * Modifie le niveau de force de l'étudiant.
   *
   * @param pointsForce le nombre de points de force à ajouter
   * @return true si la modification a réussi, false sinon
   */
  public boolean setForce(int pointsForce){
      if(this.force + pointsForce <= 10){
        this.force += pointsForce;
        return true;
      }
      return false;
  }

  /**
   * Modifie le niveau de résistance de l'étudiant.
   *
   * @param pointsResistance le nombre de points de résistance à ajouter
   * @return true si la modification a réussi, false sinon
   */
  public boolean setResistance(int pointsResistance){
    if(this.resistance + pointsResistance <= 10){
      this.resistance += pointsResistance;
      return true;
    }
    return false;
  }

  /**
   * Modifie le niveau de constitution de l'étudiant.
   *
   * @param pointsConstitution le nombre de points de constitution à ajouter
   * @return true si la modification a réussi, false sinon
   */
  public boolean setConsitution(int pointsConstitution){
    if(this.constitution + pointsConstitution <= 30){
      this.constitution += pointsConstitution;
      return true;
    }
    return false;
  }

  /**
   * Modifie le niveau d'initiative de l'étudiant.
   *
   * @param pointsInitiative le nombre de points d'initiative à ajouter
   * @return true si la modification a réussi, false sinon
   */
  public boolean setInitiative(int pointsInitiative){
    if(this.initiative + pointsInitiative <= 10){
      this.initiative += pointsInitiative;
      return true;
    }
    return false;
  }

  /**
   * Modifie le statut de réserviste de l'étudiant.
   *
   * @param reserviste le nouveau statut de réserviste de l'étudiant
   */
   public void setReserviste(boolean reserviste){
    this.reserviste = reserviste;
    }

  public void setStrategie(int strategie){
    if(strategie == 0){
      this.strategieJeu = new StrategieOffensive();
    }else if(strategie == 1){
      this.strategieJeu = new StrategieDefensive();
    }else{
      this.strategieJeu = new StrategieAleatoire();
    }
  }

  public void setConfigure(boolean conf){
    this.estConfigure = conf;
  }

  public void supprimerStrategie(){
    this.strategieJeu = null;
  }

  public int getId(){
    return this.id;
  }

  public int getForce(){
    return this.force;
  }

  public int getDexterite(){
    return this.dexterite;
  }

  public int getResistance(){
    return this.resistance;
  }

  public int getConstitution(){
    return this.constitution;
  }

  public int getInitiative(){
    return this.initiative;
  }

  public boolean getReserviste(){
    return this.reserviste;
  }

  public StrategieJeu getStrategie(){
    return this.strategieJeu;
  }

  public int getCredits(){
    return this.creditECTS;
  }

  public String getRole(){
    return this.role;
  }

  public boolean getConfigure(){
    return this.estConfigure;
  }

  public int getNumStrategie(){
    if(this.strategieJeu instanceof StrategieOffensive){
      return 0;
    }else if(this.strategieJeu instanceof StrategieDefensive){
      return 1;
    }
    return 2;
  }

  @Override
  public String toString() {
    if(this.strategieJeu instanceof StrategieOffensive){
      return "Etudiant: " + (this.id + 1) + ", Role: " + this.role + ", Dexterite: " + this.dexterite + ", Force: " + this.force + ", Resistance: " + this.resistance + ", Constitution: " + this.constitution + ", Initiative: " + this.initiative + ", Strategie: offensive" + ", ECTS: " + this.creditECTS;
    }else if(this.strategieJeu instanceof StrategieDefensive){
      return "Etudiant: " + (this.id + 1) + ", Role: " + this.role + ", Dexterite: " + this.dexterite + ", Force: " + this.force + ", Resistance: " + this.resistance + ", Constitution: " + this.constitution + ", Initiative: " + this.initiative + ", Strategie: defensive" + ", ECTS: " + this.creditECTS;
    }
    return "Etudiant: " + (this.id + 1) + ", Role: " + this.role + ", Dexterite: " + this.dexterite + ", Force: " + this.force + ", Resistance: " + this.resistance + ", Constitution: " + this.constitution + ", Initiative: " + this.initiative + ", Strategie: aleatoire" + ", ECTS: " + this.creditECTS;
  }
}
