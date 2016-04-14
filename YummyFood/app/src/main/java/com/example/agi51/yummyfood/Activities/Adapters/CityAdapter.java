package com.example.agi51.yummyfood.Activities.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.agi51.yummyfood.Activities.Beans.LocationBean;
import com.example.agi51.yummyfood.R;

import java.util.List;

/**
 * Created by agi51 on 3/3/16.
 */
public class CityAdapter extends RecyclerView.Adapter<CityAdapter.MyViewHolder>{

    private Context context;
    private List<LocationBean> beanList;
   /* int selectedPosition;
    private static CheckBox lastChecked = null;
    private static int lastCheckedPos = 0;*/


    public CityAdapter(List<LocationBean> beanList){
        this.beanList = beanList;
    }

    @Override
    public CityAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.city_view_item,parent,false);
        MyViewHolder vh = new MyViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final CityAdapter.MyViewHolder holder, final int position) {

        LocationBean bean = beanList.get(position);

        holder.tv1.setText(bean.getLocationCity());
        /*holder.checkBox.setChecked(beanList.get(position).isChecked());
        holder.checkBox.setTag(new Integer(position));
        if(position == 0 && beanList.get(0).isChecked() && holder.checkBox.isChecked())
        {
            lastChecked = holder.checkBox;
            lastCheckedPos = 0;
        }

        holder.checkBox.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                CheckBox cb = (CheckBox)v;
                int clickedPos = ((Integer)cb.getTag()).intValue();

                if(cb.isChecked())
                {
                    if(lastChecked != null)
                    {
                        lastChecked.setChecked(false);
                        beanList.get(lastCheckedPos).setIsChecked(false);
                    }

                    lastChecked = cb;
                    lastCheckedPos = clickedPos;
                }
                else
                    lastChecked = null;

                beanList.get(clickedPos).setIsChecked(cb.isChecked());
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return beanList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView tv1;
       // CheckBox checkBox;


        public MyViewHolder(View itemView) {
            super(itemView);
            tv1=(TextView)itemView.findViewById(R.id.city_view);
            //checkBox = (CheckBox) itemView.findViewById(R.id.checkbox);

        }
    }
}