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

import com.nctu.cryptography.crypto.Substitution;

import java.util.Objects;

public class SubstitutionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_substitution);

        Objects.requireNonNull(getSupportActionBar()).hide();

        View back = findViewById(R.id.backButton);
        EditText text = findViewById(R.id.text);
        EditText key = findViewById(R.id.key);
        Button encrypt = findViewById(R.id.encrypt);
        Button decrypt = findViewById(R.id.decrypt);
        Button frequency = findViewById(R.id.frequency_analysis);
        TextView result = findViewById(R.id.result);

        back.setOnClickListener(v -> {
            finish();
        });

        key.setOnClickListener(v -> {
            key.setText(Substitution.generateRandomCiphertext());
        });

        encrypt.setOnClickListener(v -> {
            if (text.getText().toString().isEmpty() || key.getText().toString().isEmpty()) {
                Toast.makeText(this, "Text cannot be empty.", Toast.LENGTH_SHORT).show();
                return;
            }
            String enc = Substitution.encrypt(text.getText().toString(), key.getText().toString());
            result.setText(enc);
            ((ClipboardManager) getSystemService(getBaseContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("encrypt", enc));
            Toast.makeText(this, "Copied to clipboard.", Toast.LENGTH_SHORT).show();

        });

        decrypt.setOnClickListener(v -> {
            if (text.getText().toString().isEmpty() || key.getText().toString().isEmpty()) {
                Toast.makeText(this, "Text cannot be empty.", Toast.LENGTH_SHORT).show();
                return;
            }
            String dec = Substitution.decrypt(text.getText().toString(), key.getText().toString());
            ((ClipboardManager) getSystemService(getBaseContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("decrypt", dec));
            Toast.makeText(this, "Copied to clipboard.", Toast.LENGTH_SHORT).show();

            result.setText(dec);
        });

        frequency.setOnClickListener(v -> {
            Intent intent = new Intent(this, FrequencyAnalysis.class);
            intent.putExtra("text", text.getText().toString());
            startActivity(intent);
        });
    }
}