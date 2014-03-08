# Chinese Checkers Vagrant Setup Tutorial

Vagrant will be our platform that essentially duplicates the server environment, so we can each work on our own separate instance.

The framework we are using is [Play 2.2.2](www.playframework.com)

**Installation**
1. Install [Virtual Box](https://www.virtualbox.org/wiki/Downloads)
2. Download [Vagrant](www.vagrantup.com/downloads)
3. Read the [getting started](http://docs.vagrantup.com/v2/getting-started/index.html) tutorial.

**Vagrant Setup**
1. Open a shell in the <VAGRANT_FOLDER>, then type 
2. `vagrant up`
3. `vagrant ssh`

**How To Run The Application**
Run `sudo play "~run 8080"` in the `/vagrant/server-application/ChineseCheckersServer` folder. The application should automatically reload and compile the files when you change them.

**Notes**
1. The server you run in the Vagrant VM should be running on `0.0.0.0:8080`. This will be mapped to `127.0.0.1:8080` on your local computer.
2. The folder in the vm `/vagrant` is mapped to the folder the where `vagrant up` was ran. So the current folder.
3. MySQL username is `user` and password is `root`
4. To start vagrant at each development session run `vagrant up`
5. DON'T FORGET to run `vagrant suspend` or `vagrant halt` at the end of each development session or it will keep running in the background.
