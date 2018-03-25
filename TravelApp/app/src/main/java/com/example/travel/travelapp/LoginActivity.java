package com.example.travel.travelapp;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;



public class LoginActivity extends AppCompatActivity
        implements GoogleApiClient.OnConnectionFailedListener,
        GoogleApiClient.ConnectionCallbacks {

        private static final int REQ_CODE_GOOGLE_SIGNIN = 32767 / 2;

        private GoogleApiClient google;
        private FirebaseAuth mAuth;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);


            SignInButton button = (SignInButton) findViewById(R.id.sign_in_button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    signInClick(v);
                }
            });

            // request the user's ID, email address, and basic profile
            GoogleSignInOptions options = new GoogleSignInOptions.Builder(
                    GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(getString(R.string.default_web_client_id))
                    .requestEmail()
                    .build();

            // build API client with access to Sign-In API and options above
            google = new GoogleApiClient.Builder(this)
                    .enableAutoManage(this, this)
                    .addApi(Auth.GOOGLE_SIGN_IN_API, options)
                    .addConnectionCallbacks(this)
                    .build();



        }



    /*
     * This method is called when the Sign in with Google button is clicked.
     * It launches the Google Sign-in activity and waits for a result.
     */
    public void signInClick(View view) {
        Toast.makeText(this, "Sign in was clicked!", Toast.LENGTH_SHORT).show();


        // connect to Google server to log in
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(google);
        startActivityForResult(intent, REQ_CODE_GOOGLE_SIGNIN);
    }






    /*
     * This method is called when Google Sign-in comes back to my activity.
     * We grab the sign-in results and display the user's name and email address.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == REQ_CODE_GOOGLE_SIGNIN) {
            // google sign-in has returned
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(intent);
            if (result.isSuccess()) {
                // yay; user logged in successfully
                GoogleSignInAccount acct = result.getSignInAccount();
                Log.v("login", "success " + acct.getDisplayName() + " " +acct.getEmail());

                mAuth = FirebaseAuth.getInstance();
                AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
                mAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Log.d("tag", "signInWithCredential", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this, "Authentication Sucessful.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                Intent startIntent = new Intent(this, MenuActivity.class );
                startIntent.putExtra("name",acct.getDisplayName());
                startIntent.putExtra("id",acct.getId());
                startActivityForResult(startIntent, 1);
            } else {
                Log.v("login", "failure");
            }
        }
    }



    // this method is required for the GoogleApiClient.OnConnectionFailedListener above
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.v("login", "onConnectionFailed");
    }

    // this method is required for the GoogleApiClient.ConnectionCallbacks above
    public void onConnected(Bundle bundle) {
        Log.v("login","onConnected");
    }

    // this method is required for the GoogleApiClient.ConnectionCallbacks above
    public void onConnectionSuspended(int i) {
        Log.v("login","onConnectionSuspended");
    }
}

