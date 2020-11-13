package org.maktab.digikala.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.maktab.digikala.R;
import org.maktab.digikala.data.model.Orderby;
import org.maktab.digikala.data.model.Product;
import org.maktab.digikala.databinding.ListItemProductBinding;
import org.maktab.digikala.viewModel.HomePageViewModel;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {

    private static final String TAG = "bashir";
    private HomePageViewModel mViewModel;
    private Orderby mOrderby;

    public ProductAdapter(HomePageViewModel viewModel, Orderby orderby) {
        mViewModel = viewModel;
        mOrderby = orderby;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemProductBinding listItemProductBinding = DataBindingUtil.inflate(LayoutInflater.from(mViewModel.getApplication()),
                R.layout.list_item_product,
                parent,
                false);
        return new ProductHolder(listItemProductBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        holder.bindProduct(position);
    }

    @Override
    public int getItemCount() {
        return mViewModel.getItems(mOrderby).size();
    }


    public class ProductHolder extends RecyclerView.ViewHolder {
        private ListItemProductBinding mListItemProductBinding;
        public ProductHolder(@NonNull ListItemProductBinding listItemProductBinding) {
            super(listItemProductBinding.getRoot());
            mListItemProductBinding = listItemProductBinding;
            mListItemProductBinding.setViewModel(mViewModel);
            mListItemProductBinding.setOrderBy(mOrderby);
        }

        public void bindProduct(int position){
            mListItemProductBinding.setPosition(position);
            Log.d(TAG,"url: " +mViewModel.getItems(mOrderby).get(position).getImagesUrl().get(0));
            Picasso.get()
                    .load(mViewModel.getItems(mOrderby).get(position).getImagesUrl().get(0))
                    .into(mListItemProductBinding.imageView);
            //                    .placeholder(R.mipmap.ic_android)
        }
    }
}

