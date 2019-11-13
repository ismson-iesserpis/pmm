package com.example.examenpmm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Pantalla3 extends AppCompatActivity {
    ImageView imgFoto;

    TextView textNombre, textPrecio, textExtras, textTiempo, textSeguro, textCosteTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla3);

        Bundle bundle = getIntent().getExtras();
        Factura factura = (Factura) bundle.getSerializable("factura");

        imgFoto = findViewById(R.id.facturaImgBici);

        textNombre = findViewById(R.id.facturaTextNombre);
        textPrecio = findViewById(R.id.facturaTextPrecio);
        textExtras = findViewById(R.id.facturaTextExtras);
        textTiempo = findViewById(R.id.facturaTextTiempo);
        textSeguro = findViewById(R.id.facturaTextSeguro);
        textCosteTotal = findViewById(R.id.facturaTextCosteTotal);

        MedioTransporte transporte = factura.getItem();
        imgFoto.setImageDrawable(getResources().getDrawable(transporte.getImagen()));
        textNombre.setText("Modelo: " + transporte.getNombre());
        textPrecio.setText("Precio por dias: " + transporte.getPrecio());
        textExtras.setText("Extras: " + factura.getCosteExtras());
        textTiempo.setText("Tiempo: " + factura.getNumDias());

        String seguro;
        if (factura.tieneSeguro())
        {
            seguro = "Con seguro";
        }
        else
        {
            seguro = "Sin seguro";
        }
        textSeguro.setText("Seguro: " + seguro);

        textCosteTotal.setText(String.valueOf(factura.getCosteTotal()));
    }
}
