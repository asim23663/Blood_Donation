package com.example.asimyamin.blood_donation.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.asimyamin.blood_donation.Model.DonorInfomation;
import com.example.asimyamin.blood_donation.R;


import java.util.ArrayList;

/**
 * Created by Asim Yamin on 12/10/2017.
 */

public class Donor_Adapter extends RecyclerView.Adapter<Donor_Adapter.ViewHolder>{


    private Context ct;
    private ArrayList<DonorInfomation> list;


    public Donor_Adapter( Context ct,ArrayList<DonorInfomation> list){


        this.ct=ct;
        this.list=list;


    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ct=parent.getContext();
        View v= LayoutInflater.from(ct).inflate(R.layout.donor_info_view,parent,false);

        ViewHolder holder=new ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final DonorInfomation donor=list.get(position);

        holder.name.setText("Name: "+donor.getName());
        holder.blood.setText(donor.getBlood());
        holder.address.setText(donor.getAddress());
        holder.number.setText(donor.getPhoneNumber());
        holder.city.setText(donor.getCity());

        holder.number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String dialNumber=holder.number.getText().toString();
                Intent phoneIntent=new Intent(Intent.ACTION_DIAL,Uri.fromParts("tel",dialNumber,null));
                ct.startActivity(phoneIntent);
                //Toast.makeText(ct,"Number was clicked",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView name,blood,address,city,number;
        public ViewHolder(View itemView) {

            super(itemView);

            name=itemView.findViewById(R.id.tvName);
            blood=itemView.findViewById(R.id.tvBlood);
            address=itemView.findViewById(R.id.tvAddress);
            city=itemView.findViewById(R.id.tvCity);
            number=itemView.findViewById(R.id.tvNumber);

        }
    }

    public void setFilter(ArrayList<DonorInfomation> list){

        this.list=new ArrayList<>();
        this.list.addAll(list);
        notifyDataSetChanged();

    }
}
