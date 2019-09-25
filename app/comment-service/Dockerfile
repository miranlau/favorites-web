FROM openjdk:8-jdk
ENV TZ=Asia/Shanghai
ARG JAR_FILE
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
ADD ${JAR_FILE} app.jar
VOLUME /tmp
EXPOSE 8096
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Xms512m","-Xmx512m","-jar","/app.jar"]