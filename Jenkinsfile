pipeline {
    agent any
    tools {
        maven 'MAVEN'
    }
    environment {
    }
    stages {
        stage('Checkout') {
            steps {
                // Récupérer le code depuis le dépôt Git
                checkout scm
            }
        }
        stage('Build Module') {
            steps {
                script {
                    // Construire le module Spring Boot
                    sh """
                        mvn clean install
                    """
                }
            }
        }
        
//        stage('SonarQube Analysis') {
//            steps {
//                // Nom du serveur configuré dans l'étape 2
//                withSonarQubeEnv('SonarQubeServer') {
//                    sh '''
//                        mvn sonar:sonar \
//                            -Dsonar.java.binaries=. \
//                            -Dsonar.projectName=gr-common-configs \
//                            -Dsonar.projectKey=gr-common-configs
//                    '''
//                }
//            }
//        }

    }
    post {
        success {
            echo 'Module [gr-common-configs] installé avec succés..'
        }
        failure {
            echo 'Echec de l\'installation du module [gr-common-configs] !!'
        }
    }
}