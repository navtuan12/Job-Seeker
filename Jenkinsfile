pipeline {
    agent any

    environment {
        // DOCKER_REGISTRY_URL = "https://index.docker.io/v1/"
        SONAR_SERVER_URL = 'http://sonarqube:9000'
        SERVER_PORT = 8800
        CLIENT_PORT = 80
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
        
        // stage('Build and Push Docker Images') {
        //     agent {
        //         docker {
        //             image 'docker:latest'
        //             reuseNode true
        //         }
        //     }
        //     steps {
        //         script {
        //             def harborUrl = "harbor.proj.nt548.com"

        //             def harborProject = "nt548proj"

        //             docker.withRegistry("https://$harborUrl", 'harbor-credentials') {
        //                 def serverImage = docker.build("$harborUrl/$harborProject/job-seeker-server:${env.BUILD_NUMBER}", "-f server/Dockerfile .")
        //                 serverImage.push()

        //                 def clientImage = docker.build("$harborUrl/$harborProject/job-seeker-client:${env.BUILD_NUMBER}",
        //                         "-f client/Dockerfile .")
        //                 clientImage.push()
        //             }
        //         }
        //     }
        // }
        /* 
        stage('Deploy') {
            agent {
                 docker {
                    image 'docker:latest'
                    reuseNode true
                    label 'docker-agent' // Sử dụng agent có label 'docker-agent' nếu bạn muốn chỉ định agent cụ thể
                 }
            }
            steps {
                echo "Deploying to production..."
                sh """
                    docker-compose -f docker-compose.prod.yml down
                    docker-compose -f docker-compose.prod.yml pull
                    docker-compose -f docker-compose.prod.yml up -d --build
                """
            }
        }
        */
    }
}
