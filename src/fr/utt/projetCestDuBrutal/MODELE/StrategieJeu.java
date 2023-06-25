package fr.utt.projetCestDuBrutal.MODELE;

import java.util.List;
import java.util.LinkedList;

public interface StrategieJeu {
    void executer(Etudiant utilisateur, LinkedList<Etudiant> gentils, LinkedList<Etudiant> mechants);
}
