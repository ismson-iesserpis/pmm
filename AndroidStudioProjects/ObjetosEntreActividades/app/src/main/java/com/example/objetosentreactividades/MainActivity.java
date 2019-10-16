package com.example.objetosentreactividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final int ACTIVITY_PERSONA = 0;
    public static final int PICK_IMAGE = 1;

    private EditText textNombre;
    private EditText textApellidos;
    private EditText textEdad;
    private ImageView userImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textNombre = findViewById(R.id.textNombre);
        textApellidos = findViewById(R.id.textApellidos);
        textEdad = findViewById(R.id.textEdad);
        userImage = findViewById(R.id.userImage);
    }

    public void enviarPersona(View view)
    {
        String nombre = textNombre.getText().toString();
        if (nombre.isEmpty())
        {
            return;
        }

        String apellidos = textApellidos.getText().toString();
        if (apellidos.isEmpty())
        {
            return;
        }

        String edadStr = textEdad.getText().toString();
        if (edadStr.isEmpty())
        {
            return;
        }

        int edad;
        try
        {
            edad = Integer.parseInt(edadStr);
        }
        catch (NumberFormatException ex)
        {
            return;
        }

        String imgPath = userImage.getTag().toString();
        if (imgPath.isEmpty())
        {
            return;
        }

        Persona persona = new Persona(nombre, apellidos, edad, imgPath);

        Intent intent = new Intent(MainActivity.this, CargarPersona.class);
        Bundle bundle = new Bundle();

        bundle.putSerializable("infoPersona", persona);
        intent.putExtras(bundle);

        startActivityForResult(intent, ACTIVITY_PERSONA);
    }

    public void selectImage(View view)
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Elegir Imagen"), PICK_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == PICK_IMAGE)
        {
            Uri img = data.getData();
            userImage.setImageURI(img);
            userImage.setTag(img.toString());
        }
    }
}
