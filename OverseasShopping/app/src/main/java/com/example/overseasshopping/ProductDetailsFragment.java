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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import static com.example.overseasshopping.MainActivity.EXTRA_USER_NO;


public class ProductDetailsFragment extends Fragment {

    private TextView mProductName;
    private TextView mProductPrice;
    private TextView mProductDesc;
    private TextView mProductQuantity;
    private TextView mProductOwner;
    private SpannableString ss;
    private Button mPlaceOrderButton;
    private DatabaseHelper db;

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
        int mDataFromActivity = getArguments().getInt("PID",1);

        String productName = db.getProductName(mDataFromActivity);
        double productPrice = Double.valueOf(db.getProductPrice(mDataFromActivity));
        String productDesc = db.getProductDesc(mDataFromActivity);
        int productQuantity = Integer.valueOf(db.getProductQuantity(mDataFromActivity));
        int productOwner = Integer.valueOf(db.getProductOwner(mDataFromActivity));
        String productOwnerUsername = db.getUsername(productOwner);


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

        mPlaceOrderButton = (Button) v.findViewById(R.id.place_order_button);

        return v;
    }

}