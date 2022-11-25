FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/otp/src
COPY pom.xml /home/otp
RUN mvn -f /home/otp/pom.xml clean install package


#FROM postgres:14.3
#COPY init.sql /docker-entrypoint-initdb.d/
#ENV POSTGRES_USER postgres
#ENV POSTGRES_PASSWORD admin
#ENV POSTGRES_DB otp_db


#
#From tomcat:8.0.51-jre8-alpine
#RUN rm -rf /usr/local/tomcat/webapps/*
#COPY ./target/otp-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/otp.war
#CMD ["catalina.sh","run"]