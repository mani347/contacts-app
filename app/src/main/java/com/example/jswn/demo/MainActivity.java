package com.example.jswn.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView list;
    private TextView textEmail;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private ImageButton btnCallButton;
    private TextView txtPhoneNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

                Intent intent = new Intent(MainActivity.this,AddContact.class);
                startActivity(intent);
            }
        });

        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() == null){
            Intent intent = new Intent(MainActivity.this,Login.class);
            startActivity(intent);
            MainActivity.this.finish();
        }
        FirebaseUser user = firebaseAuth.getCurrentUser();
//        textEmail = (TextView) findViewById(R.id.txtEmail);

        list = findViewById(R.id.listView);

        final List<MyData> arrayList = new ArrayList<>();


//        arrayList.add(new MyData("Ram Patel","385789934",""));
//        arrayList.add(new MyData("Om Patel","935080944",""));
//        arrayList.add(new MyData("Jay Patel","209053909",""));
//        arrayList.add(new MyData("Ajay Patel","398598934",""));

        final MyAdapter adapter = new MyAdapter(arrayList,this);


        databaseReference = FirebaseDatabase.getInstance().getReference(user.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                arrayList.clear();
                Toast.makeText(MainActivity.this,"Start",Toast.LENGTH_SHORT).show();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    MyData data = snapshot.getValue(MyData.class);
                    arrayList.add(data);
                    Toast.makeText(MainActivity.this,data.getName() + "Hello",Toast.LENGTH_SHORT).show();
                }
                MyAdapter adapter1 = new MyAdapter(arrayList,MainActivity.this);
                list.setAdapter(adapter1);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                TextView txt = view.findViewById(R.id.name);
                Toast.makeText(MainActivity.this,txt.getText(),Toast.LENGTH_LONG).show();
            }
        });
    }

}
