# -*- mode: ruby -*-
# vi: set ft=ruby :

Vagrant::configure("2") do |config|
  # All Vagrant configuration is done here. The most common configuration
  # options are documented and commented below. For a complete reference,
  # please see the online documentation at vagrantup.com.

  # Every Vagrant virtual environment requires a box to build off of.
   config.vm.box = "deb/wheezy-amd64"
  # below mounted path is being used in org.ops4j.pax.url.mvn.cfg
   config.vm.synced_folder ENV['HOME'] + "/.m2" ,  "/home/vagrant/.m2", type: "nfs"
   config.vm.synced_folder ENV['HOME'] + "/Downloads", "/home/vagrant/Downloads", type: "nfs"


  # The url from where the 'config.vm.box' box will be fetched if it
  # doesn't already exist on the user's system.
  #  config.vm.box_url = "http://files.vagrantup.com/precise32.box"


  config.vm.define "servicemix", primary: true do |servicemix|
    #config.vm.box_url = "http://hudson2-test.colo.elex.be/wheezy64.box"
    #config.vm.box = "wheezy64mlx"
    servicemix.vm.provider "virtualbox" do |vb|
      vb.customize ["modifyvm", :id, "--memory", 4096]
    end

    servicemix.vm.network "private_network", ip: "10.0.0.100"
    servicemix.vm.host_name = "servicemix"

    servicemix.vm.provision :shell, inline: $servicemix_script
    # servicemix.vm.provision :shell, inline: $smx_541_script
  end



end


$servicemix_script = <<END

  sudo echo "deb http://aptmaster.colo.elex.be unstable main contrib non-free \n deb http://aptmaster.colo.elex.be wheezy_test main contrib non-free" >/etc/apt/sources.list.d/elex.list

  sudo apt-get update
  sudo apt-get -y --force-yes install oracle-java8-jdk

  wget http://repo.fusesource.com/nexus/content/repositories/releases/org/apache/servicemix/apache-servicemix/4.4.1-fuse-07-11/apache-servicemix-4.4.1-fuse-07-11.tar.gz
  tar xvfz apache-servicemix-4.4.1-fuse-07-11.tar.gz
  cp /vagrant/boot/servicemix/etc/* apache-servicemix-4.4.1-fuse-07-11/etc
  cp /vagrant/boot/servicemix/deploy/* apache-servicemix-4.4.1-fuse-07-11/deploy
  cp /vagrant/boot/servicemix/bin/* apache-servicemix-4.4.1-fuse-07-11/bin

  sudo chown -R vagrant apache-servicemix-4.4.1-fuse-07-11
  sudo cp /vagrant/boot/servicemix/servicemix.init /etc/init.d/servicemix
  sudo chmod +x /etc/init.d/servicemix
  sudo update-rc.d servicemix defaults 99
  sudo /etc/init.d/servicemix start


END


$smx_541_script = <<END

  sudo echo "deb http://aptmaster.colo.elex.be unstable main contrib non-free \n deb http://aptmaster.colo.elex.be wheezy_test main contrib non-free" >/etc/apt/sources.list.d/elex.list

  sudo apt-get update
  sudo apt-get -y --force-yes install oracle-java8-jdk

  cp /home/vagrant/Downloads/apache-servicemix-5.4.1.zip /home/vagrant
  unzip apache-servicemix-5.4.1.zip
  sudo chown -R vagrant apache-servicemix-5.4.1
  # cp /vagrant/boot/servicemix/etc/org.ops4j.pax.url.mvn.cfg apache-servicemix-5.4.1/etc
  # cp /vagrant/boot/servicemix/etc/org.apache.karaf.features.cfg apache-servicemix-5.4.1/etc
  # cp /vagrant/boot/servicemix/deploy/* apache-servicemix-5.4.1/deploy

  # tanuki wrapper used according to blog post
  # http://ddelizia.blogspot.be/2013/05/install-servicemix-442-as-linux-service.html
  cp /vagrant/boot/servicemix/bin_smx541/* apache-servicemix-5.4.1/bin
  cp /vagrant/boot/servicemix/etc_smx541/karaf-wrapper.conf apache-servicemix-5.4.1/etc
  cp /vagrant/boot/servicemix/etc_smx541/org.ops4j.pax.url.mvn.cfg  apache-servicemix-5.4.1/etc
  cp /vagrant/boot/servicemix/lib_smx541/* apache-servicemix-5.4.1/lib

  # sudo cp /vagrant/boot/servicemix/smx_541.init /etc/init.d/servicemix
  sudo ln -s /home/vagrant/apache-servicemix-5.4.1/bin/karaf-service /etc/init.d/
  sudo chmod +x /etc/init.d/karaf-service
  sudo update-rc.d karaf-service defaults 99
  sudo /etc/init.d/karaf-service start

END
