package com.example.securityproject;

import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class EncryptDecryptMessage {
    String msg,password;
    SecretKeySpec key;

    public EncryptDecryptMessage(String Data) throws Exception {
        this.msg = Data;
        this.password = Data;
        key = generateKey(this.msg);

    }

    public String encrypt() throws Exception {
        Cipher c = Cipher.getInstance("AES");
        c.init(Cipher.ENCRYPT_MODE,key);
        byte[] encVal = c.doFinal(this.msg.getBytes());
        String encryptedMsg = Base64.encodeToString(encVal,Base64.DEFAULT);
        return encryptedMsg;
    }

    public String decrypt(String outputString) throws Exception{
            Cipher c = Cipher.getInstance("AES");
            c.init(Cipher.DECRYPT_MODE,key);
            byte[] decodedValue = Base64.decode(outputString,Base64.DEFAULT);
            byte[] decValue = c.doFinal(decodedValue);
            String decryptedValue = new String(decValue);
            return decryptedValue;


    }
    private SecretKeySpec generateKey(String pass) throws Exception {
        final MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = pass.getBytes("UTF-8");
        digest.update(bytes,0,bytes.length);
        byte[] key = digest.digest();
        SecretKeySpec secretKeySpec = new SecretKeySpec(key,"AES");
        return secretKeySpec;
    }
}
