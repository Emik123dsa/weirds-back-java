FROM maven:3.5.2-jdk-8-alpine AS MAVEN_BUILD

MAINTAINER EM1L SHARI

COPY pom.xml /build/
COPY src /build/src/
ARG BRANCH
WORKDIR /build/
RUN if[$BRANCH = "master"] ; then mvn clean package -Pprod; else mvn clean package -Pdev; fi

FROM tomcat:9.0 as runtime
WORKDIR /app
EXPOSE 8080
COPY --from=MAVEN_BUILD /build/target/api-0.0.2.war /usr/local/tomcat/webapps/
RUN sh -c "touch /usr/local/tomcat/webapps/api-0.0.2.war"
ENTRYPOINT ["java","-jar","/usr/local/tomcat/webapps/api-0.0.2.war"]