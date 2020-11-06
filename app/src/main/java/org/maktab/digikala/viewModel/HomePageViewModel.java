package org.maktab.digikala.viewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.maktab.digikala.data.model.Product;
import org.maktab.digikala.data.repository.DigiRepository;

import java.util.ArrayList;
import java.util.List;

public class HomePageViewModel extends AndroidViewModel {
    DigiRepository mDigiRepository;
    private LiveData<List<Product>> mProductsLiveData;

    public HomePageViewModel(@NonNull Application application) {
        super(application);
        mDigiRepository = DigiRepository.getInstance(getApplication());
        mProductsLiveData = DigiRepository.getProductsLiveData();
    }

    public void fetchProductsLiveDataApi() {
        mDigiRepository.fetchProductsLiveDataApi();
    }

    public LiveData<List<Product>> getProductsLiveData() {
        return mProductsLiveData;
    }

    public List<Product> getItems() {
        List<Product> items;
        items = mProductsLiveData.getValue();
        if( items == null)
            return new ArrayList<>();
        else
            return items;
    }

}
