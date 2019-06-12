package com.example.overseasshopping;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class ProductDetailsFragment extends Fragment {

    private TextView mProductName;
    private TextView mProductPrice;
    private TextView mProductDesc;
    private TextView mProductQuantity;
    private TextView mProductOwner;
    private Button mPlaceOrderButton;
    private DatabaseHelper db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_product_details, container, false);

        ProductDetailsActivity activity = (ProductDetailsActivity) getActivity();
        db = new DatabaseHelper(getActivity());

        int mDataFromActivity = activity.productID();

        String productName = db.getProductName(mDataFromActivity);
        double productPrice = Double.valueOf(db.getProductPrice(mDataFromActivity));
        String productDesc = db.getProductDesc(mDataFromActivity);
        int productQuantity = Integer.valueOf(db.getProductQuantity(mDataFromActivity));


        mProductName = (TextView) v.findViewById(R.id.display_product_name);
        mProductName.setText(productName);

        mProductPrice = (TextView) v.findViewById(R.id.display_product_price);
        mProductPrice.setText(String.valueOf(productPrice));

        mProductDesc = (TextView) v.findViewById(R.id.display_product_desc);
        mProductDesc.setText(productDesc);

        mProductQuantity = (TextView) v.findViewById(R.id.display_product_quantity);
        mProductQuantity.setText(String.valueOf(productQuantity));

        mProductOwner = (TextView) v.findViewById(R.id.display_product_owner);

        mPlaceOrderButton = (Button) v.findViewById(R.id.place_order_button);

        return v;
    }

}