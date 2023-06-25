$(info $(SHELL))
# COMMANDES #
JAVAC = javac
# note $$ to get a single shell $
JAVAC_OPTIONS = -d build -cp build:$$CLASSPATH -implicit:none -sourcepath src
JAVA = java
JAR = jar
EXEC_JAR = ${JAVA} -jar

# CHEMINS RELATIFS
SRC = src/fr/utt/projetCestDuBrutal
BUILD = build/fr/utt/projetCestDuBrutal
DOC = doc/fr/utt/projetCestDuBrutal

# CHOIX NOMS
JAR_PROJET_BRUTAL = projet_brutal.jar

# BUTS FACTICES #
.PHONY : run clean doc

# BUT PAR DEFAUT #
run : ${JAR_PROJET_BRUTAL}
	${EXEC_JAR} ${JAR_PROJET_BRUTAL}

# AUTRE BUTS
doc :
	##javadoc -d doc src/fr/iutfbleau/projetTourelle/MODELE/*.java src/fr/iutfbleau/projetTourelle/VUE/*.java src/fr/iutfbleau/projetTourelle/CONTROLEUR/*.java

clean :
	rm -rf ${BUILD}/* *.jar

# REGLES DE DEPENDANCE #
## VUE ##
${BUILD}/VUE/Vue.class : ${SRC}/VUE/Vue.java \
	                              ${BUILD}/CONTROLEUR/ControleurBoutons.class \
																${BUILD}/VUE/PanneauBoutons.class \
																${BUILD}/VUE/PanneauConfiguration.class \
																${BUILD}/VUE/PanneauJoueur.class \
																${BUILD}/VUE/PanneauTroupes.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/VUE/Vue.java

${BUILD}/VUE/GraphPanneau.class : ${SRC}/VUE/GraphPanneau.java
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/VUE/GraphPanneau.java

${BUILD}/VUE/PanneauBoutons.class : ${SRC}/VUE/PanneauBoutons.java \
																${BUILD}/VUE/GraphPanneau.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/VUE/PanneauBoutons.java

${BUILD}/VUE/PanneauConfiguration.class : ${SRC}/VUE/PanneauConfiguration.java \
																${BUILD}/VUE/GraphPanneau.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/VUE/PanneauConfiguration.java

${BUILD}/VUE/PanneauJoueur.class : ${SRC}/VUE/PanneauJoueur.java \
																${BUILD}/VUE/GraphPanneau.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/VUE/PanneauJoueur.java

${BUILD}/VUE/PanneauTroupes.class : ${SRC}/VUE/PanneauTroupes.java \
																${BUILD}/VUE/GraphPanneau.class
		${JAVAC} ${JAVAC_OPTIONS} ${SRC}/VUE/PanneauTroupes.java


## CONTROLEUR ##
${BUILD}/CONTROLEUR/ControleurBoutons.class : ${SRC}/CONTROLEUR/ControleurBoutons.java \
																								${BUILD}/MODELE/Partie.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/CONTROLEUR/ControleurBoutons.java

## MODELE ##
${BUILD}/MODELE/Partie.class : ${SRC}/MODELE/Partie.java \
																								${BUILD}/VUE/Vue.class \
																								${BUILD}/CONTROLEUR/ControleurBoutons.class \
																								${BUILD}/MODELE/Programme.class \
																								${BUILD}/MODELE/Joueur.class \
																								${BUILD}/MODELE/Partie.class \
																								${BUILD}/MODELE/Plateau.class \
																								${BUILD}/MODELE/ZoneInfluence.class \
																								${BUILD}/MODELE/Etudiant.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/MODELE/Partie.java


${BUILD}/MODELE/Joueur.class : ${SRC}/MODELE/Joueur.java \
																									${BUILD}/MODELE/Etudiant.class \
																									${BUILD}/MODELE/ZoneInfluence.class \
																									${BUILD}/MODELE/Programme.class \
																									${BUILD}/MODELE/MaitreGobi.class \
																									${BUILD}/MODELE/Elite.class \
																									${BUILD}/MODELE/Etudiant.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/MODELE/Joueur.java

${BUILD}/MODELE/Etudiant.class : ${SRC}/MODELE/Etudiant.java \
																									${BUILD}/MODELE/StrategieJeu.class \
																									${BUILD}/MODELE/ZoneInfluence.class \
																									${BUILD}/MODELE/StrategieAleatoire.class \
																									${BUILD}/MODELE/StrategieDefensive.class \
																									${BUILD}/MODELE/StrategieOffensive.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/MODELE/Etudiant.java

${BUILD}/MODELE/Plateau.class : ${SRC}/MODELE/Plateau.java \
																									${BUILD}/MODELE/Etudiant.class \
																									${BUILD}/MODELE/ZoneInfluence.class \
																									${BUILD}/MODELE/NomZone.class \
																									${BUILD}/MODELE/Joueur.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/MODELE/Plateau.java

${BUILD}/MODELE/ZoneInfluence.class : ${SRC}/MODELE/ZoneInfluence.java \
																									${BUILD}/MODELE/Etudiant.class \
																									${BUILD}/MODELE/NomZone.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/MODELE/ZoneInfluence.java

${BUILD}/MODELE/Main.class : ${SRC}/MODELE/Main.java \
																								${BUILD}/MODELE/Partie.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/MODELE/Main.java

${BUILD}/MODELE/Elite.class : ${SRC}/MODELE/Elite.java \
																									${BUILD}/MODELE/Etudiant.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/MODELE/Elite.java

${BUILD}/MODELE/MaitreGobi.class : ${SRC}/MODELE/MaitreGobi.java \
																								${BUILD}/MODELE/Etudiant.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/MODELE/MaitreGobi.java

${BUILD}/MODELE/NomZone.class : ${SRC}/MODELE/NomZone.java
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/MODELE/NomZone.java

${BUILD}/MODELE/StrategieJeu.class : ${SRC}/MODELE/StrategieJeu.java \
																								${BUILD}/MODELE/Etudiant.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/MODELE/StrategieJeu.java

${BUILD}/MODELE/StrategieOffensive.class : ${SRC}/MODELE/StrategieOffensive.java \
																								${BUILD}/MODELE/Etudiant.class \
																								${BUILD}/MODELE/StrategieJeu.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/MODELE/StrategieOffensive.java

${BUILD}/MODELE/StrategieDefensive.class : ${SRC}/MODELE/StrategieDefensive.java \
																								${BUILD}/MODELE/Etudiant.class \
																								${BUILD}/MODELE/StrategieJeu.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/MODELE/StrategieDefensive.java

${BUILD}/MODELE/StrategieAleatoire.class : ${SRC}/MODELE/StrategieAleatoire.java \
																								${BUILD}/MODELE/Etudiant.class \
																								${BUILD}/MODELE/StrategieJeu.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/MODELE/StrategieAleatoire.java

${BUILD}/MODELE/Programme.class : ${SRC}/MODELE/Programme.java
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/MODELE/Programme.java


# ## JARS ##
 ${JAR_PROJET_BRUTAL} : ${BUILD}/MODELE/Main.class
	${JAR} cvfe ${JAR_PROJET_BRUTAL} fr.utt.projetCestDuBrutal.MODELE.Main -C build fr
