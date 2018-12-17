package com.selecto.banana2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.github.chrisbanes.photoview.PhotoView;

public class PageFourFragment extends Fragment {
    View view;
    ImageView imageView;

    public PageFourFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view != null) {
            return view;
        } else {
            view = inflater.inflate(R.layout.fragment_page_four, null);
        }

        PhotoView photoView = (PhotoView) view.findViewById(R.id.imageView44);
        photoView.setImageResource(R.drawable.tentip);
        photoView.setScaleType(ImageView.ScaleType.FIT_START);

        //imageView = (ImageView) view.findViewById(R.id.ImageView44);
        //imageView.setImageResource(R.drawable.tentip);
        return view;
    }

    public static PageFourFragment newInstance() {
        Bundle args = new Bundle();

        PageFourFragment fragment = new PageFourFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
