package com.example.asimyamin.blood_donation.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.asimyamin.blood_donation.R;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

public class Sign_In extends AppCompatActivity implements View.OnClickListener {

    Button addDonor,searchBlood;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign__in);

        //For Offline store data
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        Toolbar toolbar=findViewById(R.id.signInToolBar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Welcome to Blood Bank");


        addDonor=findViewById(R.id.btnAddBlood);
        searchBlood=findViewById(R.id.btnSearchBlood);

        addDonor.setOnClickListener(this);
        searchBlood.setOnClickListener(this);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        getMenuInflater().inflate(R.menu.my_menu,menu);
        return true;
    }

    ///checks Which item is selected and perform action accordingly/////
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.btnAddBlood:
                startActivity(new Intent(this,AddDonorInfo.class));
                break;
            case R.id.btnSearchBlood:
                startActivity(new Intent(this,DonorData.class));
                break;

        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder=new AlertDialog.Builder(Sign_In.this);
        builder.setMessage("Do you want to close this App !!");
        builder.setCancelable(false);
        builder.setTitle("Exit App");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Sign_In.super.onBackPressed();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alert=builder.create();
        alert.show();
    }

    public void logOut(MenuItem item) {
        AuthUI.getInstance().signOut(this).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                startActivity(new Intent(Sign_In.this,MainActivity.class));
                finish();
            }
        });

    }
}
