package com.selecto.banana2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ShopListFragment extends Fragment {

    View view;
    Button backButton;

    public ShopListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view != null) {
            return view;
        } else {
            view = inflater.inflate(R.layout.fragment_shop_list, null);
        }

        ArrayList<Shoplistitem> data = ((MainActivity)getActivity()).getData();
        ListView listView = (ListView)view.findViewById(R.id.listview);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Test", "List Click");
                String tv = (String)parent.getAdapter().getItem(position);
                //Toast.makeText(getContext(), "TEl : " + tv, Toast.LENGTH_LONG).show();
                ((MainActivity)getActivity()).callShop(tv);
            }
        });


        ShoplistAdapter adapter = new ShoplistAdapter(getActivity(), R.layout.shoplistview, data);    //(in shoplistview.xml)
        listView.setAdapter(adapter);

        backButton = (Button)view.findViewById(R.id.button2);
        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().remove(ShopListFragment.this).commit();
                fragmentManager.popBackStack();
            }
        });

        return view;
    }
}
