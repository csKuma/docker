FROM amazoncorretto:17-alpine-jdk AS build
WORKDIR /app
COPY .. /app
RUN chmod +x ./gradlew
RUN ./gradlew build -x test

FROM amazoncorretto:17-alpine-jdk AS release
WORKDIR /app
COPY --from=build /app/build/libs/autenticador-*.war ./app.war
RUN chmod +x ./app.war
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.war"]

