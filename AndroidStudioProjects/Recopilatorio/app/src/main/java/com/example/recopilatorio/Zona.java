package com.example.recopilatorio;

import java.io.Serializable;

public class Zona implements Serializable {
    public String zona;
    public String continentes;
    public int precio;

    public Zona(String zona, String continentes, int precio)
    {
        this.zona = zona;
        this.continentes = continentes;
        this.precio = precio;
    }
}
