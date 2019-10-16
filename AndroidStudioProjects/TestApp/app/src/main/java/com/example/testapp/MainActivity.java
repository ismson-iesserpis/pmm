package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText textoNombre;
    TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textoNombre = findViewById(R.id.editText);
        resultTextView = findViewById(R.id.resultTextView);
    }

    public void btnClick(View view)
    {
        Intent newIntent = new Intent(MainActivity.this, Pantalla2.class);
        Bundle bundle = new Bundle();

        String msg = "Te saludo, " + textoNombre.getText();
        bundle.putString("Mensaje", msg);
        newIntent.putExtras(bundle);

        startActivityForResult(newIntent, 0);
    }

    public void onActivityResult(int response, int result, Intent intent)
    {

        Bundle bundle = intent.getExtras();
        resultTextView.setText(bundle.getString("Mensaje"));
    }
}
