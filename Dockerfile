FROM openjdk:8 AS BUILD_IMAGE
ENV APP_HOME=/root/dev/myapp/
RUN mkdir -p $APP_HOME/src/main/java
WORKDIR $APP_HOME
COPY build.gradle gradlew gradlew.bat $APP_HOME
COPY gradle $APP_HOME/gradle
# download dependencies
COPY . .
RUN ./gradlew build
FROM openjdk:8-jre
WORKDIR /root/
COPY --from=BUILD_IMAGE /root/dev/myapp/build/libs/springboot-jpa-mysql-0.0.1.jar .
EXPOSE 8080
CMD ["java","-jar","springboot-jpa-mysql-0.0.1.jar"]