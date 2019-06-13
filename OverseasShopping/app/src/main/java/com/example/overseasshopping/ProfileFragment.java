package com.example.overseasshopping;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
    private Button mButtonLogoutSend;
    private DatabaseHelper mDatabaseHelper;
    private String mUsername;
    private User user;
    private Integer mCurrentFunction;

    public static String RECEIVED_USERNAME = "com.example.overseasshoping.profilefragment.username";
    public static String RECEIVED_USERNO = "com.example.overseasshoping.profilefragment.userno";
    public static String CURRENT_FUNCTION = "com.example.overseasshoping.profilefragment.currentfunction";


    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment showOwnProfile(String username) {
        Bundle args = new Bundle();
        args.putString(RECEIVED_USERNAME, username);
        args.putInt(CURRENT_FUNCTION, 0);

        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static ProfileFragment showOtherProfile(String username) {
        Bundle args = new Bundle();
        args.putString(RECEIVED_USERNAME, username);
        args.putInt(CURRENT_FUNCTION, 1);

        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);
        return fragment;
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
        mButtonLogoutSend = (Button) v.findViewById(R.id.buttonLogoutSend);

        mCurrentFunction = getArguments().getInt(CURRENT_FUNCTION);
        if (mCurrentFunction == 0) {
            mButtonLogoutSend.setText(R.string.profile_logout_button);
            mButtonLogoutSend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
            });
        } else {
            mButtonLogoutSend.setText(R.string.profile_send_message_button);
            mButtonLogoutSend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int mUserNo =  getActivity().getIntent().getIntExtra(MainActivity.EXTRA_USER_NO,1);
                    Intent intent = MessageActivity.newIntent(getActivity().getBaseContext(), mUserNo ,user.getUserNo());
                    startActivity(intent);
                }
            });
        }

        mDatabaseHelper = new DatabaseHelper(getActivity());
        mUsername = getArguments().getString(RECEIVED_USERNAME);
        Log.d("Trial", mUsername);
        user = mDatabaseHelper.getUser(mUsername);


        mTextViewProfileUsername.setText(mUsername);
        mTextViewProfileRating.setText(Integer.toString(user.getRating()));
        mTextViewProfileRatedBy.setText(Integer.toString(user.getTotalRatedBy()));
        mTextViewProfilePhone.setText(user.getTelephone());
        mTextViewProfileAddress.setText(user.getAddress());
        mTextViewProfileCreditCardNo.setText(user.getCreditCardNo());
        mTextViewProfileCreditCardExpiryDate.setText(user.getExpiryDate());
        mTextViewProfileCreditCardCcv.setText(Integer.toString(user.getSecurityNo()));

        Log.d("Date", user.getExpiryDate());

        return v;


    }

}
