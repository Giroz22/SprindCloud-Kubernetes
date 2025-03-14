FROM openjdk:23-jdk AS builder

WORKDIR /app/msvc-users

COPY ./pom.xml /app

#Copia las Dependencias
COPY ./msvc-users/.mvn ./.mvn 

#Copia el wrapper de maven
COPY ./msvc-users/mvnw .

#Copia el pom de users
COPY ./msvc-users/pom.xml .

RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/
# RUN ./mvnw dependency:go-offline

COPY ./msvc-users/src ./src

RUN ./mvnw clean package -DskipTest

FROM openjdk:23-jdk

WORKDIR /app

COPY --from=builder /app/msvc-users/target/msvc-users-0.0.1-SNAPSHOT.jar .

EXPOSE 8001

ENTRYPOINT [ "java", "-jar", "msvc-users-0.0.1-SNAPSHOT.jar" ]