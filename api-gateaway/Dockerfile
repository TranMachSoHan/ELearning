FROM maven:3.8.4-openjdk-17 AS build
COPY api-gateaway/src /home/app/src
COPY api-gateaway/pom.xml /home/app
RUN mvn -B clean package --file /home/app/pom.xml -DskipTests=true

FROM openjdk:17-oracle
COPY --from=build /home/app/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
