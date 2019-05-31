package com.example.overseasshopping;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.overseasshopping.Model.Product;

public class ProductFragment extends Fragment {
    private Product mProduct;
    private EditText mProductName;
    private EditText mProductPrice;
    private EditText mProductQuantity;
    private EditText mProductDescription;
    private Button mPostProductButton;

    DatabaseHelper mDatabaseHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void AddProduct(Product p) {

         mDatabaseHelper.addProduct(p);

//        if (insertData) {
//            toastMessage("Product Added Successfully.");
//        } else {
//            toastMessage("Something went wrong");
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_product, container, false);

        mProduct = new Product();
        mDatabaseHelper = new DatabaseHelper(getActivity());
        Log.w("Hello", "KMS");

        mProductName = (EditText) v.findViewById(R.id.product_name);
        mProductName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mProduct.setProductName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                //Intentionally left blank
            }
        });

        mProductPrice = (EditText) v.findViewById(R.id.product_price);
        mProductPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mProduct.setPrice(Integer.parseInt(s.toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {
                //Intentionally left blank
            }
        });

        mProductQuantity = (EditText) v.findViewById(R.id.product_quantity);
        mProductQuantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mProduct.setProductQuantity(Integer.parseInt(s.toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {
                //Intentionally left blank
            }
        });

        mProductDescription = (EditText) v.findViewById(R.id.product_description);
        mProductDescription.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mProduct.setDescription(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                //Intentionally left blank
            }
        });

        mPostProductButton = (Button) v.findViewById(R.id.post_product_button);
        mPostProductButton.setEnabled(true);

        mPostProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w("Product Status", mProduct.getProductName() + String.valueOf(mProduct.getProductQuantity()));
                AddProduct(mProduct);
            }
        });

        return v;
    }

    private void toastMessage(String message){
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

}
