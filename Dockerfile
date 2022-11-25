From tomcat:8.0.51-jre8-alpine AS deploy
RUN rm -rf /usr/local/tomcat/webapps/*
COPY ./target/otp-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/otp.war
CMD ["catalina.sh","run"]