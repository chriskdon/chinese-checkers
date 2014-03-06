### Intall APT Tools
sudo apt-get update
sudo apt-get install -y python-software-properties
sudo apt-get install -y software-properties-common

# Add Repositories
sudo add-apt-repository ppa:chris-lea/node.js
sudo add-apt-repository ppa:webupd8team/java
sudo apt-get update

### Install NodeJS
sudo apt-get install -y nodejs

### Install Orace Java 7
echo debconf shared/accepted-oracle-license-v1-1 select true | sudo debconf-set-selections
echo debconf shared/accepted-oracle-license-v1-1 seen true | sudo debconf-set-selections
sudo apt-get install -y oracle-java7-installer

### Install MySQL
if [[ $1 == "dev" || $1 == "development" ]]; then
	sudo debconf-set-selections <<< 'mysql-server mysql-server/root_password password root'
	sudo debconf-set-selections <<< 'mysql-server mysql-server/root_password_again password root'
fi
sudo apt-get install -y mysql-server

### Install Node Libraris
sudo npm -g install forever
sudo npm -g install http-proxy


# Environment Setup
if [[ $1 == "dev" || $1 == "development" ]]; then
	#------ DEVELOPMENT ------#
	echo "Running DEVELOPMENT"
else
	#------ PRODUCTION ------#
	echo "Running PRODUCTION"

	sudo apt-get install -y git
	
	### Stop Apache
	sudo /etc/init.d/apache2 stop
	
	### Load nginx 
	sudo apt-get install -y nginx

	# Main nginx conf
	cd /etc/nginx
	sudo rm nginx.conf
	sudo wget https://raw.github.com/kubasub/chinese-checkers/develop/server/setup/nginx/codecanister.com.conf

	# Codecanister conf
	cd /etc/nginx/conf.d
	sudo rm codecanister.com.conf
	sudo wget https://raw.github.com/kubasub/chinese-checkers/develop/server/setup/nginx/nginx.conf

	# Restart
	sudo service nginx restart

	# Install Latest Chinese Checkers App Version
	alias pullChineseCheckers = 'rm -rf ~/apps/chinese-checkers/; mkdir ~/apps/chinese-checkers/; sudo git clone -b develop https://github.com/kubasub/chinese-checkers; cp -r /tmp/chinese-checkers/server/server-application/* ~/apps/chinese-checkers/; sudo forever restartall; rm -rf /tmp/chinese-checkers/; cd ~/;'
fi


