From maven:3.6.0-jdk-11-slim AS build
COPY src /home/otp/src
COPY pom.xml /home/otp
RUN mvn -f /home/otp/pom.xml clean
RUN mvn -f /home/otp/pom.xml install
RUN mvn -f /home/otp/pom.xml package
