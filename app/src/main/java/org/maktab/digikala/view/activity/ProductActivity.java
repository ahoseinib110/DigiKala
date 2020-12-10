package org.maktab.digikala.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import org.maktab.digikala.R;
import org.maktab.digikala.data.model.Product;
import org.maktab.digikala.view.fragment.ProductFragment;

public class ProductActivity extends AppCompatActivity {

    public static final String EXTRA_PRODUCT = "org.maktab.digikala.view.activity.extraProduct";

    public static Intent getIntent(Context context, Product product){
        Intent intent = new Intent(context,ProductActivity.class);
        intent.putExtra(EXTRA_PRODUCT,product);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        Intent intent = getIntent();
        Product product = (Product) intent.getSerializableExtra(EXTRA_PRODUCT);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.fragmentProductContainer,ProductFragment.newInstance(product))
                .commit();
    }
}