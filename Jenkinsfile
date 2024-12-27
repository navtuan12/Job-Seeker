pipeline {
    agent any

    environment {
        SONAR_SERVER_URL = 'http://sonarqube:9000' 
        HARBOR_URL = 'https://harbor.proj.nt548.com:443'
        HARBOR_PROJECT = 'nt548proj'
        HARBOR_CREDENTIALS = 'harbor-credentials'   
        }

    tools {
        maven 'maven'
    }

    stages {
        stage('Check Docker') {
            steps {
                sh 'docker --version'
            }
        }

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
                    withCredentials([string(credentialsId: '3', variable: 'SIGNER_KEY')]) {
                    sh " mvn -f server/pom.xml clean verify sonar:sonar -Dsonar.projectKey=java-maven -Dsonar.sources=src -Dsonar.java.binaries=target/classes -Dsonar.tests=src/test/java -Dsonar.exclusions=src/test/java/**/* -DSIGNER_KEY=${SIGNER_KEY}"
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
            // agent {
            //     docker {
            //         image 'docker:latest'
            //         reuseNode true
            //     }
            // }
            steps {
                script {
                    docker.withRegistry(env.HARBOR_URL, env.HARBOR_CREDENTIALS) {
                        def serverImage = docker.build("${env.HARBOR_URL}/${env.HARBOR_PROJECT}/job-seeker-server:${env.BUILD_NUMBER}", "-f server/Dockerfile .")
                        serverImage.push()

                        def clientImage = docker.build("${env.HARBOR_URL}/${env.HARBOR_PROJECT}/job-seeker-client:${env.BUILD_NUMBER}", "-f client/Dockerfile .")
                        clientImage.push()
                    }
                }
            }
        }
        // stage('Deploy') {
        //     agent {
        //          docker {
        //             image 'docker:latest'
        //             reuseNode true
        //          }
        //     }
        //     steps {
        //         echo "Deploying to production..."
        //         sh """
        //             docker-compose -f docker-compose.prod.yml down
        //             docker-compose -f docker-compose.prod.yml pull
        //             docker-compose -f docker-compose.prod.yml up -d --build
        //         """
        //     }
        // }
    }
}
