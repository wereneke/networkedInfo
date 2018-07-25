# networkedInfo
Client-server communication on sockets. Server sends to clients informations about serving machine.

To run App you need JDK in version 1.8, Git and Maven.

on unix run
```
git clone https://github.com/wereneke/networkedInfo.git
cd networkedInfo
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
to stop asking the server for information press enter key two times in client console,
remember to stop all the clients before closing server,
to stop server type CTRL+C in console where server is running
