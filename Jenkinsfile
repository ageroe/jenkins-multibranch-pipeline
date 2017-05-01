node {
   stage('Preparation') {
      git 'https://github.com/ageroe/jenkins-multibranch-pipeline.git'
   }
    try{
        stage('Build / Test') {
            bat 'gradle.bat build'
        }
    } finally {
        stage ('Finish') {
            archiveArtifacts artifacts: 'build/libs/**/*.jar', fingerprint: true
            publishHTML([allowMissing: false, alwaysLinkToLastBuild: true, keepAll: true, reportDir: 'build/reports/tests/test', reportFiles: 'index.html', reportName: 'BigMoneySalesApp Test Report', reportTitles: ''])
        }
    }
}