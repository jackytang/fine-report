FROM azul/zulu-openjdk-debian:8u342

MAINTAINER JackyTang

RUN mkdir -p /ruoyi/report/tmp && \
    sed -i "s@http://deb.debian.org@http://mirrors.aliyun.com@g" /etc/apt/sources.list && rm -Rf /var/lib/apt/lists/* && \
    apt-get update && \
    apt-get install -y lsb-release && \
    apt-get clean all

WORKDIR /ruoyi/report

EXPOSE 9200

ADD ./target/ruoyi-fine-report.jar ./app.jar

#ENTRYPOINT ["sh", "-c", "java -DDOC_BASE=/ruoyi/report/fr11-web -Dloader.path=BOOT-INF/classes/,file:/ruoyi/report/fr11-web/WEB-INF/classes,file:/ruoyi/report/fr11-web/WEB-INF/lib,BOOT-INF/lib/,/usr/lib/jvm/zulu8-ca-amd64/lib/tools.jar -Djava.security.egd=file:/dev/./urandom -jar app.jar"]
ENTRYPOINT exec java \
  -Djava.io.tmpdir=/ruoyi/report/tmp \
  -DDOC_BASE=/ruoyi/report/fr11-web \
  -Dloader.path=BOOT-INF/classes/,file:/ruoyi/report/fr11-web/WEB-INF/classes,file:/ruoyi/report/fr11-web/WEB-INF/lib,BOOT-INF/lib/,/usr/lib/jvm/zulu8-ca-amd64/lib/tools.jar \
  -Djava.security.egd=file:/dev/./urandom \
  -jar app.jar
