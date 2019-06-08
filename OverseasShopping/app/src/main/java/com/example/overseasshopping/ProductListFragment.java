package com.example.overseasshopping;
import com.example.overseasshopping.Model.Product;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ProductListFragment extends Fragment {
    private RecyclerView mProductRecyclerView;
    private ProductAdapter mAdapter;


    private class ProductHolder extends RecyclerView.ViewHolder {
        private Product mProduct;
        private TextView mTitleTextView;
        private TextView mPrice;

        public ProductHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.list_item_product,parent, false));
            // itemView.setOnClickListener(this);
            mTitleTextView = (TextView) itemView.findViewById(R.id.product_title);
            mPrice = (TextView) itemView.findViewById(R.id.price);
        }
        public void bind(Product product){
            mProduct = product;
            mTitleTextView.setText(mProduct.getProductName());
            //mPrice.setText(mProduct.getPrice());
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
