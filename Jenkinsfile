pipeline {
    agent any
    stages {
        stage('Build and Test') {
            steps {
                sh '''#!/bin/bash
                    set -e
                    #sudo find -type f -exec chmod 755 {} \;
                    find . -name "*.sh" -exec chmod 755 {} +
                    ./build_and_run.sh

                '''
            }
        }



    }
}