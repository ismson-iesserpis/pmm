package com.example.recopilatorio;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Zona[] datos = new Zona[] {
        new Zona("Zona A", "Europa", 75),
        new Zona("Zona B", "Asia", 50),
        new Zona("Zona C", "Africa", 100)
    };

    Spinner spinner;
    RadioButton rb;
    EditText edPeso;
    CheckBox extra1, extra2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);
        spinner.setAdapter(new AdaptadorZona(this));

        rb = findViewById(R.id.radioButton2);
        edPeso = findViewById(R.id.editText);
        extra1 = findViewById(R.id.checkBox);
        extra2 = findViewById(R.id.checkBox2);

        Button btnCalcular = findViewById(R.id.btnCalcular);
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calcular();
            }
        });


    }

    private void Calcular()
    {
        Intent intent = new Intent(MainActivity.this, ActivityResultado.class);
        Bundle bundle = new Bundle();

        bundle.putSerializable("zona", datos[spinner.getSelectedItemPosition()]);
        bundle.putBoolean("urgente", rb.isChecked());
        bundle.putInt("peso", Integer.parseInt(edPeso.getText().toString()));
        bundle.putBoolean("extra1", extra1.isChecked());
        bundle.putBoolean("extra2", extra2.isChecked());

        intent.putExtras(bundle);
        startActivity(intent);
    }

    class AdaptadorZona extends ArrayAdapter<Zona>
    {
        Activity miActividad;

        public AdaptadorZona(Activity actividad) {
            super(actividad, R.layout.spinnerlayout, datos);

            miActividad = actividad;
        }

        public View getDropDownView(int position, View convertView, ViewGroup parent)
        {
            View vistaDesplegada = getView(position, convertView, parent);
            return vistaDesplegada;
        }

        public View getView(int position, View convertView, ViewGroup parent)
        {
            LayoutInflater inflater = miActividad.getLayoutInflater();
            View item = inflater.inflate(R.layout.spinnerlayout, null);

            TextView lblZoneName = item.findViewById(R.id.textZoneName);
            lblZoneName.setText(datos[position].zona);

            TextView lblZoneContinents = item.findViewById(R.id.textZoneContinent);
            lblZoneContinents.setText(datos[position].continentes);

            TextView lblZonePrice = item.findViewById(R.id.textZonePrice);
            lblZonePrice.setText(datos[position].precio + "€");

            return item;
        }
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menuprincipal, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.itemDibujo:
                Intent intent = new Intent(MainActivity.this, ActivityDibujo.class);
                startActivity(intent);
                break;

            case R.id.itemAcercaDe:
                Toast.makeText(this, "Ismail Yilmaz Soñora", Toast.LENGTH_SHORT).show();
                break;

            default:
                super.onOptionsItemSelected(item);
        }

        return true;
    }
}
