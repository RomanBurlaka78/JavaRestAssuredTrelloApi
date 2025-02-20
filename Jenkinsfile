pipeline {
    agent any  // Используем любой доступный агент
    environment {
        CONFIG_FILE = "src/test/resources/config.properties"
    }
    stages {
        stage('Checkout Code') {
            steps {
                checkout scm  // Клонируем код из репозитория
            }
        }

        stage('Load Secrets & Create Config') {
            steps {
                withCredentials([
                    string(credentialsId: 'key', variable: 'key'),
                    string(credentialsId: 'token', variable: 'token')
                ]) {
                    sh '''
                    echo "key=$KEY" > $CONFIG_FILE
                    echo "token=$TOKEN" >> $CONFIG_FILE
                    '''
                }
            }
        }
        stage('Build & Run Docker') {
            steps {
                    sh 'docker-compose up -d --build'
            }

        }
    }
}
