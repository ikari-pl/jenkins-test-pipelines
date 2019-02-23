@Library("jenkins-shared-library@RELENG-4072") _

node ("centos7") {
    checkout scm
    env.PACKAGE_VERSION = "1.0.${env.BUILD_NUMBER}"
    sh('git config --get remote.origin.url | cut -b 9- > git_remote_url')
    env.GIT_URL = readFile 'git_remote_url'
    echo GIT_URL
    withCredentials([usernamePassword(credentialsId: '8c2257a7-5035-422f-87e4-d0fea32219a1', passwordVariable: 'GIT_PASSWORD', usernameVariable: 'GIT_USERNAME')]) {
        sh('git config --global user.email "releng@openx.org"')
        sh('git config --global user.name "jenkins"')
        sh('git tag -a rpm-${PACKAGE_VERSION} -m "tag for release"')
        sh('git push https://${GIT_USERNAME}:${GIT_PASSWORD}@${GIT_URL} --tags')
    }
    // gitTag "test-tag123"
}
