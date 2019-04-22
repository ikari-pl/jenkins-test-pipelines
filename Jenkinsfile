@Library("jenkins-shared-library") _

node ("centos7&&java") {
    withCredentials([file(credentialsId: 'id_rsa', variable: 'ID_RSA')]) {
        sh 'cp -f $ID_RSA ~/.ssh'
    }
    sh "touch ~/.ssh/known_hosts && ssh-keyscan github-proxy.master.openx.org >> ~/.ssh/known_hosts"
    sh 'git clone git@github-proxy.master.openx.org:dsspielm/delivery-broker-server.git'
    env.PACKAGE_VERSION = "tag.for.release"
    sh 'cd delivery-broker-server'
    gitTag
}
