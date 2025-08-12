FROM openjdk:21-jdk-slim

WORKDIR /app

COPY . .

RUN apt-get-update && apt-get install -y maven

RUN mvn clean package -DskipTests

RUN cp target/*.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]