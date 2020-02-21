package com.example.examenpmm;

import java.io.Serializable;

public class MedioTransporte implements Serializable {
    String _nombre;
    String _marca;
    int _precio;
    int _imagen;

    public MedioTransporte(String nombre, String marca, int precio, int imagen)
    {
        _nombre = nombre;
        _marca = marca;
        _precio = precio;
        _imagen = imagen;
    }

    public String getNombre()
    {
        return _nombre;
    }

    public String getMarca()
    {
        return _marca;
    }

    public int getPrecio()
    {
        return _precio;
    }

    public int getImagen()
    {
        return _imagen;
    }
}
