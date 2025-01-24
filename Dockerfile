FROM gradle:8.5 AS builder
WORKDIR /app
COPY ./src ./src
COPY ./build.gradle .
RUN gradle bootJar


FROM openjdk:17-alpine
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar gateway-server.jar
CMD ["java",  "-jar", "gateway-server.jar"]

