package com.monari.thenews.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.monari.thenews.MainActivity;
import com.monari.thenews.R;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = SignupActivity.class.getSimpleName();

    @BindView(R.id.idSignUpButton)
    Button midSignUpButton;
    @BindView(R.id.name)
    EditText mname;
    @BindView(R.id.userEmail)
    EditText muserEmail;
    @BindView(R.id.userPassword)
    EditText muserPassword;
    @BindView(R.id.idLoginButton)
    Button mIdLoginButton;
    @BindView(R.id.userConfirmPassword)
    EditText mUserConfirmPassword;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();
        createAuthStateListener();

        midSignUpButton.setOnClickListener(this);
        mIdLoginButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v){


        if (v == mIdLoginButton) {
            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }

        if (v == midSignUpButton) {
            createNewUser();
        }

    }

    private void createNewUser() {
        final String name = mname.getText().toString().trim();
        final String email = muserEmail.getText().toString().trim();
        String password = muserPassword.getText().toString().trim();
        String confirmPassword = mUserConfirmPassword.getText().toString().trim();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {

                        Log.d(TAG, "Authentication successful");
                    } else {
                        Toast.makeText(SignupActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }

                });
    }

    private void createAuthStateListener() {
        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }

        };
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


}
