package com.nctu.cryptography;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nctu.cryptography.crypto.Caesar;

import java.util.Objects;

public class CaesarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caesar);

        Objects.requireNonNull(getSupportActionBar()).hide();

        View back = findViewById(R.id.backButton);
        EditText text = findViewById(R.id.text);
        EditText key = findViewById(R.id.key);
        Button encrypt = findViewById(R.id.encrypt);
        Button decrypt = findViewById(R.id.decrypt);
        TextView result = findViewById(R.id.result);

        back.setOnClickListener(v -> {
            finish();
        });

        encrypt.setOnClickListener(v -> {
            if (text.getText().toString().isEmpty() || key.getText().toString().isEmpty()) {
                Toast.makeText(this, "Text cannot be empty.", Toast.LENGTH_SHORT).show();
                return;
            }
            String enc = Caesar.encrypt(text.getText().toString(), Integer.parseInt(key.getText().toString()));
            result.setText(enc);

            ((ClipboardManager) getSystemService(getBaseContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("encrypt", enc));
            Toast.makeText(this, "Copied to clipboard.", Toast.LENGTH_SHORT).show();
        });

        decrypt.setOnClickListener(v -> {
            if (key.getText().toString().isEmpty()) {
                Intent intent = new Intent(this, CaesarBruteforce.class);
                intent.putExtra("text", text.getText().toString());
                startActivity(intent);
                return;
            }
            String dec = Caesar.decrypt(text.getText().toString(), Integer.parseInt(key.getText().toString()));
            result.setText(dec);


            ((ClipboardManager) getSystemService(getBaseContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("decrypt", dec));
            Toast.makeText(this, "Copied to clipboard.", Toast.LENGTH_SHORT).show();
        });
    }
}