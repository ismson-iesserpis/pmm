package com.example.listaobjetos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Titular[] datos = new Titular[]{
            new Titular("Titulo 1", "Subtitulo largo 1", R.drawable.img1),
            new Titular("Titulo 2", "Subtitulo largo 2", R.drawable.img2),
            new Titular("Titulo 3", "Subtitulo largo 3", R.drawable.img3)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AdaptadorTitulares adaptador = new AdaptadorTitulares(this);
        ListView listOpciones = findViewById(R.id.listOpciones);
        listOpciones.setAdapter(adaptador);

        listOpciones.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String mensaje = "Titulo: " + datos[position].getTitulo() + ". Subtitulo: " + datos[position].getSubtitulo();
                Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_LONG).show();
            }
        });
    }

    public class AdaptadorTitulares extends ArrayAdapter {
        Activity context;

        public AdaptadorTitulares(Activity context)
        {
            super(context, R.layout.listitem_titular, datos);
            this.context = context;
        }

        public View getView(int i, View convertView, ViewGroup parent)
        {
            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.listitem_titular, null);

            TextView lbltitulo = item.findViewById(R.id.tvTitulo);
            lbltitulo.setText(datos[i].getTitulo());

            TextView lblSubtitulo = item.findViewById(R.id.tvSubtitulo);
            lblSubtitulo.setText(datos[i].getSubtitulo());

            ImageView img = item.findViewById(R.id.ivImagen);
            img.setImageDrawable(getResources().getDrawable(datos[i].getImg()));

            return (item);
        }
    }

}

