package com.example.examenpmm;

import java.io.Serializable;

public class Factura implements Serializable {
    MedioTransporte _item;
    boolean _seguro;
    float _costeExtras;
    int _numDias;
    float _costeTotal;

    public Factura(MedioTransporte item, boolean seguro, float costeExtras, int numDias, float costeTotal)
    {
        _item = item;
        _seguro = seguro;
        _costeExtras = costeExtras;
        _numDias = numDias;
        _costeTotal = costeTotal;
    }

    public MedioTransporte getItem()
    {
        return _item;
    }

    public boolean tieneSeguro()
    {
        return _seguro;
    }

    public float getCosteExtras()
    {
        return _costeExtras;
    }

    public int getNumDias()
    {
        return _numDias;
    }

    public float getCosteTotal()
    {
        return _costeTotal;
    }
}
