package com.example.objetosentreactividades;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CargarPersona extends AppCompatActivity {

    private TextView textNombre;
    private TextView textApellidos;
    private TextView textEdad;
    private ImageView userImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargar_persona);

        textNombre = findViewById(R.id.personaNombre);
        textApellidos = findViewById(R.id.personaApellidos);
        textEdad = findViewById(R.id.personaEdad);
        userImage = findViewById(R.id.personaFoto);
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        Bundle bundle = getIntent().getExtras();
        Persona persona = (Persona) bundle.getSerializable("infoPersona");

        textNombre.setText(persona.getNombre());
        textApellidos.setText(persona.getApellido());
        textEdad.setText(String.valueOf(persona.getEdad()));

        userImage.setImageURI(Uri.parse(persona.getFoto()));
    }
}
