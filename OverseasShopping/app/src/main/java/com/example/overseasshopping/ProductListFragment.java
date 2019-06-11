package com.example.overseasshopping;
import com.example.overseasshopping.Model.Product;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

public class ProductListFragment extends Fragment {
    private RecyclerView mProductRecyclerView;
    private ProductAdapter mAdapter;


    private class ProductHolder extends RecyclerView.ViewHolder {
        private Product mProduct;
        private TextView mTitleTextView;
        private TextView mPrice;
        private ImageView mProductImage;

        public ProductHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.list_item_product,parent, false));
            // itemView.setOnClickListener(this);
            mTitleTextView = (TextView) itemView.findViewById(R.id.product_title);
            mPrice = (TextView) itemView.findViewById(R.id.product_price);
            mProductImage = (ImageView) itemView.findViewById(R.id.product_image);
        }
        public void bind(Product product){
            mProduct = product;

            mTitleTextView.setText(mProduct.getProductName());
            //mPrice.setText(mProduct.getPrice());

            //sets up the image loader library
            ProductLab.get(getActivity()).setupImageLoader();
            ImageLoader imageLoader = ImageLoader.getInstance();

            DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true)
                    .cacheOnDisc(true).resetViewBeforeLoading(true)
                    .showImageForEmptyUri(null)
                    .showImageOnFail(null)
                    .showImageOnLoading(null).build();
            //download and display image from url
            imageLoader.displayImage(mProduct.getPhoto(), mProductImage,options);


        }


//    @Override
//    public void onClick(View view) {
//        Toast.makeText(getActivity(),
//                mProduct.getProductName()+" clicked!" , Toast.LENGTH_SHORT)
//                .show();
//    }
}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_product_list,container,false);

        mProductRecyclerView = (RecyclerView) view.findViewById(R.id.product_recycler_view);
        mProductRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }
    private void updateUI(){
        ProductLab productLab = ProductLab.get(getActivity());
        List<Product> products = productLab.getProducts();
        if (mAdapter ==null){
            mAdapter = new ProductAdapter(products);
            mProductRecyclerView.setAdapter(mAdapter);
        }else{
            mAdapter.setProducts(products);
            mAdapter.notifyDataSetChanged();
        }

    }
    //Adapter
    private class ProductAdapter extends RecyclerView.Adapter<ProductHolder>{
        private List<Product> mProducts;

        public ProductAdapter(List<Product> products){
            mProducts= products;
        }
        @Override
        public ProductHolder onCreateViewHolder(ViewGroup parent,int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new ProductHolder(layoutInflater, parent);
        }
        @Override
        public void onBindViewHolder(ProductHolder holder, int position){
            Product product = mProducts.get(position);
            holder.bind(product);
        }
        @Override
        public int getItemCount(){

            return mProducts.size();
        }

        public void setProducts(List<Product> products){
            mProducts= products;
    }


    }



}


