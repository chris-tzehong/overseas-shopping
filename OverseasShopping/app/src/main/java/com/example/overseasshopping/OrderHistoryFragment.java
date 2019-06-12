package com.example.overseasshopping;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.overseasshopping.Model.Order;
import com.example.overseasshopping.Model.Product;

import java.util.List;

public class OrderHistoryFragment extends Fragment {
    private RecyclerView mOrderHistoryRecyclerView;
    private OrderHisAdapter mAdapter;

    private class OrderHistoryHolder extends RecyclerView.ViewHolder {
        private Order mOrder;
        private TextView mOrderTitleTextView;
        private TextView mOrderPrice;

        public OrderHistoryHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_orderhistory,parent, false));
            mOrderTitleTextView = (TextView) itemView.findViewById(R.id.product_title);
            mOrderPrice = (TextView) itemView.findViewById(R.id.product_price);
        }

        public void bind(Order order){
            mOrder = order;


            //mPrice.setText(mProduct.getPrice());



        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_product_list,container,false);

        mOrderHistoryRecyclerView = (RecyclerView) view.findViewById(R.id.product_recycler_view);
        mOrderHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }
    private void updateUI(){
        OrderHistoryLab orderhistoryLab = OrderHistoryLab.get(getActivity());
        List<Order> orders = orderhistoryLab.getOrders();
        if (mAdapter ==null){
            mAdapter = new OrderHistoryFragment.OrderHisAdapter(orders);
            mOrderHistoryRecyclerView.setAdapter(mAdapter);
        }else{
            mAdapter.setProducts(orders);
            mAdapter.notifyDataSetChanged();
        }

    }
    //Adapter
    private class OrderHisAdapter extends RecyclerView.Adapter<OrderHistoryFragment.OrderHistoryHolder>{
        private List<Order> mOrders;

        public OrderHisAdapter(List<Order> orders){
            mOrders= orders;
        }
        @Override
        public OrderHistoryFragment.OrderHistoryHolder onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new OrderHistoryFragment.OrderHistoryHolder(layoutInflater, parent);
        }
        @Override
        public void onBindViewHolder(OrderHistoryFragment.OrderHistoryHolder holder, int position){
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
