pipeline {
    agent any

    environment {
        SONAR_SERVER_URL = 'http://sonarqube:9000'
        HARBOR_URL = 'reg.devnoneknow.online'
        HARBOR_PROJECT = 'jobseeker'
        HARBOR_CREDENTIALS = 'harborCredentials'
    }

    tools {
        maven 'maven'
    }

    stages {
        stage('Checkout') {
            steps {
                deleteDir()
                checkout scm
            }
        }

        stage('Check Docker') {
            steps {
                sh 'docker --version'
            }
        }

        stage('Build Server') {
            steps {
                sh 'mvn -f server/pom.xml clean package -DskipTests'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv(installationName: 'sonarqube_server') {
                    withCredentials([
                        string(credentialsId: 'signerkey', variable: 'SIGNER_KEY'),
                        string(credentialsId: 'mongoURI', variable: 'MONGO_URI')
                        ]) {
                        sh """ mvn -f server/pom.xml clean verify sonar:sonar \
                        -Dsonar.projectKey=jenkins \
                        -Dsonar.sources=src \
                        -Dsonar.java.binaries=target/classes \
                        -Dsonar.exclusions=src/test/java/**/* \
                        -DSIGNER_KEY=${SIGNER_KEY} \
                        -Dspring.data.mongodb.uri=${MONGO_URI}"""
                        }
                }
            }
        }

        stage('Quality Gate') {
            steps {
                timeout(time: 10, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }

        stage('Build and Push Docker Images') {
            steps {
                script {
                    docker.withRegistry("https://${env.HARBOR_URL}", env.HARBOR_CREDENTIALS) {
                        def serverImage = docker.build("${env.HARBOR_URL}/${env.HARBOR_PROJECT}/job-seeker-server:${env.BUILD_NUMBER}", '-f server/Dockerfile ./server/')
                        def clientImage = docker.build("${env.HARBOR_URL}/${env.HARBOR_PROJECT}/job-seeker-client:${env.BUILD_NUMBER}", '-f client/Dockerfile ./client/')
                        serverImage.push()
                        clientImage.push()
                    }
                }
            }
        }
    }
}