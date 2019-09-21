package com.example.horim;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private EditText mEmail,mPassword;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communication);

        mEmail= findViewById(R.id.login_email);
        mPassword= findViewById(R.id.login_password);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        findViewById(R.id.login_signup).setOnClickListener(this);
        findViewById(R.id.login_success).setOnClickListener(this);

    }

    @Override
    protected  void onStart(){
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if(user != null){
            //Toast.makeText(this,"자동 로그인:"+ user.getUid(),Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, Main2Activity.class));
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_signup:
                startActivity(new Intent(this ,SignupActivity.class));

                break;

            case R.id.login_success:
                mAuth.signInWithEmailAndPassword(mEmail.getText().toString(), mPassword.getText().toString())
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                  FirebaseUser user = mAuth.getCurrentUser();
                                    if(user !=null) {
                                     //   Toast.makeText(LoginActivity.this, "로그인 성공:"+ user.getUid(), Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(LoginActivity.this, Main2Activity.class));
                                    }

                                } else {
                                    Toast.makeText(LoginActivity.this, "Login error.", Toast.LENGTH_SHORT).show();

                                }

                                // ...
                            }
                        });

                break;
        }
    }
}
