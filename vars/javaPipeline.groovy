def call() {
    pipeline {
        agent any

        tools {
            jdk 'java-8'
        }

        environment {
            DOCKER_USER = credentials('dockerhub-user')
            DOCKER_PASS = credentials('dockerhub-password')
        }

        stages {
            stage('Build') {
                steps {
                    echo 'Building Java project...'
                    sh './mvnw clean package'
                }
            }

            stage('Docker Build') {
                steps {
                    echo 'Building Docker image...'
                    sh "docker build -t my-java-image ."
                }
            }

            stage('Docker Push') {
                steps {
                    echo 'Pushing Docker image...'
                    sh "echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin"
                    sh "docker push my-java-image"
                }
            }
        }
    }
}

