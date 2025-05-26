def call() {
    pipeline {
        agent any
        stages {
            stage('Install Dependencies') {
                steps {
                    sh 'pip install -r requirements.txt'
                }
            }
            stage('Run App') {
                steps {
                    sh 'python app.py &'
                }
            }
            stage('Test') {
                steps {
                    sh 'pytest'
                }
            }
        }
    }
}

