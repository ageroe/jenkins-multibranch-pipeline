node {
   stage('Preparation') {
      git 'https://github.com/ageroe/jenkins-multibranch-pipeline.git'
   }
    try{
        stage('Build / Test') {
            bat 'gradle.bat build'
            bat 'gradle.bat jacocoTestReport'
        }
    } finally {
        stage ('Finish') {
            archiveArtifacts artifacts: 'build/libs/**/*.jar', fingerprint: true
            publishHTML([allowMissing: false, alwaysLinkToLastBuild: true, keepAll: true, reportDir: 'build/reports/tests/test', reportFiles: 'index.html', reportName: 'Test Report', reportTitles: ''])
            publishHTML([allowMissing: false, alwaysLinkToLastBuild: true, keepAll: true, reportDir: 'build/reports/jacoco/test/html', reportFiles: 'index.html', reportName: 'Code Coverage', reportTitles: ''])
        }
    }
}