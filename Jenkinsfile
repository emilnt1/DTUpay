pipeline {
    agent any
    stages {
        stage('Build and Test') {
            steps {
                sh 'set -e'
                sh 'chmod +x -R ${env.WORKSPACE}'
                sh './build_and_run.sh'
            }
        }



    }
}