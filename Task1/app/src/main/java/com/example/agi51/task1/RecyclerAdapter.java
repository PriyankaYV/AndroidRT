package com.example.agi51.task1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by agi51 on 28/1/16.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>{

    private List<DataBean> beanlist;
    private Context context;

    public RecyclerAdapter(List<DataBean> beanlist) {
        this.beanlist = beanlist;
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
        DataBean bean = beanlist.get(position);
        holder.tv1.setText("ID"+bean.getId());
        holder.tv2.setText("Title :"+bean.getName());
        holder.tv3.setText("Body :"+bean.getTitle());

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv1, tv2, tv3;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv1=(TextView)itemView.findViewById(R.id.idText);
            tv2=(TextView)itemView.findViewById(R.id.idTitle);
            tv3=(TextView)itemView.findViewById(R.id.body);
        }
    }
}
