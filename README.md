# AnnuaireJava
Annuaire d'entreprise multi sites et multi services en Java, avec accès utilisateur libre et accès administrateur.

## Sujet :
Dans le cadre de cette évaluation vous allez créer une application d’annuaire entreprise.
Cette application doit être architecturée ainsi :
- Base de donnée
- Manipulation des données au travers d’une API
- Interface utilisateur sur une application Lourde (Winform/WPF/Swing/JavaFX etc.)

## Contexte :
Vous êtes développeur dans une industrie aggro alimentaire qui s’étend sur le territoire Français. L’entreprise est en
croissance et aujourd’hui elle est composée de
- 5 sites dans différentes villes
    - Paris : Siège administratif
    - Nantes : site de Production
    - Toulouse : site de Production
    - Nice : site de Production
    - Lille : site de Production
- Plus de 1000 salariés

A ce titre il est nécessaire de fournir aux utilisateur une application permettant de connaitre instantanément le numéro
de téléphone d’un collaborateur.

Les roles dans l’application seront :
- Visiteur : Afficher les information, recherche
- Administration : Ajout / Modification / Suppression d’information. Un accès « secret » à l’application via une combinaison de touche qui demande ensuite un mot de passe.

L’administrateur doit pouvoir faire :
- CRUD des sites : champ « ville » uniquement
- CRUD des services : Comptabilité, production, accueil, informatique, commercial etc.
- CRUD des salariés : Nom ,prénom, téléphone fixe, téléphone portable, email, service (association), site
(association)

Le visiteur doit pouvoir afficher la fiche salarié et réaliser une recherche :
- Par nom de salarié : saisir X lettres pour afficher les salariés correspondant
- Par site via une liste des sites
- Par service via une liste des services

La fiche salarié affiche toutes les informations du salarié (nom, prénom, téléphone fixe, téléphone portable, email,
service, site)

### Indications supplémentaires :
Cet exercice doit être réalisé seul, mais toujours dans un esprit d’équipe. On reste solidaire et soutient ses pairs.

## Travail demandé
### Code
Développer une API simplifiée permettant d’interagir avec les données d’une base de données (Select, Insert, Update,
Delete).  
La base de données peut être un fichier local (SQLite, Sérialisation d’objet, Excel, CSV, MS Access etc.) ou bien distante (MySql, SQLServer, MongoDB etc.) du moment que l’API peut y accéder.  
Dans tous les cas plusieurs « visiteurs » peuvent ouvrir l’application en accès concurrent en lecture.  
Pour l’administrateur, si l’accès à la base de données en modification est bloquant (fichier CSV par exemple) alors prévoir un message de refus pour le second administrateur.
### Ecrit
Un cahier de test complet couvrant l’ensemble des fonctionnalités développées
### Oral
Pendant 10 minutes, apporter les preuves de fonctionnement de l’application, puis ensuite une série de questions vous sera posée pendant environ 15 minutes.

## Conseils
Vous aurez à valider vos choix technologiques par votre pilote (le langage doit être Java ou C#).  
Vous pouvez versionner votre code : vous vous prémunirez ainsi d’un incident technique (vol/panne de votre pc ..)  
Pensez à tester les différents cas de figure : Ecrivez vos scénarios de test (sur une seule ligne) afin de les rejouer avant la
soutenance orale.  
Pour votre cahier de test vous pouvez vous inspirer des conseils livrés dans cette vidéo : https://youtu.be/SM0R_pEJPys

## Grille d'évaluation

| **Category**                  | **Description**                                                                                 |
|-------------------------------|-----------------------------------------------------------------------------------------------|
| Partie 1 : API                | Une API est présente et fonctionnelle                                                          |
| Partie 1 : Accès              | L'application est accessible en visiteur sans action particulière<br>L'application est accessible en administrateur avec un contrôle d'accès |
| Partie 1 : CRUD Site          | Il est possible d'ajouter un site, de modifier le nom et de le supprimer seulement si aucun salarié n'est affecté à ce site |
| Partie 1 : CRUD Service       | Il est possible d'ajouter un service, de modifier le nom et de le supprimer seulement si aucun salarié n'est affecté à ce service |
| Partie 1 : CRUD Salarié       | Il est possible d'ajouter / modifier / supprimer un salarié                                      |
| Partie 2 : Recherche          | Il est possible de faire une recherche selon 3 critères : par site, par service, par nom        |
| Partie 2 : Consultation       | Il est possible de consulter la fiche du salarié avec toutes les informations                   |
| Partie 3 : Code               | Le code est en Objet, il est bien organisé, suffisamment commenté (ni trop ni trop peu)         |
| Test & Recette                | Un cahier de test complet est livré, il est cohérent                                            |
| Présentation                  | La prise de parole est juste et maîtrisée.<br>Niveau technique convaincant                      |
