package com.example.agi51.yummyfood.Activities.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.agi51.yummyfood.Activities.Beans.AreaBean;
import com.example.agi51.yummyfood.R;

import java.util.List;

/**
 * Created by agi51 on 9/3/16.
 */
public class AreaAdapter extends RecyclerView.Adapter<AreaAdapter.ViewHolder>{

    private Context context;
    private List<AreaBean> beanListArea;

    public AreaAdapter(List<AreaBean> beanListArea){
        this.beanListArea = beanListArea;
    }

    @Override
    public AreaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.area_view_item,parent,false);
        ViewHolder va = new ViewHolder(view);
        return va;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        AreaBean bean = beanListArea.get(position);

        holder.tv1.setText(bean.getSubLocality());
    }

    @Override
    public int getItemCount() {
        return beanListArea.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tv1;

        public ViewHolder(View itemView) {
            super(itemView);
            tv1=(TextView)itemView.findViewById(R.id.area_view);

        }
    }
}
