package HttpServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.lang.StringBuilder;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class WebServer {

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/", new IndexHandler());
        server.createContext("/backgroundInsulinDose", new BackgroundInsulinDoseHandler());
        server.start();
    }

    static class IndexHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            System.out.println(t.getRequestURI());
            StringBuilder contentBuilder = new StringBuilder();
            try 
            {
                BufferedReader in = new BufferedReader(new FileReader("HttpServer/html/index.html"));
                String str;
                while ((str = in.readLine()) != null) {
                    contentBuilder.append(str);
                }
                in.close();
            } 
            catch (IOException e) 
            { System.out.println("Erro: " + e +  "\n"); }

            String response = contentBuilder.toString();
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
    static class BackgroundInsulinDoseHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            System.out.println(t.getRequestURI());
            System.out.println("AQUI");            

            String response = "Funciona!";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}