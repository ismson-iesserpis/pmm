package com.example.objetosentreactividades;

import android.net.Uri;

import java.io.Serializable;

/**
 * Created by angel on 22/09/2016.
 */
public class Persona implements Serializable {
    private String nombre;
    private String apellido;
    private int edad;
    private String foto;

    Persona (String nombre,String apellido,int edad,String foto){
        this.nombre=nombre;
        this.apellido=apellido;
        this.edad=edad;
        this.foto=foto;
    }

    public String getNombre (){
        return nombre;
    }
    public String getApellido(){
        return apellido;
    }
    public int getEdad(){
        return edad;
    }
    public String getFoto(){return foto;}

    public void setNombre(String nuevoNom){
        nombre=nuevoNom;
    }
    public void setApellido(String nuevoApe){
        apellido= nuevoApe;
    }
    public void setEdad (int num){
        edad=num;
    }
    public void setFoto (String nuevoFoto) { foto=nuevoFoto;}
    public String toString(){
        String cad="";
        cad+="NOMBRE:"+ nombre+ " APELLIDO:" + apellido + " EDAD:" + edad;
        return cad;
    }

}
