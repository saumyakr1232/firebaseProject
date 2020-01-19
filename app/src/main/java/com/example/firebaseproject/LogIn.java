package com.example.firebaseproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogIn extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private EditText editEmailLogin, editPasswordLogin;
    private Button btnLogIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);


        editEmailLogin = (EditText) findViewById(R.id.editEmailLogin);
        editPasswordLogin = (EditText) findViewById(R.id.editPasswordLogin);

        btnLogIn = (Button) findViewById(R.id.btnLogIn);

        mAuth = FirebaseAuth.getInstance();


        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editEmailLogin.getText().toString().isEmpty() || editPasswordLogin.getText().toString().isEmpty()){
                    Toast.makeText(LogIn.this, "Empty Fields are not allowed", Toast.LENGTH_SHORT).show();
                    return;
                }
                mAuth.signInWithEmailAndPassword(editEmailLogin.getText().toString(), editPasswordLogin.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(LogIn.this, "Logged in succesfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(LogIn.this , HomePage.class);
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(LogIn.this, "Failed to login",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}
