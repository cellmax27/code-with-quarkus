#FROM ubuntu:latest
# Utilisez une image de base appropriée
FROM openjdk:17

LABEL authors="Arno"

# Installez Maven
RUN apt-get update && apt-get install -y maven

# Ajoutez Maven au PATH
ENV PATH="/usr/share/maven/bin:${PATH}"

# Ajoutez le répertoire de l'application
COPY . /app

# Changez de répertoire
WORKDIR /app

# Commande pour exécuter l'application
CMD ["mvn", "clean", "install"]

ENTRYPOINT ["top", "-b"]