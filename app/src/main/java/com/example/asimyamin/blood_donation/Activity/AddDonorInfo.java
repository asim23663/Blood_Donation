package com.example.asimyamin.blood_donation.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.widget.Toolbar;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asimyamin.blood_donation.Model.DonorInfomation;
import com.example.asimyamin.blood_donation.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class AddDonorInfo extends AppCompatActivity {

    Spinner spBlood,spCities;
    Button submit;
    Toolbar toolbar;
    TextInputEditText etName,etPhone,etAddress;
    CheckBox checkBox;
    TextView bError,cError,checkError;
    DatabaseReference dbRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_donor_info);

        submit=findViewById(R.id.btnSubmit);


        toolbar=findViewById(R.id.addDonorToolBar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Add Donor Info");
        toolbar.setSubtitle("Submit Correct Information");


        toolbar.setNavigationIcon(R.drawable.back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddDonorInfo.this,"Back",Toast.LENGTH_SHORT).show();
                /*Intent backIntent=new Intent(AddDonorInfo.this,Sign_In.class);
                startActivity(backIntent);*/
            }
        });

        bError=findViewById(R.id.tvErrorB);
        cError=findViewById(R.id.tvErrorC);
        checkError=findViewById(R.id.checkError);

        dbRef=FirebaseDatabase.getInstance().getReference("Donor's Information");
        dbRef.keepSynced(true);

        etName=findViewById(R.id.etName);
        etPhone=findViewById(R.id.etPhoneNumber);
        etAddress=findViewById(R.id.etAddress);
        spBlood=findViewById(R.id.bGroup);
        spCities=findViewById(R.id.city);
        checkBox=findViewById(R.id.checkBox);




    }

    public void reset(){
        etName.setText(null);
        etPhone.setText(null);
        etAddress.setText(null);
        spBlood.setSelection(0);
        spCities.setSelection(0);
    }

    public void addDonorInfo(View v){

        String name=etName.getText().toString().trim();
        String blood=spBlood.getSelectedItem().toString();
        String city=spCities.getSelectedItem().toString();
        String phoneNumber=etPhone.getText().toString().trim();
        String address=etAddress.getText().toString().trim();

        if (name.isEmpty()){
            etName.setError("Name is required");
            etName.requestFocus();
            return;
        }
        if (spBlood.getSelectedItemPosition()==0){
            bError.setText("Blood Group is required");
            bError.setVisibility(View.VISIBLE);
            spBlood.requestFocus();
            return;
        }
        if (phoneNumber.isEmpty()){
            etPhone.setError("Phone Number is required");
            return;
        }
        if (address.isEmpty()){
            etAddress.setError("Address is required");
            etAddress.requestFocus();
            return;
        }

        if (spCities.getSelectedItemPosition()==0){

            cError.setText("City is required");
            cError.setVisibility(View.VISIBLE);
            return;
        }
        if (!checkBox.isChecked()){
            checkError.setText("Tick this");
            checkError.setVisibility(View.VISIBLE);
            return;

        }

        String id=dbRef.push().getKey();
        DonorInfomation donor=new DonorInfomation(name,phoneNumber,address,blood,city);

        dbRef.child(id).setValue(donor);

        Toast.makeText(AddDonorInfo.this,"Donor Add Successfully...",Toast.LENGTH_SHORT).show();
        reset();


    }

    public void removeError(View v) {

        if (checkBox.isChecked()){
            checkError.setVisibility(View.GONE);
        }
        if (spBlood.getSelectedItemPosition()  !=0){
            bError.setVisibility(View.INVISIBLE);
        }

        if (spCities.getSelectedItemPosition() !=0){
            cError.setVisibility(View.INVISIBLE);
        }
    }


}
