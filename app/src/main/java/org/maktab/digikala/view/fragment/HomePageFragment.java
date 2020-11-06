package org.maktab.digikala.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.maktab.digikala.R;
import org.maktab.digikala.adapter.ProductAdapter;
import org.maktab.digikala.data.model.Product;
import org.maktab.digikala.databinding.FragmentHomePageBinding;
import org.maktab.digikala.viewModel.HomePageViewModel;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomePageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class HomePageFragment extends Fragment {
    FragmentHomePageBinding mBinding;
    HomePageViewModel mHomePageViewModel;
    RecyclerView mRecyclerView;
    ProductAdapter mAdapter;

    public HomePageFragment() {
        // Required empty public constructor
    }


    public static HomePageFragment newInstance() {
        HomePageFragment fragment = new HomePageFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
        mHomePageViewModel = new ViewModelProvider(this).get(HomePageViewModel.class);
        mHomePageViewModel.fetchProductsLiveDataApi();
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
        mBinding.recyclerViewLatest.setLayoutManager(new GridLayoutManager(getActivity(),2));
    }

    private void registerObservers() {
        //this observer is declared in main thread
        mHomePageViewModel.getProductsLiveData().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> items) {
                setupAdapter(items);
            }
        });
    }

    private void setupAdapter(List<Product> items) {
        mAdapter = new ProductAdapter(mHomePageViewModel);
        mBinding.recyclerViewLatest.setAdapter(mAdapter);
    }
}