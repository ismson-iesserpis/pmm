package com.example.spinnerpersona;

public class Persona {
    private String _nombre;
    private String _apellido;
    private int _edad;

    public Persona(String nombre, String apellido, int edad)
    {
        _nombre = nombre;
        _apellido = apellido;
        _edad = edad;
    }

    public String getNombre() { return _nombre; }
    public String getApellido() { return _apellido; }
    public int getEdad() { return _edad; }
}
