pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                make compile
            }
        }
        stage('Test') {
            steps {
                make test
            }
        }
        stage('Deploy') {
            steps {
                make package
            }
        }
    }
}
