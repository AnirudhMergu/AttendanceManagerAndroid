package com.anirudhmergu.attendancemanager;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class LauncherActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private TextView greeting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        greeting = findViewById(R.id.greeting);

        init();
    }

    public void init() {

        if(currentUser == null)
        {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Intent intent = new Intent(LauncherActivity.this,LoginActivity.class);
                    startActivity(intent);

                }
            }).start();
        }
        else
        {
            greeting.setText("Welcome back buddy!");

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    goToIntialActivity();

                }
            }).start();


            //Toast.makeText(LauncherActivity.this,"Launching activity",Toast.LENGTH_LONG).show();
        }
    }

    public void goToIntialActivity()
    {
        Intent intent = new Intent(LauncherActivity.this,IntialScreen.class);
        startActivity(intent);
        finish();
    }
}
