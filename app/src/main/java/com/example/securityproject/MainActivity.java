package com.example.securityproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public Button encryption_button, decryption_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        encryption_button =findViewById(R.id.ec);
        decryption_button =findViewById(R.id.dc);
        encryption_button.setOnClickListener(this);
        decryption_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    switch (v.getId())
    {
        case R.id.ec:
            Intent i = new Intent(MainActivity.this,Encrypt.class);
            startActivity(i);
            break;

        case  R.id.dc:

                Intent j = new Intent(MainActivity.this,Decrypt.class);
                startActivity(j);
                break;
    }
    }
}
