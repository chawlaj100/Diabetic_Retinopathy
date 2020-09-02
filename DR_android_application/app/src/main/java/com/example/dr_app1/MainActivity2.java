package com.example.dr_app1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.widget.Toast.*;

public class MainActivity2 extends AppCompatActivity {

    private ImageView imgview;
    private Button select, check;
    private int IMG_REQUEST = 10;
    public Bitmap btmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        imgview = (ImageView) findViewById(R.id.image_upload);
        select = (Button) findViewById(R.id.btnSelect);
        check = (Button) findViewById(R.id.btnCheck);

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, IMG_REQUEST);
            }
        });

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadImage();
            }
        });
    }

    public void uploadImage(){
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        btmp.compress(Bitmap.CompressFormat.PNG, 100, byteArray);
        byte[] byteImage = byteArray.toByteArray();

        String encodedImage = Base64.encodeToString(byteImage, Base64.DEFAULT);

        Call<ResponsePOJO> call = RetrofitClient.getInstance().getApi().uploadImage(encodedImage);
        call.enqueue(new Callback<ResponsePOJO>() {
            @Override
            public void onResponse(Call<ResponsePOJO> call, Response<ResponsePOJO> response) {
                Toast.makeText(MainActivity2.this, response.body().getRemarks(), LENGTH_SHORT).show();
                /*if (response.body().isStatus()) {

                } else{

                }*/
            }
            @Override
            public void onFailure(Call<ResponsePOJO> call, Throwable t) {
                Toast.makeText(MainActivity2.this, "Network Failed", LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == IMG_REQUEST && resultCode == RESULT_OK && data != null){

            Uri path = data.getData();
            try {
                btmp = MediaStore.Images.Media.getBitmap(getContentResolver(), path);
                imgview.setImageBitmap(btmp);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}