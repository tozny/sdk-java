FROM java:7-jdk

RUN mkdir -p /tmp/aws && \
    curl https://s3.amazonaws.com/aws-cli/awscli-bundle.zip -o /tmp/aws/awscli-bundle.zip && \
    unzip /tmp/aws/awscli-bundle.zip -d /tmp/aws && \
    /tmp/aws/awscli-bundle/install -i /usr/local/aws -b /usr/local/bin/aws && \
    rm -rf /tmp/aws/

