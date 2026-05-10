FROM maven:3.9.9-amazoncorretto-21 AS build 

WORKDIR /app
COPY . .

RUN mvn clean package -DskipTests

FROM amazoncorretto:21-al2023-headless

WORKDIR /app

EXPOSE 8080

COPY --from=build /app/target/qrcode-generator-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT [ "java", "-jar", "app.jar" ]