## Building

To build the images follow these steps:

```
./gradlew installDist
docker build -t docker-gradle .
```

The image may be run then with this command:

```
docker run docker-gradle
```

To poke around in the container, we can start up a shell instead of the default `CMD`

```
docker run -it docker-gradle bash
```

## Ports

With an HTTP server, we can access it from outside the container if we share the port

```
docker run \
  -p 8080:8080 \
  docker-gradle
```

```
curl http://localhost:8080/hello

Hello from HttpServer
```

## Debugging with IDE

This relies on the `JAVA_OPTS` environment variable that's part of the Gradle
startup scripts

For IntelliJ, this then requires setting up a Remote JVM Debug run configuration
pointed to port 8090.

```
docker run \
  -p 8090:8090 \
  -e JAVA_OPTS=-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8090 \
  docker-gradle
```

Should see this in the output when starting when it worked correctly in container:

```
Listening for transport dt_socket at address: 8090
```

## Logging

It's relatively common to have containers emit logs to STDOUT and capture that
at the orchastrator layers instead of writing to files inside containers, but
we can still do it (or both like in our sample)

Adding `--name` just to make easier to target (otherwise need `docker ps` to
find the current container name) and `--rm` because you can't reuse name if you
don't clean up the container afterward.

```
docker run \
  -p 8080:8080 \
  --rm \
  --name docker-gradle \
  docker-gradle
```

In another terminal you can tail the log via this

```
docker exec docker-gradle tail -f ./access.log
```

You can copy it via this. Note that while `exec` will use our current WORKDIR,
`cp` you have to know the full path of the file that you're trying to access.

```
docker cp docker-gradle:/opt/app/bin/access.log .
```

## JFR

Can issue from outside container, but faintly easier to shell in

```
docker exec -it docker-gradle bash
```

```
root@4f146639fcfc:/opt/app/bin# jps
1 Main
83 Jps

For more information about a specific command use 'help <command>'.

root@4f146639fcfc:/opt/app/bin# jcmd Main JFR.start
1:
Started recording 1. No limit specified, using maxsize=250MB as default.

Use jcmd 1 JFR.dump name=1 filename=FILEPATH to copy recording data to file.

root@4f146639fcfc:/opt/app/bin# jcmd 1 JFR.dump name=1 filename=./capture.jfr
1:
Dumped recording "1", 293.3 kB written to:
```

Back out of the container, just grab the file

```
gradle:docker cp docker-gradle:/opt/app/bin/capture.jfr .
```
