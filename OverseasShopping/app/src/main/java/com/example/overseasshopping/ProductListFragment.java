package com.example.overseasshopping;
import com.example.overseasshopping.Model.Product;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;


import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;


import static com.example.overseasshopping.LoginActivity.username;
import static com.example.overseasshopping.MainActivity.EXTRA_USER_NO;


public class ProductListFragment extends Fragment implements SearchView.OnQueryTextListener {

    private RecyclerView mProductRecyclerView;
    private ProductAdapter mAdapter;
    private DatabaseHelper mDatabaseHelper;

    private Product mProduct;
    private List<Product> mPro;


    private class ProductHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mPID;
        private TextView mTitleTextView;
        private TextView mPrice;
        private ImageView mProductImage;
        private EditText mSearch;


        public ProductHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.list_item_product,parent, false));

            mPID = (TextView) itemView.findViewById(R.id.product_ID);

            itemView.setOnClickListener(this);

            mTitleTextView = (TextView) itemView.findViewById(R.id.product_title);
            mPrice = (TextView) itemView.findViewById(R.id.product_price);
            mProductImage = (ImageView) itemView.findViewById(R.id.product_image);
            mSearch = (EditText) itemView.findViewById(R.id.search);


        }

        public void bind(Product product){
            DecimalFormat formater = new DecimalFormat("0.00");
            mProduct = product;
            mPID.setText(String.valueOf(mProduct.getProductNo()));
            mTitleTextView.setText(mProduct.getProductName());
            mPrice.setText("RM " + formater.format(mProduct.getPrice()));

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

            mSearch.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    filter(s.toString());
                }
                private void filter(String text){
                    List<Product> filteredList = new ArrayList<>();

                        if(mProduct.getProductName().toLowerCase().contains(text.toLowerCase())){
                            filteredList.add(mProduct);
                        }

                    mAdapter.filterList(filteredList);
                }
            });

//            ProductLab.get(getActivity()).setupImageLoader();
////            ImageLoader imageLoader = ImageLoader.getInstance();
////
////            DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true)
////                    .cacheOnDisc(true).resetViewBeforeLoading(true)
////                    .showImageForEmptyUri(null)
////                    .showImageOnFail(null)
////                    .showImageOnLoading(null).build();
////            //download and display image from url
////            imageLoader.displayImage(mProduct.getPhoto(), mProductImage,options);

            Bitmap bitmap = PictureUtils.getScaledBitmap(mProduct.getPhoto(), getActivity());
            mProductImage.setImageBitmap(bitmap);

        }

        @Override
        public void onClick(View view) {
            //Toast.makeText(getActivity(), mProduct.getProductName() + " :" + mProduct.getUserNo(), Toast.LENGTH_SHORT).show();
            Integer userNo = getActivity().getIntent().getIntExtra(EXTRA_USER_NO,1);
            int productID = Integer.parseInt(mPID.getText().toString());
//            Intent intent = new Intent(getActivity(), ProductDetailsActivity.class);
//            intent.putExtra("PID", productID);
//            startActivity(intent);
            ProductDetailsFragment fragment = new ProductDetailsFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(EXTRA_USER_NO, userNo);
            bundle.putInt("PID", productID);
            fragment.setArguments(bundle);

            FragmentManager fm = getFragmentManager();
            fm.beginTransaction().replace(R.id.main_container,fragment).commit();
        }

}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_product_list, menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
       searchView.setOnQueryTextListener(this);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_product:
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.main_container, new ProductFragment()).commit();
                return true;
                default:

                    return super.onOptionsItemSelected(item);
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

        public void filterList(List<Product> filteredList) {
            mProducts = filteredList;
            notifyDataSetChanged();
        }

        public void updateList(List<Product> products){
            mProducts = new ArrayList<>();
            mProducts.addAll(products);
            notifyDataSetChanged();
        }



    }
    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        mDatabaseHelper = new DatabaseHelper(getActivity());

        ProductLab productLab = ProductLab.get(getActivity());
        String userInput = newText.toLowerCase();
        List<Product> products = new ArrayList<>();
        List<Product> productList = productLab.getProducts();
        List<String> productName = new ArrayList<String>();
        for (int i = 0; i < productList.size(); i++) {
            Product p = productList.get(i);
            productName.add(p.getProductName());
        }



        for(String name: productName){
            if(name.toLowerCase().contains(userInput)){
                Product p = mDatabaseHelper.getProduct(name);
                products.add(p);
            }
        }

        mAdapter.updateList(products);
            return true;
    }

}


