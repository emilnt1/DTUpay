pipeline {
    agent any
    stages {
        stage('Build and Test') {
            steps {
                sh '''#!/bin/bash
                    set -e

                    find . -name "*.sh" -exec chmod 755 {} +
                    ./build_and_run.sh

                '''
            }
        }




    }
    post {
            aborted {
                script{
                  echo "Damn it. I was aborted!"
                  sudo kill -9 $(sudo lsof -t -i:8080)
                }
            }
        }
}