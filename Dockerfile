FROM java:8

ADD build/libs/app-*.jar app.jar

RUN bash -c 'touch /app.jar'

EXPOSE 8090

CMD ["java", "-jar", "/app.jar"]