FROM maven:3.9.4-amazoncorretto-17 as module-build
LABEL authors="razi.ahmad"

WORKDIR /module/app

COPY data-harvester .

RUN mvn clean package


FROM openjdk:17.0.2 as production

WORKDIR /app
COPY --from=module-build /module/app/target/data-harvester-0.0.1-SNAPSHOT.jar app.jar

ARG SERVER_URL
ENV BASE_STREAM_URL "$SERVER_URL"

ENTRYPOINT ["java","-jar","app.jar"]
