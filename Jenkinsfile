pipeline {
    agent any
    stages {
        stage('Test') {
            steps {
                sh '''
                    set -e
                    chmod +x -R ${env.WORKSPACE}
                    ./build_and_run.sh
                '''
            }
        }
        stage('Build Docker Compose'){
            steps {
                sh '''
                    pushd code-with-quarkus
                    docker-compose down
                    docker-compose build
                '''
            }
        }
        stage('Run Docker Compose'){
            steps {
                sh '''

                    docker-compose up -d
                '''
            }
        }
    }
}