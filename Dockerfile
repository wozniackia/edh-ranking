FROM bellsoft/liberica-openjdk-alpine-musl:17
VOLUME /tmp
ARG JAR_FILE
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
