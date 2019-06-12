package com.example.overseasshopping;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.FileProvider;
import android.text.Editable;
import android.text.TextWatcher;
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
import java.util.List;

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProduct = new Product();
        mPhotoFile = ProductLab.get(getActivity()).getPhotoFile(mProduct);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_product, container, false);

        mPhotoButton = (ImageButton) v.findViewById(R.id.product_camera);
        final Intent captureImage =new Intent (MediaStore.ACTION_IMAGE_CAPTURE);

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
                db = new DatabaseHelper(getActivity());
                User user = new User("ki", "ki", "la", "la", 23, 23, null);
                //db.addUser(user);
                Product product = new Product(mProductName.getText().toString(), "Empty", 1, mProductDescription.getText().toString(), Integer.parseInt(mProductPrice.getText().toString()), Integer.parseInt(mProductQuantity.getText().toString()));
                db.addProduct(product, user);
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.main_container, new ProductListFragment()).commit();
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
