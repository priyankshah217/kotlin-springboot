FROM openjdk:8-jre
ENV APP_HOME=/root/dev/myapp/
ARG JAR_FILE
WORKDIR $APP_HOME
COPY ${JAR_FILE} app.jar
EXPOSE 8181
ENTRYPOINT ["java","-jar","app.jar"]