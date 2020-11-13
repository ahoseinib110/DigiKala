package org.maktab.digikala.viewModel;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.maktab.digikala.Constants;
import org.maktab.digikala.data.model.Orderby;
import org.maktab.digikala.data.model.Product;
import org.maktab.digikala.data.repository.DigiRepository;
import org.maktab.digikala.view.activity.ProductActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HomePageViewModel extends AndroidViewModel {

    DigiRepository mDigiRepository;
    private LiveData<List<Product>> mLatestProductsLiveData;
    private LiveData<List<Product>> mMostRatedProductsLiveData;
    private LiveData<List<Product>> mMostViewedProductsLiveData;
    private MutableLiveData<Product> mSelectedProduct;

    public HomePageViewModel(@NonNull Application application) {
        super(application);
        mDigiRepository = DigiRepository.getInstance(getApplication());
        mLatestProductsLiveData = DigiRepository.getProductsLiveData(Orderby.DATE);
        mMostRatedProductsLiveData = DigiRepository.getProductsLiveData(Orderby.MOST_RATED);
        mMostViewedProductsLiveData = DigiRepository.getProductsLiveData(Orderby.MOST_VIEWED);
    }

    public void fetchProductsLiveDataApi(Orderby orderby) {
        mDigiRepository.fetchProductsLiveDataApi(orderby);
    }

    public LiveData<List<Product>> getProductsLiveData(Orderby orderby) {
        switch (orderby){
            case DATE:return mLatestProductsLiveData;
            case MOST_RATED:return mMostRatedProductsLiveData;
            case MOST_VIEWED:return mMostViewedProductsLiveData;
            default:return mLatestProductsLiveData;
        }
    }

    public List<Product> getItems(Orderby orderby) {
        List<Product> items= null;
        switch (orderby){
            case DATE:items = mLatestProductsLiveData.getValue();break;
            case MOST_RATED:items = mMostRatedProductsLiveData.getValue();break;
            case MOST_VIEWED:items = mMostViewedProductsLiveData.getValue();break;
        }
        if( items == null)
            return new ArrayList<>();
        else
            return items;
    }

    public LiveData<Product> getSelectedProduct(){
        mSelectedProduct = new MutableLiveData<>();
        return mSelectedProduct;
    }

    public void onItemClicked(Product product){
        mSelectedProduct.setValue(product);
    }
}
