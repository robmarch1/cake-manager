FROM openjdk:15

WORKDIR /app

COPY build.gradle gradlew gradlew.bat settings.gradle ./
COPY /src ./src
COPY /gradle ./gradle

RUN ./gradlew clean build

CMD ["./gradlew", "bootRun"]
