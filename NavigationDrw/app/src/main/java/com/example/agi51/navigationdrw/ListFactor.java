package com.example.agi51.navigationdrw;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by agi51 on 4/2/16.
 */
public class ListFactor extends ListFragment {

    String [] Versions = {"cupcake","donut","eclair","froyo","gingerbread","honeycomb","icecream sandwitch","jelly bean","kitkat"};
    String [] Numbers = {"1.5","1.6","2.0-2.1","2.2","2.3","3.0-3.2","4.0","4.1-4.2","4.3"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup v = (ViewGroup)inflater.inflate(R.layout.list_fragment,container,false);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_expandable_list_item_1,Versions);
        setListAdapter(adapter);
        return v;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        ShowFragment showFragment = (ShowFragment)getActivity().getSupportFragmentManager().findFragmentById(R.id.fragshowid);
        showFragment.nowChange(Versions[position],Numbers[position]);
        getListView().setSelector(android.R.color.holo_green_dark);

    }
}
