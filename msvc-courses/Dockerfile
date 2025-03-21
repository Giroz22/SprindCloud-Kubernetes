FROM openjdk:23-jdk AS builder

WORKDIR /app/msvc-courses

COPY ./pom.xml /app

#Copia las Dependencias
COPY ./msvc-courses/.mvn ./.mvn 

#Copia el wrapper de maven
COPY ./msvc-courses/mvnw .

#Copia el pom de courses
COPY ./msvc-courses/pom.xml .

RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/
#RUN ./mvnw dependency:go-offline

COPY ./msvc-courses/src ./src

RUN ./mvnw clean package -DskipTests

FROM openjdk:23-jdk

WORKDIR /app
RUN mkdir ./logs

COPY --from=builder /app/msvc-courses/target/msvc-courses-0.0.1-SNAPSHOT.jar .

EXPOSE 8002

CMD [ "java", "-jar", "msvc-courses-0.0.1-SNAPSHOT.jar" ]