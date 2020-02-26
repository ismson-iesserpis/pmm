package com.ismail.examenpdm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class StatsActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        MainActivity.mDbHelper.open();
        Cursor itemCursor = MainActivity.mDbHelper.getItems();

        int numBajos = 0;
        int numMedios = 0;
        int numAltos = 0;

        // se procesa el resultado
        while (itemCursor.moveToNext()) {
            int importance = itemCursor.getInt(itemCursor.getColumnIndex(DataBaseHelper.SL_IMPORTANCE));

            switch (importance)
            {
                case 1:
                    numBajos++;
                    break;

                case 2:
                    numMedios++;
                    break;

                case 3:
                    numAltos++;
                    break;
            }
        }

        itemCursor.close();
        MainActivity.mDbHelper.close();

        List<Integer> nums = new ArrayList<>();
        nums.add(numBajos);
        nums.add(numMedios);
        nums.add(numAltos);

        fillData(nums);
    }

    private void fillData(List<Integer> nums)
    {
        AdaptadorStats adaptador = new AdaptadorStats(this, R.layout.stats_list, nums, getLayoutInflater());
        setListAdapter(adaptador);
    }

    private class AdaptadorStats extends ArrayAdapter<Integer>
    {
        private LayoutInflater mInflater;
        private List<Integer> mObjects;

        public AdaptadorStats(Context context, int resource, List<Integer> objects, LayoutInflater mInflater) {
            super(context, resource, objects);
            this.mInflater = mInflater;
            this.mObjects = objects;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            Integer num = mObjects.get(position);
            View row = mInflater.inflate(R.layout.stats_list, null);

            // Data
            TextView info = row.findViewById(R.id.stats_text);
            String infoStr = "Hay " + num + " tareas de prioridad ";

            ImageView img = row.findViewById(R.id.stats_importance);
            img.setTag(num);
            switch (num)
            {
                case 1:
                    infoStr += "baja";
                    img.setImageResource(R.drawable.ic_green);
                    break;

                case 2:
                    infoStr += "media";
                    img.setImageResource(R.drawable.ic_yellow);
                    break;

                default:
                    infoStr += "alta";
                    img.setImageResource(R.drawable.ic_red);
                    break;
            }

            info.setText(infoStr);
            return row;
        }
    }
}
