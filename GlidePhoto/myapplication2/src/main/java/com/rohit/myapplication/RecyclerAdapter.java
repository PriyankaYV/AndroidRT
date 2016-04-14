package com.rohit.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.net.URL;
import java.util.List;

/**
 * Created by rohit on 27/1/16.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private Context context;
    private List<DataBean> beanList;

    public RecyclerAdapter(List<DataBean> beanList){
        this.beanList = beanList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.data_item,parent,false);
        MyViewHolder vh = new MyViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        DataBean bean = beanList.get(position);

        holder.idi.setText("Date : "+bean.getDiscount());
        holder.is.setText("Time: " + bean.getForm_date());
        holder.ie.setText("UserId: " + bean.getTo_date());
        holder.iu.setText("UserType : " + bean.getUsertype());
        holder.ir.setText("Activity: " + bean.getActivity());
        //holder.ird.setText("Ip : " + bean.getIp());
        //Glide.with(holder.im.getContext()).load(bean.getUrl()).into(holder.im);


    }

    @Override
    public int getItemCount() {
        return beanList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView idi,is,ie,iu,ir,ird;

        public MyViewHolder(View itemView) {
            super(itemView);
            idi= (TextView) itemView.findViewById(R.id.idi);
            is = (TextView) itemView.findViewById(R.id.isd);
            ie = (TextView) itemView.findViewById(R.id.ie);
            iu = (TextView) itemView.findViewById(R.id.iu);
            ir = (TextView) itemView.findViewById(R.id.ir);
          //  ird = (TextView) itemView.findViewById(R.id.ird);

        }
    }
}
