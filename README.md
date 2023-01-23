# Bank Account
J'ai créé une base de données Postgres nommé mybankaccount
le fichier schéma.sql contient le modele de la base de données et la création des tables
Le fichier data.sql contient des données à ajouter dans les tables dès qu'on lance le projet
le projet est décrit par SWAGGER.
le projet contient 3 api:
    - /deposit : un API post permet de verser de l'argent dans le compte 
    - /withdraw : un API post permet de retirer de l'argent d'un compte (si c'est possible) 
    - /{accountNo}/print : un API get permet d'afficher toutes les transactions effectuées sur le compte 
le projet est couvert par des tests unitaires 
