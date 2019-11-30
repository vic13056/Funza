package com.example.funza.adapters;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.funza.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ViewTeachersAdapter extends RecyclerView.ViewHolder {

   public CircleImageView image_user;
   public TextView username, userphone, useraddress, userlocation;
   public Button mcall,Mmassage;

    public ViewTeachersAdapter(@NonNull View itemView) {
        super(itemView);

        image_user = itemView.findViewById(R.id.img);
        username = itemView.findViewById(R.id.name);
        userphone = itemView.findViewById(R.id.phone);
        useraddress = itemView.findViewById(R.id.address);
        userlocation = itemView.findViewById(R.id.location);
        mcall = itemView.findViewById(R.id.btncall);
        Mmassage = itemView.findViewById(R.id.btnmessage);
    }
}
