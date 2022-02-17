FROM eclipse-temurin:17-jdk

# /opt/app is where we'll place all of our stuff to run
RUN mkdir /opt/app

# We grab all our contents from the location Gradle's installDist places them
COPY build/install/docker-gradle /opt/app/

EXPOSE 8080

# Uses the generated startup script Gradle creates for us
WORKDIR /opt/app/bin
CMD ["./docker-gradle"]
