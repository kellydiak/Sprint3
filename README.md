# Compte Rendu : Kotlin Monsters

## <ins>I/ Introduction

> Le jeu `Kotlin Monsters` est inspiré de Pokemon. Nous incarnons un dresseur polyvalent et plein de vie !
> Le projet est réparti en (2/3 sprints) pour nous apprendre à travailler de façon ordonnée et autonome. Le but : mobiliser toutes nos connaissances vues jusqu'à présent.

> `Objectifs` : 
> - poo
> - Travailler en mode projet
> - Mise en place de bonnes pratiques (_documentation, commentaires_ ..)
> - Résolution de problèmes et bugs
> - Réalisation de tests
> - Utilisation des DAO

> `Technologies` : 
> - Kotlin ..
> - Gradle
> - MariaDB
> - JUnit5
> - Java DataBase Connector
> - IntelliJ
> - Git
> - GitHub
> - UML

---

## <ins>II/ Développement du projet

### <mark>Sprint 1 :

> Cette partie consiste à créer toute la base du jeu, son noyau. Nous y retrouvons toutes les fonctions basiques pour faire tourner le jeu.

### Configuration : 

- Nous ajoutons le proxy (ou pas) dans le fichier `gradle.properties`.

- Nous importons les ressources utiles en téléchargeant le fichier zip. Puis, nous le décompressons dans le dossier `../main/resources`. 
L'intérieur du zip contient les **ASCII-Art** des monstres.

- En retournant dans `../main/kotlin`, nous créons tous les packages afin de regrouper des classes communes.

### Créations des classes :

- ### Entraineur
  - Il représente un dresseur dans le jeu, c'est notre point d'entrée qui nous permettra de réaliser
  les différentes actions.
  - Il possède des attributs propres à lui et une méthode `.afficheDetail()`, qui permet d'afficher les informations
  du dresseur.

- ### EspeceMonstre
  - xyz

- ### IndividuMonstre
  - xyz

- ### Item
  - xyz

- ### Badge
    - xyz

- ### Utilisable
  - lol
  
- ### MonsterKube
  - xyz

- ### CombatMonstre
  - xyz

- ### Partie
    - xyz

A la fin ce sprint, nous pouvons : 

- [x] Se déplacer dans différentes zones.
- [x] Rencontrer des monstres sauvages.
- [x] Combattre ou capturer des monstres sauvages.
- [x] Consulter et organiser son équipe de monstres.
- [x] Utiliser des objets pendant et hors combat.

### <mark>Sprint 3 :

> Cette partie consiste à créer 


[ mettre du contenu ]: #

### Configuration :

- Si pas encore fait, nous installons mariaDB.
- Nous créons la base de données dans intellij et nous la connectons en renseignant les informations.
- Puis, nous n'oublions pas de créer un fichier `tables.sql` dans le dossier `../../main/resources`.
- Il est important d'écrire tout ce que nous faisons dans `tables.sql`car la console n'est disponible qu'en local (_tout s'efface en la fermant_).

- ### Etape 2
    - Nous créons les tables en nous basant sur les classes existantes.
    - Seules : `Entraineur`, `EspeceMonstre`, `IndividuMonstre`, `Zone` & `Zone_EspeceMonstre` sont créés.
    - Pour mieux visualiser le projet, on télécharge et modifie le fichier `TODO ERD.puml` afin de faire correspondre les tables.

- ### Etape 3
  - On insère 3 entraineurs dans la table `Entraineur`.
  - On insère les espèces de monstre dans la table `EspeceMonstre`.
  - On insère les individus de monstre dans la table `IndividuMonstre` en faisant attention à quel entraineur sont ils reliés.

- ### Etape 4 
  - Pour rendre nos manipulations fluides, nous importons `Java DataBase Connector` et le plaçons les informations dans le fichier `build.gradle.kts`.
  - Cela va nous permettre d'envoyer des <ins>requêtes SQL</ins>, <ins>récupérer les résultats</ins> et <ins>gérer les transactions</ins>.
  - Nous créons un package JDBC dans lequel nous placons notre classe BDD.
  - La classe `BDD` gère les connexions et l'exécution des requêtes, on n'oublie pas de configurer les informations de connexion.

- ### Etape 5
  - Nous réalisons un test unitaire avec `JUnit5`. Pour vérifier si notre base de donées peut communiquer avec <ins>Kotlin</ins>.
  - La table `Entraineur` sert d'exemple pour ce test.
  
- ### Etape 6
  - On crée un `Composant d'accès aux données` pour centraliser les interactions avec la base.
  - Dans notre projet les tables : `Entraineur`, `EspeceMonstre`, `IndividuMonstre` possèdent un DAO.
  - On y renseigne dedans les opérations CRUD[^1]
[^1]: create, read, update, delete
  
- ### Etape 7
  - On établit une connexion à la base de données.
  - On teste si notre joueur se créer automatiquement grâce à la méthode `.save()`.

- ### Etape 8
  - Création des DAO pour `EspeceMonstre` & `IndividuMonstre`.

- ### Etape 9 
  - Création de l'EspeceMonstre `IceeFreezy`.

A la fin ce sprint, nous pouvons :

- [x] Intégrer d’une BDD via JDBC.
- [x] Gestionner des sauvegardes via fichiers.

---

## III/ <ins>Résultats et tests :

<p style="text-align:center">SPRINT 1</p>

![image](images/couleurTest1.png)

![image](images/couleurTest2.png)

![image](images/entraineurTest.png)

![image](images/especeMonstreTest.png)

![image](images/individuMonstreTest1.png)

![image](images/individuMonstreTest2.png)

![image](images/individuMonstreTest3.png)

![image](images/individuMonstreTest4.png)

![image](images/badgeTest.png)

![image](images/monsterKubeTest1.png)

![image](images/monsterKubeTest2.png)

<p style="text-align:center">SPRINT 3</p>

![image](images/diagramme2classe.png)

![image](images/BDDtest.png)

![image](images/listeEntraineur.png)

![image](images/savefunction.png)

---

## IV/ <ins>Bilan et conclusion :

> Le projet étant guidé pour favoriser la compréhension et l'apprentissage ..