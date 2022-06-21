package com.monari.thenews.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.monari.thenews.R;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);

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
//            createNewUser();
//            showProgressBar();
        }




    }
}