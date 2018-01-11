package com.company;


import com.google.gson.Gson;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

public abstract class UrlConnection {

    private static String user_Agent = "Mozilla/5.0";
    private static URL objtUrl = null;
    private static HttpURLConnection con = null;

    public static void connect(String url,String method){
        try {
            objtUrl = new URL(url);
            con = (HttpURLConnection) objtUrl.openConnection();
            // optional default is GET
            con.setRequestMethod(method);

            //add request header
            con.setRequestProperty("User-Agent", user_Agent);
            //datod de conexion
            /*int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);*/

        }catch (Exception e){
            System.out.println("Error connect: " + e.getMessage());
        }
    }

    public static String getAllUsers(){
        connect("http://localhost:8080/users/usersList","GET");
        BufferedReader in = null ;

        try {
            in = new BufferedReader( new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            //print result
            //System.out.println("datos: " + response.toString());
            return response.toString();
        }catch (Exception e){
            return "Error getAllUsers: " + e.getMessage();
        }finally {

            try {
                if(in != null){
                    in.close();
                }
            }catch (Exception e){
                System.out.println("Error cerrando in : " + e.getMessage());
            }

        }
    }
    public static String getUserByID(int id){
        connect("http://localhost:8080/users/usersList/" + id,"GET");
        BufferedReader in = null ;

        try {
            in = new BufferedReader( new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            //print result
            //System.out.println("datos: " + response.toString());
            return response.toString();
        }catch (Exception e){
            return "Error getUserByID: " + e.getMessage();
        }finally {

            try {
                if(in != null){
                    in.close();
                }
            }catch (Exception e){
                System.out.println("Error cerrando in : " + e.getMessage());
            }

        }
    }
    public static void deleteUserByID(int id){
        connect("http://localhost:8080/users/delete/" + id,"DELETE");
        BufferedReader in = null ;

        try {
            in = new BufferedReader( new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            //print result
            //System.out.println("datos: " + response.toString());
        }catch (Exception e){
            System.out.println("Error deleteUserByID: " + e.getMessage());
        }finally {

            try {
                if(in != null){
                    in.close();
                }
            }catch (Exception e){
                System.out.println("Error cerrando in : " + e.getMessage());
            }
        }
    }
    public static void addUser(String jsonUsuario){
        //System.out.println("JSON: " + usuario);
        connect("http://localhost:8080/users/adduser","POST");
        BufferedReader in = null ;
        ObjectOutputStream objout = null;
        OutputStream os = null;
        try {
            con.setRequestProperty("Content-Type","application/json");
            con.setDoOutput(true);
            con.setDoInput(true);

            os = con.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(jsonUsuario);
            writer.close();
            os.close();

            //Read *******necesario para que funcione correctamente
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

            String line = null;
            StringBuilder sb = new StringBuilder();

            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }

            bufferedReader.close();
            String result = sb.toString();
            //Mostrar resultado
            //System.out.println(result);

        }catch (Exception e){
            System.out.println("Error addUser: " + e.getMessage());
        }finally {

            try {
                if(objout != null){
                objout.close();
                }
                if(in != null){
                    in.close();
                }

            }catch (Exception e){
                System.out.println("Error cerrando in : " + e.getMessage());
            }

        }
    }
    public static void updateUsuario(int id,String json){
        connect("http://localhost:8080/users/update/" + id,"PUT");
        BufferedReader in = null ;
        ObjectOutputStream objout = null;
        OutputStream os = null;
        try {
            con.setRequestProperty("Content-Type","application/json");
            con.setDoOutput(true);
            con.setDoInput(true);

            os = con.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(json);
            writer.close();
            os.close();

            //Read *******necesario para que funcione correctamente
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

            String line = null;
            StringBuilder sb = new StringBuilder();

            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }

            bufferedReader.close();
            String result = sb.toString();
            //Mostrar resultado
            //System.out.println(result);

        }catch (Exception e){
            System.out.println("Error updateUsuario: " + e.getMessage());
        }finally {

            try {
                if(objout != null){
                    objout.close();
                }
                if(in != null){
                    in.close();
                }

            }catch (Exception e){
                System.out.println("Error cerrando in : " + e.getMessage());
            }
        }
    }
}
