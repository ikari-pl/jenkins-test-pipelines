def projectName = "Test Pipelines"
def label = UUID.randomUUID().toString()

podTemplate(
  inheritFrom: 'default',
  label: label,
)

node(label) {
    try {
        stage('BUILD') {
            deleteDir()
            checkout scm
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
            sh 'echo $PACKAGE_LOCATION'
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
