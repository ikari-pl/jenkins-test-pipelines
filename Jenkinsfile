@Library("jenkins-shared-library@RELENG-4072") _

node ("centos7") {
    checkout scm
    env.PACKAGE_VERSION = "1.0.${env.BUILD_NUMBER}"
    echo "${env.GIT_URL}"
    withCredentials([usernamePassword(credentialsId: '8c2257a7-5035-422f-87e4-d0fea32219a1', passwordVariable: 'GIT_PASSWORD', usernameVariable: 'GIT_USERNAME')]) {
        sh('git config --global user.email "releng@openx.org"')
        sh('git config --global user.name "jenkins"')
        sh('git tag -a rpm-${PACKAGE_VERSION} -m "tag for release"')
        //sh('git push https://${GIT_USERNAME}:${GIT_PASSWORD}@github.corp.openx.com/daniel-spielman/test.git --tags')
        //sh('git push --tags')
    }
    // gitTag "test-tag123"
}
