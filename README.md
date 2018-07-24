# networkedInfo
Client-server communication on sockets. Server sends to clients informations about serving machine.

To run App you need JDK in version 1.8, Git and Maven.

on unix run
```
cd info
mvn package
```
and as first
```
java -jar info-1.0-SNAPSHOT.jar server
```
and then in another console
```
java -jar info-1.0-SNAPSHOT.jar client
```
