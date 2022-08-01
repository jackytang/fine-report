FROM azul/zulu-openjdk-debian:8u342

MAINTAINER JackyTang

RUN mkdir -p /ruoyi/report && \
    apt-get update && \
    apt-get install -y lsb-release && \
    apt-get clean all

WORKDIR /ruoyi/report

EXPOSE 9200

ADD ./target/ruoyi-fine-report.jar ./app.jar

ENTRYPOINT ["sh", "-c", "java -DDOC_BASE=/ruoyi/report/fr11-web -Dloader.path=BOOT-INF/classes/,file:/ruoyi/report/fr11-web/WEB-INF/classes,file:/ruoyi/report/fr11-web/WEB-INF/lib,BOOT-INF/lib/,/usr/lib/jvm/zulu8-ca-amd64/lib/tools.jar -Djava.security.egd=file:/dev/./urandom -jar app.jar"]
