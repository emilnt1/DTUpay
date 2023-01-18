pipeline {
    agent any
    stages {
        stage('Test backend') {
            steps {
                sh '''#!/bin/bash
                    set -e
                    chmod +x build_and_run.sh

                    pushd code-with-quarkus
                    mvn clean package

                    # Start the server in the background so that the
                    # shell script is not blocked and can execute the tests
                    java -jar target/quarkus-app/quarkus-run.jar &

                    # Remember the process id of the server process
                    # so that we can shutdown the server after the tests
                    # are run.
                    # Later, when we use docker, we are going to have better
                    # options available.
                    server_pid=$!

                    # Install a hook that on err or on normal exit of this script,
                    # the server is killed, so that we can run the script again
                    trap 'kill $server_pid' err exit


                '''
            }
        }
        stage('Build Docker Compose'){
            steps {
                sh '''#!/bin/bash
                    pushd code-with-quarkus
                    docker-compose down
                    docker-compose build
                '''
            }
        }
        stage('Run Docker Compose'){
            steps {
                sh '''#!/bin/bash

                    docker-compose up -d
                    popd
                '''
            }
        }
        stage('Run end to end test'){
            steps {
                sh '''#!/bin/bash
                    pushd client
                    mvn test
                    popd
                '''
            }
        }


    }
}