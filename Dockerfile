FROM jenkins/jenkins:lts
USER root

# Cài đặt tini
RUN apt-get update && apt-get install -y tini && rm -rf /var/lib/apt/lists/*

RUN groupadd -g 999 docker && usermod -aG docker jenkins && \
    apt-get update && apt-get install -y docker.io && rm -rf /var/lib/apt/lists/*

# Tạo init script để tự động sửa quyền Docker socket khi container khởi động
COPY fix-docker-socket.sh /usr/local/bin/fix-docker-socket.sh
RUN chmod +x /usr/local/bin/fix-docker-socket.sh

# Sử dụng tini để khởi động Jenkins
ENTRYPOINT ["/usr/bin/tini", "--", "/usr/local/bin/jenkins.sh"]

USER jenkins
