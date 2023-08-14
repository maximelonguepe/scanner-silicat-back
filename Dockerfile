# Utilisation d'une image de base Maven pour construire l'application
FROM maven:3.8.4-openjdk-17 AS build

# Définition du répertoire de travail dans le conteneur pour la construction
WORKDIR /app

# Copie du fichier pom.xml pour résoudre les dépendances
COPY pom.xml .

# Téléchargement des dépendances de l'application
RUN mvn dependency:go-offline

# Copie des sources de l'application dans le conteneur
COPY src ./src

# Construction de l'application
RUN mvn package -DskipTests

# Utilisation d'une image de base OpenJDK 17 pour exécuter l'application
FROM openjdk:17-jdk-alpine

# Répertoire de travail dans le conteneur
WORKDIR /app

# Copie du jar de l'application depuis l'étape de construction
COPY --from=build /app/target/scanner-silicat-back-0.0.1-SNAPSHOT.jar app.jar

# Exposition du port sur lequel l'application écoute
EXPOSE 8080

# Commande pour démarrer l'application lorsque le conteneur est lancé
CMD ["java", "-jar", "app.jar"]
