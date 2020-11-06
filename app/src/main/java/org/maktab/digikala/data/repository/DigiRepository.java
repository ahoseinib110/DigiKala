package org.maktab.digikala.data.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.google.gson.reflect.TypeToken;

import org.maktab.digikala.data.model.Product;
import org.maktab.digikala.data.remote.NetworkParams;
import org.maktab.digikala.data.remote.retrofit.GetProductDeserializer;
import org.maktab.digikala.data.remote.retrofit.MaktabService;
import org.maktab.digikala.data.remote.retrofit.RetrofitInstance;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DigiRepository {

    private static final String TAG = "bashir";
    private static DigiRepository sDigiRepository;
    private Context mContext;

    private static MaktabService mMaktabService;
    private static MutableLiveData<List<Product>> mProductLiveData = new MutableLiveData<>();

    private DigiRepository(Context context) {
        mContext = context;
        Type type = new TypeToken<List<Product>>(){}.getType();
        Object typeAdapter = new GetProductDeserializer();

        Retrofit retrofit = RetrofitInstance.getInstance(type, typeAdapter);
        mMaktabService = retrofit.create(MaktabService.class);
    }

    public static DigiRepository getInstance(Context context){
        if(sDigiRepository==null){
            sDigiRepository = new DigiRepository(context);
        }
        return sDigiRepository;
    }

    public static MutableLiveData<List<Product>> getProductsLiveData() {
        return mProductLiveData;
    }

    public static void fetchProductsLiveDataApi() {
        Log.d("taggg","fetch");
        Call<List<Product>> call =mMaktabService.listItems(NetworkParams.BASE_OPTIONS);
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call,Response<List<Product>> response) {
                mProductLiveData.setValue(response.body());
                Log.d("taggg","result of callback");
                Log.d("taggg","size" + response.body().size());
                for (int i = 0; i <response.body().size() ; i++) {
                    Log.d("taggg","id " + response.body().get(i).getId());
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }
}
