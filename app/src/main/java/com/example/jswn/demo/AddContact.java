package com.example.jswn.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddContact extends AppCompatActivity implements View.OnClickListener {

    private TextView txtName;
    private TextView txtPhoneNo;
    private TextView txtEmail;
    private Button btnAdd;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        txtEmail = findViewById(R.id.Email);
        txtName = findViewById(R.id.Name);
        txtPhoneNo = findViewById(R.id.PhoneNo);
        btnAdd = findViewById(R.id.btnAdd);
        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() == null){
            Intent intent = new Intent(AddContact.this,Login.class);
            startActivity(intent);
            AddContact.this.finish();
        }
        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        btnAdd.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view == btnAdd){
            AddUser();
            finish();
        }
    }

    private void AddUser() {
        String Name = txtName.getText().toString().trim();
        String Phone = txtPhoneNo.getText().toString().trim();
        String Email = txtEmail.getText().toString().trim();
        MyData data = new MyData(Name,Phone,Email);
        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference.child(user.getUid()+txtPhoneNo.getText()).setValue(data);
        Toast.makeText(this, Name + " Saved in Your Contact List.",Toast.LENGTH_SHORT).show();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    MyData data = snapshot.getValue(MyData.class);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
