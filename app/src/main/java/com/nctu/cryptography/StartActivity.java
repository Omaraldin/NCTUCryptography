package com.nctu.cryptography;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Objects.requireNonNull(getSupportActionBar()).hide();

        TextView home = findViewById(R.id.home);
        Button caesar = findViewById(R.id.caesar);
        Button vigenere = findViewById(R.id.vigenere);
        Button substitution = findViewById(R.id.substitution);

        home.setOnClickListener(v -> {
            finish();
        });

        caesar.setOnClickListener(v -> {
            startActivity(new Intent(this, CaesarActivity.class));
        });

        vigenere.setOnClickListener(v -> {
            startActivity(new Intent(this, VigenereActivity.class));
        });

        substitution.setOnClickListener(v -> {
            startActivity(new Intent(this, SubstitutionActivity.class));
        });
    }
}