####
#!/bin/ksh

# Installer les prérequis
sudo apt update
sudo apt install openjdk-11-jdk maven nodejs podman postman edge

# Télécharger Eclipse
wget https://download.eclipse.org/eclipse/downloads/drops/R-4.24-SR1-202309271000/release/eclipse-inst-linux64.tar.gz -O /tmp/eclipse.tar.gz

# Décompresser Eclipse
tar xzf /tmp/eclipse.tar.gz -C /opt/eclipse

# Créer un lien symbolique vers Eclipse
sudo ln -s /opt/eclipse/eclipse /usr/local/bin/eclipse