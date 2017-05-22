#WebService

javac server/*.java
wsgen -cp . server.Insulin
java -cp . server.Insulin


#HttpServer

#javac HttpServer/*.java
#java HttpServer.WebServer

javac -cp HttpServer/javax.json-1.0.4.jar:. HttpServer/*.java
java -cp HttpServer/javax.json-1.0.4.jar:. HttpServer.WebServer


#Client

#wsimport -p nome_do_package -keep http://10.17.1.6:8080/InsulinDoseCalculator/insulin?wsdl

#javac client/Client.java
#java -cp . client.Client