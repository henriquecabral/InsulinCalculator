
import java.io.*;
import java.net.InetSocketAddress;
import java.lang.StringBuilder;
import java.util.*;

import javax.json.*;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class WebServer {

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/", new IndexHandler());
        server.createContext("/backgroundInsulinDose", new BackgroundInsulinDoseHandler());
        server.createContext("/customInsulinDose", new CustomInsulinDoseHandler());
        server.createContext("/standardInsulinDose", new StandardInsulinDoseHandler());

        server.start();
    }

    static class IndexHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            System.out.println(t.getRequestURI());
            StringBuilder contentBuilder = new StringBuilder();
            try 
            {
                BufferedReader in = new BufferedReader(new FileReader("html/index.html"));
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
            
            //PARSE TO JSON OBJECT
            InputStreamReader body =  new InputStreamReader(t.getRequestBody(),"utf-8");
            BufferedReader buffer = new BufferedReader(body);
            JsonReader reader = Json.createReader(new StringReader(buffer.readLine()));
            JsonObject formData = reader.readObject();
            reader.close();
            
            // Tratar de todos os objectos 
            Voter voter = new Voter(Integer.parseInt(formData.getString("bodyWeight")));
            
            //VOTER
            int res=voter.result(2);
            int result[] = voter.results;

            //SEND DATA TO FRONTEND
            JsonObjectBuilder finalData = Json.createObjectBuilder();
            finalData.add("result", Integer.toString(res));
            finalData.add("nversion", "3");
            finalData.add("service1", Integer.toString(result[0]));
            finalData.add("service2", Integer.toString(result[1]));
            finalData.add("service3", Integer.toString(result[2]));

            String response = finalData.build().toString();

            t.sendResponseHeaders(200, response.length()); //200: sucess
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
    static class CustomInsulinDoseHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            System.out.println(t.getRequestURI());
            
            //PARSE TO JSON OBJECT
            InputStreamReader body =  new InputStreamReader(t.getRequestBody(),"utf-8");
            BufferedReader buffer = new BufferedReader(body);
            JsonReader reader = Json.createReader(new StringReader(buffer.readLine()));
            JsonObject formData = reader.readObject();
            reader.close();

            List<Integer> physicalActivitySamples = new ArrayList<Integer>();
            List<Integer> bloodSugarDropSamples = new ArrayList<Integer>();

            physicalActivitySamples.add(Integer.parseInt(formData.getString("physicalActivitySamples1")));
            physicalActivitySamples.add(Integer.parseInt(formData.getString("physicalActivitySamples2")));
            System.out.println("ENTROU 1"+ formData.getString("physicalActivitySamples3") + "!\n");
            if(!formData.getString("physicalActivitySamples3").equals(""))
                physicalActivitySamples.add(Integer.parseInt(formData.getString("physicalActivitySamples3")));
            if(!formData.getString("physicalActivitySamples4").equals(""))
                physicalActivitySamples.add(Integer.parseInt(formData.getString("physicalActivitySamples4")));
            if(!formData.getString("physicalActivitySamples5").equals(""))
                physicalActivitySamples.add(Integer.parseInt(formData.getString("physicalActivitySamples5")));
            if(!formData.getString("physicalActivitySamples6").equals(""))
                physicalActivitySamples.add(Integer.parseInt(formData.getString("physicalActivitySamples6")));
            if(!formData.getString("physicalActivitySamples7").equals(""))
                physicalActivitySamples.add(Integer.parseInt(formData.getString("physicalActivitySamples7")));
            if(!formData.getString("physicalActivitySamples8").equals(""))
                physicalActivitySamples.add(Integer.parseInt(formData.getString("physicalActivitySamples8")));
            if(!formData.getString("physicalActivitySamples9").equals(""))
                physicalActivitySamples.add(Integer.parseInt(formData.getString("physicalActivitySamples9")));
            if(!formData.getString("physicalActivitySamples10").equals(""))
                physicalActivitySamples.add(Integer.parseInt(formData.getString("physicalActivitySamples10")));
            bloodSugarDropSamples.add(Integer.parseInt(formData.getString("bloodSugarDropSamples1")));
            bloodSugarDropSamples.add(Integer.parseInt(formData.getString("bloodSugarDropSamples2")));
            if(!formData.getString("bloodSugarDropSamples3").equals(""))
                bloodSugarDropSamples.add(Integer.parseInt(formData.getString("bloodSugarDropSamples3")));
            if(!formData.getString("bloodSugarDropSamples4").equals(""))            
                bloodSugarDropSamples.add(Integer.parseInt(formData.getString("bloodSugarDropSamples4")));
            if(!formData.getString("bloodSugarDropSamples5").equals(""))
                bloodSugarDropSamples.add(Integer.parseInt(formData.getString("bloodSugarDropSamples5")));
            if(!formData.getString("bloodSugarDropSamples6").equals(""))
                bloodSugarDropSamples.add(Integer.parseInt(formData.getString("bloodSugarDropSamples6")));
            if(!formData.getString("bloodSugarDropSamples7").equals(""))
                bloodSugarDropSamples.add(Integer.parseInt(formData.getString("bloodSugarDropSamples7")));
            if(!formData.getString("bloodSugarDropSamples8").equals(""))
                bloodSugarDropSamples.add(Integer.parseInt(formData.getString("bloodSugarDropSamples8")));
            if(!formData.getString("bloodSugarDropSamples9").equals(""))
                bloodSugarDropSamples.add(Integer.parseInt(formData.getString("bloodSugarDropSamples9")));
            if(!formData.getString("bloodSugarDropSamples10").equals(""))
                bloodSugarDropSamples.add(Integer.parseInt(formData.getString("bloodSugarDropSamples10")));

            
            // Tratar de todos os objectos 
            Voter voter = new Voter(Integer.parseInt(formData.getString("carbohydrateAmount")),             Integer.parseInt(formData.getString("carbohydrateToInsulinRatio")),             Integer.parseInt(formData.getString("preMealBloodSugar")) ,             Integer.parseInt(formData.getString("targetBloodSugar")),             Integer.parseInt(formData.getString("physicalActivityLevel")),             physicalActivitySamples, bloodSugarDropSamples);
            
            //Voter
            int res=voter.result(1);
            int result[] = voter.results;

            //SEND DATA TO FRONTEND
            JsonObjectBuilder finalData = Json.createObjectBuilder();
            finalData.add("result", Integer.toString(res));
            finalData.add("nversion", "3");
            finalData.add("service1", Integer.toString(result[0]));
            finalData.add("service2", Integer.toString(result[1]));
            finalData.add("service3", Integer.toString(result[2]));

            String response = finalData.build().toString();

            t.sendResponseHeaders(200, response.length()); //200: sucess
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
    static class StandardInsulinDoseHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            System.out.println(t.getRequestURI());
            
            //PARSE TO JSON OBJECT
            InputStreamReader body =  new InputStreamReader(t.getRequestBody(),"utf-8");
            BufferedReader buffer = new BufferedReader(body);
            JsonReader reader = Json.createReader(new StringReader(buffer.readLine()));
            JsonObject formData = reader.readObject();
            reader.close();
            
            // Tratar de todos os objectos 
            Voter voter = new Voter(Integer.parseInt(formData.getString("carbohydrateAmount")),             Integer.parseInt(formData.getString("carbohydrateToInsulinRatio")),             Integer.parseInt(formData.getString("preMealBloodSugar")) ,             Integer.parseInt(formData.getString("targetBloodSugar")),             Integer.parseInt(formData.getString("personalSensitivity")));
            
            
            //VOTER
            int res=voter.result(0);
            int result[] = voter.results;

            //SEND DATA TO FRONTEND
            JsonObjectBuilder finalData = Json.createObjectBuilder();
            finalData.add("result", Integer.toString(res));
            finalData.add("nversion", "3");
            finalData.add("service1", Integer.toString(result[0]));
            finalData.add("service2", Integer.toString(result[1]));
            finalData.add("service3", Integer.toString(result[2]));

            String response = finalData.build().toString();
            t.sendResponseHeaders(200, response.length()); //200: sucess
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}