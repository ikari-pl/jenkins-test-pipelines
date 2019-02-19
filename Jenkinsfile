node {
   checkout scm
   def branchName = sh(returnStdout: true, script: 'git rev-parse --abbrev-ref HEAD').trim()
   echo branchName
   echo BRANCH_NAME
   if (env.BRANCH_NAME == 'master') {
           echo "go ahead boys!"
   } else {
   input(message: 'Push to Repository?', id: 'push', ok: 'push')
   }
   env.PACKAGE_VERSION = 'tag_test123'
   gitTag(${PACKAGE_VERSION})
}
