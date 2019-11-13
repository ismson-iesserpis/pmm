package com.example.examenpmm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Pantalla2 extends AppCompatActivity {
    Spinner spVehiculos;

    Button btnTotalPrecio, btnFactura;

    RadioButton rbSinSeguro;

    CheckBox cbExtra1, cbExtra2, cbExtra3;

    EditText textNumDias;
    TextView textTotalPrecio;

    // helper para Factura
    int dias;
    boolean tieneSeguro;
    float totalExtras;
    float costeTotal;

    MedioTransporte[] electricos = new MedioTransporte[]{
            new MedioTransporte("skate", "Roxi", 12, R.drawable.skate),
            new MedioTransporte("patinete", "Roxi", 15, R.drawable.patinete),
            new MedioTransporte("monociclo", "Oneil", 18, R.drawable.monociclo1)};

    MedioTransporte[] bicis = new MedioTransporte[]{
            new MedioTransporte("Paseo", "Orbea", 15, R.drawable.bici1),
            new MedioTransporte("Ciudad", "Cube", 20, R.drawable.bici2),
            new MedioTransporte("Montaña", "Bike", 25, R.drawable.bici3)};

    MedioTransporte[] coches = new MedioTransporte[]{
            new MedioTransporte("Megane", "Renault", 60, R.drawable.megan1),
            new MedioTransporte("Leon", "Seat", 70, R.drawable.leon3),
            new MedioTransporte("Fiesta", "Ford", 75, R.drawable.fiesta2)};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla2);

        spVehiculos = findViewById(R.id.spVehiculos);

        btnTotalPrecio = findViewById(R.id.btnTotalPrecio);
        btnFactura = findViewById(R.id.botonFactura);

        rbSinSeguro = findViewById(R.id.rbSinSeguro);
        rbSinSeguro.setChecked(true);

        cbExtra1 = findViewById(R.id.extra1);
        cbExtra2 = findViewById(R.id.extra2);
        cbExtra3 = findViewById(R.id.extra3);

        textNumDias = findViewById(R.id.editTextNumDias);
        textTotalPrecio = findViewById(R.id.textTotalPrecio);

        Bundle bundle = getIntent().getExtras();
        int tipo = bundle.getInt("tipoSeleccionado");

        ArrayAdapter<MedioTransporte> adaptador;
        switch (tipo)
        {
            // Patinetes
            case 0:
                adaptador = new AdaptadorVehiculos(this, electricos);
                break;

            // Bicis
            case 1:
                adaptador = new AdaptadorVehiculos(this, bicis);
                break;

            // Coches
            case 2:
                adaptador = new AdaptadorVehiculos(this, coches);
                break;

            default:
                adaptador = new AdaptadorVehiculos(this, null);
                break;
        }
        spVehiculos.setAdapter(adaptador);

        btnTotalPrecio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalcTotalPrecio();
            }
        });

        btnFactura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HacerFactura();
            }
        });
    }

    public void CalcTotalPrecio()
    {
        String strNumDias = textNumDias.getText().toString();
        if (strNumDias.isEmpty())
        {
            Toast.makeText(this, "Debes introducir un numero de dias", Toast.LENGTH_SHORT).show();
            return;
        }

        int numDias = Integer.parseInt(strNumDias);
        dias = numDias;

        AdaptadorVehiculos adaptador = (AdaptadorVehiculos) spVehiculos.getAdapter();
        float result = numDias * adaptador.selectedData.getPrecio();

        totalExtras = 0f;
        if (cbExtra1.isChecked())
        {
            result += 50f;
            totalExtras += 50f;
        }

        if (cbExtra2.isChecked())
        {
            result += 50f;
            totalExtras += 50f;
        }

        if (cbExtra3.isChecked())
        {
            result += 50f;
            totalExtras += 50f;
        }

        tieneSeguro = false;
        if (!rbSinSeguro.isChecked())
        {
            result += result * 0.2f;
            tieneSeguro = true;
        }

        costeTotal = result;
        textTotalPrecio.setText(result + "€");
    }

    public void HacerFactura()
    {
        // Update variables
        CalcTotalPrecio();
        if (dias <= 0)
        {
            return;
        }

        AdaptadorVehiculos adaptador = (AdaptadorVehiculos) spVehiculos.getAdapter();

        Bundle bundle = new Bundle();
        Factura factura = new Factura(adaptador.selectedData, tieneSeguro, totalExtras, dias, costeTotal);
        bundle.putSerializable("factura", factura);

        Intent intent = new Intent(Pantalla2.this, Pantalla3.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public class AdaptadorVehiculos extends ArrayAdapter<MedioTransporte>
    {
        Activity activity;
        MedioTransporte[] transportes;
        public MedioTransporte selectedData;

        public AdaptadorVehiculos(Activity actividad, MedioTransporte[] transportes) {
            super(actividad, R.layout.spinner_vehiculos, transportes);

            this.transportes = transportes;
            this.activity = actividad;
        }

        public View getDropDownView(int position, View convertView, ViewGroup parent)
        {
            View vistaDesplegada = getView(position, convertView, parent);
            return vistaDesplegada;
        }

        public View getView(int position, View convertView, ViewGroup parent)
        {
            LayoutInflater inflater = activity.getLayoutInflater();
            View item = inflater.inflate(R.layout.spinner_vehiculos, null);

            TextView lblNombre = item.findViewById(R.id.vehiculoNombre);
            lblNombre.setText(transportes[position].getNombre());

            TextView lblMarca = item.findViewById(R.id.vehiculoMarca);
            lblMarca.setText(transportes[position].getMarca());

            TextView lblPrecio = item.findViewById(R.id.vehiculoPrecio);
            lblPrecio.setText(String.valueOf(transportes[position].getPrecio()));

            ImageView imgVehiculo = item.findViewById(R.id.vehiculoImagen);
            imgVehiculo.setImageDrawable(getResources().getDrawable(transportes[position].getImagen()));

            selectedData = transportes[position];

            return item;
        }
    }
}
