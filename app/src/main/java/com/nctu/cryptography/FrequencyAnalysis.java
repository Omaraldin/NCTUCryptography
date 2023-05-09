package com.nctu.cryptography;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nctu.cryptography.crypto.Substitution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class FrequencyAnalysis extends AppCompatActivity {

    private Map<String, List<Integer>> indices = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frequency_analysis);
        Objects.requireNonNull(getSupportActionBar()).hide();

        TextView message = findViewById(R.id.message);
        Button update = findViewById(R.id.update);

        String text = getIntent().getStringExtra("text");
        Map<String, Integer> anagram = Substitution.countNGramFrequencies(text, 1);
        List<Map.Entry<String, Integer>> sortedFrequencies = Substitution.sortFrequencies(anagram);
        List<FrequencyItem> items = new ArrayList<>();

        message.setText(text.toUpperCase());

        sortedFrequencies.forEach(entry -> {
            if (!entry.getKey().equals("_total")) {
                FrequencyItem item = new FrequencyItem();
                item.setWord(entry.getKey());
                item.setCount(entry.getValue());
                items.add(item);
            }
        });

        String msg = message.getText().toString();
        for (int i = 0; i < msg.length(); ++i) {
            if (!indices.containsKey(String.valueOf(msg.charAt(i)))) {
                indices.put(String.valueOf(msg.charAt(i)), new ArrayList<>());
            }

            Objects.requireNonNull(indices.get(String.valueOf(msg.charAt(i)))).add(i);
        }


        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FrequencyAnalysisViewModel viewModel = new ViewModelProvider(this).get(FrequencyAnalysisViewModel.class);
        viewModel.setItemList(items);

        viewModel.getItemList().observe(this, frequencyItems -> {
            FrequencyAnalysisAdapter adapter = new FrequencyAnalysisAdapter(frequencyItems, message);
            recyclerView.setAdapter(adapter);
        });

        update.setOnClickListener(v -> {
            ((FrequencyAnalysisAdapter) recyclerView.getAdapter()).getItems().forEach(frequencyItem -> {
                if (!frequencyItem.replacement.getText().toString().isEmpty()) {
                    if (indices.containsKey(frequencyItem.getWord())) {
                        List<Integer> words = indices.get(frequencyItem.getWord());
                        for (int i = 0; i < words.size(); ++i) {
                            if (msg.)
                        }
                    }
                    message.setText(message.getText().toString().toUpperCase().replaceAll(frequencyItem.getWord(), frequencyItem.replacement.getText().toString().toUpperCase()));
                }
            });
        });
    }
}