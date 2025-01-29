# Используем Gradle с JDK 
FROM gradle:8.11.1-jdk17 AS builder

WORKDIR /app

# Копируем исходники и файлы Gradle Wrapper
COPY ./src ./src
COPY ./build.gradle .
COPY ./gradlew .
COPY ./gradle ./gradle

# Даем права на выполнение Gradle Wrapper
RUN chmod +x ./gradlew

# Проверяем Java-версию для отладки
RUN java -version

# Запускаем сборку проекта через Gradle Wrapper
RUN ./gradlew bootJar && ls -lah build/libs

# Создаём финальный образ с JDK 
FROM openjdk:17

WORKDIR /app

# Копируем jar файл из стадии сборки
COPY --from=builder /app/build/libs/*.jar loanrequest-server.jar

# Запускаем приложение
CMD ["java",  "-jar", "loanrequest-server.jar"]
