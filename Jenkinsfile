@Library("jenkins-shared-library") _

node ("centos7&&erlang") {
    withCredentials([file(credentialsId: 'id_rsa', variable: 'ID_RSA')]) {
        sh 'cp -f $ID_RSA ~/.ssh'
    }
    checkout scm
    gitTag "test-tag123"
}
