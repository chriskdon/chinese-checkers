# Chinese Checkers Vagrant Setup Tutorial

Vagrant will be our platform that essentially duplicates the server environment, so we can each work on our own separate instance.

**Installation**
1. Install [Virtual Box](https://www.virtualbox.org/wiki/Downloads)
2. Download [Vagrant](www.vagrantup.com/downloads)
3. Read the [getting started](http://docs.vagrantup.com/v2/getting-started/index.html) tutorial.

**Vagrant Setup**
1. Open a shell in the <VAGRANT_FOLDER>, then type 
2. `vagrant box add raring64 http://cloud-images.ubuntu.com/vagrant/raring/current/raring-server-cloudimg-amd64-vagrant-disk1.box`
3. `vagrant up`
4. `vagrant ssh`
5. 