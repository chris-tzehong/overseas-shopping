package com.example.overseasshopping;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.overseasshopping.Model.Order;


import java.text.DecimalFormat;
import java.util.List;

public class OrderHistoryFragment extends Fragment {
   // private static final String USERNAME = "com.example.overseasshopping.orderhistoryfragment.username";
    private RecyclerView mOrderHistoryRecyclerView;
    private OrderHisAdapter mAdapter;
    private String mUserName;
    private DatabaseHelper mDatabaseHelper;
    private List<Order> mOrderList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_order_list,container,false);

        mOrderHistoryRecyclerView = (RecyclerView) view.findViewById(R.id.order_recycler_view);
        mOrderHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mUserName =  (String) getActivity().getIntent().getStringExtra(MainActivity.EXTRA_USERNAME);
        //  Intent intent = OrderHistoryActivity.newIntent(getActivity().getBaseContext(),mUserName);
        //  startActivity(intent);
        updateUI();
        return view;
    }
//
//    public static OrderHistoryFragment newInstance(String userName) {
//        Bundle bundle = new Bundle();
//        bundle.putString(USERNAME, userName);
//        OrderHistoryFragment fragment = new OrderHistoryFragment();
//        fragment.setArguments(bundle);
//        return fragment;
//    }

    private class OrderHistoryHolder extends RecyclerView.ViewHolder {
        private Order mOrder;

        private TextView mOrderID;
        private TextView mOrderTitleTextView;
        private TextView mOrderPrice;
        private TextView mQuantity;
        private TextView mBuyer_Seller;
        private TextView mDate;

        public OrderHistoryHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_orderhistory, parent, false));
            mOrderID = (TextView) itemView.findViewById(R.id.order_ID);
            mOrderTitleTextView = (TextView) itemView.findViewById(R.id.order_title);
            mOrderPrice = (TextView) itemView.findViewById(R.id.order_total_price);
            mQuantity = (TextView) itemView.findViewById(R.id.order_total_quantity);
            mBuyer_Seller = (TextView) itemView.findViewById(R.id.order_buyer_seller);
            mDate = (TextView) itemView.findViewById(R.id.order_date);
        }

        public void bind(Order order){
            mOrder = order;
            final DecimalFormat formater = new DecimalFormat("0.00");

            if(mUserName.equals(mOrder.getBuyer())) {



                mOrderID.setText(String.valueOf("Order ID: " + mOrder.getOrderNo()));
                mOrderTitleTextView.setText("Product Name: " + mOrder.getProductName());
                mOrderPrice.setText(String.valueOf("Total Price: " + " RM "+ formater.format(mOrder.getTotalPrice())));
                mQuantity.setText(String.valueOf("Total Quantity: " + mOrder.getPurchaseQuantity()));
                mBuyer_Seller.setText("Seller: " + mOrder.getSeller());
                mDate.setText("Date: " + mOrder.getTime());

            }
                else if(mUserName.equals(mOrder.getSeller())){
                mOrderID.setText(String.valueOf("Order ID: " + mOrder.getOrderNo()));
                mOrderTitleTextView.setText("Product Name: " + mOrder.getProductName());
                mOrderPrice.setText(String.valueOf("Total Price: " + " RM "+ formater.format(mOrder.getTotalPrice())));
                mQuantity.setText(String.valueOf("Total Quantity: " + mOrder.getPurchaseQuantity()));
                mBuyer_Seller.setText("Buyer: " + mOrder.getBuyer());
                mDate.setText("Date: " + mOrder.getTime());
                }

        }

    }



    private void updateUI(){

        OrderHistoryLab orderLab = OrderHistoryLab.get(getActivity());
        List<Order> orders = orderLab.getOrders(mUserName);

        if (mAdapter ==null){
            mAdapter = new OrderHisAdapter(orders);
            mOrderHistoryRecyclerView.setAdapter(mAdapter);
        }else{
            mAdapter.setProducts(orders);
            mAdapter.notifyDataSetChanged();
        }

    }
    //Adapter
    private class OrderHisAdapter extends RecyclerView.Adapter<OrderHistoryHolder>{
        private List<Order> mOrders;


        public OrderHisAdapter(List<Order> orders){
            mOrders= orders;

        }
        @Override
        public OrderHistoryHolder onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new OrderHistoryHolder(layoutInflater, parent);
        }
        @Override
        public void onBindViewHolder(OrderHistoryHolder holder, int position){
            Order order = mOrders.get(position);

            holder.bind(order);

        }
        @Override
        public int getItemCount(){

            return mOrders.size();

        }

        public void setProducts(List<Order> orders){
            mOrders= orders;

        }


    }
}
