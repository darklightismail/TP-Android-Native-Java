package com.example.gestionfichier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    Button buttonEffacer,buttonAjouter,buttonCharger,buttonQuitter;
    EditText edt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt = findViewById(R.id.edt);
        buttonEffacer = findViewById(R.id.button1);
        buttonAjouter = findViewById(R.id.button2);
        buttonCharger = findViewById(R.id.button3);
        buttonQuitter = findViewById(R.id.button4);
        buttonEffacer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                File file=getApplicationContext().getFileStreamPath("texte.txt");
                file.delete();
            }
        });

        buttonAjouter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try{
                    FileOutputStream f=openFileOutput("texte.txt",MODE_APPEND);
                    OutputStreamWriter outputfl=new OutputStreamWriter(f);
                    outputfl.write(edt.getText().toString());
                    outputfl.close();
                    f.close();
                    Toast.makeText(MainActivity.this,"Enregistrement effectué",Toast.LENGTH_LONG).show();
                }
                catch(IOException e)
                {
                    Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                }}
        });
        buttonCharger.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                File f=getApplicationContext().getFileStreamPath("texte.txt");
                String ligneTXT;
                if(f.exists())
                {
                    try{
                        String txt = "";
                        BufferedReader rdr= new BufferedReader(new
                                InputStreamReader(openFileInput("texte.txt")));
                        while((ligneTXT=rdr.readLine())!=null)
                        {
                            txt= txt + ligneTXT+"\n";
                        }
                        rdr.close();
                        edt.setText(txt);
                    }
                    catch(IOException e)
                    {
                        Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                    } }
            }});

    }

}