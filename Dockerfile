FROM openjdk:11
VOLUME /tmp
EXPOSE 8087
ARG JAR_FILE=delivery-main/target/delivery-main-0.0.1-SNAPSHOT-jar-with-dependencies.jar
ARG DATABASE_RESOURCES=delivery-dao/target/classes/database
WORKDIR /opt/delivery
COPY ${JAR_FILE} delivery.jar
COPY ${DATABASE_RESOURCES} database
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-jar","delivery.jar"]