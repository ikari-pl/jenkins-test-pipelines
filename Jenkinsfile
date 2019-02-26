@Library("jenkins-shared-library") _

javaBuildPipeline_c7c6 {
        componentName = "test"
        agentLabel = "java"
    }
node ("centos7") {
        gitTag "${PACKAGE_VERSION}"
    }
