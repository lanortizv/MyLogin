package com.example.lan.mylogin.data;

/**
 * Created by lan on 07-06-17.
 */

public class Cuenta {
    private  String nombre;
    private String password;
    private String link;

    public Cuenta(String nombre, String password, String link) {
        this.nombre = nombre;
        this.password = password;
        this.link = link;
    }

    public String getNombre() {

        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
