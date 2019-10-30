package com.example.spinnerconlista;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public Activity main;
    public Spinner spinner;
    public ListView lista;

    public Persona[] personas = new Persona[]{
            new Persona("Angeles", "Campos", 21),
            new Persona("Consuelo", "Martin", 20),
            new Persona("Fernando", "Molina", 26)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main = this;
        spinner = findViewById(R.id.spinnerPersona);
        lista = findViewById(R.id.listaPersona);

        AdaptadorSpinner adaptadorSpinner = new AdaptadorSpinner(this);
        spinner.setAdapter(adaptadorSpinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                AdaptadorLista adaptadorLista = new AdaptadorLista(main, personas[position]);
                lista.setAdapter(adaptadorLista);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public class AdaptadorSpinner extends ArrayAdapter<Persona>
    {
        public AdaptadorSpinner(Activity actividad)
        {
            super(actividad, R.layout.spinnerlayout, personas);
        }

        public View getDropDownView(int position, View convertView, ViewGroup parent)
        {
            View vistaDesplegada = getView(position, convertView, parent);
            return vistaDesplegada;
        }

        public View getView(int position, View convertView, ViewGroup parent)
        {
            LayoutInflater inflater = main.getLayoutInflater();
            View item = inflater.inflate(R.layout.spinnerlayout, null);

            TextView lblNombre = item.findViewById(R.id.campoNombre);
            lblNombre.setText(personas[position].getNombre());

            return item;
        }
    }

    public class AdaptadorLista extends ArrayAdapter<Persona>
    {
        public Persona persona;

        public AdaptadorLista(Activity actividad, Persona persona)
        {
            super(actividad, R.layout.listalayout, new Persona[] { persona });
            this.persona = persona;
        }

        public View getView(int position, View convertView, ViewGroup parent)
        {
            LayoutInflater inflater = main.getLayoutInflater();
            View item = inflater.inflate(R.layout.listalayout, null);

            TextView lblNombre = item.findViewById(R.id.tvNombre);
            TextView lblApellido = item.findViewById(R.id.tvApellido);
            TextView lblEdad = item.findViewById(R.id.tvEdad);

            lblNombre.setText(persona.getNombre());
            lblApellido.setText(persona.getApellido());
            lblEdad.setText(String.valueOf(persona.getEdad()));

            return item;
        }
    }
}
