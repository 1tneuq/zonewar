package fr.utt.projetCestDuBrutal.MODELE;

public class MaitreGobi extends Etudiant {
    public MaitreGobi(int id) {
      super(id);
      this.role = "Maitre du Gobi";
      this.force = 2;
      this.dexterite = 2;
      this.resistance = 2;
      this.constitution = 10;
      this.initiative = 2;
    }

}
