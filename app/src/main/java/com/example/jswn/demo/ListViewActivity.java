package com.example.jswn.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        ListView list = findViewById(R.id.listView);

        ArrayList<MyData> arrayList = new ArrayList<>();
        arrayList.add(new MyData("Ram Patel","385789934",""));
        arrayList.add(new MyData("Om Patel","935080944",""));
        arrayList.add(new MyData("Jay Patel","209053909",""));
        arrayList.add(new MyData("Ajay Patel","398598934",""));

        MyAdapter adapter = new MyAdapter(arrayList,this);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                TextView txt = view.findViewById(R.id.name);
                Toast.makeText(ListViewActivity.this,txt.getText(),Toast.LENGTH_LONG).show();
            }
        });

    }
}
