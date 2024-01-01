FROM --platform=linux/amd64 maven:3.8.3-openjdk-17 AS build
COPY ./ /app
WORKDIR /app
RUN mvn --show-version --update-snapshots --batch-mode clean package

FROM --platform=linux/amd64 eclipse-temurin:17-jre
RUN mkdir /app
WORKDIR /app
COPY --from=build ./app/api/target/api-1.0-SNAPSHOT.jar /app
EXPOSE 8080
CMD ["java", "-jar", "api-1.0-SNAPSHOT.jar"]