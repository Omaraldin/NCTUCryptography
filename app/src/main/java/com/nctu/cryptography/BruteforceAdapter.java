package com.nctu.cryptography;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BruteforceAdapter extends RecyclerView.Adapter<BruteforceAdapter.ViewHolder> {
    private List<BruteforceResultItem> items;

    public BruteforceAdapter(List<BruteforceResultItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bruteforce_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BruteforceResultItem item = items.get(position);
        holder.message.setText(item.getText());
        holder.key.setText(item.getKey() + "");
    }

    @Override
    public int getItemCount() {
        return items
                .size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView key;
        private TextView message;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            key = itemView.findViewById(R.id.key);
            message = itemView.findViewById(R.id.message);
        }
    }
}
