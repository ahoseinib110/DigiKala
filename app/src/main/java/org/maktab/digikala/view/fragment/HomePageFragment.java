package org.maktab.digikala.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.maktab.digikala.Constants;
import org.maktab.digikala.R;
import org.maktab.digikala.adapter.ProductAdapter;
import org.maktab.digikala.data.model.Orderby;
import org.maktab.digikala.data.model.Product;
import org.maktab.digikala.databinding.FragmentHomePageBinding;
import org.maktab.digikala.view.activity.ProductActivity;
import org.maktab.digikala.viewModel.HomePageViewModel;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomePageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class HomePageFragment extends Fragment {
    public static final String ARG_ORDERBY = "ArgOrderby";
    private FragmentHomePageBinding mBinding;
    private HomePageViewModel mHomePageViewModel;
    private ProductAdapter mAdapter;
    private Orderby mOrderby;

    public HomePageFragment() {
        // Required empty public constructor
    }


    public static HomePageFragment newInstance(Orderby orderby) {
        HomePageFragment fragment = new HomePageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ORDERBY,orderby.ordinal());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mOrderby= Orderby.values()[getArguments().getInt(ARG_ORDERBY)];
        }
        mHomePageViewModel = new ViewModelProvider(this).get(HomePageViewModel.class);
        mHomePageViewModel.fetchProductsLiveDataApi(mOrderby);
        mHomePageViewModel.getSelectedProduct().observe(this, new Observer<Product>() {
            @Override
            public void onChanged(Product product) {
                Intent intent = ProductActivity.getIntent(getActivity(),product);
                getActivity().startActivity(intent);
            }
        });
        registerObservers();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_home_page, container, false);

        mBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_home_page,
                container,
                false);

        initViews();

        return mBinding.getRoot();
    }


    private void initViews() {
        mBinding.recyclerViewLatest.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,true));
        //GridLayoutManager(getActivity(),2)
    }

    private void registerObservers() {
        //this observer is declared in main thread
        mHomePageViewModel.getProductsLiveData(mOrderby).observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> items) {
                setupAdapter(items);
            }
        });
    }

    private void setupAdapter(List<Product> items) {
        mAdapter = new ProductAdapter(mHomePageViewModel,mOrderby);
        mBinding.recyclerViewLatest.setAdapter(mAdapter);
    }
}