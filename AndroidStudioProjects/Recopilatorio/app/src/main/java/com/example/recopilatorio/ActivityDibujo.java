package com.example.recopilatorio;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class ActivityDibujo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new ViewDibujo(this));
    }

    public class ViewDibujo extends View
    {
        public ViewDibujo(Context context)
        {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas)
        {
            // Gorro
            Paint paint = new Paint();
            paint.setColor(Color.CYAN);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(10);

            Path path = new Path();
            path.moveTo(500f, 500f);
            path.lineTo(425f, 575f);
            path.moveTo(425f, 575f);
            path.lineTo(575f, 575f);
            path.moveTo(575f, 575f);
            path.lineTo(500f, 500f);
            path.close();

            canvas.drawPath(path, paint);

            // Cabeza
            canvas.drawCircle(500f, 650f, 75f, paint);

            // Ojos
            canvas.drawCircle(475f, 625f, 10f, paint);
            canvas.drawCircle(525f, 625f, 10f, paint);

            // Cuerpo
            canvas.drawLine(500f, 725f, 500f, 1000f, paint);

            // Brazos
            canvas.drawLine(500f, 750f, 300f, 850f, paint);
            canvas.drawLine(500f, 750f, 700f, 850f, paint);

            // Piernas
            canvas.drawLine(500f, 1000f, 425f, 1150f, paint);
            canvas.drawLine(500f, 1000f, 575f, 1150f, paint);
        }
    }
}
