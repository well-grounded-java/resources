# Use full JDK for building
FROM eclipse-temurin:17-jdk AS build

RUN mkdir /project
WORKDIR /project

ADD ./gradle ./gradle
ADD ./gradlew* ./settings.gradle* .
RUN ./gradlew

# Copy everything into container
ADD . .

# Build as before
RUN ./gradlew clean installDist


# Actual output image now uses JRE for smaller footprint
FROM eclipse-temurin:17-jre

# /opt/app is where we'll place all of our stuff to run
RUN mkdir /opt/app

# We grab from our build image results
COPY --from=build /project/build/install/docker-gradle-multi /opt/app/

# Uses the generated startup script Gradle creates for us
WORKDIR /opt/app/bin
CMD ["./docker-gradle-multi"]
