# QCS project

Projecto desenvolvido no âmbito da unidade curricular "Qualidade e Confiabildiade de Software" do Mestrado em Engenharia Informática na Universidade de Coimbra

Neste projecto foram desenvolvidas três componentes distintas:

* webService para calcular valores de insulina (Java)

* Frontend para os utilizaodres fazerem o calculo da insulina (HTML/CSS/Javascript/AJAX/JQuery/JSON)

* WebServer e Votador do N-Version Programming (Java)

Um utilizador realiza um pedido na página Html para ser realizado o cálculo das doses de insulina que deve tomar. O WebServer recebe o pedido e manda para 3 webServices distintos para calcularem o valor. Quando recebe os valores, realiza uma votação e só depois envia o valor final.

##How Run

###WebService
```
javac server/*.java
wsgen -cp . server.Insulin
java -cp . server.Insulin
```

###HttpServer
```
#javac HttpServer/*.java
#java HttpServer.WebServer

javac -cp HttpServer/javax.json-1.0.4.jar:. HttpServer/*.java
java -cp HttpServer/javax.json-1.0.4.jar:. HttpServer.WebServer
```

###Client
```
#wsimport -p nome_do_package -keep http://10.17.1.6:8080/InsulinDoseCalculator/insulin?wsdl

#javac client/Client.java
#java -cp . client.Client
```

