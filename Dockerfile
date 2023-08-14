# Utilisation d'une image de base OpenJDK 17
FROM openjdk:17-jdk-alpine

# Répertoire de travail dans le conteneur
WORKDIR /app

# Copie du jar de l'application dans le conteneur
COPY target/scanner-silicat-back-0.0.1-SNAPSHOT.jar app.jar


# Exposition du port sur lequel l'application écoute
EXPOSE 8080

# Commande pour démarrer l'application lorsque le conteneur est lancé
CMD ["java", "-jar", "app.jar"]