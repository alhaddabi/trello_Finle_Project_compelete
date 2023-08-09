From openjdk:17-jdk-alpine
Run apk add --no-cache maven
WORKDIR /app
copy . /app
RUN mvn clean install -DskipTests
EXPOSE 8080
CMD ["java", "-jar", "./target/trello-0.0.1.jar"]