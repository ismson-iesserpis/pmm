package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class Pantalla2 extends AppCompatActivity {
    TextView resultText;
    String msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla2);

        resultText = findViewById(R.id.resultText);
        Bundle bundle = getIntent().getExtras();
        msg = bundle.getString("Mensaje");
        resultText.setText(msg);
    }

    public void VolverAtras(View view)
    {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("Mensaje", msg);
        intent.putExtras(bundle);

        setResult(RESULT_OK, intent);
        finish();
    }
}
