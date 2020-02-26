package com.ismail.examenpdm;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import org.w3c.dom.Text;


public class DataFragment extends Fragment
{
    int id;
    String task;
    String place;

    public void setup(int entryId)
    {
        MainActivity.mDbHelper.open();
        Cursor entry = MainActivity.mDbHelper.getItem(entryId);

        while (entry.moveToNext()) {
             id = entry.getInt(entry.getColumnIndex(DataBaseHelper.SL_ID));
             task = entry.getString(entry.getColumnIndex(DataBaseHelper.SL_ITEM));
             place = entry.getString(entry.getColumnIndex(DataBaseHelper.SL_PLACE));
        }

        entry.close();
        MainActivity.mDbHelper.close();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_item, container, false);

        TextView textRegistro = view.findViewById(R.id.itemRegistro);
        textRegistro.setText("ID: " + id);

        TextView textTarea = view.findViewById(R.id.itemTarea);
        textTarea.setText("Tarea: " + task);

        Button btn = view.findViewById(R.id.btnBorrar);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.mDbHelper.open();
                MainActivity.mDbHelper.delete(id);
                MainActivity.mDbHelper.close();
            }
        });

        Button btn2 = view.findViewById(R.id.btnActualizar);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), ItemActivity.class);
                i.putExtra(DataBaseHelper.SL_ID, id);
                startActivityForResult(i, MainActivity.EDIT_ITEM);
            }
        });

        return view;
    }
}
