package com.example.examenpmm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int tiposMedio[] = {R.drawable.patinete, R.drawable.bicis, R.drawable.coches};
    int tipoSeleccionado = -1;

    ImageView imgPatinete, imgBici, imgCoche;
    ImageView resultImg;

    Button btnContinuar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultImg = findViewById(R.id.imgResul);

        imgPatinete = findViewById(R.id.img_11);
        imgPatinete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectType(0);
            }
        });

        imgBici = findViewById(R.id.img_12);
        imgBici.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectType(1);
            }
        });

        imgCoche = findViewById(R.id.img_13);
        imgCoche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectType(2);
            }
        });

        btnContinuar = findViewById(R.id.btn);
        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SiguientePantalla();
            }
        });
    }

    public void SelectType(int type)
    {
        resultImg.setImageDrawable(getResources().getDrawable(tiposMedio[type]));
        tipoSeleccionado = type;
    }

    public void SiguientePantalla()
    {
        if (tipoSeleccionado == -1)
        {
            Toast.makeText(this, "Selecciona un tipo", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(MainActivity.this, Pantalla2.class);

        Bundle bundle = new Bundle();
        bundle.putInt("tipoSeleccionado", tipoSeleccionado);

        intent.putExtras(bundle);
        startActivity(intent);
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.itemDibujo:
                break;

            case R.id.itemAyuda:
                Toast.makeText(this, "Ismail Yilmaz Soñora", Toast.LENGTH_SHORT).show();
                break;

            case R.id.itemCopyright:
                Toast.makeText(this, "Ismail Yilmaz Soñora", Toast.LENGTH_SHORT).show();
                break;

            default:
                super.onOptionsItemSelected(item);
        }

        return true;
    }
}
