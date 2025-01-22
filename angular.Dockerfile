# Utilisez une image de base appropriée
FROM node:18

# Définissez le répertoire de travail
WORKDIR /app

# Copiez les fichiers package.json et package-lock.json (si disponible)
COPY package*.json ./

# Installez les dépendances de l'application
RUN npm install

# Copiez le reste des fichiers de l'application
COPY . .

# Exposez le port
EXPOSE 4200

# Commande pour démarrer l'application
CMD ["npm", "start"]
