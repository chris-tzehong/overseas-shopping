package com.example.overseasshopping;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.overseasshopping.Model.Product;
import com.example.overseasshopping.Model.User;

import java.io.File;
import java.text.DecimalFormat;
import java.util.List;

import static com.example.overseasshopping.MainActivity.EXTRA_USER_NO;

public class ProductFragment extends Fragment {
    private static final int REQUEST_PHOTO = 0;

    private Product mProduct;
    private File mPhotoFile;
    private EditText mProductName;
    private EditText mProductPrice;
    private EditText mProductQuantity;
    private EditText mProductDescription;
    private Button mPostProductButton;
    private DatabaseHelper db;
    private ImageButton mPhotoButton;
    private ImageView mPhotoView;
    private Drawable mWarningIcon;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProduct = new Product();
        mPhotoFile = ProductLab.get(getActivity()).getPhotoFile(mProduct);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_product, container, false);

        final int mUserNo = (Integer) getActivity().getIntent().getIntExtra(MainActivity.EXTRA_USER_NO,0);
        mWarningIcon = (Drawable) getResources().getDrawable(R.drawable.ic_alert_red_icon);
        mWarningIcon.setBounds(0,0, mWarningIcon.getIntrinsicWidth(), mWarningIcon.getIntrinsicHeight());

        mPhotoButton = (ImageButton) v.findViewById(R.id.product_camera);
        final Intent captureImage = new Intent (MediaStore.ACTION_IMAGE_CAPTURE);

        mPhotoButton.setEnabled(true);

        mPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Uri uri = FileProvider.getUriForFile(getActivity(), "com.example.overseasshopping.fileprovider", mPhotoFile);
                captureImage.putExtra(MediaStore.EXTRA_OUTPUT, uri);

                List<ResolveInfo> cameraActivities = getActivity().getPackageManager().queryIntentActivities(captureImage, PackageManager.MATCH_DEFAULT_ONLY);

                for(ResolveInfo activity : cameraActivities) {
                    getActivity().grantUriPermission(activity.activityInfo.packageName, uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                }

                startActivityForResult(captureImage, REQUEST_PHOTO);
            }
        });


        mPhotoView = (ImageView) v.findViewById(R.id.product_photo);
        updatePhotoView();


        mProductName = (EditText) v.findViewById(R.id.product_name);
        mProductName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               //mProduct.setProductName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (mProductName.getText().toString().isEmpty()) {
                    mProductName.setError(getResources().getString(R.string.product_error_empty_productname), mWarningIcon);
                }
            }
        });

        mProductPrice = (EditText) v.findViewById(R.id.product_price);
        final DecimalFormat formater = new DecimalFormat("0.00");
        mProductPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //mProduct.setPrice(Double.parseDouble(s.toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (mProductPrice.getText().toString().isEmpty()) {
                    mProductPrice.setError(getResources().getString(R.string.product_error_empty_productprice), mWarningIcon);
                } else if (formater.format(Double.valueOf(mProductPrice.getText().toString())).equals(formater.format(0))) {
                    mProductPrice.setError(getResources().getString(R.string.product_error_invalid_productprice), mWarningIcon);
                }
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
                //mProduct.setProductQuantity(Integer.parseInt(s.toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (mProductQuantity.getText().toString().isEmpty()) {
                    mProductQuantity.setError(getResources().getString(R.string.product_error_empty_productquantity), mWarningIcon);
                } else if (Integer.valueOf(mProductQuantity.getText().toString()).equals(0)) {
                    mProductQuantity.setError(getResources().getString(R.string.product_error_invalid_productquantity), mWarningIcon);
                }
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
                //mProduct.setDescription(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (mProductDescription.getText().toString().isEmpty()) {
                    mProductDescription.setError(getResources().getString(R.string.product_error_empty_productdesc), mWarningIcon);
                }
            }
        });

        mPostProductButton = (Button) v.findViewById(R.id.post_product_button);
        mPostProductButton.setEnabled(true);
        mPostProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mProductName.getError() != null || mProductPrice.getError() != null || mProductQuantity.getError() != null || mProductDescription.getError() != null) {
                    AlertDialog.Builder alertDialogBuilder0 = new AlertDialog.Builder(getActivity());
                    alertDialogBuilder0.setMessage(R.string.product_error_invalid_field);
                    alertDialogBuilder0.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    AlertDialog alertDialog0 = alertDialogBuilder0.create();
                    alertDialog0.show();
                } else if (mProductName.getText().length() == 0 || mProductPrice.getText().length() == 0 || mProductQuantity.getText().length() == 0 || mProductDescription.getText().length() == 0) {
                    AlertDialog.Builder alertDialogBuilder9 = new AlertDialog.Builder(getActivity());
                    alertDialogBuilder9.setMessage(R.string.product_error_empty_field);
                    alertDialogBuilder9.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    AlertDialog alertDialog9 = alertDialogBuilder9.create();
                    alertDialog9.show();
                } else {
                    db = new DatabaseHelper(getActivity());
                    //db.addUser(user);
                    Product product = new Product();
                    //Product product = new Product(mProductName.getText().toString(), "Empty", mUserNo, mProductDescription.getText().toString(), Double.parseDouble(mProductPrice.getText().toString()), Integer.parseInt(mProductQuantity.getText().toString()));
                    product.setProductName(mProductName.getText().toString());
                    product.setPhoto("Empty");
                    product.setUserNo(mUserNo);
                    product.setDescription(mProductDescription.getText().toString());
                    product.setPrice(Double.parseDouble(formater.format(Double.parseDouble(mProductPrice.getText().toString()))));
                    product.setProductQuantity(Integer.parseInt(mProductQuantity.getText().toString()));
                    db.addProduct(product, product.getUserNo());
                    AlertDialog.Builder alertDialogBuilder10 = new AlertDialog.Builder(getActivity());
                    alertDialogBuilder10.setMessage(R.string.product_added_successfully);
                    alertDialogBuilder10.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    AlertDialog alertDialog10 = alertDialogBuilder10.create();
                    alertDialog10.show();

                    FragmentManager fm = getFragmentManager();
                    fm.beginTransaction().replace(R.id.main_container, new ProductListFragment()).commit();
                }
            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if(requestCode == REQUEST_PHOTO) {
            Uri uri = FileProvider.getUriForFile(getActivity(), "com.example.overseasshopping.fileprovider", mPhotoFile);

            getActivity().revokeUriPermission(uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

            updatePhotoView();
        }
    }

    private void updatePhotoView() {
        if (mPhotoFile == null || !mPhotoFile.exists()) {
            mPhotoView.setImageDrawable(null);
        } else {
            Bitmap bitmap = PictureUtils.getScaledBitmap(mPhotoFile.getPath(), getActivity());
            mPhotoView.setImageBitmap(bitmap);
        }
    }
}
