#mvn package dependency:copy-dependencies -DincludeScope=runtime
FROM openjdk:8

RUN mkdir /app

COPY ./ /app

WORKDIR ./USR/LOCAL/OPENJDK-8/BIN

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app-module/target/app-module-1.jar"]