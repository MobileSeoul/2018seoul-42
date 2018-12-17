package com.selecto.banana2;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.chrisbanes.photoview.PhotoView;

import org.w3c.dom.Text;



public class GroupTipFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private Integer mParam2;

    ImageView imageView;
    TextView textView;
    View view;


    public GroupTipFragment() {
    }

    public static GroupTipFragment newInstance(String param1, Integer param2) {
        GroupTipFragment groupTipFragment = new GroupTipFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putInt(ARG_PARAM2, param2);
        groupTipFragment.setArguments(args);
        return groupTipFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getInt(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_group_tip, container, false);

        PhotoView photoView = (PhotoView) view.findViewById(R.id.imageView33);
        photoView.setImageResource(mParam2);

        //imageView = (ImageView) view.findViewById(R.id.imageView33);
        textView = (TextView) view.findViewById(R.id.textView3);
        Log.d("Test","Param1 : "+mParam1);
        //imageView.setImageResource(R.drawable.papertip);
        Log.d("setImage", "success----------------------------------------------------------------------------------------");
        textView.setText(mParam1);
        //imageView.setImageResource(mParam2);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void setTextView() {

    }



}
