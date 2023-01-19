FROM adoptopenjdk:11-jre-hotspot
WORKDIR /usr/src/
COPY target/tokens.jar /usr/src/tokens.jar
CMD java -Xmx64m -jar tokens.jar
EXPOSE 8080