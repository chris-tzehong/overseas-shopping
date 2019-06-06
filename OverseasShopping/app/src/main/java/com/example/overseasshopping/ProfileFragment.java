package com.example.overseasshopping;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.overseasshopping.Model.User;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private TextView mTextViewProfileUsername;
    private TextView mTextViewProfileRating;
    private TextView mTextViewProfileRatedBy;
    private TextView mTextViewProfilePhone;
    private TextView mTextViewProfileAddress;
    private TextView mTextViewProfileCreditCardNo;
    private TextView mTextViewProfileCreditCardExpiryDate;
    private TextView mTextViewProfileCreditCardCcv;
    private DatabaseHelper mDatabaseHelper;
    private String mUsername;
    private User user;


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        // Inflate the layout for this fragment
        mTextViewProfileUsername = (TextView) v.findViewById(R.id.textViewProfileUsername);
        mTextViewProfileRating = (TextView) v.findViewById(R.id.textViewProfileRating);
        mTextViewProfileRatedBy = (TextView) v.findViewById(R.id.textViewProfileRatedBy);
        mTextViewProfilePhone =  (TextView) v.findViewById(R.id.textViewProfilePhone);
        mTextViewProfileAddress = (TextView) v.findViewById(R.id.textViewProfileAddress);
        mTextViewProfileCreditCardNo = (TextView) v.findViewById(R.id.textViewProfileCreditCardNo);
        mTextViewProfileCreditCardExpiryDate = (TextView) v.findViewById(R.id.textViewProfileCreditCardExpiryDate);
        mTextViewProfileCreditCardCcv = (TextView) v.findViewById(R.id.textViewProfileCreditCardCcv);

        mDatabaseHelper = new DatabaseHelper(getActivity());
        mUsername = (String) getActivity().getIntent().getStringExtra(MainActivity.EXTRA_USERNAME);
        Log.d("Trial", mUsername);
        user = mDatabaseHelper.getUser(mUsername);


        mTextViewProfileUsername.setText(mUsername);
        mTextViewProfileRating.setText(Integer.toString(user.getRating()));
        mTextViewProfileRatedBy.setText(Integer.toString(user.getTotalRatedBy()));
        mTextViewProfilePhone.setText(user.getTelephone());
        mTextViewProfileAddress.setText(user.getAddress());
        mTextViewProfileCreditCardNo.setText(Integer.toString(user.getCreditCardNo()));
        mTextViewProfileCreditCardExpiryDate.setText(DateUtils.dateToString(user.getExpiryDate()));
        mTextViewProfileCreditCardCcv.setText(Integer.toString(user.getSecurityNo()));

        Log.d("Date", user.getExpiryDate().toString());

        return v;


    }

}
