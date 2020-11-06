package org.maktab.digikala.data.remote.retrofit;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import org.maktab.digikala.data.model.Product;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GetProductDeserializer implements JsonDeserializer<List<Product>> {
    @Override
    public List<Product> deserialize(
            JsonElement json,
            Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {

        JsonArray productsArray = json.getAsJsonArray();

        List<Product> products = new ArrayList<>();

        for (int i = 0; i < productsArray.size(); i++) {
            JsonObject productObject = productsArray.get(i).getAsJsonObject();

            int id = productObject.get("id").getAsInt();
            String name = productObject.get("name").getAsString();
            String price = productObject.get("price").getAsString();

            List<String> ImagesUrl = new ArrayList<>();
            JsonArray imagesArray = productObject.get("images").getAsJsonArray();
            for (int j = 0; j < imagesArray.size(); j++) {
                JsonObject imageObject = imagesArray.get(j).getAsJsonObject();
                ImagesUrl.add(imageObject.get("src").getAsString());
            }
            Product product = new Product(id,name,price,ImagesUrl);
            products.add(product);
        }
        return products;
    }
}
