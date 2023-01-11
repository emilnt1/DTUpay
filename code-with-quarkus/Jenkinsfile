pipeline {
    agent any
    stages {
        stage('Test') {
            steps {
                sh '''
                    set -e
                    mvn test
                '''
            }
        }
        stage('Build Docker Compose'){
            steps {
                sh '''
                    mvn package
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