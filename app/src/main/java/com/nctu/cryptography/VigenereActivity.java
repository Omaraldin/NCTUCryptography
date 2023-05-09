package com.nctu.cryptography;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nctu.cryptography.crypto.Vigenere;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class VigenereActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vigenere);

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

            if (!MainActivity.words.contains(key.getText().toString().toLowerCase())) {
                MainActivity.words.add(key.getText().toString().toLowerCase());
            }

            String[] kr = text.getText().toString().split(" ");
            for (String k : kr) {
                if (!MainActivity.words.contains(k.toLowerCase())) {
                    MainActivity.words.add(k.toLowerCase());
                }
            }
            String enc = Vigenere.encrypt(text.getText().toString(), key.getText().toString());
            result.setText(enc);
            ((ClipboardManager) getSystemService(getBaseContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("encrypt", enc));
            Toast.makeText(this, "Copied to clipboard.", Toast.LENGTH_SHORT).show();
        });

        decrypt.setOnClickListener(v -> {
            if (text.getText().toString().isEmpty()) {
                Toast.makeText(this, "Text cannot be empty.", Toast.LENGTH_SHORT).show();
                return;
            }
            if (key.getText().toString().isEmpty()) {
                List<Map<String, String>> best = new ArrayList<>();

                for (String word : MainActivity.words) {
                    String dec = Vigenere.decrypt(text.getText().toString(), word);
                    int i = 0;
                    String[] p = dec.split(" ");
                    for (String kk : p) {
                        if (MainActivity.words.contains(kk)) {
                            i++;
                        }
                    }
                    Map<String, String> map = new HashMap<>();
                    map.put("key", word);
                    map.put("freq", "" + i);
                    best.add(map);
                }

                best.sort(Comparator.comparingInt(o -> Integer.parseInt(Objects.requireNonNull(o.get("freq")))));

                String dec = Vigenere.decrypt(text.getText().toString(), best.get(best.size() - 1).get("key"));
                result.setText(dec);
                key.setText(best.get(best.size() - 1).get("key"));
                ((ClipboardManager) getSystemService(getBaseContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("decrypt", dec));
                Toast.makeText(this, "Copied to clipboard.", Toast.LENGTH_SHORT).show();

                result.setText(Vigenere.decrypt(text.getText().toString(), best.get(best.size() - 1).get("key")));
                return;

            }
            String dec = Vigenere.decrypt(text.getText().toString(), key.getText().toString());
            result.setText(dec);
            ((ClipboardManager) getSystemService(getBaseContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("decrypt", dec));
            Toast.makeText(this, "Copied to clipboard.", Toast.LENGTH_SHORT).show();

        });
    }
}