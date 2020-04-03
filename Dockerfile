FROM openjdk:8-jdk-slim
VOLUME /tmp
ADD target/ucontas-0.0.1-SNAPSHOT.jar ucontas-api.jar
EXPOSE 80
RUN bash -c 'touch /ucontas-api.jar'
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/ucontas-api.jar"]