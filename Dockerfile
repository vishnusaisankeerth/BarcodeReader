FROM tomcat

COPY target/BarcodeReader.war /usr/local/tomcat/webapps/BarcodeReader.war
