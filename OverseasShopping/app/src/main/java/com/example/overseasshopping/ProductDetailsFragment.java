package com.example.overseasshopping;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.overseasshopping.Model.Order;
import com.example.overseasshopping.Model.Product;
import static com.example.overseasshopping.MainActivity.EXTRA_USER_NO;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class ProductDetailsFragment extends Fragment {

    private TextView mProductName;
    private TextView mProductPrice;
    private TextView mProductDesc;
    private TextView mProductQuantity;
    private TextView mProductOwner;
    private SpannableString ss;
    private Button mPlaceOrderButton;
    private DatabaseHelper db;
    private EditText mPurchaseQuantity;
    private Drawable mWarningIcon;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_product_details, container, false);

        //ProductDetailsActivity activity = (ProductDetailsActivity) getActivity();
        db = new DatabaseHelper(getActivity());

        //int mDataFromActivity = activity.productID();
        final int mDataFromActivity = getArguments().getInt("PID",1);

        mWarningIcon = (Drawable) getResources().getDrawable(R.drawable.ic_alert_red_icon);
        mWarningIcon.setBounds(0,0, mWarningIcon.getIntrinsicWidth(), mWarningIcon.getIntrinsicHeight());

        //final int mUserNo = (Integer) getActivity().getIntent().getIntExtra(MainActivity.EXTRA_USER_NO, -1);
        final String mUsername = (String) getActivity().getIntent().getStringExtra(MainActivity.EXTRA_USERNAME);
        //Log.d("myApp", Integer.toString(mUserNo));
        //Log.d("myApp", mUsername);
        final String productName = db.getProductName(mDataFromActivity);
        final double productPrice = Double.valueOf(db.getProductPrice(mDataFromActivity));
        String productDesc = db.getProductDesc(mDataFromActivity);
        final int productQuantity = Integer.valueOf(db.getProductQuantity(mDataFromActivity));
        final int productOwner = Integer.valueOf(db.getProductOwner(mDataFromActivity));
        final String productOwnerUsername = db.getUsername(productOwner);
        //final String BuyerUsername = db.getUsername(mUserNo);

        mProductName = (TextView) v.findViewById(R.id.display_product_name);
        mProductName.setText(productName);

        mProductPrice = (TextView) v.findViewById(R.id.display_product_price);
        mProductPrice.setText(String.valueOf(productPrice));

        mProductDesc = (TextView) v.findViewById(R.id.display_product_desc);
        mProductDesc.setText(productDesc);

        mProductQuantity = (TextView) v.findViewById(R.id.display_product_quantity);
        mProductQuantity.setText(String.valueOf(productQuantity));

        mProductOwner = (TextView) v.findViewById(R.id.display_product_owner);
        ss = new SpannableString(productOwnerUsername);
        ForegroundColorSpan fcs = new ForegroundColorSpan(Color.BLUE);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {

                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.main_container, ProfileFragment.showOtherProfile(productOwnerUsername)).commit();
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
            }
        };
        ss.setSpan(clickableSpan,0, ss.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        ss.setSpan(fcs, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        mProductOwner.setMovementMethod(LinkMovementMethod.getInstance());
        mProductOwner.setHighlightColor(Color.TRANSPARENT);
        mProductOwner.setText(ss);

        mPurchaseQuantity = (EditText) v.findViewById(R.id.purchase_quantity);
        mPurchaseQuantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Intentionally left blank
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (mPurchaseQuantity.getText().toString().isEmpty()) {
                    mPurchaseQuantity.setError(getResources().getString(R.string.order_error_empty_purchasequantity), mWarningIcon);
                } else if (Integer.valueOf(mPurchaseQuantity.getText().toString()).equals(0)) {
                    mPurchaseQuantity.setError(getResources().getString(R.string.order_error_invalid_purchasequantity), mWarningIcon);
                } else if (Integer.valueOf(mPurchaseQuantity.getText().toString()) > productQuantity) {
                    mPurchaseQuantity.setError(getResources().getString(R.string.order_error_invalid_purchasequantity), mWarningIcon);
                }
            }
        });

        mPlaceOrderButton = (Button) v.findViewById(R.id.place_order_button);
        if (productQuantity == 0){
            mPlaceOrderButton.setEnabled(false);
        } else if (productOwnerUsername.equals(mUsername)){
            mPlaceOrderButton.setEnabled(true);
            mPlaceOrderButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder alertDialogBuilder_5 = new AlertDialog.Builder(getActivity());
                    alertDialogBuilder_5.setMessage(R.string.purchase_own_product);
                    alertDialogBuilder_5.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    AlertDialog alertDialog_5 = alertDialogBuilder_5.create();
                    alertDialog_5.show();
                }
            });
        } else {
            mPlaceOrderButton.setEnabled(true);
            mPlaceOrderButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    db = new DatabaseHelper(getActivity());
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd 'at' HH:mm:ss");
                    sdf.setTimeZone(TimeZone.getTimeZone("Asia/Kuala_Lumpur"));
                    String currentDateAndTime = sdf.format(new Date());

                    double mTotalPrice = Integer.valueOf(mPurchaseQuantity.getText().toString()) * productPrice;

                    //Order order = new Order(productOwnerUsername, mUserName, currentDateAndTime, mDataFromActivity, Integer.parseInt(mPurchaseQuantity.getText().toString()), mTotalPrice, productName);
                    Order order = new Order();

                    order.setSeller(productOwnerUsername);
                    order.setBuyer(mUsername);
                    order.setTime(currentDateAndTime);
                    order.setProductNo(mDataFromActivity);
                    order.setProductName(productName);
                    order.setPurchaseQuantity(Integer.parseInt(mPurchaseQuantity.getText().toString()));
                    order.setTotalPrice(mTotalPrice);

                    db.addOrder(order);

                    FragmentManager fm = getFragmentManager();
                    fm.beginTransaction().replace(R.id.main_container, new ProductListFragment()).commit();
                }
            });
        }

        return v;
    }

}