pipeline {
    agent any
    stages {
        stage('Build and Test') {
            steps {
                sh 'set -e'
                sh './build_and_run.sh'
            }
        }



    }
}