FROM tomcat

COPY target/BarcodeReader.war /usr/local/tomcat/webapps/BarcodeReader.war

RUN apt-get update -y && apt-get install maven -y


RUN mkdir -p /usr/app

COPY . /usr/app

RUN mvn dependency:resolve -f /usr/app/pom.xml



