package com.qmart.qmartapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LogActivity extends AppCompatActivity{

    EditText email, password;
    Button loginbtn;
    TextView signup;

    FirebaseAuth auth;

    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        email = findViewById(R.id.logemail);
        password = findViewById(R.id.logpassword);
        loginbtn = findViewById(R.id.loginbtn);
        signup = findViewById(R.id.signNow);

        auth = FirebaseAuth.getInstance();

        dialog = new ProgressDialog(this);
        dialog.setMessage("Logging in...");
        dialog.setCancelable(false);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailID, userPassword;

                emailID = email.getText().toString();
                userPassword = password.getText().toString();

                if (emailID.isEmpty()){
                    email.setError("Введите Электронную почту");
                    email.requestFocus();
                }else if (userPassword.isEmpty()){
                    password.setError("Введите Пароль");
                    password.requestFocus();
                }else{
                    dialog.show();
                    auth.signInWithEmailAndPassword(emailID, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                dialog.dismiss();
                                startActivity(new Intent(LogActivity.this, MenuActivity.class));
                                finish();
                            }else{
                                dialog.dismiss();
                                Toast.makeText(LogActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogActivity.this, SignActivity.class));
            }
        });


    }
}