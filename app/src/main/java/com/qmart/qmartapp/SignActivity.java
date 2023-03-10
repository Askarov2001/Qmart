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
import com.google.firebase.database.FirebaseDatabase;
import com.qmart.qmartapp.model.User;

public class SignActivity extends AppCompatActivity{

    EditText name, email, password;
    Button signupbtn;
    TextView login;


    FirebaseAuth auth;

    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        name = findViewById(R.id.signup_name);
        email = findViewById(R.id.signup_email);
        password = findViewById(R.id.signup_password);
        signupbtn = findViewById(R.id.signup_btn);
        login = findViewById(R.id.logNow);


        auth = FirebaseAuth.getInstance();

        dialog = new ProgressDialog(this);
        dialog.setMessage("Creating account!");
        dialog.setCancelable(false);

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName, emailID, userPassword;

                userName = name.getText().toString();
                emailID = email.getText().toString();
                userPassword = password.getText().toString();

                User user = new User(userName,emailID,userPassword);

                if (userName.isEmpty()){
                    name.setError("Пожалуйста введите Имя");
                    name.requestFocus();
                }else if (emailID.isEmpty()){
                    email.setError("Пожалуйста введите Электронную почту");
                    email.requestFocus();
                }else if (userPassword.isEmpty()){
                    password.setError("Пожалуйста введите Пароль");
                    password.requestFocus();
                }else if (password.length() < 6){
                    password.setError("Минимальное количество символов(6)");
                }else {
                    auth.createUserWithEmailAndPassword(emailID, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                dialog.show();
                                startActivity(new Intent(SignActivity.this, MenuActivity.class));
                                finish();

                                FirebaseDatabase.getInstance().getReference("Users")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if(task.isSuccessful()){
                                                    dialog.show();
                                                    Toast.makeText(SignActivity.this, "Вы зарегистрировалиь успешно!", Toast.LENGTH_SHORT).show();
                                                }else{
                                                    dialog.dismiss();
                                                    Toast.makeText(SignActivity.this, "Ошибка! Повторите еще раз!", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                                
                            }else{
                                dialog.dismiss();
                                Toast.makeText(SignActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignActivity.this, LogActivity.class));
            }
        });






    }
}