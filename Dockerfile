FROM openjdk:17
ARG JAR_FILE=target/zam-ecommerce-0.0.1.jar
COPY ${JAR_FILE} app_ecommerce.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app_ecommerce.jar"]