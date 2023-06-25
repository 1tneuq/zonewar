package fr.utt.projetCestDuBrutal.MODELE;

public class Elite extends Etudiant {
    public Elite(int id) {
      super(id);
      this.role = "Etudiant d'elite";
      this.force = 1;
      this.dexterite = 1;
      this.resistance = 1;
      this.constitution = 5;
      this.initiative = 1;
    }

}
