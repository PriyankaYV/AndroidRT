package com.example.agi51.yummyfood.Activities.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.agi51.yummyfood.Activities.Beans.RestuarantBean;
import com.example.agi51.yummyfood.R;

import java.util.List;

/**
 * Created by agi51 on 11/3/16.
 */
public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder>{

    private Context context;
    private List<RestuarantBean> beanListMenu;

    public MenuAdapter(List<RestuarantBean> beanListMenu){
        this.beanListMenu = beanListMenu;
    }

    @Override
    public MenuAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.menu_view_item,parent,false);
        ViewHolder va = new ViewHolder(view);
        return va;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        RestuarantBean bean = beanListMenu.get(position);

        holder.tv1.setText(bean.getMenus());
    }

    @Override
    public int getItemCount() {
        return beanListMenu.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tv1;

        public ViewHolder(View itemView) {
            super(itemView);
            tv1=(TextView)itemView.findViewById(R.id.menu_view);

        }
    }
}
