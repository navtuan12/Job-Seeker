pipeline {
    agent any

    environment {
        SONAR_SERVER_URL = 'http://sonarqube:9000'
        SERVER_PORT = 8800
        CLIENT_PORT = 80
        HARBOR_URL = 'https://harbor.proj.nt548.com:443'
        HARBOR_PROJECT = 'nt548proj'
        HARBOR_CREDENTIALS = 'harborCredentials'
    }

    tools {
        maven 'maven'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
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
                    withCredentials([string(credentialsId: 'signerkey', variable: 'SIGNER_KEY')]) {
                        sh " mvn -f server/pom.xml clean verify sonar:sonar -Dsonar.projectKey=jenkins -Dsonar.sources=src -Dsonar.java.binaries=target/classes -Dsonar.tests=src/test/java -Dsonar.exclusions=src/test/java/**/* -DSIGNER_KEY=${SIGNER_KEY}"
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
            agent {
                docker {
                    image 'docker:24-dind'
                    args '--priviliged --network=host -v /var/run/docker.sock:/var/run/docker.sock'
                    reuseNode true
                }
            }
            steps {
                script {
                    sh '''
                    dockerd &
                    sleep 10
                    docker info
                    '''

                    sh '''
                    echo "Testing Docker socket..."
                    ls -l /var/run/docker.sock
                    docker version
                    docker ps
                    '''

                    docker.withRegistry(env.HARBOR_URL, env.HARBOR_CREDENTIALS) {
                        def serverImage = docker.build("${env.HARBOR_URL}:${env.HARBOR_PORT}/${env.HARBOR_PROJECT}/job-seeker-server:${env.BUILD_NUMBER}", '-f server/Dockerfile .')
                        def clientImage = docker.build("${env.HARBOR_URL}:${env.HARBOR_PORT}/${env.HARBOR_PROJECT}/job-seeker-client:${env.BUILD_NUMBER}", '-f client/Dockerfile .')
                        serverImage.push()
                        clientImage.push()
                    }
                }
            }
        }
    }
}
