ARG MSVC_NAME=msvc-shoppingcart

FROM eclipse-temurin:21-jdk-alpine as builder

ARG MSVC_NAME

WORKDIR /app/${MSVC_NAME}

COPY ./.mvn ./.mvn
COPY ./mvnw .
COPY ./mvnw.cmd .
COPY ./pom.xml .

# RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target
RUN ./mvnw dependency:go-offline

COPY ./src ./src

RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:21-jdk-alpine

ARG MSVC_NAME

WORKDIR /app

RUN mkdir ./logs

ARG TARGET_FOLFER=/app/${MSVC_NAME}/target

COPY --from=builder $TARGET_FOLFER/msvc-shoppingcart-0.0.1-SNAPSHOT.jar .

EXPOSE 8002

CMD ["java", "-jar", "msvc-shoppingcart-0.0.1-SNAPSHOT.jar"]

