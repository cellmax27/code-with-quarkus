pipeline {
    agent any

    environment {
        SONARQUBE_URL = 'http://sonar.example.com'   // URL de SonarQube
        SONARQUBE_TOKEN = credentials('sonar-token') // Token d'authentification dans Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Clonage du repository...'
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo 'Compilation de l\'application avec Maven...'
                sh 'mvn clean package'
            }
        }

        stage('Unit Tests') {
            steps {
                echo 'Exécution des tests unitaires...'
                sh 'mvn test'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                echo 'Analyse de la qualité du code avec SonarQube...'
                withSonarQubeEnv('SonarQube') {  // Utilise l\'outil configuré dans Jenkins
                    sh 'mvn sonar:sonar -Dsonar.projectKey=mon-projet-java'
                }
            }
        }

        stage('OWASP Dependency Check') {
            steps {
                echo 'Analyse des vulnérabilités avec OWASP Dependency Check...'
                sh '''
                    mvn org.owasp:dependency-check-maven:check \
                        -Ddependency-check.failBuildOnCVSS=7 \
                        -Dformat=HTML -DoutputDirectory=target/dependency-check-report
                '''
            }
        }

        stage('Archive Artifacts') {
            steps {
                echo 'Archivage des artefacts...'
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }

        stage('Deploy') {
            steps {
                echo 'Déploiement de l\'application...'
                // Ajoute tes étapes de déploiement ici (Docker, Tomcat, Kubernetes...)
            }
        }
    }

    post {
        always {
            echo 'Pipeline terminé !'
            cleanWs()
        }
    }
}