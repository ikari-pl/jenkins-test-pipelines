@Library("jenkins-shared-library@RELENG-4072") _

node ("centos7") {
    checkout scm
    gitTag{ PACKAGE_VERSION = "test-tag123"}
}
