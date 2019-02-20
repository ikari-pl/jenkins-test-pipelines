@Library("jenkins-shared-library@RELENG-4072") _

node ("centos7") {
    checkout scm
    env.PACKAGE_VERSION = "1.0"
    sh('git config --global user.email "releng@openx.org"')
    sh('git config --global user.name "jenkins"')
    sh('git tag -a rpm-${PACKAGE_VERSION} -m "tag for release"')
    sh('git push --tags')
    // gitTag "test-tag123"
}
