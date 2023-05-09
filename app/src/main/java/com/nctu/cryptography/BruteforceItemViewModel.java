package com.nctu.cryptography;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class BruteforceItemViewModel extends ViewModel {
    private MutableLiveData<List<BruteforceResultItem>> items = new MutableLiveData<>();


    public LiveData<List<BruteforceResultItem>> getItems() {
        return items;
    }

    public void setItems(List<BruteforceResultItem> items) {
        this.items.setValue(items);
    }
}
