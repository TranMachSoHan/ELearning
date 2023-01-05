#FROM openjdk:8-jdk-alpine
#ADD target/eLearning-course.jar app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]

# syntax=docker/dockerfile:1

# syntax=docker/dockerfile:1

FROM eclipse-temurin:17-jdk-jammy as base
WORKDIR /backend_course
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:resolve
COPY src ./src

FROM base as development
CMD ["./mvnw", "spring-boot:run"]

FROM base as build
RUN ./mvnw package

#FROM eclipse-temurin:17-jre-jammy as production
#EXPOSE 8080
#COPY --from=build /backend_course/target/eLearning-course-*.jar /eLearning-course.jar
#CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/spring-petclinic.jar"]