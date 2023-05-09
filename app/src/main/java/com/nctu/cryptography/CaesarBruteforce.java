package com.nctu.cryptography;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nctu.cryptography.crypto.Caesar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CaesarBruteforce extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caesar_bruteforce);
        Objects.requireNonNull(getSupportActionBar()).hide();


        String text = getIntent().getStringExtra("text");

        Map<Integer, String> texts = Caesar.bruteforce(text);

        List<BruteforceResultItem> items = new ArrayList<>();

        texts.forEach((key, content) -> {
            BruteforceResultItem item = new BruteforceResultItem();
            item.setKey(key);
            item.setText(content);
            items.add(item);
        });

        System.out.println(items);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        BruteforceItemViewModel viewModel = new ViewModelProvider(this).get(BruteforceItemViewModel.class);
        viewModel.setItems(items);

        viewModel.getItems().observe(this, bruteforceResultItems -> {
            BruteforceAdapter adapter = new BruteforceAdapter(bruteforceResultItems);
            recyclerView.setAdapter(adapter);
        });
    }
}