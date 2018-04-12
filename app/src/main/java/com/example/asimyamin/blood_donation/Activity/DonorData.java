package com.example.asimyamin.blood_donation.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.asimyamin.blood_donation.Model.DonorInfomation;
import com.example.asimyamin.blood_donation.Adapter.Donor_Adapter;
import com.example.asimyamin.blood_donation.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;

public class DonorData extends AddDonorInfo {


    Toolbar toolbar;
    RecyclerView rv;
    Donor_Adapter adapter;
    ArrayList<DonorInfomation> list;

    MaterialSearchView searchView;

    ProgressBar bar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_data);

        searchView=findViewById(R.id.search_view);

        searchView.setHint("Search blood");
        //searchView.setSuggestions(getResources().getStringArray(R.array.bloods));
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                Toast.makeText(DonorData.this,"Choose "+query+ " Blood",Toast.LENGTH_LONG).show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                newText=newText.toLowerCase();
                ArrayList<DonorInfomation> filterList=new ArrayList<>();

                for (DonorInfomation donor:list){
                    String bloodName=donor.getBlood().toLowerCase();
                    if (bloodName.contains(newText)){
                        filterList.add(donor);

                    }
                }

                adapter.setFilter(filterList);
                return true;
            }
        });

        toolbar=findViewById(R.id.donorDataToolBar);
        toolbar.setTitle("Search Blood");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backIntent=new Intent(DonorData.this,Sign_In.class);
                startActivity(backIntent);
            }
        });
        bar=findViewById(R.id.progressBar);

        list=new ArrayList<>();

        rv=findViewById(R.id.recycler);

        showData();




    }

    public void showData(){


        bar.setVisibility(View.VISIBLE);


        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot user:dataSnapshot.getChildren()){

                    DonorInfomation donor=user.getValue(DonorInfomation.class);


                    list.add(donor);


                }
                adapter=new Donor_Adapter(DonorData.this,list);
                rv.setAdapter(adapter);
                rv.setLayoutManager(new LinearLayoutManager(DonorData.this));
                rv.hasFixedSize();
                bar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                bar.setVisibility(View.GONE);

                Toast.makeText(getApplicationContext(),"Some Error Occured!!!",Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //Handle item selection

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search,menu);

        MenuItem item=menu.findItem(R.id.search_icon);
        searchView.setMenuItem(item);
        return true;
    }
}
