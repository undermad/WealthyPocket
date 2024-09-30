FROM maven:3.9 as BUILD
WORKDIR /app
COPY . /app
RUN mvn clean
RUN mvn package -DskipTests -X

FROM amazoncorretto:21
COPY --from=BUILD /app/api/target/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]