package com.example.agi51.yummyfood.Activities.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.agi51.yummyfood.Activities.Beans.DataBean;
import com.example.agi51.yummyfood.R;

import java.util.List;

/**
 * Created by agi51 on 29/2/16.
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
        View view = LayoutInflater.from(context).inflate(R.layout.data_item_recyclr,parent,false);
        MyViewHolder vh = new MyViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        DataBean bean = beanList.get(position);
        holder.titleShop.setText(bean.getRest_name());
        holder.likenums.setText(bean.getRest_rating());
       Glide.with(holder.imLogo.getContext()).load(bean.getLogo()).into(holder.imLogo);

       /* holder.idi.setText("Date : "+bean.getDiscount());
        holder.is.setText("Time: " + bean.getForm_date());
        holder.ie.setText("UserId: " + bean.getTo_date());
        holder.iu.setText("UserType : " + bean.getUsertype());
        holder.ir.setText("Activity: " + bean.getActivity());
        //holder.ird.setText("Ip : " + bean.getIp());*/
        //Glide.with(holder.im.getContext()).load(bean.getUrl()).into(holder.im);


    }

    @Override
    public int getItemCount() {
        return beanList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView titleShop,likenums;
        public ImageView imLogo;

        public MyViewHolder(View itemView) {
            super(itemView);
            titleShop = (TextView) itemView.findViewById(R.id.titleShop);
            likenums = (TextView)itemView.findViewById(R.id.likenumbs);
            imLogo = (ImageView)itemView.findViewById(R.id.imLogo);
           /* idi= (TextView) itemView.findViewById(R.id.idi);
            is = (TextView) itemView.findViewById(R.id.isd);
            ie = (TextView) itemView.findViewById(R.id.ie);
            iu = (TextView) itemView.findViewById(R.id.iu);
            ir = (TextView) itemView.findViewById(R.id.ir);*/
            //  ird = (TextView) itemView.findViewById(R.id.ird);

        }
    }
}
