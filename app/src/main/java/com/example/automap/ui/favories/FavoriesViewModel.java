package com.example.automap.ui.favories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FavoriesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public FavoriesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is favories fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}