FROM maven:3.8.4-openjdk-17 AS build
COPY backend_course/src /home/app/src
COPY backend_course/pom.xml /home/app
RUN mvn -B clean package --file /home/app/pom.xml -DskipTests=true

FROM openjdk:17-oracle
USER spring:spring
COPY --from=build /home/app/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
#COPY */course_eLearning.jar app.jar
#ENTRYPOINT ["java","-jar", "app.jar"]
