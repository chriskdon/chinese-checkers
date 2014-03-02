# Intall APT Tools
sudo apt-get update
sudo apt-get install -y python-software-properties
sudo apt-get install -y software-properties-common

# Add Repositories
sudo add-apt-repository ppa:chris-lea/node.js
sudo add-apt-repository ppa:webupd8team/java
sudo apt-get update

# Install NodeJS
sudo apt-get install -y nodejs

# Install Orace Java 7
sudo apt-get install -y oracle-java7-installer

# Install Reverse Proxy
sudo apt-get install -y nginx

# Install Node Libraris
sudo npm -g install forever
sudo npm -g install http-proxy

# Stop Apache
sudo /etc/init.d/apache2 stop

# Load nginx conf