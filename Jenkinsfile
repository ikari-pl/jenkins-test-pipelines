@Library("jenkins-shared-library") _

node ("centos7") {
    checkout scm
    env.PACKAGE_VERSION = "1.0.${env.BUILD_NUMBER}"
    gitTag ${PACKAGE_VERSION}
