package com.example.securityproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Decrypt extends AppCompatActivity implements View.OnClickListener {
    private TextView text1,dcMsg,encrypted,decrypted;
    private Button submit;
    FirebaseDatabase rootNode;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decrypt);

        text1=findViewById(R.id.text1);
        dcMsg=findViewById(R.id.dcMsg);
        encrypted=findViewById(R.id.encrypted);
        decrypted=findViewById(R.id.decrypted);
        submit=findViewById(R.id.submit);

        submit.setOnClickListener(this);
        text1.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = getIntent();
        String SentMSG = intent.getStringExtra("key");

        try {
            ReadData(SentMSG);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void ReadData(String msg) throws Exception {
        final EncryptDecryptMessage decryptMessage = new EncryptDecryptMessage(msg);
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("EncryptedMessage");
        reference.child("msg").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String data = snapshot.getValue(String.class);
                try {
                    dcMsg.setText(decryptMessage.decrypt(data));
                    text1.setText(data);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
