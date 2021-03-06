package com.example.agi51.hmkcloud;

/**
 * Created by agi51 on 5/2/16.
 */
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

        holder.id.setText("ID : "+bean.getId());
        holder.title.setText("Title : "+bean.getTitle());
        Glide.with(holder.im.getContext()).load(bean.getUrl()).into(holder.im);


    }

    @Override
    public int getItemCount() {
        return beanList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView id,title;
        public ImageView im;

        public MyViewHolder(View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.iV);
            title = (TextView) itemView.findViewById(R.id.iT);
            im = (ImageView) itemView.findViewById(R.id.iM);
        }
    }
}
