FROM openjdk:17

LABEL author=21zam03.com

COPY target/zamMarket-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]