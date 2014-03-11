### Intall APT Tools
sudo apt-get update
sudo apt-get install -y python-software-properties
sudo apt-get install -y software-properties-common

# Add Repositories
sudo add-apt-repository ppa:chris-lea/node.js
sudo add-apt-repository ppa:webupd8team/java
sudo apt-get update

### Install NodeJS
#sudo apt-get install -y nodejs

### Install Orace Java 7
echo debconf shared/accepted-oracle-license-v1-1 select true | sudo debconf-set-selections
echo debconf shared/accepted-oracle-license-v1-1 seen true | sudo debconf-set-selections
sudo apt-get install -y oracle-java7-installer

### Install Scala
sudo apt-get install -y scala
cd /tmp
wget http://apt.typesafe.com/repo-deb-build-0002.deb
sudo dpkg -i repo-deb-build-0002.deb
sudo apt-get update
sudo apt-get install -y sbt

### Install Unzip
sudo apt-get install -y unzip

### Get Play 2.2.2
cd /tmp
mkdir -p ~/dev/frameworks/
wget http://downloads.typesafe.com/play/2.2.2/play-2.2.2.zip
sudo unzip -d /opt play-2.2.2.zip
sudo chmod a+x /opt/play-2.2.2/play
sudo ln -s /opt/play-2.2.2/play /usr/local/bin/play

### Install MySQL
if [[ $1 == "dev" || $1 == "development" ]]; then
	sudo debconf-set-selections <<< 'mysql-server mysql-server/root_password password root'
	sudo debconf-set-selections <<< 'mysql-server mysql-server/root_password_again password root'
fi
sudo apt-get install -y mysql-server

### Install Node Libraris
#sudo npm -g install forever
#sudo npm -g install http-proxy

# Environment Setup
if [[ $1 == "dev" || $1 == "development" ]]; then
	#------ DEVELOPMENT ------#
	
	sudo apt-get install -y language-pack-en

	echo "DEVELOPMENT Created"
else
	#------ PRODUCTION ------#

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
	# alias pullChineseCheckers='rm -rf ~/apps/chinese-checkers; mkdir ~/apps/chinese-checkers/; cd /tmp/; sudo git clone -b develop https://github.com/kubasub/chinese-checkers; cp -R /tmp/chinese-checkers/server/server-application/* ~/apps/chinese-checkers/; rm -rf /tmp/chinese-checkers/; cd ~/;'

	echo "PRODUCTION Created"
fi


