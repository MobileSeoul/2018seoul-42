package com.selecto.banana2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ShoplistAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private ArrayList<Shoplistitem> data;
    private int layout;

    public ShoplistAdapter(Context context, int layout, ArrayList<Shoplistitem> data)
    {
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.data = data;
        this.layout = layout;
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position).getTEL();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
        {
            convertView = inflater.inflate(layout, parent, false);
        }

        final Shoplistitem listviewitem = data.get(position);

        TextView name = (TextView)convertView.findViewById(R.id.nm);
        name.setText(listviewitem.getNM());

        TextView address_old = (TextView)convertView.findViewById(R.id.addr_old);
        if(listviewitem.getADDR_OLD() == "null")
            address_old.setText("소재지주소 : (정보없음)");
        else
            address_old.setText("소재지주소 : " + listviewitem.getADDR_OLD());

        TextView address = (TextView)convertView.findViewById(R.id.addr);
        if(listviewitem.getADDR() == "null")
            address.setText("도로명주소 : (정보없음)");
        else
            address.setText("도로명주소 : " + listviewitem.getADDR());

        TextView tel = (TextView)convertView.findViewById(R.id.tel);
        if(listviewitem.getTEL() == "null")
            tel.setText("TEL : (정보없음)");
        else
            tel.setText("TEL : " + listviewitem.getTEL());

        /*
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
*/

        return convertView;
    }
}
