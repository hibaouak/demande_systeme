# Utiliser une image Eclipse Temurin (maintenue par la communauté Adoptium)
FROM eclipse-temurin:17-jdk-alpine

# Créer un répertoire de travail
WORKDIR /app

# Copier le JAR
COPY target/*.jar app.jar

# Copier les certificats SSL
COPY zyn/ssl/localhost/keystore.p12 ./zyn/ssl/localhost/keystore.p12
COPY zyn/ssl/prod/keystore.p12 ./zyn/ssl/prod/keystore.p12

# Exposer le port
EXPOSE 8036

# Commande d'exécution
CMD ["java", "-jar", "app.jar"]