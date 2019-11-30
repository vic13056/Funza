package com.example.funza;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import de.hdodenhof.circleimageview.CircleImageView;

public class SignUpActivity extends AppCompatActivity {
    CircleImageView mimage;
    RadioGroup radioGroup;
    RadioButton radioButton;
    EditText mfullname,mphone,maddress,mlocation;
    static int REQUESCODE = 1;
    static int PregCode = 1;
    Uri pickedimg;
    private ProgressDialog dialog;
    Button msave,mproceed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mimage = findViewById(R.id.imageView);
        mfullname = findViewById(R.id.edtfullname);
        mphone = findViewById(R.id.edtphone);
        radioGroup = findViewById(R.id.radiogroup);
        maddress  = findViewById(R.id.edtaddress);
        mlocation  = findViewById(R.id.edtocation);
        msave = findViewById(R.id.btnsave);
        mproceed = findViewById(R.id.btnproceed);

        dialog = new ProgressDialog(this);
        dialog.setTitle("Saving");
        dialog.setMessage("Please Wait...");

        //start by coonecting your app to firebse and then,
        //set an onclick listener to the save button to start saving

        mproceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ViewTeachers.class));
            }
        });

        msave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //start by getting data from the user
                String name = mfullname.getText().toString().trim();
                String phone = mphone.getText().toString().trim();
                String location = mlocation.getText().toString().trim();
                String address = maddress.getText().toString().trim();
                String gender = radioButton.getText().toString().trim();
                //check if the inputs are empty
                if (name.isEmpty()){
                    mfullname.setError("Enter Name");
                }else
                    if (phone.isEmpty()){
                        mphone.setError("Enter Phone");
                    }else
                        if (location.isEmpty()){
                            mlocation.setError("Enter Your Location");
                        }else
                            if (address.isEmpty()){
                                maddress.setError("Enter Your Address");
                            }else
                                if (gender.isEmpty()){
                                    radioButton.setError("Select One");
                                }else {
                                    long time = System.currentTimeMillis();
                                    String timeconv = String.valueOf(time);

                                    //write code to create the table/a child/get the firebase database instance to save data
                                    final DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users/" + timeconv);

                                    //use the item constructor to save data from the user
                                    ItemConstructor constructor = new ItemConstructor(timeconv,name,phone,location,address,gender);
                                    dialog.show();
                                    ref.setValue(constructor).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            dialog.dismiss();
                                            if (task.isSuccessful()){
                                                Toast.makeText(SignUpActivity.this, "User Created", Toast.LENGTH_SHORT).show();
                                                clear();

                                                //update user info the profile pictureg
                                            }

                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(SignUpActivity.this, "Failed"+e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
            }
        });
        mimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >22){

                    checkAndRequestForPermission ();
                }
                else {
                    openGallery();
                }

            }
        });
    }
    public void clear(){
        mfullname.setText("");
        mphone.setText("");
        mlocation.setText("");
        maddress.setText("");
        radioButton.setText("");

    }

    private void openGallery() {
        //open gallery intent and wait for the user to pick an image

        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,REQUESCODE);

    }

    private void checkAndRequestForPermission() {
        if (ContextCompat.checkSelfPermission(SignUpActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(SignUpActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)){
                Toast.makeText(this, "Please accept for the required permission", Toast.LENGTH_SHORT).show();
            }else {
                ActivityCompat.requestPermissions(SignUpActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        PregCode);

            }

        }
        else {
            openGallery();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == REQUESCODE && data != null){
            //the user has sucessfully picked an image
            //we need its reference to a Uri Varaible

            pickedimg= data.getData();
            mimage.setImageURI(pickedimg);
        }
    }

    public void checkbutton(View view) {
        int radioid = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioid);
    }

}
