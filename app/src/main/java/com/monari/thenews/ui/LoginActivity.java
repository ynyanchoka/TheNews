package com.monari.thenews.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;
import com.monari.thenews.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = LoginActivity.class.getSimpleName();
    private FirebaseAuth.AuthStateListener mAuthListener;

    @BindView(R.id.idLoginButton)
    Button midLoginButton;
    @BindView(R.id.signUpButton) Button mSignUpButton;
    @BindView(R.id.idUserEmail)
    EditText midUserEmail;
    @BindView(R.id.iduserPassword)
    EditText midUserPassword;
    @BindView(R.id.firebaseProgressBar)
    ProgressBar mSignInProgressBar;
//    @BindView(R.id.loadingTextView) TextView mLoadingSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mSignUpButton.setOnClickListener(this);
        midLoginButton.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        if (view == midLoginButton) {
//            loginWithPassword();
//            showProgressBar();
        }
        if (view == mSignUpButton) {
            Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
    }
}