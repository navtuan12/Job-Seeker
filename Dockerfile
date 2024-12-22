FROM jenkins/jenkins:lts
USER root

RUN groupadd -g 999 docker && usermod -aG docker jenkins && \
    apt-get update && apt-get install -y docker.io && rm -rf /var/lib/apt/lists/*

USER jenkins
