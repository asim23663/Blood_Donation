package com.example.asimyamin.blood_donation.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.asimyamin.blood_donation.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button logIn;
    private ConstraintLayout error;
    private final int RC_LOGIN=123;
    private FirebaseAuth auth;

    LoginButton fbLogin;
    private CallbackManager cbManager;

    private static final String EMAIL="email";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        logIn=findViewById(R.id.logIn);
        error=findViewById(R.id.error);
        fbLogin=findViewById(R.id.fb_login_button);

        auth=FirebaseAuth.getInstance();

        logIn.setOnClickListener(this);

        cbManager= CallbackManager.Factory.create();


        fbLogin.setReadPermissions(Arrays.asList(EMAIL));

        fbLogin.registerCallback(cbManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                Toast.makeText(MainActivity.this,"Login Successful!!!!",Toast.LENGTH_LONG).show();
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {

                Toast.makeText(MainActivity.this,"canceled",Toast.LENGTH_LONG).show();

            }

            @Override
            public void onError(FacebookException error) {

                Toast.makeText(MainActivity.this,"Internet Failure!!!",Toast.LENGTH_LONG).show();

                Log.d("Error!!",error.getMessage());
            }
        });


    }
    private void handleFacebookAccessToken(AccessToken token) {

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        auth.signInWithCredential(credential)
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = auth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI();
                        }

                        // ...
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();



        if (auth.getCurrentUser() !=null)
        {
            FirebaseUser currentUser=auth.getCurrentUser();
            updateUI(currentUser);

        }
    }

    private void updateUI(FirebaseUser user){
        startActivity(new Intent(this,Sign_In.class));
        finish();
    }


    public void register(){

        if (auth.getCurrentUser() !=null) {
            //If already Log in..//
            if (!auth.getCurrentUser().getPhoneNumber().isEmpty()) {
                startActivity(new Intent(this, Sign_In.class)
                        .putExtra("phone", auth.getCurrentUser().getPhoneNumber().isEmpty()));
                finish();

            }
        }
        else
        {
            startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(
                    Arrays.asList(new AuthUI.IdpConfig.Builder(AuthUI.PHONE_VERIFICATION_PROVIDER).build())
            ).build(),RC_LOGIN);
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        cbManager.onActivityResult(requestCode,resultCode,data);
        try {
            if (requestCode==RC_LOGIN)
            {
                IdpResponse response=IdpResponse.fromResultIntent(data);

                /////Succesfully Sign in/////
                if (resultCode==RESULT_OK)
                {
                    if (!auth.getCurrentUser().getPhoneNumber().isEmpty()) {
                        startActivity(new Intent(this,Sign_In.class).putExtra("phonne",auth.getCurrentUser().getPhoneNumber()));
                        finish();
                        return;
                    }
                    ///If Sign in Failed
                    else {
                        if (response==null)
                        {
                            Toast.makeText(this,"Canceld",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (response.getErrorCode()== ErrorCodes.NO_NETWORK)
                        {
                            Toast.makeText(this,"No INternet",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (response.getErrorCode()== ErrorCodes.UNKNOWN_ERROR)
                        {
                            Toast.makeText(this,"Unknown Error!!",Toast.LENGTH_SHORT).show();
                            return;
                        }

                    }


                    Toast.makeText(this,"Unkown Sign in Error!!!",Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Snackbar.make(error,"Internet Failure!!",Snackbar.LENGTH_LONG).show();
                        //ErrorDialog(this);


                    //Toast.makeText(this,"Internet Failure",Toast.LENGTH_SHORT).show();
                    //ErrorDialog(MainActivity.this);
                }
            }

        }catch (Exception e)
        {
            Toast.makeText(this,"Welcome",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.logIn:
                register();

                //startActivity(new Intent(this,Sign_In.class));

                break;
        }

    }



}
