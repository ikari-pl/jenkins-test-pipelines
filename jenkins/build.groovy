def projectName = "Test Pipelines"
def label = UUID.randomUUID().toString()

podTemplate(
  inheritFrom: 'default',
  label: label,
  containers: [
    containerTemplate(
      name: 'deploy-tools',
      image: 'gcr.io/ox-registry-prod/orange/deploy-tools:ea0b368',
      ttyEnabled: true,
      command: 'cat',
    )
  ]
){
node(label) {
    try {
        stage('BUILD') {
            container(name: 'deploy-tools'){
            deleteDir()
            checkout scm
            sh 'make clean compile'
        }
        }
        stage ('TEST') {
            container(name: 'deploy-tools'){
            sh 'make test'
        }
        }
        stage('PACKAGE') {
            container(name: 'deploy-tools'){
            sh 'make package'
            archiveArtifacts artifacts: '**/target/**/*.jar, **/target/**/*.war', onlyIfSuccessful: true
            env.PACKAGE_VERSION = readFile 'PACKAGE_VERSION.txt'
            env.PACKAGE_LOCATION = readFile 'PACKAGE_LOCATION.txt'
            sh 'echo $PACKAGE_LOCATION'
            }
        }
        if (env.BRANCH_NAME == 'master' || env.BRANCH_NAME == 'develop') {
        stage('UPLOAD ARTIFACT') {
                sh 'for pkg in $PACKAGE_LOCATION; do ls $pkg;done'
                sh 'echo "UPLOADDDDDDDDDDDDD ARTIFAAAAAAAACTS!"'
                //sh 'for pkg in $PACKAGE_LOCATION; do ox-artifact cp $pkg openx-apps:/7/testing/packages/ ;done'
                }
        }
   }
   catch (err) {
   sh 'echo "BUILD FAILED"'
   throw(err)
   }
  }
}
