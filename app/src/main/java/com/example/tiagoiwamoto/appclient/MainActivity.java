package com.example.tiagoiwamoto.appclient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends Activity{

    FirebaseAuth firebaseAuth;
    EditText editEmail;
    EditText editSenha;
    Button btnLogar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

        editEmail = (EditText) findViewById(R.id.emailLogin);
        editSenha = (EditText) findViewById(R.id.senha);
        btnLogar = (Button) findViewById(R.id.btnEntrar);

        btnLogar.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                signIn(view);
            }
        });
    }

    public void signIn(View v) {
        String email = editEmail.getText().toString();
        String senha = editSenha.getText().toString();
        firebaseAuth.signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            startActivity(new Intent(MainActivity.this, PrincipalActivity.class));
                        } else {
                            Toast.makeText(MainActivity.this, "Falha na autenticação.",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
