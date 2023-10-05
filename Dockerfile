FROM openjdk:17-alpine

ARG PROJECT_NAME
ARG PROJECT_VERSION

COPY ./build/libs/${PROJECT_NAME}-${PROJECT_VERSION}-SNAPSHOT.jar /build/libs/SNAPSHOT.jar

ENTRYPOINT ["java","-agentlib:jdwp=transport=dt_socket,address=*:5005,server=y,suspend=n","-jar","/build/libs/SNAPSHOT.jar"]
