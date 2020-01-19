package com.example.firebaseproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.internal.InternalTokenResult;

import java.io.IOException;
import java.util.BitSet;

public class HomePage extends AppCompatActivity {
    private ImageView imgUpload ;
    private Uri photo;      //this will hold our selected photo
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        imgUpload = (ImageView) findViewById(R.id.imgUpload);

        imgUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 0);
            }
        });
    }
    //to upload that image
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==0 && resultCode == RESULT_OK && data != null){
            photo= data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),photo);     //converting that photo to bitmap in order to place it
                imgUpload.setImageBitmap(bitmap);

            } catch (IOException e) {
                Toast.makeText(this, "Something not found", Toast.LENGTH_SHORT).show();

            }
        }
    }
}
