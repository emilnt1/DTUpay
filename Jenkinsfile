pipeline {
    agent any
    stages {
        stage('Build and Test') {
            steps {
                sh '''#!/bin/bash
                    set -e
                    sudo -type f -exec chmod 755 {} \;
                    ./build_and_run.sh

                '''
            }
        }



    }
}