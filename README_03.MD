# 🕹️ Titre du projet : Battle Arena (prototype console)

## 🎯 Objectif du jeu

Deux joueurs s’affrontent avec leurs équipes respectives de trois personnages chacun.
Chaque personnage a un type, un nom unique, des points de vie (HP) et une arme.
Le but est de tuer tous les personnages de l’équipe adverse.

## ⚙ Structure du jeu

### Étape 1 : Création des équipes

Chaque joueur crée 3 personnages :

- Le joueur choisit le type et le nom de chaque personnage.
- Le nom doit être unique dans toute la partie.
- Le type doit être unique dans chaque équipe et détermine les points de vie de départ et l’arme du personnage.

Les types disponibles sont :
| Type | Description | Points de vie | Puissance de l’arme |
| ------------ | ----------------------- | ------------- | ------------------- |
| **Warrior**  | Attaquant équilibré | Moyens | Moyenne |
| **Magus**    | Peut soigner ses alliés | Élevés | Faibles |
| **Colossus** | Très résistant | Très élevés | Moyens |
| **Dwarf**    | Très fort mais fragile | Faibles | Très élevés |

Vous êtes libres de déterminer les valeurs exactes des points de vie et de la puissance des armes.

### Étape 2 : Le combat

Le jeu se déroule en tours successifs :

- Le joueur actif choisit un personnage de son équipe.

- Il choisit une action :
    - Attaquer un personnage de l’équipe adverse
    - Soigner un allié (si le type de personnage le permet, ex. Magus)

- Il choisit la cible (ennemi à attaquer ou allié à soigner).
    - Le programme exécute l’action : mise à jour des points de vie, affichage du résultat.
    - Les attaques et soins sont effectués via les armes ou les capacités spéciales des personnages.

### Étape 3 : Fin de partie

- Quand un personnage atteint 0 HP, il meurt définitivement.
- Si tous les personnages d’un joueur sont morts, l’autre joueur gagne.
- À la fin, le programme affiche :
    - Le gagnant
    - Le nombre de tours joués
    - Le statut de chaque personnage (nom, type, HP, etc.)

## 💡 Contraintes techniques

- Pas d’interface graphique. Le jeu doit se jouer dans le terminal (console).
- L’application doit être documentée en anglais (commentaires clairs).
- Le code doit être structuré pour être facilement compréhensible (idéal pour un stagiaire).
- Le but est un prototype fonctionnel, pas une version finale.

## Concepts clés à utiliser :

| Concept              | Exemple dans le jeu                                                  |
|----------------------|----------------------------------------------------------------------|
| **Classe**           | `Character`, `Weapon`, `Player`                                      |
| **Héritage**         | `Warrior`, `Magus`, `Colossus` héritent de `Character`               |
| **Interface**        | `Healer`, `Attacker` définissant des capacités spécifiques           |
| **Classe abstraite** | `Character` (force les sous-classes à implémenter `action()`)        |
| **Polymorphisme**    | Liste de `Character` contenant différents types (appel à `action()`) |
| **Encapsulation**    | Gestion interne de la santé et des dégâts                            |
| **Composition**      | Un `Character` possède une `Weapon`                                  |
