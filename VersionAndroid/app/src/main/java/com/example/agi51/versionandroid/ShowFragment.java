package com.example.agi51.versionandroid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by agi51 on 4/2/16.
 */
public class ShowFragment extends Fragment {

    TextView tv1, tv2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup v = (ViewGroup)inflater.inflate(R.layout.show_fragment,container,false);
        tv1 = (TextView)v.findViewById(R.id.tv1);
        tv2 = (TextView)v.findViewById(R.id.tv2);
        return v;
    }
    public void nowChange(String txt1, String txt2){
        tv1.setText(txt1);
        tv2.setText(txt2);
    }
}
