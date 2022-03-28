package com.example.photocapture;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    protected final static int REQUEST_ID_IMAGE_CAPTURE = 0;
    protected final static int REQUEST_ID_VIDEO_CAPTURE = 0;

    Button buttonImage, buttonVideo, buttonExit;
    ImageView imageView;
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonImage = findViewById(R.id.button_image);
        buttonVideo = findViewById(R.id.button_video);
        buttonExit = findViewById(R.id.button_exit);
        imageView = findViewById(R.id.imageView);
        videoView = findViewById(R.id.videoView);

        buttonImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_ID_IMAGE_CAPTURE);
            }
        });

        buttonVideo.setOnClickListener(view -> {
            Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            startActivityForResult(intent, REQUEST_ID_VIDEO_CAPTURE);
        });

        buttonExit.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ID_IMAGE_CAPTURE) {
            if (resultCode == RESULT_OK) {
                Bitmap bp = (Bitmap) data.getExtras().get("data");
                this.imageView.setImageBitmap(bp);
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Action annulée", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Action échouée", Toast.LENGTH_LONG).show();
            }
        }
        //////
        else if (requestCode == REQUEST_ID_VIDEO_CAPTURE) {
            if (resultCode == RESULT_OK) {
                Uri videoUri = data.getData();
                this.videoView.setVideoURI(videoUri);
                this.videoView.start();
            }
            else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, " Action annulée", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(this, "Action échouée",Toast.LENGTH_LONG).show();
            }
        }
    }

}