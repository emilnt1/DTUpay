pipeline {
    agent any
    stages {
        stage('Build and Test') {
            steps {
                sh '''#!/bin/bash
                    set -e
                    ./build_and_run.sh

                '''
            }
        }



    }
}