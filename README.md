# Projet "C'est du brutal !" (zonewar)

## Description générale du projet

Réalisation d'un jeu à deux joueurs (moteur + début d'interface graphique) de combat d'étudiants sur le territoire de l'UTT. Le but étant de contrôler le maximum de zones, le premier joueur à en contrôler 3 gagne la partie.


## Specifications demandées

1. Configurer une équipe de 20 étudiants
2. Déployer chaque étudiant sur une des 5 zones de la partie
3. Les combattants se battent de manière "aléatoire" selon leur stratégie de jeu
4. Dès qu'une zone est contrôlée le jeu se met en pause et chaque joueur est libre de changer ses combattants de zone ou de stratégie de jeu
5. Dès que 3 zones sont contrôlées par un des deux joueurs, celui-ci gagne la partie
6. Créer une interface graphique pour la configuration des joueurs


## Dépendances

```bash
sudo apt-get install default-jre
```


## Jeu

```
MakeFile

USAGE

    make

COMMANDES 

    run (commande par défaut) : crée une archive .jar du jeu si elle n'existe pas et l'éxecute.

    clean : supprime tous les fichiers de compilation .class ainsi que l'archive .jar.

    doc : génère la documentation du projet avec ses différentes classes et méthodes sous forme de pages HTML Javadoc.

```


### Contributeurs

- Quentin LACOMBE
