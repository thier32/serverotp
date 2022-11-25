From maven:3.6.3-jdk-8-openj9 AS build
COPY src /home/otp/src
COPY pom.xml /home/otp
RUN mvn -f /home/otp/pom.xml clean install package
