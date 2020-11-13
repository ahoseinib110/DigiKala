package org.maktab.digikala.data.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.reflect.TypeToken;

import org.maktab.digikala.Constants;
import org.maktab.digikala.data.model.Orderby;
import org.maktab.digikala.data.model.Product;
import org.maktab.digikala.data.remote.NetworkParams;
import org.maktab.digikala.data.remote.retrofit.GetProductDeserializer;
import org.maktab.digikala.data.remote.retrofit.MaktabService;
import org.maktab.digikala.data.remote.retrofit.RetrofitInstance;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DigiRepository {

    private static final String TAG = "bashir";
    private static DigiRepository sDigiRepository;
    private Context mContext;

    private static MaktabService mMaktabService;

    private static MutableLiveData<List<Product>> mLatestProductsLiveData = new MutableLiveData<>();
    private static MutableLiveData<List<Product>> mMostRatedProductsLiveData = new MutableLiveData<>();
    private static MutableLiveData<List<Product>> mMostViewedProductsLiveData = new MutableLiveData<>();

    private DigiRepository(Context context) {
        mContext = context;
        Type type = new TypeToken<List<Product>>() {
        }.getType();
        Object typeAdapter = new GetProductDeserializer();

        Retrofit retrofit = RetrofitInstance.getInstance(type, typeAdapter);
        mMaktabService = retrofit.create(MaktabService.class);
    }

    public static DigiRepository getInstance(Context context) {
        if (sDigiRepository == null) {
            sDigiRepository = new DigiRepository(context);
        }
        return sDigiRepository;
    }

    public static MutableLiveData<List<Product>> getProductsLiveData(Orderby orderby) {
        switch (orderby) {
            case DATE:
                return mLatestProductsLiveData;
            case MOST_RATED:
                return mMostRatedProductsLiveData;
            case MOST_VIEWED:
                return mMostViewedProductsLiveData;
            default:
                return mLatestProductsLiveData;
        }
    }

    public static void fetchProductsLiveDataApi(Orderby orderby) {
        switch (orderby) {
            case DATE:
                new digiCallback(mLatestProductsLiveData).enqueue(orderby);
                break;
            case MOST_RATED:
                new digiCallback(mMostRatedProductsLiveData).enqueue(orderby);
                break;
            case MOST_VIEWED:
                new digiCallback(mMostViewedProductsLiveData).enqueue(orderby);
                break;
        }
    }

    public static class digiCallback implements Callback<List<Product>> {
        MutableLiveData<List<Product>> mLiveData;

        public digiCallback(MutableLiveData<List<Product>> liveData) {
            mLiveData = liveData;
        }

        public void enqueue(Orderby orderby) {
            Call<List<Product>> call = mMaktabService.listProductItems(NetworkParams.getProductsOptions(orderby));
            call.enqueue(this);
        }

        @Override
        public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
            mLiveData.setValue(response.body());
        }

        @Override
        public void onFailure(Call<List<Product>> call, Throwable t) {

        }
    }
}
