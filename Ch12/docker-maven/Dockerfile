FROM eclipse-temurin:17-jdk

# /opt/app is where we'll place all of our stuff to run
RUN mkdir /opt/app
WORKDIR /opt/app

# We grab all our contents from where Maven puts them
# This assumes that we've run `mvn package dependency:copy-dependencies`
COPY target/*.jar /opt/app/
COPY target/dependency/*.jar /opt/app/

# Enabling debugging

CMD ["java", "-cp", "./*", "com.wellgrounded.Main"]
