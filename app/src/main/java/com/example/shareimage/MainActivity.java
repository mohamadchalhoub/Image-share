package com.example.shareimage;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView img;
    Uri universal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = (ImageView) findViewById(R.id.imageView);
    }

    public void SelectImage(View view) {
        Intent ViewImage = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(ViewImage, 30);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 30 && resultCode==RESULT_OK){
            Uri universal = data.getData();
            img.setImageURI(universal);
        }
    }

    public void share(View view) {
        Intent send = new Intent(Intent.ACTION_SEND);
        send.setType("image/*");
        send.putExtra(Intent.EXTRA_STREAM, universal);
        send.putExtra(Intent.EXTRA_TEXT, "send universal");
        Intent choose = Intent.createChooser(send, "share image");
        startActivity(choose);
    }

    public void shareLocation(View view) {
        Intent map = new Intent(Intent.ACTION_VIEW);
        map.setData(Uri.parse("geo:33.8314645,35.5139454,16z"));
        startActivity(map);
    }
}