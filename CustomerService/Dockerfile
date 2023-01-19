FROM adoptopenjdk:11-jre-hotspot
COPY target/lib /usr/src/lib
COPY target/CustomerService-1.0.0.jar /usr/src/
WORKDIR /usr/src/
CMD java -Xmx32m -jar CustomerService-1.0.0.jar
