package com.example.jswn.demo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class Register extends AppCompatActivity implements View.OnClickListener {

    private Button btn;
    private EditText txtUser;
    private EditText txtPwd;
    private TextView txtReg;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        btn = (Button) findViewById(R.id.button);
        txtUser = (EditText) findViewById(R.id.UserID);
        txtPwd = (EditText) findViewById(R.id.Password);
        txtReg = (TextView) findViewById(R.id.txtCreate);
        btn.setOnClickListener(this);
        txtReg.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == txtReg){
            Intent intent = new Intent(Register.this,Login.class);
            startActivity(intent);
            Register.this.finish();
        }

        if(view == btn){
            RegisterUser();
            Intent intent = new Intent(Register.this,MainActivity.class);
            startActivity(intent);
            Register.this.finish();
        }
    }

    private void RegisterUser() {
        final String Email = txtUser.getText().toString().trim();
        final String Password = txtPwd.getText().toString().trim();
        if(TextUtils.isEmpty(Email)){
            txtPwd.setError("Please Enter Email.");
            return;
        }
        if(TextUtils.isEmpty(Password)){
            txtPwd.setError("Please Enter Password.");
            return;
        }
        progressDialog.setMessage("Registering User...");

        firebaseAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.show();
                if(task.isSuccessful()){
                    Toast.makeText(Register.this,"Registered Successfully.",Toast.LENGTH_SHORT).show();
                }
                else {
                    if(task.getException() instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(Register.this,"You are already Registered.",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(Register.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
