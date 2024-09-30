FROM maven:3.9 as BUILD
WORKDIR /app
COPY pom.xml /app
RUN mvn dependency:resolve
COPY . /app
RUN mvn clean
RUN mvn package -DskipTests -X

FROM amazoncorretto:21
COPY --from=BUILD /app/target/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]