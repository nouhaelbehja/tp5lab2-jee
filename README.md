# microservice-product — TP Spring et Hibernate

## État du TP

Le projet initial contenait les dépendances Maven, les propriétés MySQL et trois
classes vides. Les étapes fournies du TP sont maintenant implémentées sous
`com.microservice.product` :

- `entity.Product` : entité JPA, table `products`, identifiant auto-incrémenté.
- `dao.IDao<T>` : contrat CRUD générique.
- `dao.ProductDaoImpl` : opérations Hibernate via `SessionFactory`.
- `config.HibernateConfig` : DataSource et SessionFactory.
- `config.TransactionConfig` : gestion des transactions Spring.
- `config.AppConfig` : détection des composants.
- `service.ProductService` et `ProductServiceImpl` : ajout avec validation du
  prix et lecture des produits, dans des transactions.
- `TestHibernate` : démarrage Spring et vérification explicite de la connexion.
- `MainApp` : ajout d'un produit puis affichage du nombre de produits.

Flux : `MainApp → ProductService → IDao<Product> → Hibernate → MySQL`.

Ce TP utilise Spring classique et Hibernate, sans Spring Boot ni contrôleur REST.
Le DAO propose les cinq opérations CRUD ; le service expose les deux opérations
demandées dans le scénario fourni.

## Préparer l'exécution

Après un clonage depuis GitHub, copier
`src/main/resources/application.properties.example` vers
`src/main/resources/application.properties`. Le fichier personnel contenant les
identifiants MySQL est ignoré par Git.

1. Utiliser le JDK 17 pour le projet et pour Maven. Dans IntelliJ, vérifier le
   Project SDK et le JRE du Maven Runner. Le Java par défaut du terminal peut
   être différent.
2. Démarrer MySQL sur `localhost:3306`.
3. Créer la base si elle n'existe pas, dans un client MySQL :

   ```sql
   CREATE DATABASE IF NOT EXISTS product_db;
   ```

4. Adapter `jdbc.username` et `jdbc.password` dans
   `src/main/resources/application.properties` aux identifiants MySQL locaux.
5. Recharger le projet Maven dans IntelliJ.
6. Exécuter `com.microservice.product.TestHibernate`, puis `MainApp` si la
   connexion réussit.

`hibernate.hbm2ddl.auto=update` permet à Hibernate de créer ou mettre à jour la
table `products` dans la base existante au démarrage. Le compte MySQL doit avoir
les droits nécessaires. Chaque exécution de `MainApp` ajoute un nouveau produit.

## Vérification de compilation

Dans PowerShell, depuis la racine du projet, avec le JDK installé localement :

```powershell
$env:JAVA_HOME = 'C:\Program Files\Java\jdk-17'
$env:Path = "$env:JAVA_HOME\bin;$env:Path"
mvn test
```

La compilation a été vérifiée avec succès avec Java 17. L'exécution de
`TestHibernate` et de `MainApp` a ensuite été validée localement avec Java 25 :
connexion MySQL, création de la table, insertion et lecture d'un produit.
Il n'y a pas de tests automatisés ; ces deux classes sont des programmes à
lancer manuellement avec MySQL disponible.
