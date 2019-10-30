package com.example.listaobjetos;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

public class Titular implements Serializable {
    private String _titulo;
    private String _subtitulo;
    private int _img;

    public Titular(String titulo, String subtitulo, int img) {
        _titulo = titulo;
        _subtitulo = subtitulo;
        _img = img;
    }

    public void setTitulo(String titulo) { _titulo = titulo; }
    public String getTitulo() { return _titulo; }

    public void setSubtitulo(String subtitulo) { _subtitulo = subtitulo; }
    public String getSubtitulo() { return _subtitulo; }

    public void setImg(int img) { _img = img; }
    public int getImg() { return _img; }
}
