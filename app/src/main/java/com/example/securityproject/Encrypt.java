package com.example.securityproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static java.lang.Integer.parseInt;

public class Encrypt extends AppCompatActivity implements View.OnClickListener {
    private EditText ecText;
    private TextView showEncryptedText;
    private Button submit;
    FirebaseDatabase rootNode;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encrypt);
        ecText =(EditText) findViewById(R.id.ecText);
        submit = (Button) findViewById(R.id.submit);
        showEncryptedText = (TextView)findViewById(R.id.showEncryptedText);
        submit.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("EncryptedMessage");

        String s = ecText.getText().toString();
        try {
            EncryptDecryptMessage encryptMessage = new EncryptDecryptMessage(s);
            String encryptedMsg = encryptMessage.encrypt();
            showEncryptedText.setText(encryptedMsg);
            reference.child("msg").setValue(encryptedMsg);
            Toast.makeText(this, encryptedMsg, Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "Sent To DB", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Encrypt.this,Decrypt.class);
            intent.putExtra("key",ecText.getText().toString());
            startActivity(intent);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
   /* private String getSecretKey(){
        return key.getText().toString();
    }

    */
}
