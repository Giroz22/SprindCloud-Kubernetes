FROM openjdk:23-jdk AS builder

ARG MSVC_NAME=msvc-users

WORKDIR /app/$MSVC_NAME

COPY ./pom.xml /app

#Copia las Dependencias
COPY ./$MSVC_NAME/.mvn ./.mvn 

#Copia el wrapper de maven
COPY ./$MSVC_NAME/mvnw .

#Copia el pom de users
COPY ./$MSVC_NAME/pom.xml .

RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/
# RUN ./mvnw dependency:go-offline

COPY ./$MSVC_NAME/src ./src

RUN ./mvnw clean package -DskipTests

FROM openjdk:23-jdk

WORKDIR /app
RUN mkdir ./logs

COPY --from=builder /app/msvc-users/target/msvc-users-0.0.1-SNAPSHOT.jar .

ARG PORT_APP=8001
ENV PORT=$PORT_APP

EXPOSE $PORT

CMD [ "java", "-jar", "msvc-users-0.0.1-SNAPSHOT.jar" ]