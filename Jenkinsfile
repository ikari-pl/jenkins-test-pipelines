@Library("jenkins-shared-library") _

node ("centos7&&java") {
    withCredentials([file(credentialsId: 'id_rsa', variable: 'ID_RSA')]) {
        sh 'cp -f $ID_RSA ~/.ssh'
    }
    sh "touch ~/.ssh/known_hosts && ssh-keyscan github-proxy.master.openx.org >> ~/.ssh/known_hosts"
    sh 'git clone git@github-proxy.master.openx.org:dsspielm/delivery-broker-server.git && cd delivery-broker-server'
    env.PACKAGE_VERSION = "tag.for.release"
    env.GIT_URL = "dsspielm/delivery-broker-server.git"
    sh 'cd delivery-broker-server && git config --global user.email "releng@openx.org && git config --global user.name "jenkins-openx" && git tag -a rpm-${PACKAGE_VERSION} -m "tag for release" && pwd && git push git@github-proxy.master.openx.org:${GIT_URL} -f --tags'
}
