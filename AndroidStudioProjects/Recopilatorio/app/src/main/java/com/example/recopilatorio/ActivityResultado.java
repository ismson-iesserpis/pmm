package com.example.recopilatorio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivityResultado extends AppCompatActivity {
    public int zonaInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        Bundle bundle = getIntent().getExtras();

        TextView lblTextZona = findViewById(R.id.resultadoTextZona);
        TextView lblTextTarifa = findViewById(R.id.resultadoTextTarifa);
        TextView lblTextPeso = findViewById(R.id.resultadoTextPeso);
        TextView lblTextDecoracion = findViewById(R.id.resultadoTextDecoracion);
        TextView lblTextCosteFinal = findViewById(R.id.resultadoTextCosteFinal);

        Zona zona = (Zona) bundle.getSerializable("zona");
        lblTextZona.setText("Zona: " + zona.zona + "("+ zona.continentes + ")");

        ImageView img = findViewById(R.id.imageView2);
        if (zona.zona.equalsIgnoreCase("zona a"))
        {
            img.setImageDrawable(getResources().getDrawable(R.drawable.mapaeuropa));
            zonaInt = 0;
        }
        else if (zona.zona.equalsIgnoreCase("zona b"))
        {
            img.setImageDrawable(getResources().getDrawable(R.drawable.mapaasia));
            zonaInt = 1;
        }
        else if (zona.zona.equalsIgnoreCase("zona c"))
        {
            img.setImageDrawable(getResources().getDrawable(R.drawable.mapaafrica));
            zonaInt = 2;
        }
        registerForContextMenu(img);

        boolean urgente = bundle.getBoolean("urgente");
        String tarifa = urgente ? "urgente" : "normal";
        lblTextTarifa.setText("Tarifa: " + tarifa);

        int peso = bundle.getInt("peso");
        lblTextPeso.setText("Peso: " + peso);

        String extras = "";
        boolean extra1 = bundle.getBoolean("extra1");
        boolean extra2 = bundle.getBoolean("extra2");
        extras += extra1 ? "Con caja regalo" : "";
        extras += extra2 ? extra1 ? " y dedicatoria" : "Con dedicatoria" : "";
        lblTextDecoracion.setText("Decoración: " +  extras);

        float costeFinal = zona.precio;

        if (peso <= 5)
        {
            costeFinal += peso;
        }
        else if (peso <= 10)
        {
            costeFinal += peso * 1.5;
        }
        else
        {
            costeFinal += peso * 2;
        }

        if (urgente)
        {
            costeFinal *= 1.3;
        }

        lblTextCosteFinal.setText("Coste Final: " + costeFinal + "€");
    }

    @Override
    public void onCreateContextMenu(ContextMenu cMenu, View v, ContextMenu.ContextMenuInfo cMenuInfo)
    {
        super.onCreateContextMenu(cMenu, v, cMenuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menucontextual, cMenu);

        switch (zonaInt)
        {
            case 0:
                cMenu.getItem(0).setTitle("Europa");
                break;

            case 1:
                cMenu.getItem(0).setTitle("Asia");
                break;

            case 2:
                cMenu.getItem(0).setTitle("Africa");
                break;
        }

    }


}
