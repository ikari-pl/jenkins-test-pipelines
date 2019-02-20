@Library("jenkins-shared-library@RELENG-4072") _

node ("centos7") {
    checkout scm
    env.PACKAGE_VERSION = "1.0"
    sh('git tag -a rpm-${PACKAGE_VERSION')
    sh('git push --tags')
    // gitTag "test-tag123"
}
