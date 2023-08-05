FROM openjdk:17
COPY target/Case-Itau-0.0.1-SNAPSHOT.jar case-itau-server-1.0.0.jar
ENTRYPOINT ["java","-jar","/case-itau-server-1.0.0.jar"]