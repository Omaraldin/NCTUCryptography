package com.nctu.cryptography;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class FrequencyAnalysisViewModel extends ViewModel {
    private MutableLiveData<List<FrequencyItem>> itemList = new MutableLiveData<>();

    public MutableLiveData<List<FrequencyItem>> getItemList() {
        return itemList;
    }

    public void setItemList(List<FrequencyItem> itemList) {
        this.itemList.setValue(itemList);
    }
}
