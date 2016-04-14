package com.example.agi51.navigationdrw;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by agi51 on 6/2/16.
 */
public class RecylAdapter extends RecyclerView.Adapter<RecylAdapter.MyViewHolder> {

    List<Beam> beamList;


    public RecylAdapter(List<Beam> beamList) {
        this.beamList = beamList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_contents,parent,false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Beam beam = beamList.get(position);
        holder.tv1.setText("Title="+beam.getTitle());
        holder.tv2.setText("Title="+beam.getThumbnail());

    }

    @Override
    public int getItemCount() {
        return beamList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv1,tv2;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv1 = (TextView)itemView.findViewById(R.id.tvfortitle);
            tv2 = (TextView)itemView.findViewById(R.id.tvfortumbanail);

        }
    }
}
