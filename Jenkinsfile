#!/usr/bin/groovy

@Library('jenkins-shared-library') _

node("centos7&&java") {
    try {
        stage('BUILD') {
            deleteDir()
            withCredentials([file(credentialsId: 'id_rsa', variable: 'ID_RSA')]) {
                sh 'cp -f $ID_RSA ~/.ssh'
            }
            sh 'git config --global http.sslverify false'
            checkout scm
            sh 'make clean compile'
        }

        stage ('TEST') {
            sh 'make test'
        }

        stage('PACKAGE') {
            sh 'make package'
            archiveArtifacts artifacts: '**/target/**/*.jar, **/target/**/*.war', onlyIfSuccessful: true
            env.PACKAGE_VERSION = readFile 'PACKAGE_VERSION.txt'
            env.PACKAGE_LOCATION = readFile 'PACKAGE_LOCATION.txt'
        }
            if (env.BRANCH_NAME == 'master' || env.BRANCH_NAME == 'develop') {
                stage('UPLOAD ARTIFACT') {
                gitTag "${PACKAGE_VERSION}"
                for(pkg in "${PACKAGE_LOCATION}") {
                sh 'ox-artifact cp pkg openx-apps:/7/testing/packages/'
            }
          }
        }
     
        if (env.BRANCH_NAME == 'master' || env.BRANCH_NAME == 'develop') {
        stage ('QUEUE FOR DEPLOY') {
            build job: "${config.componentName}-queue", parameters: [string(name: 'VERSION', value: "${PACKAGE_VERSION}")], wait: false
            }
          }
        }
   catch (err) {
   notifySlack('FAILED', '#failed-builds')
   throw(err)
   }
}
