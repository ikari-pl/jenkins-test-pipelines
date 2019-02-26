#!/usr/bin/groovy

@Library('jenkins-shared-library') _

def componentName = 'broker'
def buildNum = "${env.BUILD_NUMBER}"

// Define k8s podTemplate for agent
def label = "${componentName}-${UUID.randomUUID().toString()}"
podTemplate(
  cloud: 'k8s-prod',
  label: label,
  containers:[
    // The jnlp containerTemplate definition doesn't need to be defined because it's used by default...
    // ...unless you want to use a different image for the agent
    // All of these containers are spun up on the same k8s pod and share a common working directory
    // Therefore, any files produced on one container, is automatically available on the next.
    containerTemplate(name: 'jnlp', image: 'us.gcr.master.openx.org/ox-registry/jenkins/jenkins-jnlp', args: '${computer.jnlpmac} ${computer.name}'),
    containerTemplate(name: 'centos7', image: 'us.gcr.master.openx.org/ox-registry/jenkins-agent-java:centos7', command: 'cat', ttyEnabled: true),
    containerTemplate(name: 'centos6', image: 'us.gcr.master.openx.org/ox-registry/jenkins-agent-java:centos6', command: 'cat', ttyEnabled: true),
  ]
)

{
    node(label) {
        try {
            stage('BUILD') {
                deleteDir()
                withCredentials([file(credentialsId: 'id_rsa', variable: 'ID_RSA')]) {
                    sh 'cp -f $ID_RSA ~/.ssh'
                }
                checkout scm
                if (config.RUN_BOOTSTRAP) {sh './bootstrap'}
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
                sh 'ox-artifact cp ${PACKAGE_LOCATION} openx-apps:/6/testing/packages/'
                sh 'ox-artifact cp ${PACKAGE_LOCATION} openx-apps:/7/testing/packages/'
                }
            }

        if (env.BRANCH_NAME == 'master' || env.BRANCH_NAME == 'develop') {
        stage ('QUEUE FOR DEPLOY') {
            build job: "${config.componentName}-queue", parameters: [string(name: 'VERSION', value: "${PACKAGE_VERSION}")], wait: false
            }
        }
      catch (err) {
        notifySlack('FAILED', '#failed-builds')
        throw(err)
      }
    }
  }
}
