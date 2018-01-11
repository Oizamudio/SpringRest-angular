package com.company;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import main.Teclado;
import main.TecladoBuffer;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import javax.net.ssl.HttpsURLConnection;

public class Main {

    private static UrlConnection urlConnection = null;
    public static TecladoBuffer teclado = new TecladoBuffer();
    public static void main(String[] args) {
        String json = "";
        menu();



    }

    //Menu
    public static void menu(){
        int op = 1;
        while (op>=1 && op<=5){

            op = teclado.leeEntero("\n********* MENU **********\n" +
            "\t1- Mostrar usuarios\n" +
            "\t2- Mostrar 1 usuario\n" +
            "\t3- Añadir un usuario\n" +
            "\t4- Borrar un usuario\n" +
            "\t5- Actualizar usuario\n" +
            "\t*- Salir\n=>");
            //System.out.println("op: " + op);
            switch (op){
                case 1:
                    mostrarUsuarios();
                    break;
                case 2:
                    mostrarUsuario();
                    break;
                case 3:
                    addUsuario();
                    break;
                case 4:
                    deleteUsuario();
                    break;
                case 5:
                    updateUsuario();
                    break;
            }
        }
    }

    //Recoger lista de usuarios
    public static List<User> getUsuarios(){
        //Recoger todos los usuarios
        String json = urlConnection.getAllUsers();
        Type listType = new TypeToken<List<User>>() {}.getType();
        List<User> usuarios = new Gson().fromJson(json,listType);
        return usuarios;
    }

    //Mostrar usuarios
    public static void mostrarUsuarios(){
        List<User> users = getUsuarios();
        for (User usu:users) {
            System.out.println("List: " + usu.getId() + " " + usu.getName());
        }
    }

    //Recoger un usuario por ID
    public static User getUsuario(int id){
        String json = urlConnection.getUserByID(id);
        User usuario = new Gson().fromJson(json, User.class);
        return usuario;
    }

    //Mostrar un usuario
    public static void mostrarUsuario(){
        int id = teclado.leeEntero("Introduce el ID del usuario a mostrar");
        User usuario = getUsuario(id);
        System.out.println(" - " + usuario.getName() + " " + usuario.getLastname() + " " + usuario.getMail() + " " + usuario.getAge() + " " + usuario.getTelephone());
    }

    //Borrar usuario por id
    public static void deleteUsuario(){
        int id = teclado.leeEntero("Introduce el ID del usuario para borrar");
        urlConnection.deleteUserByID(id);
    }

    public static void addUsuario(){
        User usuario = new User();
        usuario.setName(teclado.leeTexto("Nombre: "));
        usuario.setLastname(teclado.leeTexto("Apellidos: "));
        usuario.setAge(teclado.leeEntero("Edad: "));
        usuario.setMail(teclado.leeTexto("Email: "));
        usuario.setTelephone(teclado.leeTexto("Telefono: "));

        //convertir Usuario a json
        Gson gson = new GsonBuilder().create();
        String jsonUser = gson.toJson(usuario);
        //System.out.println(jsonUser);
        urlConnection.addUser(jsonUser);
    }

    public static void updateUsuario(){
        User usuario = getUsuario(teclado.leeEntero("Introduce la ID del usuario para actualizar"));

        if(teclado.leeBoolean("¿quieres cambiar el nombre?")){
            usuario.setName(teclado.leeTexto("Nombre"));
        }
        if(teclado.leeBoolean("¿quieres cambiar los apellidos?")){
            usuario.setLastname(teclado.leeTexto("Apellidos"));
        }
        if(teclado.leeBoolean("¿quieres cambiar el mail?")){
            usuario.setMail(teclado.leeTexto("Email"));
        }
        if(teclado.leeBoolean("¿quieres cambiar la edad?")){
            usuario.setAge(teclado.leeEntero("Edad"));
        }
        if(teclado.leeBoolean("¿quieres cambiar el telefono?")){
            usuario.setTelephone(teclado.leeTexto("Telefono"));
        }

        //convertir Usuario a json
        Gson gson = new GsonBuilder().create();
        String jsonUser = gson.toJson(usuario);
        //System.out.println(jsonUser);
        urlConnection.updateUsuario(usuario.getId(),jsonUser);
    }

}
