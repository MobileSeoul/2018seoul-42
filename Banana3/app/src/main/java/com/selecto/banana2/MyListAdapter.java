package com.selecto.banana2;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;
import java.text.NumberFormat;

public class MyListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private ArrayList<Continent> continentList;
    private ArrayList<Continent> originalList;

    public MyListAdapter(Context context, ArrayList<Continent> continentList) {
        this.context = context;
        this.continentList = new ArrayList<Continent>();
        this.continentList.addAll(continentList);
        this.originalList = new ArrayList<Continent>();
        this.originalList.addAll(continentList);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<Country> countryList = continentList.get(groupPosition).getCountryList();
        return countryList.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup parent) {

        Country country = (Country) getChild(groupPosition, childPosition);
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.child_row, null);
        }

        TextView code = view.findViewById(R.id.code);
        TextView name = view.findViewById(R.id.name);
        code.setText(country.getCode().trim());
        name.setText(country.getName().trim());

        Log.d("tag", "getChildView()");
        Log.d("tag", "name : " + name);

        return view;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        ArrayList<Country> countryList = continentList.get(groupPosition).getCountryList();
        return countryList.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return continentList.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return continentList.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isLastChild, View view, ViewGroup parent) {

        Continent continent = (Continent) getGroup(groupPosition);

        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.group_row, null);
        }
        TextView heading = view.findViewById(R.id.heading);
        heading.setText(continent.getName());
        ImageView recycleIcon = view.findViewById(R.id.recycleIcon);

        final String groupName = continent.getName();
        /*
        recycleIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GroupTipFragment groupTipFragment;
                FragmentManager fm = ((AppCompatActivity)context).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                groupTipFragment = GroupTipFragment.newInstance("", R.drawable.papertip2);
                if (groupName.equals("음식물류")) {
                    groupTipFragment = GroupTipFragment.newInstance("", R.drawable.foodtip);
                } else if (groupName.equals("종이류")) {
                    groupTipFragment = GroupTipFragment.newInstance("", R.drawable.papertip2);
                } else if (groupName.equals("종이컵 · 종이팩")) {
                    groupTipFragment = GroupTipFragment.newInstance("", R.drawable.papercuptip);
                } else if (groupName.equals("캔류 · 고철류")) {
                    groupTipFragment = GroupTipFragment.newInstance("", R.drawable.cantip);
                } else if (groupName.equals("유리병류")) {
                    groupTipFragment = GroupTipFragment.newInstance("", R.drawable.glasstip);
                } else if (groupName.equals("플라스틱류")) {
                    groupTipFragment = GroupTipFragment.newInstance("", R.drawable.plastictip);
                } else if (groupName.equals("비닐류")) {
                    groupTipFragment = GroupTipFragment.newInstance("", R.drawable.vyniltip);
                } else if (groupName.equals("형광등")) {
                    groupTipFragment = GroupTipFragment.newInstance("", R.drawable.lamptip);
                } else if (groupName.equals("폐전지")) {
                    groupTipFragment = GroupTipFragment.newInstance("", R.drawable.batterytip);
                } else if (groupName.equals("섬유류")) {
                    groupTipFragment = GroupTipFragment.newInstance("", R.drawable.clothtip);
                } else if (groupName.equals("폐전자제품")) {
                    groupTipFragment = GroupTipFragment.newInstance("", R.drawable.plugtip);
                } else if (groupName.equals("폐식용유")) {
                    groupTipFragment = GroupTipFragment.newInstance("", R.drawable.oiltip);
                } else if (groupName.equals("기타")) {
                    groupTipFragment = GroupTipFragment.newInstance("", R.drawable.otherstip);
                }
                fragmentTransaction.replace(R.id.fragment_group_tip, groupTipFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        */

        if (continent.getName().equals("음식물류")) {
            view.setBackgroundColor(0xffffdf5e);
            heading.setTextColor(0xffffffff);
            recycleIcon.setImageResource(R.drawable.foodicon);
        } else if (continent.getName().equals("종이류")) {
            view.setBackgroundColor(0xff663300);
            heading.setTextColor(0xffffffff);
            recycleIcon.setImageResource(R.drawable.paper);
        } else if (continent.getName().equals("종이컵 · 종이팩")) {
            view.setBackgroundColor(0xff009936);
            heading.setTextColor(0xffffffff);
            recycleIcon.setImageResource(R.drawable.milk);
        } else if (continent.getName().equals("캔류 · 고철류")) {
            view.setBackgroundColor(0xff9e9e9e);
            heading.setTextColor(0xffffffff);
            recycleIcon.setImageResource(R.drawable.can);
        } else if (continent.getName().equals("유리병류")) {
            view.setBackgroundColor(0xffed7e30);
            heading.setTextColor(0xffffffff);
            recycleIcon.setImageResource(R.drawable.glass);
        } else if (continent.getName().equals("플라스틱류")) {
            view.setBackgroundColor(0xff005bab);
            heading.setTextColor(0xffffffff);
            recycleIcon.setImageResource(R.drawable.plastic);
        } else if (continent.getName().equals("비닐류")) {
            view.setBackgroundColor(0xff703e94);
            heading.setTextColor(0xffffffff);
            recycleIcon.setImageResource(R.drawable.vinyl);
        } else if (continent.getName().equals("형광등")) {
            view.setBackgroundColor(0xff84b825);
            heading.setTextColor(0xffffffff);
            recycleIcon.setImageResource(R.drawable.bulbicon);
        } else if (continent.getName().equals("폐전지")) {
            view.setBackgroundColor(0xffe83820);
            heading.setTextColor(0xffffffff);
            recycleIcon.setImageResource(R.drawable.battery);
        } else if (continent.getName().equals("섬유류")) {
            view.setBackgroundColor(0xffeb6ca3);
            heading.setTextColor(0xffffffff);
            recycleIcon.setImageResource(R.drawable.clothe);
        } else if (continent.getName().equals("폐전자제품")) {
            view.setBackgroundColor(0xffed858a);
            heading.setTextColor(0xffffffff);
            recycleIcon.setImageResource(R.drawable.plug);
        } else if (continent.getName().equals("폐식용유")) {
            view.setBackgroundColor(0xff5e67ab);
            heading.setTextColor(0xffffffff);
            recycleIcon.setImageResource(R.drawable.oil);
        } else if (continent.getName().equals("기타")) {
            view.setBackgroundColor(0xff5c7691);
            heading.setTextColor(0xffffffff);
            recycleIcon.setImageResource(R.drawable.other);
        }
        return view;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void filterData(String query){

        query = query.toLowerCase();
        Log.d("tag", String.valueOf(continentList.size()));
        continentList.clear();

        if(query.isEmpty()){
            continentList.addAll(originalList);
        }
        else {
            for(Continent continent: originalList){
                ArrayList<Country> countryList = continent.getCountryList();
                ArrayList<Country> newList = new ArrayList<Country>();
                for(Country country: countryList){
                    if(country.getCode().toLowerCase().contains(query) || country.getName().toLowerCase().contains(query)){
                        newList.add(country);
                    }
                }
                if(newList.size() > 0){
                    Continent nContinent = new Continent(continent.getName(), continent.getTip(), newList);
                    continentList.add(nContinent);
                }
            }
        }

        Log.d("tag", String.valueOf(continentList.size()));
        notifyDataSetChanged();

    }
}
