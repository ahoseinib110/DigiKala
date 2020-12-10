package org.maktab.digikala.data.remote.retrofit;

import org.maktab.digikala.data.model.Product;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface MaktabService {
    @GET(".")
    Call<List<Product>> listProductItems(@QueryMap Map<String, String> options);
}
