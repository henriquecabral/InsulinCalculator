#WebService

javac server/*.java
wsgen -cp . server.Insulin
java -cp . server.Insulin


#HttpServer

javac HttpServer/*.java
java HttpServer.WebServer


#Client

wsimport -p client -keep http://10.17.1.7:8081/insulin?wsdl