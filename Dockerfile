FROM jenkins/jenkins:lts
USER root
RUN groupadd -g 999 docker && usermod -aG docker jenkins && \
    apt-get update && apt-get install -y docker.io && rm -rf /var/lib/apt/lists/*
RUN echo '#!/bin/bash\nchmod 666 /var/run/docker.sock' > /usr/local/bin/fix-docker-socket.sh && \
    chmod +x /usr/local/bin/fix-docker-socket.sh
ENTRYPOINT ["/bin/bash", "-c", "/usr/local/bin/fix-docker-socket.sh && exec /sbin/tini -- /usr/local/bin/jenkins.sh"]

USER jenkins
