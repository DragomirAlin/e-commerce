FROM openjdk:17-bullseye

WORKDIR /app

COPY /boot/target/*.jar /srv/
COPY /boot/src/main/resources/application.yml /srv/config/

EXPOSE 8080

ENTRYPOINT ["java","-jar","/srv/boot-0.0.1-SNAPSHOT.jar", "--spring.config.location=file:/srv/config/application.yml"]