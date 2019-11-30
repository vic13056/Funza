package com.example.funza;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.funza.adapters.ViewTeachersAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class ViewTeachers extends AppCompatActivity {

    RecyclerView recycler_list;
    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    FirebaseRecyclerAdapter<ItemConstructor, ViewTeachersAdapter> adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_teachers);

        recycler_list = findViewById(R.id.recycler);
        recycler_list.setLayoutManager(new LinearLayoutManager(this));
        recycler_list.setHasFixedSize(true);

        //Firebase init
        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference().child("Users");
        adapter = new FirebaseRecyclerAdapter<ItemConstructor, ViewTeachersAdapter>(
                ItemConstructor.class,
                R.layout.teacher,
                ViewTeachersAdapter.class,
                mRef
        ) {
            @Override
            protected void populateViewHolder(ViewTeachersAdapter viewTeachersAdapter, ItemConstructor itemConstructor, int i) {

                viewTeachersAdapter.username.setText(itemConstructor.getName());
                viewTeachersAdapter.userlocation.setText(itemConstructor.getLocation());
                viewTeachersAdapter.userphone.setText(itemConstructor.getPhone());
                viewTeachersAdapter.useraddress.setText(itemConstructor.getAddress());

                viewTeachersAdapter.mcall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + ""));
                        if (ContextCompat.checkSelfPermission(ViewTeachers.this,
                                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(ViewTeachers.this, new String[]{Manifest.permission.CALL_PHONE},1);
                        }
                        else
                        {
                            startActivity(intent);
                        }
                    }
                });

                viewTeachersAdapter.Mmassage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Uri uri = Uri.parse("smsto:07");
                        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
                        intent.putExtra("sms_body", "");
                        startActivity(intent);
                    }
                });

               // Picasso.get().load(itemConstructor.getImage()).into(viewTeachersAdapter.image_user);
            }
        };
        recycler_list.setAdapter(adapter);

    }
}
