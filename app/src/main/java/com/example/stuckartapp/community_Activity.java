package com.example.stuckartapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.net.HttpCookie;

public class community_Activity extends AppCompatActivity {
    Button Telegram1;
    Button Telegram2;
    Button Telegram3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);


        Telegram1 = findViewById(R.id.Telegram1);
        Telegram1 = findViewById(R.id.Telegram2);
        Telegram1 = findViewById(R.id.Telegram3);

        Telegram1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://t.me/+jm_1ckpB8Lg1MDc1");

            }
        });
        Telegram2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                gotoUrl("https://t.me/+NcRzdA05TpowNDll");
            }
        });

        Telegram3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://t.me/+TAizTdEmTxM5NjI1");
            }
        });

    }

    private void gotoUrl(String s){
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));

    }
}