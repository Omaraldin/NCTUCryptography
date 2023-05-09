package com.nctu.cryptography;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FrequencyAnalysisAdapter extends RecyclerView.Adapter<FrequencyAnalysisAdapter.ViewHolder> {
    private List<FrequencyItem> items;

    private TextView message;

    public FrequencyAnalysisAdapter(List<FrequencyItem> items, TextView message) {
        super();
        this.items = items;
        this.message = message;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.frequency_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FrequencyItem item = items.get(position);
        item.replacement = holder.replacement;
        holder.word.setText(item.getWord());
        holder.count.setText("" + item.getCount());
    }

    @Override
    public int getItemCount() {
        return getItems().size();
    }

    public List<FrequencyItem> getItems() {
        return items;
    }

    public void setItems(List<FrequencyItem> items) {
        this.items = items;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView word;
        public EditText replacement;
        public TextView count;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            word = itemView.findViewById(R.id.word);
            replacement = itemView.findViewById(R.id.replacement);
            count = itemView.findViewById(R.id.rate);
        }

    }
}
