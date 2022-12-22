package com.example.tiendavirtual;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    private Button BtnRegiste;
    private EditText etEmail, etPass,etConfirm;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        BtnRegiste = (Button) findViewById(R.id.BtnRegisterReg);
        etEmail =(EditText) findViewById(R.id.editEmail);
        etPass = (EditText) findViewById(R.id.editPass);
        etConfirm =(EditText) findViewById(R.id.editConfirm);
        mAuth = FirebaseAuth.getInstance();

        BtnRegiste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String email = etEmail.getText().toString().trim();
               String pass = etPass.getText().toString().trim();
               String confirm = etConfirm.getText().toString().trim();

               if(pass.compareTo(confirm) == 0){
                    mAuth.createUserWithEmailAndPassword(email,pass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(Register.this, "Usuario creado exitosamente", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(getApplicationContext(), Login.class);
                                        startActivity(intent);
                                    }
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(Register.this, "Error en el registo", Toast.LENGTH_LONG).show();
                                    Log.e("userCreate",e.toString());
                                }
                            });
               }else{
                   Toast.makeText(Register.this, "Contraseñas no coinciden", Toast.LENGTH_LONG).show();
               }
            }
        });

    }
}